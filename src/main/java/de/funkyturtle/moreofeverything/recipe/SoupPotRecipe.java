package de.funkyturtle.moreofeverything.recipe;

import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class SoupPotRecipe implements Recipe<RecipeInput> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public SoupPotRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }


    @Override
    public boolean matches(RecipeInput p_343697_, Level p_44003_) {
        if(p_44003_.isClientSide()) {
            p_343697_.getItem(0);
        }
        return inputItems.get(0).test(p_343697_.getItem(0));
    }

    @Override
    public ItemStack assemble(RecipeInput p_343633_, HolderLookup.Provider p_332698_) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_331967_) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<SoupPotRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "cook_in_a_pot";
    }

    public static class Serializer implements RecipeSerializer<SoupPotRecipe> {
        public static final ResourceLocation.Serializer INSTANCE = new ResourceLocation.Serializer();
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "cook_in_a_pot");

        @Override
        public MapCodec<SoupPotRecipe> codec() {
            return null;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SoupPotRecipe> streamCodec() {
            return null;
        }
    }
}
