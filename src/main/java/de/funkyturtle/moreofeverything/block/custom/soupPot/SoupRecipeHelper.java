package de.funkyturtle.moreofeverything.block.custom.soupPot;

import de.funkyturtle.moreofeverything.item.MOEItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class SoupRecipeHelper {

    private static Item[][] item;
    public static Item[][] registerRecipes() {
        item = new Item[10][4];
        item[0][0] = Items.POPPY;
        item[0][1] = Items.BAMBOO;
        item[0][2] = Items.POTATO;
        item[0][3] = Items.COAL;

        item[1][0] = Items.BROWN_MUSHROOM;
        item[1][1] = Items.KELP;
        item[1][2] = Items.BEETROOT;
        item[1][3] = Items.RAW_IRON;

        item[2][0] = Items.BROWN_MUSHROOM;
        item[2][1] = Items.PITCHER_POD;
        item[2][2] = Items.SWEET_BERRIES;
        item[2][3] = Items.RAW_COPPER;

        item[3][0] = Items.LILAC;
        item[3][1] = Items.BIG_DRIPLEAF;
        item[3][2] = Items.GLOW_BERRIES;
        item[3][3] = Items.RAW_GOLD;

        item[4][0] = Items.BIRCH_SAPLING;
        item[4][1] = Items.SEAGRASS;
        item[4][2] = Items.CARROT;
        item[4][3] = Items.REDSTONE;

        item[5][0] = Items.RED_TULIP;
        item[5][1] = Items.SUGAR_CANE;
        item[5][2] = Items.MELON_SLICE;
        item[5][3] = Items.EMERALD;

        item[6][0] = Items.PITCHER_PLANT;
        item[6][1] = Items.SEA_PICKLE;
        item[6][2] = Items.DRIED_KELP;
        item[6][3] = Items.LAPIS_LAZULI;

        item[7][0] = Items.TORCHFLOWER;
        item[7][1] = Items.VINE;
        item[7][2] = Items.APPLE;
        item[7][3] = Items.DIAMOND;

        item[8][0] = Items.CHERRY_SAPLING;
        item[8][1] = Items.LILY_PAD;
        item[8][2] = Items.CHORUS_FRUIT;
        item[8][3] = Items.QUARTZ;

        item[9][0] = Items.WITHER_ROSE;
        item[9][1] = Items.SEAGRASS;
        item[9][2] = Items.GOLDEN_APPLE;
        item[9][3] = Items.NETHERITE_SCRAP;
        return item;
    }

    public static String getItem(int x, int y) {
        return item[x][y].toString().substring(10);
    }

    public static String getStringForIndex(int x, int y) {
        if(!(item[x] [y] instanceof BlockItem)) {
            return "item/" + item[x] [y].toString().substring(10);
        } else {
            return "block/" + item[x] [y].toString().substring(10);
        }
    }
    /**public static int getId(Item item) {
        if (item == MOEItem.MYSTICAL_SOUP_RECIPE.get()) {
            return 0;
        }
        else if (item == MOEItem.ENIGMATIC_SOUP_RECIPE.get()) {
            return 1;
        }
        else if (item == MOEItem.ILLUSIVE_SOUP_RECIPE.get()) {
            return 2;
        }
        else if (item == MOEItem.BEWITCHING_SOUP_RECIPE.get()) {
            return 3;
        }
        else if (item == MOEItem.SPELLBINDIG_SOUP_RECIPE.get()) {
            return 4;
        }
        else if (item == MOEItem.ARCANE_SOUP_RECIPE.get()) {
            return 5;
        }
        else if (item == MOEItem.SORCEROUS_SOUP_RECIPE.get()) {
            return 6;
        }
        else if (item == MOEItem.ELDRITCH_SOUP_RECIPE.get()) {
            return 7;
        }
        else if (item == MOEItem.HAUNTING_SOUP_RECIPE.get()) {
            return 8;
        }
        else if (item == MOEItem.INEFFABLE_SOUP_RECIPE.get()){
            return 9;
        } else {
            return -1;
        }
    }*/
}
