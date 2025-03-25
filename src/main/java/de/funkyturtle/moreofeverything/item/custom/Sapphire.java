package de.funkyturtle.moreofeverything.item.custom;

import de.funkyturtle.moreofeverything.component.MOEDataComponentTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class Sapphire extends Item {
    public Sapphire(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide && pContext.getItemInHand().get(MOEDataComponentTypes.WATER_LEVEL.get()) < 16) {
            if (pContext.getLevel().getBlockState(pContext.getClickedPos().relative(pContext.getClickedFace())).getBlock() == Blocks.WATER) {
                pContext.getItemInHand().setDamageValue(pContext.getItemInHand().get(MOEDataComponentTypes.WATER_LEVEL.get()) + 1);
                pContext.getLevel().setBlock(pContext.getClickedPos().relative(pContext.getClickedFace()), Blocks.AIR.defaultBlockState(), 3);
                return InteractionResult.SUCCESS;
            }
            System.out.println(pContext.getItemInHand().get(MOEDataComponentTypes.WATER_LEVEL.get()));
        }
        return InteractionResult.FAIL;
    }
}
