package de.funkyturtle.moreofeverything.block.blockentity.custom;

import de.funkyturtle.moreofeverything.block.blockentity.MOEBlockEntities;
import de.funkyturtle.moreofeverything.menu.ArchaeologyMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArchaeologyTableBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(60) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            assert level != null;
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public final ContainerData data;
    private final int[][] revealed = new int[4][4];
    private int checksLeft = 16;
    private int locked = 0;
    public ArchaeologyTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MOEBlockEntities.ARCHEOLOGY_TABLE_BE_TYPE.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                if(i < 16) return revealed[i / 4][i % 4];
                else return 0;
            }

            @Override
            public void set(int i, int value) {
                //if(i == 16 && 0 <= value && value <= 16) checksLeft = value;
                //if(i == 17 && (0 == value || value == 1)) locked = value;
                if(i < 16 && (value == 0 || value == 1)) {
                    revealed[i / 4][i % 4] = value;
                }
            }

            @Override
            public int getCount() {
                return 16;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("container.archaeology_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new ArchaeologyMenu(pContainerId, pPlayerInventory, this, data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        for (int i = 0; i < 16; i++) {
            pTag.putInt("archaeology_table.revealed" + i, revealed[i / 4][i % 4]);
        }
        pTag.putInt("archaeology_table.checksLeft", checksLeft);
        pTag.putInt("archaeology_table.locked", locked);
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        for (int i = 0; i < 16; i++) {
            revealed[i / 4][i % 4] = pTag.getInt("archaeology_table.revealed" + i);
        }
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("archaeology_table.checksLeft"));
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("archaeology_table.locked"));
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
