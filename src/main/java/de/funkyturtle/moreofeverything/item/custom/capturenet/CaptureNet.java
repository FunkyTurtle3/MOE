package de.funkyturtle.moreofeverything.item.custom.capturenet;

import de.funkyturtle.moreofeverything.sounds.MOESound;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class CaptureNet extends Item implements DamageTypes{
    private Entity captured;
    private boolean isFull = false;
    public CaptureNet(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext tContext, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.moreofeverything.capture_net.description"));
        } else {
            tooltip.add(Component.translatable("tooltip.moreofeverything.capture_net"));
            if(isFull) {
                tooltip.add(Component.translatable("tooltip.moreofeverything.capture_net.caught"));
            }
        }
        super.appendHoverText(stack, tContext, tooltip, flag);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity entity, @NotNull InteractionHand hand) {
        if (!this.isFull && !player.getCommandSenderWorld().isClientSide() && entity.getType() == EntityType.BEE && !entity.isBaby()) {
            captured = entity;
            captured.discard();
            isFull = true;
            player.getCommandSenderWorld().playSeededSound(null, player.getX(), player.getY(), player.getZ(), MOESound.CATCH_ENTITY_IN_CAPTURE_NET.get(), SoundSource.PLAYERS,2.0f,    1.0f, 1);
            return InteractionResult.FAIL;
        }
        return InteractionResult.SUCCESS;
    }

    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide() && isFull) {
            isFull = false;
            if (this.captured.getType().spawn((ServerLevel)pContext.getLevel(), pContext.getItemInHand(), pContext.getPlayer(), getSpawnPos(pContext), MobSpawnType.MOB_SUMMONED, false, true) != null) {
                pContext.getLevel().gameEvent(captured, GameEvent.ENTITY_PLACE, pContext.getClickLocation());
            }
            captured.setPos(pContext.getClickLocation());
            damage(pContext);
            this.captured = null;
            Objects.requireNonNull(pContext.getPlayer()).getCommandSenderWorld().playSeededSound(null, pContext.getPlayer().getX(), pContext.getPlayer().getY(), pContext.getPlayer().getZ(), MOESound.CATCH_ENTITY_IN_CAPTURE_NET.get(), SoundSource.PLAYERS,2.0f,    1.0f, 1);
        }
        return InteractionResult.SUCCESS;
    }

    private void damage(UseOnContext pContext) {
        pContext.getItemInHand().setDamageValue(pContext.getItemInHand().getDamageValue() + 1);
        if(pContext.getItemInHand().getMaxDamage() == pContext.getItemInHand().getDamageValue()) {
            pContext.getLevel().playSeededSound(null, Objects.requireNonNull(pContext.getPlayer()).getX(), pContext.getPlayer().getY(), pContext.getPlayer().getZ(), MOESound.CATCH_ENTITY_IN_CAPTURE_NET.get(), SoundSource.PLAYERS, 4.0f, 1.0f, 1);
            pContext.getPlayer().setItemInHand(pContext.getHand(), new ItemStack(Items.AIR));
        }
    }

    private static BlockPos getSpawnPos(UseOnContext pContext) {
        BlockPos pos = new BlockPos(pContext.getClickedPos().getX(), pContext.getClickedPos().getY(), pContext.getClickedPos().getZ());
        if(pContext.getClickedFace() == Direction.NORTH) {
            pos = pos.north();
        } else if(pContext.getClickedFace() == Direction.SOUTH) {
            pos = pos.south();
        } else if(pContext.getClickedFace() == Direction.EAST) {
            pos = pos.east();
        } else if(pContext.getClickedFace() == Direction.WEST) {
            pos = pos.west();
        } else if(pContext.getClickedFace() == Direction.UP) {
            pos = pos.above();
        }else if(pContext.getClickedFace() == Direction.DOWN) {
            pos = pos.below();
        }
        return pos;
    }
}