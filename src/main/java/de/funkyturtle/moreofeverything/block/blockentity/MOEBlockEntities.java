package de.funkyturtle.moreofeverything.block.blockentity;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import de.funkyturtle.moreofeverything.block.blockentity.custom.*;
import net.minecraftforge.registries.RegistryObject;


public class MOEBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MoreOfEverything.MOD_ID);
    public static final RegistryObject<BlockEntityType<ArchaeologyTableBlockEntity>> ARCHEOLOGY_TABLE_BE_TYPE =
            BLOCK_ENTITY_TYPES.register("archaeology_table_be_type", () -> BlockEntityType.Builder.of(
                    ArchaeologyTableBlockEntity::new, MOEBlock.ARCHAEOLOGY_TABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SusRedSandBE>> SUS_RED_SAND_BE_TYPE =
            BLOCK_ENTITY_TYPES.register("sus_red_sand_be_type", () -> BlockEntityType.Builder.of(
                    SusRedSandBE::new, MOEBlock.SUSPICIOUS_RED_SAND.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}