package de.funkyturtle.moreofeverything.menu;

import com.google.common.collect.Lists;
import java.util.List;

import de.funkyturtle.moreofeverything.recipe.MOERecipeType;
import de.funkyturtle.moreofeverything.recipe.WoodSawRecipe;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WoodSawMenu extends AbstractContainerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_START = 29;
    private static final int USE_ROW_SLOT_END = 38;
    private final ContainerLevelAccess access;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Level level;
    private List<RecipeHolder<WoodSawRecipe>> Recipes = Lists.newArrayList();
    private ItemStack input = ItemStack.EMPTY;
    long lastSoundTime;
    final Slot inputSlot;
    final Slot resultSlot;
    Runnable slotUpdateListener = () -> {
    };
    public final Container container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            WoodSawMenu.this.slotsChanged(this);
            WoodSawMenu.this.slotUpdateListener.run();
        }
    };
    final ResultContainer resultContainer = new ResultContainer();

    public WoodSawMenu(int p_40297_, Inventory p_40298_, final ContainerLevelAccess p_40299_) {
        super(MOEMenu.WOODEN_SAW_MENU.get(), p_40297_);
        this.access = p_40299_;
        this.level = p_40298_.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack p_40362_) {
                return false;
            }

            @Override
            public void onTake(Player p_150672_, ItemStack p_150673_) {
                p_150673_.onCraftedBy(p_150672_.level(), p_150672_, p_150673_.getCount());
                WoodSawMenu.this.resultContainer.awardUsedRecipes(p_150672_, this.getRelevantItems());
                ItemStack itemstack = WoodSawMenu.this.inputSlot.remove(1);
                if (!itemstack.isEmpty()) {
                    WoodSawMenu.this.setupResultSlot();
                }

                p_40299_.execute((p_40364_, p_40365_) -> {
                    long l = p_40364_.getGameTime();
                    if (WoodSawMenu.this.lastSoundTime != l) {
                        p_40364_.playSound(null, p_40365_, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                        WoodSawMenu.this.lastSoundTime = l;
                    }
                });
                System.out.println(p_150673_);
                super.onTake(p_150672_, p_150673_);
            }

            private List<ItemStack> getRelevantItems() {
                return List.of(WoodSawMenu.this.inputSlot.getItem());
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(p_40298_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(p_40298_, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    public List<RecipeHolder<WoodSawRecipe>> getRecipes() {
        return this.Recipes;
    }

    public int getNumRecipes() {
        return this.Recipes.size();
    }

    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && !this.Recipes.isEmpty();
    }

    @Override
    public boolean stillValid(@NotNull Player p_40307_) {
        return stillValid(this.access, p_40307_, MOEBlock.WOOD_SAW.get());
    }

    @Override
    public boolean clickMenuButton(Player p_40309_, int p_40310_) {
        if (this.isValidRecipeIndex(p_40310_)) {
            this.selectedRecipeIndex.set(p_40310_);
            this.setupResultSlot();
        }

        return true;
    }

    private boolean isValidRecipeIndex(int p_40335_) {
        return p_40335_ >= 0 && p_40335_ < this.Recipes.size();
    }

    @Override
    public void slotsChanged(Container p_40302_) {
        ItemStack itemstack = this.inputSlot.getItem();
        if (!itemstack.is(this.input.getItem())) {
            this.input = itemstack.copy();
            this.setupRecipeList(p_40302_, itemstack);
        }
    }

    private static SingleRecipeInput createRecipeInput(Container p_342113_) {
        return new SingleRecipeInput(p_342113_.getItem(0));
    }

    private void setupRecipeList(Container p_40304_, ItemStack p_40305_) {
        this.Recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!p_40305_.isEmpty()) {
            this.Recipes = this.level.getRecipeManager().getRecipesFor(MOERecipeType.WOODEN_SAW_RECIPE.get(), createRecipeInput(p_40304_), this.level);
        }
    }

    void setupResultSlot() {
        if (this.Recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
            RecipeHolder<WoodSawRecipe> Recipeholder = this.Recipes.get(this.selectedRecipeIndex.get());
            ItemStack itemstack = Recipeholder.value().assemble(createRecipeInput(this.container), this.level.registryAccess());
            if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
                this.resultContainer.setRecipeUsed(Recipeholder);
                this.resultSlot.set(itemstack);
            } else {
                this.resultSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }

        this.broadcastChanges();
    }

    @Override
    public MenuType<?> getType() {
        return MOEMenu.WOODEN_SAW_MENU.get();
    }

    public void registerUpdateListener(Runnable p_40324_) {
        this.slotUpdateListener = p_40324_;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack p_40321_, Slot p_40322_) {
        return p_40322_.container != this.resultContainer && super.canTakeItemForPickAll(p_40321_, p_40322_);
    }

    @Override
    public ItemStack quickMoveStack(Player p_40328_, int p_40329_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_40329_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (p_40329_ == 1) {
                item.onCraftedBy(itemstack1, p_40328_.level(), p_40328_);
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_40329_ == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.level.getRecipeManager().getRecipeFor(MOERecipeType.WOODEN_SAW_RECIPE.get(), new SingleRecipeInput(itemstack1), this.level).isPresent()) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (p_40329_ >= 2 && p_40329_ < 29) {
                if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (p_40329_ >= 29 && p_40329_ < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_40328_, itemstack1);
            this.broadcastChanges();
        }

        return itemstack;
    }

    @Override
    public void removed(Player p_40326_) {
        super.removed(p_40326_);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((p_40313_, p_40314_) -> this.clearContainer(p_40326_, this.container));
    }
}