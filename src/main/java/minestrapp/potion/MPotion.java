package minestrapp.potion;

import minestrapp.Minestrapp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MPotion extends Potion{

	private ResourceLocation iconTexture = new ResourceLocation(Minestrapp.MODID, "textures/gui/potion_icons.png");
	
	private int iconIndexX = 1;
	private int iconIndexY = 1;
	
	protected MPotion(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndexX, int iconIndexY) {
		super(isBadEffectIn, liquidColorIn);
		this.iconIndexX = iconIndexX;
		this.iconIndexY = iconIndexY;
		this.setPotionName(name);
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
	{
		if (mc.currentScreen != null) {
			mc.getTextureManager().bindTexture(iconTexture);
			Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, this.iconIndexX * 18, this.iconIndexY * 18, 18, 18, 256, 256);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha) { 
		mc.getTextureManager().bindTexture(iconTexture);
		Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, this.iconIndexX * 18, this.iconIndexY * 18, 18, 18, 256, 256);
		
	}

}
