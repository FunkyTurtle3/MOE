package de.funkyturtle.moreofeverything.event;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreOfEverything.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MOEClientEvents {
    @SubscribeEvent
    public static void onRenderOverlay(CustomizeGuiOverlayEvent event) {}
}