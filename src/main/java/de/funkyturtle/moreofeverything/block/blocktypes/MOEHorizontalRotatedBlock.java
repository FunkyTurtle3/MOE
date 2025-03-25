package de.funkyturtle.moreofeverything.block.blocktypes;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class MOEHorizontalRotatedBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<MOEHorizontalRotatedBlock> CODEC = simpleCodec(MOEHorizontalRotatedBlock::new);

    public MOEHorizontalRotatedBlock(Properties p_54120_) {
        super(p_54120_);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55828_) {
        p_55828_.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_52501_) {
        return this.defaultBlockState().setValue(FACING, p_52501_.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<MOEHorizontalRotatedBlock> codec() {
        return CODEC;
    }
}
