package de.funkyturtle.moreofeverything.item;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.component.MOEDataComponentTypes;
import de.funkyturtle.moreofeverything.entity.MOEEntity;
import de.funkyturtle.moreofeverything.item.custom.Sapphire;
import de.funkyturtle.moreofeverything.item.custom.capturenet.CaptureNet;
import de.funkyturtle.moreofeverything.sounds.MOESound;
import de.funkyturtle.moreofeverything.util.MOEMath;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MOEItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create( ForgeRegistries.ITEMS, MoreOfEverything.MOD_ID);

    public static final RegistryObject<Item> CAPTURE_NET = ITEMS.register("capture_net", () -> new CaptureNet(new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON)
            .durability(8)
    ));

    //public static final RegistryObject<Item> MYSTICAL_SOUP_RECIPE = ITEMS.register("mystical_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON), 0));
    //public static final RegistryObject<Item> ENIGMATIC_SOUP_RECIPE = ITEMS.register("enigmatic_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON), 1));
    //public static final RegistryObject<Item> ILLUSIVE_SOUP_RECIPE = ITEMS.register("illusive_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON), 2));
    //public static final RegistryObject<Item> BEWITCHING_SOUP_RECIPE = ITEMS.register("bewitching_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 3));
    //public static final RegistryObject<Item> SPELLBINDIG_SOUP_RECIPE = ITEMS.register("spellbinding_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 4));
    //public static final RegistryObject<Item> ARCANE_SOUP_RECIPE = ITEMS.register("arcane_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 5));
    //public static final RegistryObject<Item> SORCEROUS_SOUP_RECIPE = ITEMS.register("sorcerous_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 6));
    //public static final RegistryObject<Item> ELDRITCH_SOUP_RECIPE = ITEMS.register("eldritch_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 7));
    //public static final RegistryObject<Item> HAUNTING_SOUP_RECIPE = ITEMS.register("haunting_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 8));
    //public static final RegistryObject<Item> INEFFABLE_SOUP_RECIPE = ITEMS.register("ineffable_soup_recipe", () -> new SoupRecipeItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 9));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> WEATHERED_COPPER_NUGGET = ITEMS.register("weathered_copper_nugget", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> EXPOSED_COPPER_NUGGET = ITEMS.register("exposed_copper_nugget", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> OXIDIZED_COPPER_NUGGET = ITEMS.register("oxidized_copper_nugget", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> ANCIENT_DAGGER = ITEMS.register( "ancient_dagger", () -> new SwordItem(MOETiers.ANCIENT_TIER, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(512)
            .attributes(SwordItem.createAttributes(MOETiers.ANCIENT_TIER, 2, -1F))));
    public static final RegistryObject<Item> ANCIENT_PICKAXE = ITEMS.register( "ancient_pickaxe", () -> new PickaxeItem(MOETiers.ANCIENT_TIER, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(512)
            .attributes(SwordItem.createAttributes(MOETiers.ANCIENT_TIER, 0, -2.6F))) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext tContext, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(Component.translatable("tooltip.moreofeverything.ancient_pickaxe.description"));
                    } else {
                        tooltip.add(Component.translatable("tooltip.moreofeverything.gold_shift_text"));
                    }
                    super.appendHoverText(stack, tContext, tooltip, flag);
                }
            }
        );
    public static final RegistryObject<Item> ANCIENT_AXE = ITEMS.register( "ancient_axe", () -> new AxeItem(MOETiers.ANCIENT_TIER, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(512)
            .attributes(SwordItem.createAttributes(MOETiers.ANCIENT_TIER, 2, -1F))){
        @Override
        public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext tContext, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("tooltip.moreofeverything.ancient_axe.description"));
            } else {
                tooltip.add(Component.translatable("tooltip.moreofeverything.gold_shift_text"));
            }
            super.appendHoverText(stack, tContext, tooltip, flag);
        }
    });
    public static final RegistryObject<Item> ANCIENT_SHOVEL = ITEMS.register( "ancient_shovel", () -> new ShovelItem(MOETiers.ANCIENT_TIER, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(512)
            .attributes(SwordItem.createAttributes(MOETiers.ANCIENT_TIER, 2, -1F))) {
        @Override
        public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext tContext, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("tooltip.moreofeverything.ancient_shovel.description"));
            } else {
                tooltip.add(Component.translatable("tooltip.moreofeverything.gold_shift_text"));
            }
            super.appendHoverText(stack, tContext, tooltip, flag);
        }
    });
    public static final RegistryObject<Item> ANCIENT_HOE = ITEMS.register( "ancient_hoe", () -> new HoeItem(MOETiers.ANCIENT_TIER, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(512)
            .attributes(HoeItem.createAttributes(MOETiers.ANCIENT_TIER, -3, 0.0F))) {
        @Override
        public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext tContext, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("tooltip.moreofeverything.ancient_hoe.description"));
            } else {
                tooltip.add(Component.translatable("tooltip.moreofeverything.gold_shift_text"));
            }
            super.appendHoverText(stack, tContext, tooltip, flag);
        }
    });
    public static final RegistryObject<Item> ANCIENT_SHERD = ITEMS.register("ancient_sherd", () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> WEATHERED_COPPER_INGOT = ITEMS.register("weathered_copper_ingot", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> EXPOSED_COPPER_INGOT = ITEMS.register("exposed_copper_ingot", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> OXIDIZED_COPPER_INGOT = ITEMS.register("oxidized_copper_ingot", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BAKED_CARROT = ITEMS.register("baked_carrot", () -> new Item(new Item.Properties().food(MOEFood.BAKED_CARROT)));
    public static final RegistryObject<Item> BAKED_BEETROOT = ITEMS.register("baked_beetroot", () -> new Item(new Item.Properties().food(MOEFood.BAKED_BEETROOT)));
    public static final RegistryObject<Item> GOLDEN_POTATO = ITEMS.register("golden_potato", () -> new Item(new Item.Properties().food(MOEFood.GOLDEN_POTATO)));
    public static final RegistryObject<Item> GOLDEN_BEETROOT = ITEMS.register("golden_beetroot", () -> new Item(new Item.Properties().food(MOEFood.GOLDEN_BEETROOT)));
    public static final RegistryObject<Item> GOLDEN_KELP = ITEMS.register("golden_kelp", () -> new Item(new Item.Properties().food(MOEFood.GOLDEN_KELP)));
    public static final RegistryObject<Item> SHRIMP = ITEMS.register("shrimp", () -> new Item(new Item.Properties().food(MOEFood.SHRIMP)));

    public static final RegistryObject<Item> PULSITE = ITEMS.register("pulsite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Sapphire> SAPPHIRE = ITEMS.register("sapphire", () -> new Sapphire(new Item.Properties().component(MOEDataComponentTypes.WATER_LEVEL.get(), 0)));

    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties().component(MOEDataComponentTypes.MATERIAL_DAMAGE.get(), 4)));
    public static final RegistryObject<Item> AZURINE_DUST = ITEMS.register("azurine_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADVENTURERS_COMPASS = ITEMS.register("adventurers_compass", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMONITE = ITEMS.register("ammonite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PREHISTORIC_SPEER_TIP = ITEMS.register("prehistoric_speer_tip", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MEDIEVAL_COIN = ITEMS.register("medieval_coin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_1 = ITEMS.register("mysterious_material_1", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_2 = ITEMS.register("mysterious_material_2", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_3 = ITEMS.register("mysterious_material_3", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_4 = ITEMS.register("mysterious_material_4", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_5 = ITEMS.register("mysterious_material_5", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_6 = ITEMS.register("mysterious_material_6", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_7 = ITEMS.register("mysterious_material_7", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_8 = ITEMS.register("mysterious_material_8", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_9 = ITEMS.register("mysterious_material_9", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> MYSTERIOUS_MATERIAL_10 = ITEMS.register("mysterious_material_10", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16)));
    public static final RegistryObject<Item> BOTTLE_OF_STABILITY_ESSENCE = ITEMS.register("bottle_of_stability_essence", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1).component(MOEDataComponentTypes.MATERIAL_DAMAGE.get(), 4).durability(32)));
    public static final RegistryObject<Item> ARCANE_BOTTLE_OF_STABILITY_ESSENCE = ITEMS.register("arcane_bottle_of_stability_essence", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.MATERIAL_DAMAGE.get(), 2).durability(48)));
    public static final RegistryObject<Item> SORCEROUS_BOTTLE_OF_STABILITY_ESSENCE = ITEMS.register("sorcerous_bottle_of_stability_essence", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).component(MOEDataComponentTypes.MATERIAL_DAMAGE.get(), 1).durability(64)));

    public static final RegistryObject<Item> NO_TIME_TO_WAIT_DISC = ITEMS.register("no_time_to_wait_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(MOESound.NO_TIME_TO_WAIT_KEY).stacksTo(1).rarity(Rarity.RARE)));

    //public static final RegistryObject<Item> SCULK_PHIOLE = ITEMS.register("sculk_phiole", () -> new ExperiencePhiole(new Item.Properties().stacksTo(1), 1000, 0, 10));
    public static final RegistryObject<Item> ANCIENT_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("ancient_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "ancient")));

    public static final RegistryObject<BrushItem> IRON_BRUSH = ITEMS.register("iron_brush", () -> new BrushItem(new Item.Properties().durability(96)));
    public static final RegistryObject<BrushItem> GOLD_BRUSH = ITEMS.register("gold_brush", () -> new BrushItem(new Item.Properties().durability(32)));
    public static final RegistryObject<BrushItem> DIAMOND_BRUSH = ITEMS.register("diamond_brush", () -> new BrushItem(new Item.Properties().durability(192)));
    public static final RegistryObject<Item> CAUTIOUSTAL = ITEMS.register("cautioustal", () -> new Item(new Item.Properties().component(MOEDataComponentTypes.BRUSH_DAMAGE.get(), 8).rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final RegistryObject<Item> BEWITCHING_CAUTIOUSTAL = ITEMS.register("bewitching_cautioustal", () -> new Item(new Item.Properties().component(MOEDataComponentTypes.BRUSH_DAMAGE.get(), 6).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> ELDRITCH_CAUTIOUSTAL = ITEMS.register("eldritch_cautioustal", () -> new Item(new Item.Properties().component(MOEDataComponentTypes.BRUSH_DAMAGE.get(), 4).rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<Item> ORB_OF_LUCK = ITEMS.register("orb_of_luck", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ORB_OF_COMPLETION = ITEMS.register("orb_of_completion", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<SpawnEggItem> KIWI_SPAWN_EGG = ITEMS.register("kiwi_spawn_egg", () -> new ForgeSpawnEggItem(MOEEntity.KIWI, 0x50362c, 0xa4774c, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}