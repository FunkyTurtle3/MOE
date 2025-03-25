package de.funkyturtle.moreofeverything.block.custom.woodSaw;

import com.mojang.serialization.MapCodec;
import javax.annotation.Nullable;

import de.funkyturtle.moreofeverything.menu.WoodSawMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WoodSaw extends Block {
    public static final MapCodec<WoodSaw> CODEC = simpleCodec(WoodSaw::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.wood_saw");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected static final VoxelShape SHAPE_PLANE = Block.box(.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    protected static final VoxelShape SHAPE_NORTH_SOUTH = Shapes.or(
            SHAPE_PLANE,Block.box(0.0, 0.0, 6.0, 16.0,16.0,10.0)
    );
    protected static final VoxelShape SHAPE_WEST_EAST = Shapes.or(
            SHAPE_PLANE,Block.box(6.0, 0.0, 0.0, 10.0,16.0,16.0)
    );

    @Override
        public @NotNull MapCodec<WoodSaw> codec() {
        return CODEC;
    }

    public WoodSaw(BlockBehaviour.Properties p_57068_) {
        super(p_57068_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_57070_) {
        return this.defaultBlockState().setValue(FACING, p_57070_.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_57083_, Level p_57084_, BlockPos p_57085_, Player p_57086_, BlockHitResult p_57088_) {
        if (p_57084_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            p_57086_.openMenu(p_57083_.getMenuProvider(p_57084_, p_57085_));
            p_57086_.awardStat(Stats.INTERACT_WITH_STONECUTTER);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState p_57105_, Level p_57106_, BlockPos p_57107_) {
        return new SimpleMenuProvider(
                (p_57074_, p_57075_, p_57076_) -> new WoodSawMenu(p_57074_, p_57075_, ContainerLevelAccess.create(p_57106_, p_57107_)), CONTAINER_TITLE
        );
    }

    @Override
    protected VoxelShape getShape(BlockState p_57100_, BlockGetter p_57101_, BlockPos p_57102_, CollisionContext p_57103_) {
        return switch (p_57100_.getValue(FACING)) {
            case NORTH, SOUTH -> SHAPE_NORTH_SOUTH;
            case EAST, WEST -> SHAPE_WEST_EAST;
            default -> SHAPE_PLANE;
        };
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState p_57109_) {
        return true;
    }

    @Override
    protected @NotNull BlockState rotate(BlockState p_57093_, Rotation p_57094_) {
        return p_57093_.setValue(FACING, p_57094_.rotate(p_57093_.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState p_57090_, Mirror p_57091_) {
        return p_57090_.rotate(p_57091_.getRotation(p_57090_.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57096_) {
        p_57096_.add(FACING);
    }

    @Override
    protected boolean isPathfindable(BlockState p_57078_, PathComputationType p_57081_) {
        return false;
    }
}