package de.funkyturtle.moreofeverything.block.custom.ceramicVessel;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CeramicVessel extends Block {
    public CeramicVessel(Properties p_49795_) {
        super(p_49795_);
    }
    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState p_57100_, @NotNull BlockGetter p_57101_, @NotNull BlockPos p_57102_, @NotNull CollisionContext p_57103_) {
        return Block.box(4, 0,4, 12, 4, 12);
    }
}