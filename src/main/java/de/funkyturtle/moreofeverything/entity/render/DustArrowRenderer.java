package de.funkyturtle.moreofeverything.entity.render;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.entity.custom.DustArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DustArrowRenderer extends ArrowRenderer<DustArrow> {
    public static final ResourceLocation DUST_ARROW_LOCATION = ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID,"textures/entity/projectiles/dust_arrow.png");

    public DustArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DustArrow pEntity) {
        return DUST_ARROW_LOCATION;
    }
}
