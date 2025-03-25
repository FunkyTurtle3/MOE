package de.funkyturtle.moreofeverything.screen;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.menu.ArchaeologyMenu;
import de.funkyturtle.moreofeverything.menu.MOEMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MoreOfEverything.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MOEScreen {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(MOEMenu.WOODEN_SAW_MENU.get(), WoodSawScreen::new);
        MenuScreens.register(MOEMenu.ARCHAEOLOGY_MENU.get(), ArchaeologyScreen::new);
    }
}