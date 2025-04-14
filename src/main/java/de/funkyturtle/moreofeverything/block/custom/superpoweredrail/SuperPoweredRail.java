package de.funkyturtle.moreofeverything.block.custom.superpoweredrail;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBaseRailBlock;

public class SuperPoweredRail extends PoweredRailBlock implements IForgeBaseRailBlock {
    private final boolean isActivator;  // TRUE for an Activator Rail, FALSE for Powered Rail

    public SuperPoweredRail(Properties properties, boolean isPoweredRail) {
        super(properties);
        this.isActivator = !isPoweredRail;
    }
    public boolean isActivatorRail() {
        return isActivator;
    }
    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        if (cart instanceof MinecartFurnace) return cart.isInWater() ? 0.3f : 0.6f;
        else return cart.isInWater() ? 1f : 0.8f;
    }
}