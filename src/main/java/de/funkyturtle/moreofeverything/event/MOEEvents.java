package de.funkyturtle.moreofeverything.event;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.item.MOEItem;
import de.funkyturtle.moreofeverything.villager.MOEVillager;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Optional;


public class MOEEvents {
    @Mod.EventBusSubscriber(modid = MoreOfEverything.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void blockBreakingEvent(BlockEvent.BreakEvent event) {
            if(Math.random() < 0.0006666666666) {
                event.getLevel().addFreshEntity(new ItemEntity((Level) event.getLevel(), event.getPos().getX() + 0.5, event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, new ItemStack(MOEItem.STRANGE_MATTER.get())));
            }

            if (event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).is(MOEItem.ANCIENT_SHOVEL.get()) && event.getLevel().getBlockState(event.getPos()).getBlock() instanceof BrushableBlock) {
                ((BrushableBlockEntity) event.getLevel().getBlockEntity(event.getPos())).unpackLootTable(event.getPlayer());
                event.getLevel().addFreshEntity(new ItemEntity((Level) event.getLevel(), event.getPos().getX() + 0.5, event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, ((BrushableBlockEntity) event.getLevel().getBlockEntity(event.getPos())).getItem()));
            }
        }

        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event) {
            if(event.getType() == MOEVillager.APIARIST_VILLAGER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.POPPY, 8),
                        new ItemStack(Items.EMERALD, 1),
                        15, 5, 0.02f
                ));
                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 4),
                        new ItemStack(Items.HONEYCOMB, 3),
                        10, 5, 0.02f
                ));
                trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.CORNFLOWER, 14),
                        16, 7, 0.04f
                ));
                trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.CORNFLOWER, 14),
                        16, 7, 0.04f
                ));
                trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.CAMPFIRE, 1),
                        new ItemStack(Items.EMERALD, 2),
                        10, 9, 0.05f
                ));
                trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.HONEY_BOTTLE, 1),
                        10, 9, 0.05f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.BEEHIVE, 1),
                        10, 11, 0.06f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.GLASS_BOTTLE, 5),
                        new ItemStack(Items.EMERALD, 1),
                        15, 9, 0.06f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 3),
                        new ItemStack(Items.HONEY_BLOCK, 1),
                        7, 12, 0.07f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 6),
                        new ItemStack(MOEItem.CAPTURE_NET.get(), 1),
                        5, 20, 0.1f
                ));
            } else if(event.getType() == MOEVillager.ARCHAEOLOGIST_VILLAGER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.BRUSH, 1),
                        6, 5, 0.02f
                ));
                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.GRAVEL, 14),
                        new ItemStack(Items.EMERALD, 1),
                        10, 5, 0.02f
                ));
                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.SAND, 14),
                        new ItemStack(Items.EMERALD, 1),
                        10, 5, 0.02f
                ));
                trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.GUNPOWDER, 4),
                        new ItemStack(Items.EMERALD, 1),
                        11, 7, 0.04f
                ));
                trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.MUSIC_DISC_RELIC, 1),
                        new ItemStack(Items.EMERALD, 12),
                        5, 7, 0.05f
                ));
                trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.SUSPICIOUS_SAND, 3),
                        10, 9, 0.05f
                ));
                trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.SUSPICIOUS_GRAVEL, 3),
                        10, 9, 0.05f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.SHEAF_POTTERY_SHERD, 1),
                        new ItemStack(Items.EMERALD, 1),
                        5, 7, 0.06f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.SHELTER_POTTERY_SHERD, 1),
                        new ItemStack(Items.EMERALD, 1),
                        5, 7, 0.06f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.BREWER_POTTERY_SHERD, 1),
                        new ItemStack(Items.EMERALD, 1),
                        5, 7, 0.06f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.SNORT_POTTERY_SHERD, 1),
                        new ItemStack(Items.EMERALD, 1),
                        5, 7, 0.06f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.ARCHER_POTTERY_SHERD, 1),
                        new ItemStack(Items.EMERALD, 1),
                        5, 7, 0.06f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 18),
                        new ItemStack(Items.SNIFFER_EGG, 1),
                        3, 15, 0.06f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(MOEItem.ANCIENT_SHERD.get(), 3),
                        Optional.of(new ItemCost(Items.EMERALD, 11)),
                        new ItemStack(MOEItem.ANCIENT_DAGGER.get(), 1),
                        4, 9, 0.06f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(MOEItem.ANCIENT_SHERD.get(), 5),
                        Optional.of(new ItemCost(Items.EMERALD, 11)),
                        new ItemStack(MOEItem.ANCIENT_PICKAXE.get(), 1),
                        4, 9, 0.06f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(MOEItem.ANCIENT_SHERD.get(), 5),
                        Optional.of(new ItemCost(Items.EMERALD, 11)),
                        new ItemStack(MOEItem.ANCIENT_AXE.get(), 1),
                        4, 9, 0.06f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(MOEItem.ANCIENT_SHERD.get(), 3),
                        Optional.of(new ItemCost(Items.EMERALD, 11)),
                        new ItemStack(MOEItem.ANCIENT_SHOVEL.get(), 1),
                        4, 9, 0.06f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(MOEItem.ANCIENT_SHERD.get(), 4),
                        Optional.of(new ItemCost(Items.EMERALD, 11)),
                        new ItemStack(MOEItem.ANCIENT_HOE.get(), 1),
                        4, 9, 0.06f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 9),
                        new ItemStack(MOEItem.ANCIENT_ARMOR_TRIM_SMITHING_TEMPLATE.get(), 1),
                        1, 15, 0.0f
                ));
            } else if(event.getType() == MOEVillager.MINER_VILLAGER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.COPPER_INGOT, 12),
                new ItemStack(Items.EMERALD, 3),
                8, 3, 0.04f
                ));
                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 5),
                        new ItemStack(Items.MINECART, 1),
                        41, 2, 0.02f
                ));
                trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 1),
                        new ItemStack(Items.TORCH, 6),
                        16, 2, 0.0f
                ));
                trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.COBBLESTONE, 64),
                        new ItemStack(Items.EMERALD, 1),
                        5, 1, 0.0f
                ));
                trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 4),
                        new ItemStack(Items.LAVA_BUCKET, 1),
                        4, 6, 0.08f
                ));
                trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 3),
                        new ItemStack(Items.IRON_INGOT, 1),
                        16, 8, 0.01f
                ));
                trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 9),
                        new ItemStack(Items.GOLD_INGOT, 1),
                        8, 10, 0.08f
                ));
                trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 2),
                        new ItemStack(Items.POINTED_DRIPSTONE, 5),
                        14, 12, 0.07f
                ));
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.COBBLED_DEEPSLATE, 48),
                        new ItemStack(Items.EMERALD, 1),
                        7, 8, 0.0f
                ));trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 3),
                        new ItemStack(Items.COAL, 8),
                        11, 16, 0.08f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.NETHERITE_SCRAP, 3),
                        Optional.of(new ItemCost(Items.GOLD_INGOT, 43)),
                        new ItemStack(Items.NETHERITE_INGOT, 1),
                        2, 18, 0.0f
                ));
                trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 1),
                        new ItemStack(MOEBlock.OBSCURITE.get(), 16),
                        8, 12, 0.06f
                ));
            }
        }
        @Mod.EventBusSubscriber(modid = MoreOfEverything.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ModEventBusEvents {
            @SubscribeEvent
            public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            }
        }
    }
}