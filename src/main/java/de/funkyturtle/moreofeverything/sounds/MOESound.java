package de.funkyturtle.moreofeverything.sounds;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class MOESound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoreOfEverything.MOD_ID);

    public static final RegistryObject<SoundEvent> OBSCURITE_STEP = registerSoundEvents("obscurite_step");
    public static final RegistryObject<SoundEvent> OBSCURITE_PLACE = registerSoundEvents("obscurite_place");
    public static final RegistryObject<SoundEvent> OBSCURITE_BREAK = registerSoundEvents("obscurite_break");


    public static final RegistryObject<SoundEvent> CATCH_ENTITY_IN_CAPTURE_NET = registerSoundEvents("catch_entity_in_capture_net");
    public static final RegistryObject<SoundEvent> NO_TIME_TO_WAIT = registerSoundEvents("no_time_to_wait");
    public static final ResourceKey<JukeboxSong> NO_TIME_TO_WAIT_KEY = ResourceKey.create(
        Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "no_time_to_wait_key"));
    private static RegistryObject<SoundEvent> registerSoundEvents(String string) {
        return SOUND_EVENTS.register(string, () -> SoundEvent.createVariableRangeEvent(fromNamespaceAndPath(MoreOfEverything.MOD_ID, string)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
