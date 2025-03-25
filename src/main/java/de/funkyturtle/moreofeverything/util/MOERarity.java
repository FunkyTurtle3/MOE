package de.funkyturtle.moreofeverything.util;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public enum MOERarity implements StringRepresentable {
    LEGENDARY(5, "legendary", ChatFormatting.GOLD);

    public static final Codec<Rarity> CODEC = StringRepresentable.fromValues(Rarity::values);
    private final int id;
    private final String name;
    private final ChatFormatting color;

    private MOERarity(final int p_330136_, final String p_327766_, final ChatFormatting p_43028_) {
        this.id = p_330136_;
        this.name = p_327766_;
        this.color = p_43028_;
    }

    public ChatFormatting color() {
        return this.color;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
