package minestrapp.gui;

import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntityPressurizer;
import minestrapp.container.ContainerPressurizer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiPressurizer extends GuiContainer
{
	public static final ResourceLocation texture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/pressurizer.png");	
	private final InventoryPlayer playerInv;
	private final TileEntityPressurizer tileentity;
	
	public GuiPressurizer(ContainerPressurizer container, TileEntityPressurizer tileentity, InventoryPlayer playerInv)
	{
		super(new ContainerPressurizer(playerInv, tileentity));
		this.playerInv = playerInv;
		this.tileentity = tileentity;
		this.ySize = 196;
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(s, 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(texture);
		int j = (this.width - this.xSize) / 2;
		int k = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityPressurizer.isBurning(this.tileentity))
		{
			int burn = this.getBurnLeftScaled(12);
			this.drawTexturedModalRect(j + 81, k + 78 - burn, 176, 12 - burn, 14, burn + 2);
		}
		
		int update = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(j + 49, k + 44, 176, 14, update + 1, 16);
		this.drawTexturedModalRect(j + 126 - update, k + 44, 199 - update, 31, update + 1, 16);
		this.drawTexturedModalRect(j + 80, k + 13, 176, 48, 16, update + 1);
		}
	
	private int getBurnLeftScaled(int pixels)
	{
		int i = this.tileentity.getField(1);
		
		if(i == 0)
			i = 200;
		
		return this.tileentity.getField(0) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileentity.getField(2);
        int j = this.tileentity.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}