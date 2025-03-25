package de.funkyturtle.moreofeverything.util;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.item.MOEItem;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class MOETrimPattern {
    public static final ResourceKey<TrimPattern> ANCIENT = ResourceKey.create(Registries.TRIM_PATTERN,
            ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "ancient"));

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, MOEItem.ANCIENT_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ANCIENT);
    }

    private static void register(BootstrapContext<TrimPattern> context, @NotNull Item item, ResourceKey<TrimPattern> key) {
        TrimPattern trimPattern = new TrimPattern(key.location(), ForgeRegistries.ITEMS.getHolder(item).get(),
                Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false);
        context.register(key, trimPattern);
    }
}
