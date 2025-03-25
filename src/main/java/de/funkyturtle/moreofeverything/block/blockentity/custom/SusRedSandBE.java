package de.funkyturtle.moreofeverything.block.blockentity.custom;

import de.funkyturtle.moreofeverything.block.blockentity.MOEBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SusRedSandBE extends BrushableBlockEntity {
    public SusRedSandBE(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    public @NotNull BlockEntityType<?> getType() {
        return MOEBlockEntities.SUS_RED_SAND_BE_TYPE.get();
    }
}
