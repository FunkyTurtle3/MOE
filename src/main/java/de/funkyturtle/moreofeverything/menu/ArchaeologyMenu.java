package de.funkyturtle.moreofeverything.menu;

import com.google.common.collect.Lists;
import de.funkyturtle.moreofeverything.block.blockentity.custom.ArchaeologyTableBlockEntity;
import de.funkyturtle.moreofeverything.component.MOEDataComponentTypes;
import de.funkyturtle.moreofeverything.item.MOEItem;
import de.funkyturtle.moreofeverything.util.MOEMath;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.recipe.ArchaeologyRecipe;
import de.funkyturtle.moreofeverything.recipe.MOERecipeType;
import de.funkyturtle.moreofeverything.util.MOETag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArchaeologyMenu extends AbstractContainerMenu {
    final Slot inputSlot;
    final Slot brushSlot;
    final ArchaeologyTableBlockEntity container;
    final ContainerData data;
    private List<RecipeHolder<ArchaeologyRecipe>> Recipes = Lists.newArrayList();
    final Level level;
    public ArchaeologyMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(16));
    }
    public ArchaeologyMenu(int pContainerId, Inventory inventory, BlockEntity container, ContainerData data) {
        super(MOEMenu.ARCHAEOLOGY_MENU.get(), pContainerId);
        this.container = (ArchaeologyTableBlockEntity) container;
        level = inventory.player.level();
        this.data = data;

        for (int i1 = 0; i1 < 9; i1++) {
            this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 162 + 2*18));
        }
        for (int l = 0; l < 3; l++) {
            for (int j1 = 0; j1 < 9; j1++) {
                this.addSlot(new Slot(inventory, j1 + l * 9 + 9, 8 + j1 * 18, 104 + l * 18 + 2*18));
                System.out.println(j1 + l * 9 + 9);
            }
        }
        this.inputSlot = addSlot(new SlotItemHandler(this.container.itemHandler, 36, 26, 45)
        {
            @Override
            public boolean mayPickup(Player playerIn) {
                if(this.hasItem() && this.getItem().get(MOEDataComponentTypes.LOCKED.get()) != null){
                    return !this.getItem().get(MOEDataComponentTypes.LOCKED.get());
                } else return true;
            }

            @Override
            public boolean mayPlace(@NotNull ItemStack pStack) {
                for (int i = 38; i < 54; i++) {
                    if(getSlot(i).hasItem()) return false;
                }
                return pStack.is(MOETag.Items.MYSTERIOUS_MATERIAL);
            }
        });
        this.brushSlot = addSlot(new SlotItemHandler(this.container.itemHandler, 37, 26, 81)
        {
            @Override
            public boolean mayPlace(@NotNull ItemStack pStack) {
                return pStack.getItem() instanceof BrushItem;
            }
        });

        for (int t = 0; t < 4; t++) {
            for (int z = 0; z < 4; z++) {
                this.addSlot(new SlotItemHandler(this.container.itemHandler,38 + 4 * t + z, 62 + t * 18, 36 + z * 18){
                    @Override
                    public boolean mayPlace(@NotNull ItemStack pStack) {
                        return false;
                    }
                });
            }
        }
        this.addSlot(new SlotItemHandler(this.container.itemHandler, 54, 152, 18 ) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.get(MOEDataComponentTypes.MATERIAL_DAMAGE.get()) != null;
            }
        });
        this.addSlot(new SlotItemHandler(this.container.itemHandler, 55, 152, 36 ) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.get(MOEDataComponentTypes.BRUSH_DAMAGE.get()) != null;
            }
        });
        this.addSlot(new SlotItemHandler(this.container.itemHandler, 56, 152, 54 ) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(MOEItem.ORB_OF_LUCK.get());
            }
        });
        this.addSlot(new SlotItemHandler(this.container.itemHandler, 57, 152, 72 ) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(MOEItem.ORB_OF_COMPLETION.get());
            }
        });
        for (int i = 0; i < 2; i++) {
            this.addSlot(new SlotItemHandler(this.container.itemHandler, 58 + i, 152, 90 + i * 18));
        }
        addDataSlots(data);
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 24;  // must be the number of slots you have!
    @Override
    public @NotNull ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, container.getBlockPos()),
                pPlayer, MOEBlock.ARCHAEOLOGY_TABLE.get());
    }
    public boolean isSelected(int a) {
        return !inputSlot.hasItem() || this.container.data.get(a) == 1;
    }
    public void select(int a) {
        if(container.itemHandler.getStackInSlot(57).isEmpty() && 0.2 > Math.random()) {
            container.itemHandler.setStackInSlot(a, ItemStack.EMPTY);
        } else {
            setupResultSlot(a);
        }
            inputSlot.getItem().set(MOEDataComponentTypes.LOCKED.get(), Boolean.TRUE);
            inputSlot.getItem().setDamageValue(inputSlot.getItem().getDamageValue() + getMaterialDamage());
            if (!container.itemHandler.getStackInSlot(54).isEmpty()) {
                container.itemHandler.getStackInSlot(54).setDamageValue(container.itemHandler.getStackInSlot(54).getDamageValue() + 1);
                if (container.itemHandler.getStackInSlot(54).getDamageValue() == container.itemHandler.getStackInSlot(54).getMaxDamage()) {
                    container.itemHandler.setStackInSlot(54, ItemStack.EMPTY);
                }
            }
            container.data.set(a, 1);
            brushSlot.getItem().setDamageValue(brushSlot.getItem().getDamageValue() + getBrushDamage());
            if (inputSlot.getItem().getDamageValue() == inputSlot.getItem().getMaxDamage()) {
                inputSlot.set(ItemStack.EMPTY);
                for (int i = 0; i < 16; i++) {
                    data.set(i, 0);
                }
            }
    }

    public int getMaterialDamage() {
        if(container.itemHandler.getStackInSlot(54).get(MOEDataComponentTypes.MATERIAL_DAMAGE.get()) != null) {
            return container.itemHandler.getStackInSlot(54).get(MOEDataComponentTypes.MATERIAL_DAMAGE.get());
        } else return 8;
    }
    public int getBrushDamage() {
        if(container.itemHandler.getStackInSlot(55).get(MOEDataComponentTypes.BRUSH_DAMAGE.get()) != null) {
            return container.itemHandler.getStackInSlot(55).get(MOEDataComponentTypes.BRUSH_DAMAGE.get());
        } else return 10;
    }

    public void setupResultSlot(int x) {
        setupRecipeList(inputSlot.getItem());
        if (!Recipes.isEmpty() && container.itemHandler.getStackInSlot(56).isEmpty()) {
            this.container.itemHandler.setStackInSlot(x + 38, getWeightedRecipe().assemble(createRecipeInput(inputSlot.getItem()), this.level.registryAccess()));
        } else if(!Recipes.isEmpty()) this.container.itemHandler.setStackInSlot(x + 38, getRandomRecipe().assemble(createRecipeInput(inputSlot.getItem()), this.level.registryAccess()));
    }

    public ArchaeologyRecipe getRandomRecipe() {
        if (Recipes.isEmpty()) {
            return null;
        }
        return Recipes.get(MOEMath.getRandomRangedInt(0, Recipes.size())).value();
    }

    public ArchaeologyRecipe getWeightedRecipe() {
        if (Recipes.isEmpty()) {
            return null;
        }
        double max = 0;
        for (RecipeHolder<ArchaeologyRecipe> recipe : Recipes) {
            max = max + recipe.value().getChance();
        }
        double b = MOEMath.getRandomRangedDouble(0, max);
        int i = 0;
        while (Recipes.get(i).value().getChance() < b) {
            b = b - Recipes.get(i).value().getChance();
            i++;
        }
        return Recipes.get(i).value();
    }
    private static SingleRecipeInput createRecipeInput(ItemStack stack) {
        return new SingleRecipeInput(stack);
    }
    private void setupRecipeList(ItemStack stack) {
        Recipes.clear();
        if (!stack.isEmpty()) {
            Recipes = this.level.getRecipeManager().getRecipesFor(MOERecipeType.ARCHAEOLOGY_RECIPE.get(), createRecipeInput(stack), this.level);
        }
    }
    @Override
    public void clicked(int pSlotId, int pButton, @NotNull ClickType pClickType, @NotNull Player pPlayer) {
        super.clicked(pSlotId, pButton, pClickType, pPlayer);
        if (pSlotId >= 38 && pSlotId < 54 && !isSelected(pSlotId - 38) && inputSlot.hasItem() && brushSlot.hasItem() && brushSlot.getItem().getDamageValue() < brushSlot.getItem().getMaxDamage() - getBrushDamage()) {
            select(pSlotId - 38);
        }
    }
}