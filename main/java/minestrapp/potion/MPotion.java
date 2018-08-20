package minestrapp.potion;

import java.awt.Color;

import minestrapp.Minestrapp5;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MPotion extends Potion{

	private ResourceLocation iconTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/potion_icons.png");
	
	private int iconIndex = 1;
	
	protected MPotion(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndex) {
		super(isBadEffectIn, liquidColorIn);
		this.iconIndex = iconIndex;
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
			Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, this.iconIndex * 18, 0, 18, 18, 256, 256);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha) { 
		mc.getTextureManager().bindTexture(iconTexture);
		Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, this.iconIndex * 18, 0, 18, 18, 256, 256);
		
	}

}
