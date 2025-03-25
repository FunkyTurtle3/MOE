package de.funkyturtle.moreofeverything.item.custom.experiencephiole;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ExperiencePhiole extends Item {
    private final int maxUses;
    private final int steps;
    private int contains;
    public ExperiencePhiole(Properties properties) {
        this(properties, 100, 0, 10);
    }

    public ExperiencePhiole(Properties properties, int i) {
        this(properties, i, 0, 10);
    }

    public ExperiencePhiole(Properties properties, int use, int con) {
        this(properties, use, con, 10);
    }

    public ExperiencePhiole(Properties properties, int use, int con, int step) {
        super(properties);
        maxUses = use;
        contains = con;
        steps = step;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(!level.isClientSide()) {
            if(Screen.hasShiftDown() && player.totalExperience >= maxUses - contains) {
                player.giveExperiencePoints( -(maxUses-contains));
                contains = maxUses;
            } else if(Screen.hasShiftDown()) {
                player.giveExperiencePoints( - player.totalExperience);
                contains = contains + player.totalExperience;
            } else if(player.totalExperience >= steps && maxUses - steps >= contains) {
                player.giveExperiencePoints(-steps);
                contains = contains + steps;
            } else if(Screen.hasControlDown() && Screen.hasShiftDown() || contains >= steps) {
                player.totalExperience = player.totalExperience + contains;
                contains = 0;
            } else if(Screen.hasControlDown() && contains >= steps) {
                player.totalExperience = player.totalExperience + steps;
                contains = contains - steps;
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
