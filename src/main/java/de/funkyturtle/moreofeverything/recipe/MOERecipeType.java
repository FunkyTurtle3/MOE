package de.funkyturtle.moreofeverything.recipe;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MOERecipeType {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, MoreOfEverything.MOD_ID);

    public static final RegistryObject<RecipeType<WoodSawRecipe>> WOODEN_SAW_RECIPE = RECIPE_TYPES.register("wood_saw_recipe",
            () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "wood_saw_recipe";
                }
            });
    public static final RegistryObject<RecipeType<ArchaeologyRecipe>> ARCHAEOLOGY_RECIPE = RECIPE_TYPES.register("archaeology_recipe",
            () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "archaeology_recipe";
                }
            });

    public static void register(IEventBus eventbus) {
        RECIPE_TYPES.register(eventbus);
    }
}
