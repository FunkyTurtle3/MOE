package de.funkyturtle.moreofeverything;

import com.mojang.logging.LogUtils;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.block.blockentity.MOEBlockEntities;
import de.funkyturtle.moreofeverything.component.MOEDataComponentTypes;
import de.funkyturtle.moreofeverything.creativetab.MOECreativeTab;
import de.funkyturtle.moreofeverything.entity.MOEEntity;
import de.funkyturtle.moreofeverything.entity.render.DustArrowRenderer;
import de.funkyturtle.moreofeverything.entity.render.KiwiRenderer;
import de.funkyturtle.moreofeverything.item.MOEItem;
import de.funkyturtle.moreofeverything.menu.MOEMenu;
import de.funkyturtle.moreofeverything.recipe.MOERecipeSerializer;
import de.funkyturtle.moreofeverything.recipe.MOERecipeType;
import de.funkyturtle.moreofeverything.screen.ArchaeologyScreen;
import de.funkyturtle.moreofeverything.sounds.MOESound;
import de.funkyturtle.moreofeverything.villager.MOEVillager;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MoreOfEverything.MOD_ID)
public class MoreOfEverything
{
    public static final String MOD_ID = "moreofeverything";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MoreOfEverything()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        MOEBlock.register(modEventBus);
        MOEItem.register(modEventBus);
        MOECreativeTab.register(modEventBus);
        MOEMenu.register(modEventBus);
        MOERecipeSerializer.register(modEventBus);
        MOERecipeType.register(modEventBus);
        MOESound.register(modEventBus);
        MOEVillager.register(modEventBus);
        MOEBlockEntities.register(modEventBus);
        MOEDataComponentTypes.register(modEventBus);
        MOEEntity.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.TRIM_MATERIAL, TrimMaterials::bootstrap);

    private void commonSetup(final FMLCommonSetupEvent event)
    {LOGGER.info("HELLO FROM COMMON SETUP");

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(MOEEntity.KIWI.get(), KiwiRenderer::new);
            EntityRenderers.register(MOEEntity.DUST_ARROW.get(), DustArrowRenderer::new);
        }
    }
}