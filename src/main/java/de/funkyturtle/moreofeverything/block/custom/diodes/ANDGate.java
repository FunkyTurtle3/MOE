package de.funkyturtle.moreofeverything.block.custom.diodes;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class ANDGate extends DiodeBlock implements MOEGate {
    public static final MapCodec<RepeaterBlock> CODEC = simpleCodec(RepeaterBlock::new);
    public ANDGate(Properties p_52499_) {
        super(p_52499_);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(POWERED, Boolean.FALSE)
                        .setValue(INVERTED_DIODE, Boolean.FALSE)
        );
    }

    @Override
    protected @NotNull MapCodec<? extends DiodeBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getDelay(@NotNull BlockState p_52584_) {
        return 1;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55828_) {
        p_55828_.add(FACING, POWERED, INVERTED_DIODE);
    }

    @Override
    protected void onPlace(@NotNull BlockState p_52566_, @NotNull Level p_52567_, @NotNull BlockPos p_52568_, @NotNull BlockState p_52569_, boolean p_52570_) {
        this.updateNeighborsSide(p_52567_, p_52568_, p_52566_);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState p_55809_, Level p_55810_, BlockPos p_55811_, Player p_55812_, BlockHitResult p_55814_) {
        if (!p_55812_.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            p_55810_.setBlock(p_55811_, p_55809_.cycle(INVERTED_DIODE), 3);
            p_55810_.scheduleTick(p_55811_, this, 1);
            return InteractionResult.sidedSuccess(p_55810_.isClientSide);
        }
    }

    @Override
    protected void onRemove(@NotNull BlockState p_52532_, @NotNull Level p_52533_, @NotNull BlockPos p_52534_, @NotNull BlockState p_52535_, boolean p_52536_) {
        if (!p_52536_ && !p_52532_.is(p_52535_.getBlock())) {
            super.onRemove(p_52532_, p_52533_, p_52534_, p_52535_, false);
            this.updateNeighborsSide(p_52533_, p_52534_, p_52532_);
        }
    }

    protected void updateNeighborsSide(Level p_52581_, BlockPos p_52582_, BlockState p_52583_) {
        Direction direction = p_52583_.getValue(FACING);
        BlockPos blockpos = p_52582_.relative(direction.getOpposite());
        if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(p_52581_, p_52582_, p_52581_.getBlockState(p_52582_), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled()) {
            return;
        }
        p_52581_.neighborChanged(blockpos, this, p_52582_);
        p_52581_.updateNeighborsAtExceptFromFacing(blockpos, this, direction);
    }

    protected int getInputSignal(Level p_52544_, BlockPos p_52545_, BlockState p_52546_) {
        Direction direction = p_52546_.getValue(FACING);
        BlockPos blockpos1 = p_52545_.relative(direction.getClockWise());
        BlockPos blockpos2 = p_52545_.relative(direction.getCounterClockWise());
        int i = p_52544_.getSignal(blockpos1, direction.getClockWise());
        int n = p_52544_.getSignal(blockpos2, direction.getCounterClockWise());
        if (i >= 15 && n >=15) {
            return 15;
        } else  if (i > 0 && n > 0){
            BlockState blockstate1 = p_52544_.getBlockState(blockpos1);
            BlockState blockstate2 = p_52544_.getBlockState(blockpos1);
            return Math.max(Math.max(i, n), blockstate1.is(Blocks.REDSTONE_WIRE) ? Math.max(blockstate1.getValue(RedStoneWireBlock.POWER), blockstate2.getValue(RedStoneWireBlock.POWER)) : 0);
        }
        else return 0;
    }

    protected boolean shouldTurnOn(Level p_52502_, BlockPos p_52503_, BlockState p_52504_) {
        if (p_52504_.getValue(INVERTED_DIODE)) {
            return !(this.getInputSignal(p_52502_, p_52503_, p_52504_) > 0);
        } else {
            return this.getInputSignal(p_52502_, p_52503_, p_52504_) > 0;
        }
    }
}