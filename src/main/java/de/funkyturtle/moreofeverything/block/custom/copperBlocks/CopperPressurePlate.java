package de.funkyturtle.moreofeverything.block.custom.copperBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.ticks.TickPriority;
import org.jetbrains.annotations.NotNull;

public class CopperPressurePlate extends PressurePlateBlock {
    public CopperPressurePlate(BlockSetType p_273284_, Properties p_273571_) {
        super(p_273284_, p_273571_);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.FALSE));
    }
    @Override
    protected void attack(@NotNull BlockState p_60499_, @NotNull Level p_60500_, @NotNull BlockPos p_60501_, @NotNull Player p_60502_) {
        emmitShortSignal(p_60499_, p_60500_, p_60501_);
    }
    private void emmitShortSignal(BlockState state, Level level, BlockPos pos) {
        toggle(level, pos, state);
        level.scheduleTick(pos, this, 20, TickPriority.HIGH);
        toggle(level, pos, state);
    }

    private void toggle(@NotNull Level level, BlockPos pos, @NotNull BlockState state) {
        level.setBlock(pos, state.cycle(POWERED), 15);
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.below(), this);
    }
}
