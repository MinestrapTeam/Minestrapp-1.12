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
	private int inventoryRows;

	public GuiBarrel(Container container, InventoryPlayer playerInv) {
		super(container);
		this.playerInv = playerInv;
		this.inventoryRows = container.inventorySlots.size() / 9;
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, this.inventoryRows * 18 + 17 + 96);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString("Barrel", 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 3 * 18 - 19, 4210752);
	}
}
