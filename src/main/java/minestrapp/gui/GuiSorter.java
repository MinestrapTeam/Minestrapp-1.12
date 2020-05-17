package minestrapp.gui;

import minestrapp.Minestrapp;
import minestrapp.container.ContainerSorter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiSorter extends GuiContainer
{
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Minestrapp.MODID, "textures/gui/sorter.png");

	private final IInventory playerInventory;
	private final IInventory sorterInventory;
	private int inventoryRows;

	public GuiSorter(IInventory playerInventory, IInventory sorterInventory)
	{
		super(new ContainerSorter(playerInventory, sorterInventory, Minecraft.getMinecraft().player));
		this.playerInventory = playerInventory;
		this.sorterInventory = sorterInventory;
		this.allowUserInput = false;
		this.inventoryRows = 2;
		this.ySize = 128 + this.inventoryRows * 18;
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
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 50);
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 50, 0, 86, this.xSize, 96);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.sorterInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
}
