package de.funkyturtle.moreofeverything.block.custom.copperBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.ticks.TickPriority;

public class CopperButton extends ButtonBlock {
    public CopperButton(BlockSetType p_273462_, int p_273212_, Properties p_273290_) {
        super(p_273462_, p_273212_, p_273290_);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.FALSE));
    }

    @Override
    protected void attack(BlockState p_60499_, Level p_60500_, BlockPos p_60501_, Player p_60502_) {
        BlockState state = p_60500_.getBlockState(p_60501_);
        emmitShortSignal(state, p_60500_, p_60501_);
    }
    private void emmitShortSignal(BlockState state, Level level, BlockPos pos) {
        toggle(level, pos, state);
        level.scheduleTick(pos, this, 20, TickPriority.HIGH);
        toggle(level, pos, state);
    }

    private void toggle(Level level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.cycle(POWERED), 15);
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.below(), this);
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }
}
