package de.funkyturtle.moreofeverything.block.custom.oxidizer;

import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.item.MOEItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class Oxidizer extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE_PLANE = Block.box(.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    public Oxidizer(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    protected static final VoxelShape SHAPE_NORTH_SOUTH = Shapes.or(
            SHAPE_PLANE,
            Block.box(1, 8, 7,3, 12, 9),
            Block.box(13, 8, 7,15, 12, 9),
            Block.box(12, 12, 6,16, 16, 10),
            Block.box(0, 12, 6,4, 16, 10)
    );
    protected static final VoxelShape SHAPE_WEST_EAST = Shapes.or(
            SHAPE_PLANE,
            Block.box(7, 8, 1,9, 12, 3),
            Block.box(7, 8, 13,9, 12, 15),
            Block.box(6, 12, 12,10, 16, 16),
            Block.box(6, 12, 0,10 , 16, 4)
    );
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext p_57070_) {
        return this.defaultBlockState().setValue(FACING, p_57070_.getHorizontalDirection().getOpposite());
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState p_57100_, @NotNull BlockGetter p_57101_, @NotNull BlockPos p_57102_, @NotNull CollisionContext p_57103_) {
        return switch (p_57100_.getValue(FACING)) {
            case NORTH, SOUTH -> SHAPE_NORTH_SOUTH;
            case EAST, WEST -> SHAPE_WEST_EAST;
            default -> SHAPE_PLANE;
        };
    }

    @Override
    protected boolean useShapeForLightOcclusion(@NotNull BlockState p_57109_) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> p_57096_) {
        p_57096_.add(FACING);
    }
    private @NotNull ItemStack getStackForStack(@NotNull ItemStack stack) {
        if (stack.getItem().equals(MOEItem.WEATHERED_COPPER_INGOT.get())) {
            return new ItemStack(MOEItem.OXIDIZED_COPPER_INGOT.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEItem.EXPOSED_COPPER_INGOT.get())) {
            return new ItemStack(MOEItem.WEATHERED_COPPER_INGOT.get(), stack.getCount());
        } else if (stack.getItem().equals(Items.COPPER_INGOT)) {
            return new ItemStack(MOEItem.EXPOSED_COPPER_INGOT.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEItem.WEATHERED_COPPER_NUGGET.get())) {
            return new ItemStack(MOEItem.OXIDIZED_COPPER_NUGGET.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEItem.EXPOSED_COPPER_NUGGET.get())) {
            return new ItemStack(MOEItem.WEATHERED_COPPER_NUGGET.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEItem.COPPER_NUGGET.get())) {
            return new ItemStack(MOEItem.EXPOSED_COPPER_NUGGET.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEBlock.WEATHERED_COPPER_CHAIN.get().asItem())) {
            return new ItemStack(MOEBlock.OXIDIZED_COPPER_CHAIN.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEBlock.EXPOSED_COPPER_CHAIN.get().asItem())) {
            return new ItemStack(MOEBlock.WEATHERED_COPPER_CHAIN.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEBlock.COPPER_CHAIN.get().asItem())) {
            return new ItemStack(MOEBlock.EXPOSED_COPPER_CHAIN.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEBlock.WEATHERED_COPPER_LANTERN.get().asItem())) {
            return new ItemStack(MOEBlock.OXIDIZED_COPPER_LANTERN.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEBlock.EXPOSED_COPPER_LANTERN.get().asItem())) {
            return new ItemStack(MOEBlock.WEATHERED_COPPER_LANTERN.get(), stack.getCount());
        } else if (stack.getItem().equals(MOEBlock.COPPER_LANTERN.get().asItem())) {
            return new ItemStack(MOEBlock.EXPOSED_COPPER_LANTERN.get(), stack.getCount());
        }
        return stack;
    }

    @Override
    public @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
            if (!level.isClientSide()) {
                if (getStackForStack(stack) != stack) {
                    player.setItemInHand(hand, getStackForStack(stack));
                    level.playSound(null, pos, SoundEvents.COPPER_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                player.giveExperiencePoints(10);
                System.out.println(player.experienceLevel);
                System.out.println(player.experienceProgress);
                System.out.println(player.totalExperience);
            }
        return ItemInteractionResult.SUCCESS;
    }
}