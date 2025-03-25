package de.funkyturtle.moreofeverything.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class MOEFood {
    public static final FoodProperties BAKED_CARROT = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).build();
    public static final FoodProperties BAKED_BEETROOT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.7F).build();
    public static final FoodProperties SHRIMP = new FoodProperties.Builder().nutrition(5).saturationModifier(0.9F).build();

    public static final FoodProperties GOLDEN_POTATO = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 320, 1), 1.0F).alwaysEdible()
            .build();
    public static final FoodProperties GOLDEN_BEETROOT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 240, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.JUMP, 140, 1), 1.0F).alwaysEdible()
            .build();
    public static final FoodProperties GOLDEN_KELP = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1F)
            .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 480, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 260, 1), 1.0F).alwaysEdible().fast()
            .build();
}
