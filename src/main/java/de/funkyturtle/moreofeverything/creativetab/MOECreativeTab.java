package de.funkyturtle.moreofeverything.creativetab;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.item.MOEItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MOECreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreOfEverything.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MORE_OF_EVERYTHING_BLOCKS_CREATIVE_TAB = CREATIVE_MODE_TABS.register("more_of_everything_blocks_creative_tab", () -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(MOEItem.AZURINE_DUST.get()))
            .title(Component.translatable("creativetab.moreofeverything.blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(MOEItem.COPPER_NUGGET.get());
                output.accept(MOEItem.EXPOSED_COPPER_NUGGET.get());
                output.accept(MOEItem.WEATHERED_COPPER_NUGGET.get());
                output.accept(MOEItem.OXIDIZED_COPPER_NUGGET.get());

                output.accept(MOEItem.EXPOSED_COPPER_INGOT.get());
                output.accept(MOEItem.WEATHERED_COPPER_INGOT.get());
                output.accept(MOEItem.OXIDIZED_COPPER_INGOT.get());

                output.accept(MOEBlock.COPPER_LANTERN.get());
                output.accept(MOEBlock.EXPOSED_COPPER_LANTERN.get());
                output.accept(MOEBlock.WEATHERED_COPPER_LANTERN.get());
                output.accept(MOEBlock.OXIDIZED_COPPER_LANTERN.get());

                output.accept(MOEBlock.SUPER_POWERED_RAIL.get());
                output.accept(MOEBlock.T_DIODE.get());
                output.accept(MOEBlock.AND_GATE.get());
                output.accept(MOEBlock.OR_GATE.get());
                output.accept(MOEBlock.XOR_GATE.get());

                output.accept(MOEBlock.COPPER_CHAIN.get());
                output.accept(MOEBlock.EXPOSED_COPPER_CHAIN.get());
                output.accept(MOEBlock.WEATHERED_COPPER_CHAIN.get());
                output.accept(MOEBlock.OXIDIZED_COPPER_CHAIN.get());

                output.accept(MOEBlock.COPPER_PRESSURE_PLATE.get());
                output.accept(MOEBlock.COPPER_BUTTON.get());
                output.accept(MOEBlock.OXIDIZER.get());
                output.accept(MOEBlock.DEOXIDIZER.get());
                output.accept(MOEItem.CAPTURE_NET.get());

                output.accept(MOEItem.MYSTERIOUS_MATERIAL_1.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_2.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_3.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_4.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_5.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_6.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_7.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_8.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_9.get());
                output.accept(MOEItem.MYSTERIOUS_MATERIAL_10.get());

                output.accept(MOEItem.ANCIENT_SHERD.get());
                output.accept(MOEItem.PULSITE.get());
                output.accept(MOEItem.AMBER.get());
                output.accept(MOEItem.SAPPHIRE.get());
                output.accept(MOEItem.JADE.get());
                output.accept(MOEItem.OPAL.get());
                output.accept(MOEItem.RUBY.get());
                output.accept(MOEItem.AMETRINE.get());
                output.accept(MOEItem.GEM_UPGRADE_SMITHING_TEMPLATE.get());
                output.accept(MOEItem.AMMONITE.get());
                output.accept(MOEItem.PREHISTORIC_SPEER_TIP.get());

                output.accept(MOEItem.DUST_ARROW.get());

                output.accept(MOEItem.VIAL_OF_STABILITY_ESSENCE.get());
                output.accept(MOEItem.ARCANE_VIAL_OF_STABILITY_ESSENCE.get());
                output.accept(MOEItem.SORCEROUS_VIAL_OF_STABILITY_ESSENCE.get());
                output.accept(MOEItem.CAUTIOUSTAL.get());
                output.accept(MOEItem.BEWITCHING_CAUTIOUSTAL.get());
                output.accept(MOEItem.ELDRITCH_CAUTIOUSTAL.get());
                output.accept(MOEItem.ORB_OF_LUCK.get());
                output.accept(MOEItem.ORB_OF_COMPLETION.get());
                output.accept(MOEItem.STRANGE_MATTER.get());


                output.accept(MOEItem.GOLD_BRUSH.get());
                output.accept(MOEItem.IRON_BRUSH.get());
                output.accept(MOEItem.DIAMOND_BRUSH.get());

                output.accept(MOEItem.KIWI_SPAWN_EGG.get());


                output.accept(MOEItem.ANCIENT_DAGGER.get());
                output.accept(MOEItem.ANCIENT_PICKAXE.get());
                output.accept(MOEItem.ANCIENT_AXE.get());
                output.accept(MOEItem.ANCIENT_SHOVEL.get());
                output.accept(MOEItem.ANCIENT_HOE.get());

                output.accept(MOEItem.BAKED_CARROT.get());
                output.accept(MOEItem.BAKED_BEETROOT.get());
                output.accept(MOEItem.GOLDEN_POTATO.get());
                output.accept(MOEItem.GOLDEN_BEETROOT.get());
                output.accept(MOEItem.GOLDEN_KELP.get());
                output.accept(MOEItem.NO_TIME_TO_WAIT_DISC.get());

                output.accept(MOEItem.ANCIENT_ARMOR_TRIM_SMITHING_TEMPLATE.get());
                output.accept(MOEBlock.WOOD_SAW.get());
                output.accept(MOEBlock.ARCHAEOLOGY_TABLE.get());

                output.accept(MOEBlock.OBSCURITE.get());
                output.accept(MOEBlock.OBSCURITE_STAIRS.get());
                output.accept(MOEBlock.OBSCURITE_SLAB.get());
                output.accept(MOEBlock.OBSCURITE_BRICKS.get());
                output.accept(MOEBlock.CHISELED_OBSCURITE.get());
                output.accept(MOEBlock.POLISHED_OBSCURITE.get());
                output.accept(MOEBlock.POLISHED_OBSCURITE_STAIRS.get());
                output.accept(MOEBlock.POLISHED_OBSCURITE_SLAB.get());

                output.accept(MOEBlock.AGED_SAND_BRICKS.get());
                output.accept(MOEBlock.AGED_SAND_BRICK_STAIRS.get());
                output.accept(MOEBlock.AGED_SAND_BRICK_SLAB.get());
                output.accept(MOEBlock.AGED_SAND_SQUARE_BRICKS.get());
                output.accept(MOEBlock.AGED_SAND_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.AGED_SAND_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.TEMPLE_PILLAR_UPPER.get());
                output.accept(MOEBlock.TEMPLE_PILLAR_MIDDLE.get());
                output.accept(MOEBlock.TEMPLE_PILLAR_LOWER.get());
                output.accept(MOEBlock.TEMPLE_SHINGLES.get());
                output.accept(MOEBlock.TEMPLE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.SMALL_FIRE_BASKET.get());

                output.accept(MOEItem.AZURINE_DUST.get());
                output.accept(MOEBlock.AZURINE.get());
                output.accept(MOEBlock.AZURINE_BRICKS.get());
                output.accept(MOEBlock.AZURINE_PILLAR.get());

                output.accept(MOEBlock.AZURINE_SHINGLES_STAIRS.get());
                output.accept(MOEBlock.AZURINE_SHINGLES_SLAB.get());
                output.accept(MOEBlock.CHESS_PATTERN_BRICKS.get());

                output.accept(MOEBlock.DIORITE_SHINGLES.get());
                output.accept(MOEBlock.DIORITE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.DIORITE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.DIORITE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.TUFF_SHINGLES.get());
                output.accept(MOEBlock.TUFF_SQUARE_BRICKS.get());
                output.accept(MOEBlock.TUFF_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.TUFF_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.OBSCURITE_SHINGLES.get());
                output.accept(MOEBlock.OBSCURITE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.OBSCURITE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.OBSCURITE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.BASALT_SHINGLES.get());
                output.accept(MOEBlock.BASALT_SQUARE_BRICKS.get());
                output.accept(MOEBlock.BASALT_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.BASALT_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.BLACKSTONE_SHINGLES.get());
                output.accept(MOEBlock.BLACKSTONE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.BLACKSTONE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.BLACKSTONE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.GRANITE_SHINGLES.get());
                output.accept(MOEBlock.GRANITE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.GRANITE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.GRANITE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.DRIPSTONE_SHINGLES.get());
                output.accept(MOEBlock.DRIPSTONE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.DRIPSTONE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.DRIPSTONE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.PACKED_MUD_SHINGLES.get());
                output.accept(MOEBlock.PACKED_MUD_SQUARE_BRICKS.get());
                output.accept(MOEBlock.PACKED_MUD_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.PACKED_MUD_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.ENDSTONE_SHINGLES.get());
                output.accept(MOEBlock.ENDSTONE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.ENDSTONE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.ENDSTONE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.GOLD_SHINGLES.get());
                output.accept(MOEBlock.GOLD_SQUARE_BRICKS.get());
                output.accept(MOEBlock.GOLD_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.GOLD_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.EMERALD_SHINGLES.get());
                output.accept(MOEBlock.EMERALD_SQUARE_BRICKS.get());
                output.accept(MOEBlock.EMERALD_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.EMERALD_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.DARK_PRISMARINE_SHINGLES.get());
                output.accept(MOEBlock.DARK_PRISMARINE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.DARK_PRISMARINE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.DARK_PRISMARINE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.AZURINE_SHINGLES.get());
                output.accept(MOEBlock.AZURINE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.AZURINE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.AZURINE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.DIAMOND_SHINGLES.get());
                output.accept(MOEBlock.DIAMOND_SQUARE_BRICKS.get());
                output.accept(MOEBlock.DIAMOND_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.DIAMOND_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.LAPIS_LAZULI_SHINGLES.get());
                output.accept(MOEBlock.LAPIS_LAZULI_SQUARE_BRICKS.get());
                output.accept(MOEBlock.LAPIS_LAZULI_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.LAPIS_LAZULI_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.PURPUR_SHINGLES.get());
                output.accept(MOEBlock.PURPUR_SQUARE_BRICKS.get());
                output.accept(MOEBlock.PURPUR_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.PURPUR_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.AMETHYST_SHINGLES.get());
                output.accept(MOEBlock.AMETHYST_SQUARE_BRICKS.get());
                output.accept(MOEBlock.AMETHYST_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.AMETHYST_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.OBSIDIAN_SHINGLES.get());
                output.accept(MOEBlock.OBSIDIAN_SQUARE_BRICKS.get());
                output.accept(MOEBlock.OBSIDIAN_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.OBSIDIAN_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.NETHERRACK_SHINGLES.get());
                output.accept(MOEBlock.NETHERRACK_SQUARE_BRICKS.get());
                output.accept(MOEBlock.NETHERRACK_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.NETHERRACK_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.RED_SANDSTONE_SHINGLES.get());
                output.accept(MOEBlock.RED_SANDSTONE_SQUARE_BRICKS.get());
                output.accept(MOEBlock.RED_SANDSTONE_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.RED_SANDSTONE_COMPRESSED_BRICKS.get());
                output.accept(MOEBlock.COPPER_SHINGLES.get());
                output.accept(MOEBlock.COPPER_SQUARE_BRICKS.get());
                output.accept(MOEBlock.COPPER_TRIANGLE_BRICKS.get());
                output.accept(MOEBlock.COPPER_COMPRESSED_BRICKS.get());
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}