package de.funkyturtle.moreofeverything.enchantment;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.util.MOETag;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;

public class MOEEnchantments {
    public static final Holder<Enchantment> AUTO_SMELT = (Holder<Enchantment>) ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "auto_smelt"));

    /**public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, AUTO_SMELT, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(MOETag.Items.CAN_HAVE_AUTO_SMELT),
                items.getOrThrow(ItemTags.PICKAXES),
                5,
                1,
                Enchantment.dynamicCost(5,8),
                Enchantment.dynamicCost(25,8),
                2,
                EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE))
        );
    } */

    private static void register(BootstrapContext<Enchantment> p_345097_, ResourceKey<Enchantment> p_342560_, Enchantment.Builder p_344763_) {
        p_345097_.register(p_342560_, p_344763_.build(p_342560_.location()));
    }
    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, name));
    }
}