package de.funkyturtle.moreofeverything.sounds;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class MOESoundTypes {
    public static final SoundType OBSCURITE = new SoundType(
      2.0F, 1.0F, MOESound.OBSCURITE_BREAK.get(), MOESound.OBSCURITE_STEP.get(), MOESound.OBSCURITE_PLACE.get(), SoundEvents.STONE_HIT, SoundEvents.STONE_FALL
    );
}
