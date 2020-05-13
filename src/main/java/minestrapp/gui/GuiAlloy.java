package minestrapp.gui;

import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.container.ContainerAlloy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiAlloy extends GuiContainer{
	
	public static final ResourceLocation texture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/alloy_furnace.png");	
	private final InventoryPlayer playerInv;
	private final TileEntityAlloy tileentity;
	
	public GuiAlloy(ContainerAlloy container, TileEntityAlloy tileentity, InventoryPlayer playerInv) {
		super(new ContainerAlloy(playerInv, tileentity));
		this.playerInv = playerInv;
		this.tileentity = tileentity;
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString("Alloy Furnace", 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(texture);
		int j = (this.width - this.xSize) / 2;
		int k = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityAlloy.isBurning(this.tileentity)) {
			int burn = this.getBurnLeftScaled(12);
			this.drawTexturedModalRect(j + 47, k + 48 - burn, 176, 12 - burn, 14, burn + 2);
		}
		
		int update = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(j + 79, k + 34, 176, 14, update + 1, 16);
		}
	
	private int getBurnLeftScaled(int pixels) {
		int i = this.tileentity.getField(1);
		
		if(i == 0)
			i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.tileentity.getField(2);
        int j = this.tileentity.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}