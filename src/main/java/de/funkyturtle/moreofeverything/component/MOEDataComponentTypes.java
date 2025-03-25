package de.funkyturtle.moreofeverything.component;

import com.mojang.serialization.Codec;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.UnaryOperator;

public class MOEDataComponentTypes {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MoreOfEverything.MOD_ID);

    public static final RegistryObject<DataComponentType<Boolean>> LOCKED = register("locked",
            builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));
    public static final RegistryObject<DataComponentType<Integer>> MATERIAL_DAMAGE = register("material_damage",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));
    public static final RegistryObject<DataComponentType<Integer>> BRUSH_DAMAGE = register("brush_damage",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));
    public static final RegistryObject<DataComponentType<Integer>> WATER_LEVEL = register("water_level",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    private static <T> RegistryObject<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
