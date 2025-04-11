package de.funkyturtle.moreofeverything.block;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.block.blocktypes.MOEFlammableRotatedPillarBlock;
import de.funkyturtle.moreofeverything.block.blocktypes.MOEHorizontalRotatedBlock;
import de.funkyturtle.moreofeverything.block.blocktypes.MOEPlankBlock;
import de.funkyturtle.moreofeverything.block.custom.ArchaeologyTable;
import de.funkyturtle.moreofeverything.block.custom.MOEBrushableBlock;
import de.funkyturtle.moreofeverything.block.custom.diodes.ANDGate;
import de.funkyturtle.moreofeverything.block.custom.diodes.ORGate;
import de.funkyturtle.moreofeverything.block.custom.diodes.TDiode;
import de.funkyturtle.moreofeverything.block.custom.diodes.XORGate;
import de.funkyturtle.moreofeverything.block.custom.oxidizer.DeOxidizer;
import de.funkyturtle.moreofeverything.block.custom.oxidizer.Oxidizer;
import de.funkyturtle.moreofeverything.block.custom.copperBlocks.CopperButton;
import de.funkyturtle.moreofeverything.block.custom.copperBlocks.CopperLantern;
import de.funkyturtle.moreofeverything.block.custom.copperBlocks.CopperPressurePlate;
import de.funkyturtle.moreofeverything.block.custom.superpoweredrail.SuperPoweredRail;
import de.funkyturtle.moreofeverything.block.custom.woodSaw.WoodSaw;
import de.funkyturtle.moreofeverything.item.MOEItem;
import de.funkyturtle.moreofeverything.sounds.MOESoundTypes;
import de.funkyturtle.moreofeverything.util.MOEBlockSetType;
import de.funkyturtle.moreofeverything.util.MOEWoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MOEBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreOfEverything.MOD_ID);

    public static final RegistryObject<Block> WOOD_SAW = registerBlock("wood_saw", () -> new WoodSaw(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASEDRUM).strength(3.5F)));

    //public static final RegistryObject<Block> SOUP_POT = registerBlock("soup_pot", () -> new SoupPot(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).noOcclusion()));

    public static final RegistryObject<Block> CHISELED_GOLD_BLOCK = registerBlock("chiseled_gold_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> COPPER_LANTERN = registerBlock("copper_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .forceSolidOn()
                    .requiresCorrectToolForDrops()
                    .strength(3.5F)
                    .sound(SoundType.LANTERN)
                    .lightLevel(p_187433_ -> 15)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> EXPOSED_COPPER_LANTERN = registerBlock("exposed_copper_lantern",
            () -> new CopperLantern(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final RegistryObject<Block> WEATHERED_COPPER_LANTERN = registerBlock("weathered_copper_lantern",
            () -> new CopperLantern(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final RegistryObject<Block> OXIDIZED_COPPER_LANTERN = registerBlock("oxidized_copper_lantern",
            () -> new CopperLantern(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));

    public static final RegistryObject<Block> COPPER_CHAIN = registerBlock("copper_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final RegistryObject<Block> EXPOSED_COPPER_CHAIN = registerBlock("exposed_copper_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final RegistryObject<Block> WEATHERED_COPPER_CHAIN = registerBlock("weathered_copper_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final RegistryObject<Block> OXIDIZED_COPPER_CHAIN = registerBlock("oxidized_copper_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

    //public static final RegistryObject<Block> JAR = registerBlock("jar", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).noCollission()));

    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = registerBlock("copper_pressure_plate",
            () -> new CopperPressurePlate(BlockSetType.COPPER, BlockBehaviour.Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).noCollission()));

    public static final RegistryObject<Block> SUPER_POWERED_RAIL = registerBlock("super_powered_rail",
            () -> new SuperPoweredRail(BlockBehaviour.Properties.of().noCollission().strength(0.7F).sound(SoundType.METAL), true));

    public static final RegistryObject<Block> COPPER_BUTTON = registerBlock("copper_button",
            () -> new CopperButton(BlockSetType.COPPER, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));

    public static final RegistryObject<Block> DEOXIDIZER = registerBlock("deoxidizer",
            () -> new DeOxidizer(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion().lightLevel(p_152686_ -> 12).strength(0.5F, 5F)));
    public static final RegistryObject<Block> OXIDIZER = registerBlock("oxidizer",
            () -> new Oxidizer(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion().lightLevel(p_152686_ -> 12).strength(0.5F, 5F)));

    public static final RegistryObject<Block> FIR_LOG = registerBlock("fir_log", () -> new MOEFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_ORANGE).strength(3.0F)));
    public static final RegistryObject<Block> STRIPPED_FIR_LOG = registerBlock("stripped_fir_log", () -> new MOEFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.TERRACOTTA_ORANGE).strength(3.0F)));
    public static final RegistryObject<Block> FIR_WOOD = registerBlock("fir_wood", () -> new MOEFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).mapColor(MapColor.TERRACOTTA_ORANGE).strength(3.0F)));
    public static final RegistryObject<Block> STRIPPED_FIR_WOOD = registerBlock("stripped_fir_wood", () -> new MOEFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.TERRACOTTA_ORANGE).strength(3.0F)));

    public static final RegistryObject<Block> FIR_PLANKS = registerBlock("fir_planks", () -> new MOEPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<SlabBlock> FIR_SLAB = registerBlock("fir_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<StairBlock> FIR_STAIRS = registerBlock("fir_stairs", () -> new StairBlock(MOEBlock.FIR_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<PressurePlateBlock> FIR_PRESSURE_PLATE = registerBlock("fir_pressure_plate",
            () -> new PressurePlateBlock(MOEBlockSetType.FIR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).noCollission().mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<ButtonBlock> FIR_BUTTON = registerBlock("fir_button",
            () -> new ButtonBlock(MOEBlockSetType.FIR, 30,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).noCollission().mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<LeavesBlock> FIR_LEAVES = registerBlock("fir_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)));

    public static final RegistryObject<FenceBlock> FIR_FENCE = registerBlock("fir_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<FenceGateBlock> FIR_FENCE_GATE = registerBlock("fir_fence_gate", () -> new FenceGateBlock(MOEWoodType.FIR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<LadderBlock> FIR_LADDER = registerBlock("fir_ladder", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).mapColor(MapColor.TERRACOTTA_ORANGE)));

    public static final RegistryObject<TrapDoorBlock> FIR_TRAPDOOR = registerBlock("fir_trapdoor", () -> new TrapDoorBlock(MOEBlockSetType.FIR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<DoorBlock> FIR_DOOR = registerBlock("fir_door", () -> new DoorBlock(MOEBlockSetType.FIR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).mapColor(MapColor.TERRACOTTA_ORANGE)));

    public static final RegistryObject<RotatedPillarBlock> OBSCURITE = registerBlock("obscurite", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.GLOW_LICHEN).sound(MOESoundTypes.OBSCURITE)));
    public static final RegistryObject<Block> POLISHED_OBSCURITE = registerBlock("polished_obscurite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get()).mapColor(MapColor.GLOW_LICHEN)));
    public static final RegistryObject<SlabBlock> OBSCURITE_SLAB = registerBlock("obscurite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get()).mapColor(MapColor.GLOW_LICHEN)));
    public static final RegistryObject<StairBlock> OBSCURITE_STAIRS = registerBlock("obscurite_stairs", () -> new StairBlock(MOEBlock.OBSCURITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get()).mapColor(MapColor.GLOW_LICHEN)));
    public static final RegistryObject<SlabBlock> POLISHED_OBSCURITE_SLAB = registerBlock("polished_obscurite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get()).mapColor(MapColor.GLOW_LICHEN)));
    public static final RegistryObject<StairBlock> POLISHED_OBSCURITE_STAIRS = registerBlock("polished_obscurite_stairs", () -> new StairBlock(MOEBlock.OBSCURITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.GLOW_LICHEN)));
    public static final RegistryObject<Block> CHISELED_OBSCURITE = registerBlock("chiseled_obscurite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get()).mapColor(MapColor.GLOW_LICHEN)));
    public static final RegistryObject<Block> OBSCURITE_BRICKS = registerBlock("obscurite_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get()).mapColor(MapColor.GLOW_LICHEN)));
    public static final RegistryObject<Block> AZURINE = registerBlock("azurine", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<RotatedPillarBlock> AZURINE_PILLAR = registerBlock("azurine_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get())));
    public static final RegistryObject<SlabBlock> AZURINE_SHINGLES_SLAB = registerBlock("azurine_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get()).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<StairBlock> AZURINE_SHINGLES_STAIRS = registerBlock("azurine_shingles_stairs", () -> new StairBlock(MOEBlock.AZURINE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> POLISHED_AZURINE = registerBlock("polished_azurine", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get()).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<SlabBlock> AZURINE_SLAB = registerBlock("azurine_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get()).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<StairBlock> AZURINE_STAIRS = registerBlock("azurine_stairs", () -> new StairBlock(MOEBlock.AZURINE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<SlabBlock> POLISHED_AZURINE_SLAB = registerBlock("polished_azurine_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<StairBlock> POLISHED_AZURINE_STAIRS = registerBlock("polished_azurine_stairs", () -> new StairBlock(MOEBlock.AZURINE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<Block> CHISELED_AZURINE = registerBlock("chiseled_azurine", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<Block> AZURINE_BRICKS = registerBlock("azurine_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get()).mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> TEMPLE_PILLAR_LOWER = registerBlock("temple_pillar_lower", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> TEMPLE_PILLAR_MIDDLE = registerBlock("temple_pillar_middle", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> TEMPLE_PILLAR_UPPER = registerBlock("temple_pillar_upper", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> TEMPLE_SHINGLES = registerBlock("temple_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> TEMPLE_SQUARE_BRICKS = registerBlock("temple_square_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> AGED_SAND_SQUARE_BRICKS = registerBlock("aged_sand_square_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> AGED_SAND_TRIANGLE_BRICKS = registerBlock("aged_sand_triangle_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> AGED_SAND_COMPRESSED_BRICKS = registerBlock("aged_sand_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> AGED_SAND_BRICKS = registerBlock("aged_sand_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> AGED_SAND_BRICK_SLAB = registerBlock("aged_sand_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> AGED_SAND_BRICK_STAIRS = registerBlock("aged_sand_brick_stairs", () -> new StairBlock(MOEBlock.AGED_SAND_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> SMALL_FIRE_BASKET = registerBlock("small_fire_basket", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PLAYER_HEAD).mapColor(MapColor.DEEPSLATE).lightLevel(state -> 9).noOcclusion()) {
        @Override
        public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
            return Block.box(4.0, 0.0, 4.0, 12.0,8.0,12.0);
        }
    });


    public static final RegistryObject<ArchaeologyTable> ARCHAEOLOGY_TABLE = registerBlock("archaeology_table", () -> new ArchaeologyTable(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE).mapColor(MapColor.WOOD)));
    public static final RegistryObject<MOEHorizontalRotatedBlock> APIARY_TABLE = registerBlock("apiary_table", () -> new MOEHorizontalRotatedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE).mapColor(MapColor.WOOD)));

    //public static final RegistryObject<CeilingHangingSignBlock> FIR_HANGING_SIGN = registerBlock("fir_hanging_sign.json", () -> new CeilingHangingSignBlock(MOEWoodType.FIR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.TERRACOTTA_ORANGE)));

    public static final RegistryObject<RotatedPillarBlock> CHESS_PATTERN_BRICKS = registerBlock("chess_pattern_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get())));

    public static final RegistryObject<RotatedPillarBlock> OBSCURITE_TRIANGLE_BRICKS = registerBlock("obscurite_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get())));
    public static final RegistryObject<RotatedPillarBlock> AMETHYST_TRIANGLE_BRICKS = registerBlock("amethyst_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> AZURINE_TRIANGLE_BRICKS = registerBlock("azurine_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get())));
    public static final RegistryObject<RotatedPillarBlock> BASALT_TRIANGLE_BRICKS = registerBlock("basalt_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final RegistryObject<RotatedPillarBlock> BLACKSTONE_TRIANGLE_BRICKS = registerBlock("blackstone_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final RegistryObject<RotatedPillarBlock> COPPER_TRIANGLE_BRICKS = registerBlock("copper_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> DARK_PRISMARINE_TRIANGLE_BRICKS = registerBlock("dark_prismarine_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<RotatedPillarBlock> DIAMOND_TRIANGLE_BRICKS = registerBlock("diamond_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> DIORITE_TRIANGLE_BRICKS = registerBlock("diorite_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE)));
    public static final RegistryObject<RotatedPillarBlock> PACKED_MUD_TRIANGLE_BRICKS = registerBlock("packed_mud_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)));
    public static final RegistryObject<RotatedPillarBlock> DRIPSTONE_TRIANGLE_BRICKS = registerBlock("dripstone_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> EMERALD_TRIANGLE_BRICKS = registerBlock("emerald_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> ENDSTONE_TRIANGLE_BRICKS = registerBlock("endstone_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final RegistryObject<RotatedPillarBlock> GRANITE_TRIANGLE_BRICKS = registerBlock("granite_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)));
    public static final RegistryObject<RotatedPillarBlock> LAPIS_LAZULI_TRIANGLE_BRICKS = registerBlock("lapis_lazuli_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> NETHERRACK_TRIANGLE_BRICKS = registerBlock("netherrack_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final RegistryObject<RotatedPillarBlock> OBSIDIAN_TRIANGLE_BRICKS = registerBlock("obsidian_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
    public static final RegistryObject<RotatedPillarBlock> PURPUR_TRIANGLE_BRICKS = registerBlock("purpur_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> RED_SANDSTONE_TRIANGLE_BRICKS = registerBlock("red_sandstone_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE)));
    public static final RegistryObject<RotatedPillarBlock> GOLD_TRIANGLE_BRICKS = registerBlock("gold_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK)));

    public static final RegistryObject<RotatedPillarBlock> OBSCURITE_SQUARE_BRICKS = registerBlock("obscurite_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get())));
    public static final RegistryObject<RotatedPillarBlock> AMETHYST_SQUARE_BRICKS = registerBlock("amethyst_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> AZURINE_SQUARE_BRICKS = registerBlock("azurine_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get())));
    public static final RegistryObject<RotatedPillarBlock> BASALT_SQUARE_BRICKS = registerBlock("basalt_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final RegistryObject<RotatedPillarBlock> BLACKSTONE_SQUARE_BRICKS = registerBlock("blackstone_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final RegistryObject<RotatedPillarBlock> COPPER_SQUARE_BRICKS = registerBlock("copper_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> DARK_PRISMARINE_SQUARE_BRICKS = registerBlock("dark_prismarine_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<RotatedPillarBlock> DIAMOND_SQUARE_BRICKS = registerBlock("diamond_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> DIORITE_SQUARE_BRICKS = registerBlock("diorite_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE)));
    public static final RegistryObject<RotatedPillarBlock> PACKED_MUD_SQUARE_BRICKS = registerBlock("packed_mud_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)));
    public static final RegistryObject<RotatedPillarBlock> DRIPSTONE_SQUARE_BRICKS = registerBlock("dripstone_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> EMERALD_SQUARE_BRICKS = registerBlock("emerald_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> ENDSTONE_SQUARE_BRICKS = registerBlock("endstone_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final RegistryObject<RotatedPillarBlock> GRANITE_SQUARE_BRICKS = registerBlock("granite_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)));
    public static final RegistryObject<RotatedPillarBlock> LAPIS_LAZULI_SQUARE_BRICKS = registerBlock("lapis_lazuli_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> NETHERRACK_SQUARE_BRICKS = registerBlock("netherrack_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final RegistryObject<RotatedPillarBlock> OBSIDIAN_SQUARE_BRICKS = registerBlock("obsidian_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
    public static final RegistryObject<RotatedPillarBlock> PURPUR_SQUARE_BRICKS = registerBlock("purpur_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<RotatedPillarBlock> RED_SANDSTONE_SQUARE_BRICKS = registerBlock("red_sandstone_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE)));
    public static final RegistryObject<RotatedPillarBlock> GOLD_SQUARE_BRICKS = registerBlock("gold_square_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> OBSCURITE_SHINGLES = registerBlock("obscurite_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get())));
    public static final RegistryObject<Block> AMETHYST_SHINGLES = registerBlock("amethyst_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> AZURINE_SHINGLES = registerBlock("azurine_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get())));
    public static final RegistryObject<Block> BASALT_SHINGLES = registerBlock("basalt_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final RegistryObject<Block> BLACKSTONE_SHINGLES = registerBlock("blackstone_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> COPPER_SHINGLES = registerBlock("copper_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> DARK_PRISMARINE_SHINGLES = registerBlock("dark_prismarine_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<Block> DIAMOND_SHINGLES = registerBlock("diamond_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> DIORITE_SHINGLES = registerBlock("diorite_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE)));
    public static final RegistryObject<Block> PACKED_MUD_SHINGLES = registerBlock("packed_mud_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)));
    public static final RegistryObject<Block> DRIPSTONE_SHINGLES = registerBlock("dripstone_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final RegistryObject<Block> EMERALD_SHINGLES = registerBlock("emerald_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<Block> ENDSTONE_SHINGLES = registerBlock("endstone_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final RegistryObject<Block> GRANITE_SHINGLES = registerBlock("granite_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)));
    public static final RegistryObject<Block> LAPIS_LAZULI_SHINGLES = registerBlock("lapis_lazuli_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK)));
    public static final RegistryObject<Block> NETHERRACK_SHINGLES = registerBlock("netherrack_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final RegistryObject<Block> OBSIDIAN_SHINGLES = registerBlock("obsidian_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> PURPUR_SHINGLES = registerBlock("purpur_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> RED_SANDSTONE_SHINGLES = registerBlock("red_sandstone_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE)));
    public static final RegistryObject<Block> GOLD_SHINGLES = registerBlock("gold_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get())));

    public static final RegistryObject<Block> OBSCURITE_COMPRESSED_BRICKS = registerBlock("obscurite_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.OBSCURITE.get())));
    public static final RegistryObject<Block> AMETHYST_COMPRESSED_BRICKS = registerBlock("amethyst_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> AZURINE_COMPRESSED_BRICKS = registerBlock("azurine_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(MOEBlock.AZURINE.get())));
    public static final RegistryObject<Block> BASALT_COMPRESSED_BRICKS = registerBlock("basalt_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final RegistryObject<Block> BLACKSTONE_COMPRESSED_BRICKS = registerBlock("blackstone_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> COPPER_COMPRESSED_BRICKS = registerBlock("copper_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> DARK_PRISMARINE_COMPRESSED_BRICKS = registerBlock("dark_prismarine_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)));
    public static final RegistryObject<Block> DIAMOND_COMPRESSED_BRICKS = registerBlock("diamond_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> DIORITE_COMPRESSED_BRICKS = registerBlock("diorite_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE)));
    public static final RegistryObject<Block> PACKED_MUD_COMPRESSED_BRICKS = registerBlock("packed_mud_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)));
    public static final RegistryObject<Block> DRIPSTONE_COMPRESSED_BRICKS = registerBlock("dripstone_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final RegistryObject<Block> EMERALD_COMPRESSED_BRICKS = registerBlock("emerald_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<Block> ENDSTONE_COMPRESSED_BRICKS = registerBlock("endstone_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final RegistryObject<Block> GRANITE_COMPRESSED_BRICKS = registerBlock("granite_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE)));
    public static final RegistryObject<Block> LAPIS_LAZULI_COMPRESSED_BRICKS = registerBlock("lapis_lazuli_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK)));
    public static final RegistryObject<Block> NETHERRACK_COMPRESSED_BRICKS = registerBlock("netherrack_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)));
    public static final RegistryObject<Block> OBSIDIAN_COMPRESSED_BRICKS = registerBlock("obsidian_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> PURPUR_COMPRESSED_BRICKS = registerBlock("purpur_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> RED_SANDSTONE_COMPRESSED_BRICKS = registerBlock("red_sandstone_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE)));
    public static final RegistryObject<Block> GOLD_COMPRESSED_BRICKS = registerBlock("gold_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> TUFF_COMPRESSED_BRICKS = registerBlock("tuff_compressed_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final RegistryObject<Block> TUFF_SQUARE_BRICKS = registerBlock("tuff_square_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final RegistryObject<RotatedPillarBlock> TUFF_TRIANGLE_BRICKS = registerBlock("tuff_triangle_bricks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final RegistryObject<Block> TUFF_SHINGLES = registerBlock("tuff_shingles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final RegistryObject<RotatedPillarBlock> OAK_CARVED_PLANKS = registerBlock("oak_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> SPRUCE_CARVED_PLANKS = registerBlock("spruce_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> ACACIA_CARVED_PLANKS = registerBlock("acacia_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> JUNGLE_CARVED_PLANKS = registerBlock("jungle_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> DARK_OAK_CARVED_PLANKS = registerBlock("dark_oak_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> MANGROVE_CARVED_PLANKS = registerBlock("mangrove_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> CHERRY_CARVED_PLANKS = registerBlock("cherry_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> BIRCH_CARVED_PLANKS = registerBlock("birch_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> WARPED_CARVED_PLANKS = registerBlock("warped_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<RotatedPillarBlock> CRIMSON_CARVED_PLANKS = registerBlock("crimson_carved_planks", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)));

    public static final RegistryObject<TDiode> T_DIODE = registerBlock("t_diode", () -> new TDiode(BlockBehaviour.Properties.ofFullCopy(Blocks.REPEATER)));
    public static final RegistryObject<ANDGate> AND_GATE = registerBlock("and_gate", () -> new ANDGate(BlockBehaviour.Properties.ofFullCopy(Blocks.REPEATER).noOcclusion()));

    public static final RegistryObject<XORGate> XOR_GATE = registerBlock("xor_gate", () -> new XORGate(BlockBehaviour.Properties.ofFullCopy(Blocks.REPEATER).noOcclusion()));

    public static final RegistryObject<ORGate> OR_GATE = registerBlock("or_gate", () -> new ORGate(BlockBehaviour.Properties.ofFullCopy(Blocks.REPEATER).noOcclusion()));

    public static final RegistryObject<MOEBrushableBlock> SUSPICIOUS_RED_SAND = registerBlock("suspicious_red_sand", () -> new MOEBrushableBlock(Blocks.RED_SAND, SoundEvents.BRUSH_SAND , SoundEvents.BRUSH_SAND_COMPLETED, BlockBehaviour.Properties.ofFullCopy(Blocks.SUSPICIOUS_SAND)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        MOEItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}