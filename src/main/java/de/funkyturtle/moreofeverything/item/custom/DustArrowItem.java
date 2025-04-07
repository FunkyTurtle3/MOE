package de.funkyturtle.moreofeverything.item.custom;

import de.funkyturtle.moreofeverything.entity.custom.DustArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;

import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DustArrowItem extends ArrowItem {
    public DustArrowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level p_43237_, ItemStack p_43238_, @NotNull LivingEntity p_43239_, @Nullable ItemStack p_344301_) {
        return new DustArrow(p_43237_, p_43239_, ItemStack.EMPTY, p_344301_);
    }

    @Override
    public @NotNull Projectile asProjectile(@NotNull Level p_331476_, Position p_329787_, ItemStack p_328274_, @NotNull Direction p_330256_) {
        DustArrow dustArrow = new DustArrow(p_331476_, p_329787_.x(), p_329787_.y(), p_329787_.z(), ItemStack.EMPTY, null);
        dustArrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        return dustArrow;
    }
}