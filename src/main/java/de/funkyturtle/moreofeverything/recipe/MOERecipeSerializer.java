package de.funkyturtle.moreofeverything.recipe;


import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.arch.Processor;

public class MOERecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MoreOfEverything.MOD_ID);

    public static final RegistryObject<RecipeSerializer<WoodSawRecipe>> WOODEN_SAW_RECIPE_SERIALIZER = SERIALIZERS.register("wood_saw_recipe", () -> new WoodSawRecipe.Serializer<>(WoodSawRecipe::new) {});
    public static final RegistryObject<RecipeSerializer<ArchaeologyRecipe>> ARCHAEOLOGY_RECIPE_SERIALIZER = SERIALIZERS.register("archaeology_recipe", ArchaeologyRecipe.Serializer::new);

    public static void register(IEventBus eventbus) {
        SERIALIZERS.register(eventbus);
    }
}