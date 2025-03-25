package de.funkyturtle.moreofeverything.recipe;

import de.funkyturtle.moreofeverything.block.MOEBlock;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class WoodSawRecipe extends SingleItemRecipe {

    public WoodSawRecipe(String p_44479_, Ingredient p_44480_, ItemStack p_301701_) {
        super(MOERecipeType.WOODEN_SAW_RECIPE.get(), MOERecipeSerializer.WOODEN_SAW_RECIPE_SERIALIZER.get(), p_44479_, p_44480_, p_301701_);
    }

    public boolean matches(SingleRecipeInput p_344680_, Level p_44484_) {
        return this.ingredient.test(p_344680_.item());
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(MOEBlock.WOOD_SAW.get());
    }
}