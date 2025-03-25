package de.funkyturtle.moreofeverything.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import de.funkyturtle.moreofeverything.block.custom.soupPot.SoupRecipeHelper;
import de.funkyturtle.moreofeverything.item.MOEItem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SoupRecipeScreen extends Screen {
    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath("moreofeverything","textures/gui/overlay/bg_old.png");
    private static final ResourceLocation BG_LOCATION = ResourceLocation.fromNamespaceAndPath("moreofeverything","textures/gui/overlay/soup_recipe.png");
    private static final Component TITLE = Component.translatable("screen.moreofeverything.SoupRecipeScreen");

    private final int imageWidth;
    private final int imageHeight;

    private final int index;

    public SoupRecipeScreen(int index) {
        super(TITLE);
        this.imageWidth = 256;
        this.imageHeight = 256;
        this.index = index;
    }
    @Override
    public void renderBackground(@NotNull GuiGraphics p_283688_, int p_299421_, int p_298679_, float p_297268_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG_LOCATION);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        p_283688_.blit(BG_LOCATION, x, y, 0, 0, imageWidth, imageHeight);
    }

    public void renderDark(@NotNull GuiGraphics p_283688_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG);
        p_283688_.blit(BG, 0, 0, width, height, width, height);
    }

    public void renderItems(@NotNull GuiGraphics p_283688_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation ITEM_1 = ResourceLocation.withDefaultNamespace("textures/item/" + SoupRecipeHelper.getItem(index, 0) + ".png");
        ResourceLocation ITEM_2 = ResourceLocation.withDefaultNamespace("textures/item/" + SoupRecipeHelper.getItem(index, 1) + ".png");
        ResourceLocation ITEM_3 = ResourceLocation.withDefaultNamespace("textures/item/" + SoupRecipeHelper.getItem(index, 2) + ".png");
        ResourceLocation ITEM_4 = ResourceLocation.withDefaultNamespace("textures/item/" + SoupRecipeHelper.getItem(index, 3) + ".png");
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight);
        RenderSystem.setShaderTexture(0, ITEM_1);
        p_283688_.blit(ITEM_1, x + 41, y + 71, 0, 0, 48, 48, 48,48);
        RenderSystem.setShaderTexture(0, ITEM_2);
        p_283688_.blit(ITEM_2, x + 168, y + 71, 0, 0, 48, 48, 48,48);
        RenderSystem.setShaderTexture(0, ITEM_3);
        p_283688_.blit(ITEM_3, x + 41, y + 146, 0, 0, 48, 48, 48,48);
        RenderSystem.setShaderTexture(0, ITEM_4);
        p_283688_.blit(ITEM_4, x + 168, y + 146, 0, 0, 48, 48, 48,48);
    }

    @Override
    public void render(@NotNull GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_) {
        super.render(p_281549_, p_281550_, p_282878_, p_282465_);
        renderDark(p_281549_);
        renderBackground(p_281549_,p_281550_,p_282878_,p_282465_);
        renderItems(p_281549_);
        p_281549_.renderItem(new ItemStack(MOEItem.CAPTURE_NET.get().asItem()), 1, 1, 16, 16);
    }

    @Override
    public void init() {
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}