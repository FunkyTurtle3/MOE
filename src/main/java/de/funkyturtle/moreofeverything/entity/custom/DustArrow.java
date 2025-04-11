package de.funkyturtle.moreofeverything.entity.custom;

import de.funkyturtle.moreofeverything.entity.MOEEntity;
import de.funkyturtle.moreofeverything.util.MOEMath;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DustArrow extends AbstractArrow {
    private int particleDurationInTicks = 300;

    public DustArrow(EntityType<? extends DustArrow> dustArrowEntityType, Level level) {
        super(dustArrowEntityType, level);
    }

    public DustArrow(Level pLevel, LivingEntity pOwner, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(MOEEntity.DUST_ARROW.get(), pOwner, pLevel, pPickupItemStack, pFiredFromWeapon);
    }

    public DustArrow(Level pLevel, double pX, double pY, double pZ, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(MOEEntity.DUST_ARROW.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGround && this.particleDurationInTicks > 0) {
            for (int i = 0; i < 15; i++) {
                this.level().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, this.getX() + MOEMath.getRandomRangedDouble(-2.5, 2.5), this.getY() + MOEMath.getRandomRangedDouble(0, 2.5), this.getZ() + MOEMath.getRandomRangedDouble(-2.5, 2.5), MOEMath.getRandomRangedDouble(-0.02, 0.02), MOEMath.getRandomRangedDouble(0, 0.02), MOEMath.getRandomRangedDouble(-0.02, 0.02));
            }
            particleDurationInTicks--;
        } else if (this.inGround) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.AIR);
    }
}