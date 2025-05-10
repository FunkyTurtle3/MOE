package de.funkyturtle.moreofeverything.screen;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import de.funkyturtle.moreofeverything.menu.ArchaeologyMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ArchaeologyScreen extends AbstractContainerScreen<ArchaeologyMenu> {
    protected int imageWidth = 176;
    protected int imageHeight = 222;
    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "textures/gui/archaeology_table/bg.png");
    private static final ResourceLocation BUTTON = ResourceLocation.withDefaultNamespace("textures/block/suspicious_sand_0.png");
    public ArchaeologyScreen(ArchaeologyMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
        this.titleLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBg(pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderButtons(pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {
        int i = this.leftPos;
        int j = this.topPos;
        p_283065_.blit(BG, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
    protected void renderButtons(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {
        ResourceLocation resourceLocation = BUTTON;
        for(int t = 0; t < 4; t++) {
            for(int z = 0; z < 4; z++) {
                if(menu.isSelected(t * 4 + z)) {
                    resourceLocation = ResourceLocation.fromNamespaceAndPath(MoreOfEverything.MOD_ID, "textures/gui/archaeology_table/empty.png");
                } else {
                    resourceLocation = ResourceLocation.withDefaultNamespace("textures/block/suspicious_sand_0.png");
                }
                p_283065_.blit(resourceLocation, this.leftPos + 71 + t * 18, this.topPos + 24 + z * 18,0,0, 16, 16, 16,16);
            }
        }
    }
}