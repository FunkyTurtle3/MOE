package de.funkyturtle.moreofeverything.event;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.block.MOEBlock;
import de.funkyturtle.moreofeverything.block.blockentity.custom.MOEBrushableBlockEntity;
import de.funkyturtle.moreofeverything.item.MOEItem;
import de.funkyturtle.moreofeverything.villager.MOEVillager;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
        public static void checkForArmor(LivingEquipmentChangeEvent event) {
            LivingEntity entity = event.getEntity();
            entity.removeEffect(MobEffects.GLOWING);
            entity.setCustomName(Component.empty());
            entity.setCustomNameVisible(false);
            entity.removeEffect(MobEffects.WATER_BREATHING);
            entity.removeEffect(MobEffects.DIG_SPEED);
            entity.removeEffect(MobEffects.HERO_OF_THE_VILLAGE);
            entity.removeEffect(MobEffects.MOVEMENT_SPEED);

            if (entity.getItemBySlot(EquipmentSlot.HEAD).get(DataComponents.CUSTOM_DATA) != null) {
                String material = entity.getItemBySlot(EquipmentSlot.HEAD).get(DataComponents.CUSTOM_DATA).copyTag().get("moreofeverything.gem_upgrade").getAsString();
                if (material.equals("ametrine")) {
                    String display = entity.getItemBySlot(EquipmentSlot.HEAD).getDisplayName().getString();
                    entity.setCustomName(Component.literal(display.substring(1, display.length() - 1)));
                    entity.setCustomNameVisible(true);
                }
                if(material.contains("sapphire")) {
                    entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, -1, 0, false, false, false));
                }
            }

            if(entity.getItemBySlot(EquipmentSlot.CHEST).get(DataComponents.CUSTOM_DATA) != null) {
                String material = entity.getItemBySlot(EquipmentSlot.CHEST).get(DataComponents.CUSTOM_DATA).copyTag().get("moreofeverything.gem_upgrade").getAsString();
                if(material.contains("ruby")) {
                    entity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, -1, 0, false, false, false));
                } else entity.removeEffect(MobEffects.HEALTH_BOOST);
                if(material.contains("pulsite")) {
                    entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, -1, 0, false, false, false));
                }
            } else entity.removeEffect(MobEffects.HEALTH_BOOST);

            if(entity.getItemBySlot(EquipmentSlot.LEGS).get(DataComponents.CUSTOM_DATA) != null) {
                String material = entity.getItemBySlot(EquipmentSlot.LEGS).get(DataComponents.CUSTOM_DATA).copyTag().get("moreofeverything.gem_upgrade").getAsString();
                if(material.contains("amber")) {
                    entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, -1, 0, false, false, false));
                }
                if(material.contains("jade")) {
                    entity.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, -1, 0, false, false, false));
                }
            }

            if(entity.getItemBySlot(EquipmentSlot.FEET).get(DataComponents.CUSTOM_DATA) != null) {
                String material = entity.getItemBySlot(EquipmentSlot.FEET).get(DataComponents.CUSTOM_DATA).copyTag().get("moreofeverything.gem_upgrade").getAsString();
                if(material.contains("opal")) {
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0, false, false, false));
                }
            }
        }

        @SubscribeEvent
        public static void registerBrush(PlayerInteractEvent.RightClickBlock event) {
            if (!event.getLevel().isClientSide() && event.getLevel().getBlockEntity(event.getPos()) instanceof MOEBrushableBlockEntity moeBrushableBlockEntity) {
                boolean flag1 = moeBrushableBlockEntity.brush(event.getLevel().getGameTime(), event.getEntity(),  event.getHitVec().getDirection());
                if (flag1) {
                    EquipmentSlot equipmentslot = event.getItemStack().equals(event.getEntity().getItemBySlot(EquipmentSlot.OFFHAND))
                            ? EquipmentSlot.OFFHAND
                            : EquipmentSlot.MAINHAND;
                    event.getItemStack().hurtAndBreak(1, event.getEntity(), equipmentslot);
                }
            }
        }

        @SubscribeEvent
        public static void showGemUpgrade(ItemTooltipEvent event) {
            if (event.getItemStack().is(Tags.Items.ARMORS_HELMETS) && event.getItemStack().get(DataComponents.CUSTOM_DATA) != null) {
                String material = event.getItemStack().get(DataComponents.CUSTOM_DATA).copyTag().getString("moreofeverything.gem_upgrade");
                List<Component> tooltip = event.getToolTip();
                if (material.equals("sapphire")) {
                    addTooltip(tooltip, "tooltip.moreofeverything.sapphire_des");
                } else if (material.equals("ametrine")) {
                    addTooltip(tooltip, "tooltip.moreofeverything.ametrine_des");
                }
            }
            if (event.getItemStack().is(Tags.Items.ARMORS_CHESTPLATES) && event.getItemStack().get(DataComponents.CUSTOM_DATA) != null) {
                String material = event.getItemStack().get(DataComponents.CUSTOM_DATA).copyTag().getString("moreofeverything.gem_upgrade");
                List<Component> tooltip = event.getToolTip();
                if (material.equals("ruby")) {
                    addTooltip(tooltip, "tooltip.moreofeverything.ruby_des");
                } else if (material.equals("pulsite")) {
                    addTooltip(tooltip, "tooltip.moreofeverything.pulsite_des");
                }
            }
            if (event.getItemStack().is(Tags.Items.ARMORS_LEGGINGS) && event.getItemStack().get(DataComponents.CUSTOM_DATA) != null) {
                String material = event.getItemStack().get(DataComponents.CUSTOM_DATA).copyTag().getString("moreofeverything.gem_upgrade");
                List<Component> tooltip = event.getToolTip();
                if (material.equals("amber")) {
                    addTooltip(tooltip, "tooltip.moreofeverything.amber_des");
                } else if (material.equals("jade")) {
                    addTooltip(tooltip, "tooltip.moreofeverything.jade_des");
                }
            }
            if (event.getItemStack().is(Tags.Items.ARMORS_BOOTS) && event.getItemStack().get(DataComponents.CUSTOM_DATA) != null) {
                String material = event.getItemStack().get(DataComponents.CUSTOM_DATA).copyTag().getString("moreofeverything.gem_upgrade");
                List<Component> tooltip = event.getToolTip();
                if (material.equals("opal")) {
                    addTooltip(tooltip, "tooltip.moreofeverything.opal_des");
                }
            }

        }

        public static void addTooltip(List<Component> tooltip ,String description) {
            tooltip.add(tooltip.size() - 2, Component.empty());
            tooltip.add(tooltip.size() - 2 ,Component.translatable(description));
            tooltip.add(tooltip.size() - 2, Component.empty());
        }

        @SubscribeEvent
        public static void onRightClickHold(LivingEntityUseItemEvent.Tick event) {
            // Prüfen, ob der Spieler das richtige Item benutzt
            if (event.getEntity() instanceof Player player && event.getItem().getItem() instanceof BrushItem) {

                // Prüfen, ob der Spieler mit dem richtigen Block interagiert
                HitResult hitResult =  player.pick(5.0D, 0.0F, false);
                BlockPos pos;
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                    pos = blockHitResult.getBlockPos(); // BlockPos wird gesetzt
                } else {
                    return; // Wenn es kein Block ist, verlässt du die Methode
                }
                BlockState blockState = player.level().getBlockState(pos); // z.B. block unter dem Spieler (kannst du ändern)

                // Hier prüfen, ob der Block Brushable ist
                if (blockState.getBlock() == MOEBlock.SUSPICIOUS_RED_SAND.get()) {

                    // Holen des BlockEntitys an der Position
                    if (player.level().getBlockEntity(pos) instanceof MOEBrushableBlockEntity moeBrushableBlockEntity) {

                        // Aktionen durchführen, während der Spieler den Rechtsklick gedrückt hält
                        boolean flag1 = moeBrushableBlockEntity.brush(player.level().getGameTime(), player, event.getEntity().getDirection());

                        if (flag1) {
                            // Item im Slot beschädigen
                            EquipmentSlot equipmentSlot = player.getMainHandItem().equals(event.getItem())
                                    ? EquipmentSlot.MAINHAND
                                    : EquipmentSlot.OFFHAND;
                            event.getItem().hurtAndBreak(1, player, equipmentSlot);
                        }
                    }
                }
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