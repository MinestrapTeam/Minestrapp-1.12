package minestrapp.gui;

import minestrapp.Minestrapp5;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiBarrel extends GuiContainer{
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Minestrapp5.MODID, "textures/gui/barrel.png");

	private InventoryPlayer playerInv;

	public GuiBarrel(Container container, InventoryPlayer playerInv) {
		super(container);
		this.playerInv = playerInv;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, 186);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString("Barrel", 7, 7, 4210752);
	}
}
