package de.funkyturtle.moreofeverything.event;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.entity.MOEEntity;
import de.funkyturtle.moreofeverything.entity.custom.KiwiEntity;
import de.funkyturtle.moreofeverything.entity.render.KiwiModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreOfEverything.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MOEEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(KiwiModel.LAYER_LOCATION, KiwiModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MOEEntity.KIWI.get(), KiwiEntity.createAttributes().build());
    }
}
