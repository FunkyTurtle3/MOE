package de.funkyturtle.moreofeverything.util;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.Map;
import java.util.stream.Stream;

public record MOEBlockSetType(
        String name,
        boolean canOpenByHand,
        boolean canOpenByWindCharge,
        boolean canButtonBeActivatedByArrows,
        BlockSetType.PressurePlateSensitivity pressurePlateSensitivity,
        SoundType soundType,
        SoundEvent doorClose,
        SoundEvent doorOpen,
        SoundEvent trapdoorClose,
        SoundEvent trapdoorOpen,
        SoundEvent pressurePlateClickOff,
        SoundEvent pressurePlateClickOn,
        SoundEvent buttonClickOff,
        SoundEvent buttonClickOn
) {
    private static final Map<String, BlockSetType> TYPES = new Object2ObjectArrayMap<>();
    public static final Codec<BlockSetType> CODEC = Codec.stringResolver(BlockSetType::name, TYPES::get);
    public static final BlockSetType FIR = register(new BlockSetType("fir"));

    public static BlockSetType register(BlockSetType p_273033_) {
        TYPES.put(p_273033_.name(), p_273033_);
        return p_273033_;
    }

    public static Stream<BlockSetType> values() {
        return TYPES.values().stream();
    }
}
