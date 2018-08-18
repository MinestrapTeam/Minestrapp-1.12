package minestrapp.gui;

import minestrapp.Minestrapp5;
import minestrapp.container.ContainerBackpack;
import minestrapp.inventories.InventoryBackpack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiBackpack extends GuiContainer{
	
	private static final ResourceLocation textureBackpack = new ResourceLocation(Minestrapp5.MODID, "textures/gui/backpack.png");
	private static final ResourceLocation textureSatchel = new ResourceLocation(Minestrapp5.MODID, "textures/gui/satchel.png");
	private InventoryBackpack backpack;

	public GuiBackpack(ContainerBackpack backpack) {
		super(backpack);
		this.backpack = backpack.inventory;
		this.ySize = 186;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if(this.backpack.type == 1) {
			this.fontRenderer.drawString("Backpack", 8, 6, 4210752);
		} 
		if(this.backpack.type == 2) {
			this.fontRenderer.drawString("Satchel", 8, 6, 4210752);
		} 
		this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		// Bind the image texture of our custom container
		if(this.backpack.type == 1) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(textureBackpack);
		}
		if(this.backpack.type == 2) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(textureSatchel);
		}
		// Draw the image
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
