package de.funkyturtle.moreofeverything.item.custom.souprecipeitem;

import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.block.custom.soupPot.SoupRecipeHelper;
import de.funkyturtle.moreofeverything.screen.SoupRecipeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;

public class SoupRecipeItem extends Item {

    public int index;
    public SoupRecipeItem(Properties p_41383_, int Id) {
        super(p_41383_);
        index = Id;
    }
    /**@Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide() && !pContext.getLevel().getBlockState(pContext.getClickedPos()).is(MOEBlock.SOUP_POT.get())) {
            return InteractionResult.SUCCESS;
        }
        if (!pContext.getLevel().getBlockState(pContext.getClickedPos()).is(MOEBlock.SOUP_POT.get())) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().setScreen(new SoupRecipeScreen(SoupRecipeHelper.getId(pContext.getItemInHand().getItem()))));
        }
            return InteractionResult.SUCCESS;
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_41432_, Player p_41433_, @NotNull InteractionHand p_41434_) {
            Item item = p_41433_.getItemInHand(p_41434_).getItem();
            if (item instanceof SoupRecipeItem) {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().setScreen(new SoupRecipeScreen(SoupRecipeHelper.getId(item))));
                return InteractionResultHolder.success(p_41433_.getItemInHand(p_41434_));
            }
        return InteractionResultHolder.fail(p_41433_.getItemInHand(p_41434_));
    }*/
}
