package de.funkyturtle.moreofeverything.util;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Map;
import java.util.stream.Stream;

public record MOEWoodType(String name, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {
    private static final Map<String, WoodType> TYPES = new Object2ObjectArrayMap<>();
    public static final Codec<WoodType> CODEC = Codec.stringResolver(WoodType::name, TYPES::get);
    public static final WoodType FIR = register(new WoodType("fir", BlockSetType.OAK));
    public MOEWoodType(String p_273766_, BlockSetType p_273104_) {
        this(p_273766_, p_273104_, SoundType.WOOD, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
    }

    public static WoodType register(WoodType p_61845_) {
        TYPES.put(p_61845_.name(), p_61845_);
        return p_61845_;
    }

    public static Stream<WoodType> values() {
        return TYPES.values().stream();
    }
}
