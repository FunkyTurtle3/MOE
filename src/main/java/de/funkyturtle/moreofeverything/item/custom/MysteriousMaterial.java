package de.funkyturtle.moreofeverything.item.custom;

import de.funkyturtle.moreofeverything.component.MOEDataComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class MysteriousMaterial extends Item {

    private ItemStack insaneItem;
    private String component;

    public MysteriousMaterial(Properties pProperties, ItemStack insaneItem, String component) {
        super(pProperties.rarity(Rarity.RARE).stacksTo(1).component(MOEDataComponentTypes.LOCKED.get(), Boolean.FALSE).durability(16));
        this.insaneItem = insaneItem;
        this.component = component;
    }

    public ItemStack getInsaneItem() {
        return insaneItem;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable(component));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.moreofeverything.aqua_shift_text"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}