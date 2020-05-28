package minestrapp.gui;

import minestrapp.Minestrapp;
import minestrapp.container.ContainerActivator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiActivator extends GuiContainer
{
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Minestrapp.MODID, "textures/gui/activator.png");

	private final IInventory playerInventory;
	private final IInventory activatorInventory;
	private int inventoryRows;

	public GuiActivator(IInventory playerInventory, IInventory activatorInventory)
	{
		super(new ContainerActivator(playerInventory, activatorInventory, Minecraft.getMinecraft().player));
		this.playerInventory = playerInventory;
		this.activatorInventory = activatorInventory;
		this.allowUserInput = false;
		this.inventoryRows = 1;
		this.ySize = 114 + this.inventoryRows * 18;
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BG_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 34, this.xSize, 96);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.activatorInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
}
