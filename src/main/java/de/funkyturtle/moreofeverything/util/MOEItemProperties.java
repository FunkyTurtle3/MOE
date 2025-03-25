package de.funkyturtle.moreofeverything.util;

import com.google.common.collect.Maps;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class MOEItemProperties {
    private static final Map<Item, Map<ResourceLocation, ItemPropertyFunction>> PROPERTIES = Maps.newHashMap();
    private static void register(Item p_174571_, ResourceLocation p_174572_, ClampedItemPropertyFunction p_174573_) {
        register(p_174571_, p_174572_, (ItemPropertyFunction) p_174573_);
    }

    public static void register(Item p_174571_, ResourceLocation p_174572_, ItemPropertyFunction p_174573_) {
        PROPERTIES.computeIfAbsent(p_174571_, p_117828_ -> Maps.newHashMap()).put(p_174572_, p_174573_);
    }
    static {
        register(
                Items.RECOVERY_COMPASS,
                ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID,"angle"),
                new CompassItemPropertyFunction((p_234983_, p_234984_, p_234985_) -> p_234985_ instanceof Player player ? new GlobalPos(player.level().dimension(), player.getServer().getLevel(player.level().dimension()).findNearestMapStructure(StructureTags.MINESHAFT, new BlockPos((int) player.getX(),(int) player.getY(),(int) player.getZ()), 50, true)) : null)
        );
    }
}
