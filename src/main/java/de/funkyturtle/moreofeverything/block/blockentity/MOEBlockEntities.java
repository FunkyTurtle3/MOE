package de.funkyturtle.moreofeverything.block.blockentity;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.types.Type;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import de.funkyturtle.moreofeverything.block.blockentity.custom.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

import static com.mojang.text2speech.Narrator.LOGGER;


public class MOEBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MoreOfEverything.MOD_ID);
    public static final RegistryObject<BlockEntityType<ArchaeologyTableBlockEntity>> ARCHEOLOGY_TABLE_BE_TYPE =
            BLOCK_ENTITY_TYPES.register("archaeology_table_be_type", () -> BlockEntityType.Builder.of(
                    ArchaeologyTableBlockEntity::new, MOEBlock.ARCHAEOLOGY_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<MOEBrushableBlockEntity>> MOE_BRUSHABLE_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("moe_brushable_block_entity", () -> BlockEntityType.Builder.of(
                    MOEBrushableBlockEntity::new, MOEBlock.SUSPICIOUS_RED_SAND.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}