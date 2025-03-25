package de.funkyturtle.moreofeverything.villager;

import com.google.common.collect.ImmutableSet;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MOEVillager {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MoreOfEverything.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MoreOfEverything.MOD_ID);

    public static final RegistryObject<PoiType> APIARIST_POI = POI_TYPES.register("apiarist", () -> new PoiType(ImmutableSet.copyOf(Blocks.CAMPFIRE.getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<PoiType> ARCHAEOLOGIST_POI = POI_TYPES.register("archaeologist", () -> new PoiType(ImmutableSet.copyOf(MOEBlock.ARCHAEOLOGY_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<PoiType> MINER_POI = POI_TYPES.register("miner", () -> new PoiType(ImmutableSet.copyOf(Blocks.FURNACE.getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<VillagerProfession> APIARIST_VILLAGER = VILLAGER_PROFESSION.register("apiarist", () -> new VillagerProfession("apiarist",
            holder -> holder.get() == APIARIST_POI.get(), holder -> holder.get() == APIARIST_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.BEEHIVE_WORK));
    public static final RegistryObject<VillagerProfession> ARCHAEOLOGIST_VILLAGER = VILLAGER_PROFESSION.register("archaeologist", () -> new VillagerProfession("archaeologist",
            holder -> holder.get() == ARCHAEOLOGIST_POI.get(), holder -> holder.get() == ARCHAEOLOGIST_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.BOOK_PUT));
    public static final RegistryObject<VillagerProfession> MINER_VILLAGER = VILLAGER_PROFESSION.register("miner", () -> new VillagerProfession("miner",
            holder -> holder.get() == MINER_POI.get(), holder -> holder.get() == MINER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.FURNACE_FIRE_CRACKLE));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }
}