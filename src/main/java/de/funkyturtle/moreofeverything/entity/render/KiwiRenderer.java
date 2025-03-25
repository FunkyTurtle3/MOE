package de.funkyturtle.moreofeverything.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.entity.custom.KiwiEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KiwiRenderer extends MobRenderer<KiwiEntity, KiwiModel<KiwiEntity>> {
    public KiwiRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KiwiModel<>(pContext.bakeLayer(KiwiModel.LAYER_LOCATION)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(KiwiEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "textures/entity/kiwi/kiwi.png");
    }

    @Override
    public void render(KiwiEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pPoseStack.scale(0.75F, 0.75F, 0.75F);
        } else {
            pPoseStack.scale(1F, 1F, 1F);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}