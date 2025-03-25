package de.funkyturtle.moreofeverything.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.util.MOEMath;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record ArchaeologyRecipe(Ingredient ingredient, ItemStack result, double chance, int minimum, int maximum) implements Recipe<SingleRecipeInput> {

    @Override
    public boolean matches(@NotNull SingleRecipeInput pInput, Level pLevel) {
        return !pLevel.isClientSide() && this.ingredient.test(pInput.item());
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SingleRecipeInput pInput, HolderLookup.@NotNull Provider pRegistries) {
        ItemStack stack = this.result.copy();
        stack.setCount(MOEMath.getRandomRangedInt(minimum, maximum));
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public double getChance() {
        return chance;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider pRegistries) {
        return this.result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MOERecipeSerializer.ARCHAEOLOGY_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return MOERecipeType.ARCHAEOLOGY_RECIPE.get();
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(MOEBlock.ARCHAEOLOGY_TABLE.get());
    }

    public static class Serializer implements RecipeSerializer<ArchaeologyRecipe> {
        public static final MapCodec<ArchaeologyRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ArchaeologyRecipe::ingredient),
                ItemStack.CODEC.fieldOf("result").forGetter(ArchaeologyRecipe::result),
                Codec.DOUBLE.fieldOf("chance").forGetter(ArchaeologyRecipe::chance),
                Codec.INT.fieldOf("minimum").forGetter(ArchaeologyRecipe::minimum),
                Codec.INT.fieldOf("maximum").forGetter(ArchaeologyRecipe::maximum)
        ).apply(inst, ArchaeologyRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ArchaeologyRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, ArchaeologyRecipe::ingredient,
                        ItemStack.STREAM_CODEC, ArchaeologyRecipe::result,
                        ByteBufCodecs.DOUBLE, ArchaeologyRecipe::chance,
                        ByteBufCodecs.INT, ArchaeologyRecipe::minimum,
                        ByteBufCodecs.INT, ArchaeologyRecipe::maximum,
                        ArchaeologyRecipe::new);


        @Override
        public @NotNull MapCodec<ArchaeologyRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, ArchaeologyRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
