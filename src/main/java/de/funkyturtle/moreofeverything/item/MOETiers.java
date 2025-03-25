package de.funkyturtle.moreofeverything.item;

import de.funkyturtle.moreofeverything.util.MOETag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class MOETiers {
    public static final Tier ANCIENT_TIER = new ForgeTier(768, 9.0F, 3f, 20, MOETag.Blocks.NEEDS_ANCIENT_TOOL, () -> Ingredient.of(MOEItem.ANCIENT_SHERD.get()),MOETag.Blocks.INCORRECT_FOR_ANCIENT_TOOL);
}
