package de.funkyturtle.moreofeverything.entity;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.entity.custom.KiwiEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MOEEntity {
public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoreOfEverything.MOD_ID);

public static final RegistryObject<EntityType<KiwiEntity>> KIWI = ENTITY_TYPES.register("kiwi", () -> EntityType.Builder.of(KiwiEntity::new, MobCategory.CREATURE)
        .sized(0.5F, 0.8F).build("kiwi"));

public static void register(IEventBus eventBus) {
    ENTITY_TYPES.register(eventBus);
}
}
