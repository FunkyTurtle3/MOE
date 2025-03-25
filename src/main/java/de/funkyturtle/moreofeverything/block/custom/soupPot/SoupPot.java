package de.funkyturtle.moreofeverything.block.custom.soupPot;

import de.funkyturtle.moreofeverything.item.custom.souprecipeitem.SoupRecipeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class SoupPot extends Block{
    public static final IntegerProperty COLOR = IntegerProperty.create("color", 0, 4);
    private boolean isFull;
    private final Item[][] recipes;
    private int recipeId;
    public SoupPot(BlockBehaviour.Properties p_49795_) {
        super(p_49795_);
        recipes = SoupRecipeHelper.registerRecipes();
        isFull = false;
    }

    public boolean isFull() {
        return isFull;
    }
    private int stage;

    /**@Override
    public @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide()) {
            if (stack.getItem() instanceof SoupRecipeItem) {
                recipeId = SoupRecipeHelper.getId(stack.getItem());
                isFull = true;
                stage = 0;
                COLOR.value(1);
                player.sendSystemMessage(Component.literal("wir sind bei stage " + stage));
            } else if(isFull() && stack.is(recipes[recipeId][stage])) {
                COLOR.value(2);
                stage++;
                player.sendSystemMessage(Component.literal("wir sind bei stage " + stage));
            }
            if(stage >= 4) {
                player.sendSystemMessage(Component.literal("wir sind bei stage fertig"));
            }
        }
        return ItemInteractionResult.SUCCESS;
    }*/
}