package de.funkyturtle.moreofeverything.util;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MOETag {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ANCIENT_TOOL = createTag("needs_ancient_tool");
        public static final TagKey<Block> INCORRECT_FOR_ANCIENT_TOOL = createTag("incorrect_for_ancient_tool");
        public static final TagKey<Block> OBSCURITE_FULL_BLOCKS = createTag("obscurite_full_blocks");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CAN_HAVE_AUTO_SMELT = createTag("can_have_auto_smelt");
        public static final TagKey<Item> MYSTERIOUS_MATERIAL = createTag("mysterious_material");
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, name));
        }
    }
}
