package de.funkyturtle.moreofeverything.block.custom;

import com.mojang.serialization.MapCodec;
import de.funkyturtle.moreofeverything.block.blockentity.custom.ArchaeologyTableBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArchaeologyTable extends BaseEntityBlock {
    public static final MapCodec<ArchaeologyTable> CODEC = simpleCodec(ArchaeologyTable::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public ArchaeologyTable(BlockBehaviour.Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState p_57083_, Level p_57084_, @NotNull BlockPos p_57085_, @NotNull Player p_57086_, @NotNull BlockHitResult p_57088_) {
        if (p_57084_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            p_57086_.openMenu(p_57083_.getMenuProvider(p_57084_, p_57085_));
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof ArchaeologyTableBlockEntity archaeologyTableBlockEntity) {
                archaeologyTableBlockEntity.drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack pStack, @NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos,
                                                       @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHitResult) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof ArchaeologyTableBlockEntity archaeologyTableBlockEntity) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(archaeologyTableBlockEntity, Component.translatable("container.archaeology_table")), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55828_) {
        p_55828_.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_52501_) {
        return this.defaultBlockState().setValue(FACING, p_52501_.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new ArchaeologyTableBlockEntity(pPos, pState);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }
}
