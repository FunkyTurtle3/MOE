package de.funkyturtle.moreofeverything.block.custom.diodes;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;

public class TDiode extends DiodeBlock {
    public static final MapCodec<RepeaterBlock> CODEC = simpleCodec(RepeaterBlock::new);
    public TDiode(Properties p_52499_) {
        super(p_52499_);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(POWERED, Boolean.FALSE)
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
    protected int getSignal(BlockState p_52520_, @NotNull BlockGetter p_52521_, @NotNull BlockPos p_52522_, @NotNull Direction p_52523_) {
        if (!p_52520_.getValue(POWERED)) {
            return 0;
        } else {
            return p_52520_.getValue(FACING) == p_52523_.getClockWise() || p_52520_.getValue(FACING) == p_52523_.getCounterClockWise() ? this.getOutputSignal(p_52521_, p_52522_, p_52520_, p_52523_) : 0;
        }
    }

    protected int getOutputSignal(BlockGetter p_52541_, BlockPos p_52542_, BlockState p_52543_, Direction direction) {
        return p_52543_.getValue(POWERED) && (p_52543_.getValue(FACING) == direction.getClockWise() || p_52543_.getValue(FACING) == direction.getCounterClockWise()) ? 15 : 0 ;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55828_) {
        p_55828_.add(FACING, POWERED);
    }

    @Override
    protected void onPlace(@NotNull BlockState p_52566_, @NotNull Level p_52567_, @NotNull BlockPos p_52568_, @NotNull BlockState p_52569_, boolean p_52570_) {
        this.updateNeighborsSide(p_52567_, p_52568_, p_52566_);
    }

    @Override
    protected void onRemove(@NotNull BlockState p_52532_, @NotNull Level p_52533_, @NotNull BlockPos p_52534_, @NotNull BlockState p_52535_, boolean p_52536_) {
        if (!p_52536_ && !p_52532_.is(p_52535_.getBlock())) {
            super.onRemove(p_52532_, p_52533_, p_52534_, p_52535_, false);
            this.updateNeighborsSide(p_52533_, p_52534_, p_52532_);
        }
    }

    protected void updateNeighborsSide(Level p_52581_, BlockPos p_52582_, BlockState p_52583_) {
        Direction direction = p_52583_.getValue(FACING).getClockWise();
        BlockPos blockpos = p_52582_.relative(direction.getOpposite());
        if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(p_52581_, p_52582_, p_52581_.getBlockState(p_52582_), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled()) {
            return;
        }
        p_52581_.neighborChanged(blockpos, this, p_52582_);
        p_52581_.updateNeighborsAtExceptFromFacing(blockpos, this, direction);
        direction = p_52583_.getValue(FACING).getCounterClockWise();
        blockpos = p_52582_.relative(direction.getOpposite());
        if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(p_52581_, p_52582_, p_52581_.getBlockState(p_52582_), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled()) {
            return;
        }
        p_52581_.neighborChanged(blockpos, this, p_52582_);
        p_52581_.updateNeighborsAtExceptFromFacing(blockpos, this, direction);
    }
}
