package minestrapp.gui;

import minestrapp.Minestrapp5;
import minestrapp.container.ContainerBarrel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBarrel extends GuiContainer
{
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Minestrapp5.MODID, "textures/gui/barrel.png");

	private final IInventory playerInventory;
	private final IInventory barrelInventory;
	private int inventoryRows;

	public GuiBarrel(IInventory playerInventory, IInventory barrelInventory)
	{
		super(new ContainerBarrel(playerInventory, barrelInventory, Minecraft.getMinecraft().player));
		this.playerInventory = playerInventory;
		this.barrelInventory = barrelInventory;
		this.allowUserInput = false;
		this.inventoryRows = barrelInventory.getSizeInventory() / 9;
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
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 90, this.xSize, 96);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.barrelInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
}
