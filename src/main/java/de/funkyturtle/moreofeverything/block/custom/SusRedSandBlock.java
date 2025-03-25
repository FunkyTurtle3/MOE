package de.funkyturtle.moreofeverything.block.custom;

import de.funkyturtle.moreofeverything.block.blockentity.custom.SusRedSandBE;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SusRedSandBlock extends BrushableBlock {
    public SusRedSandBlock(Block p_277629_, SoundEvent p_278060_, SoundEvent p_277352_, Properties p_277373_) {
        super(p_277629_, p_278060_, p_277352_, p_277373_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SusRedSandBE(pPos, pState);
    }
}
