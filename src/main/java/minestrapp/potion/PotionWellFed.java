package minestrapp.potion;

import net.minecraft.entity.EntityLivingBase;

public class PotionWellFed extends MPotion
{
	protected PotionWellFed(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndexX, int iconIndexY)
	{
		super(isBadEffectIn, liquidColorIn, name, iconIndexX, iconIndexY);
	}
	
	public java.util.List<net.minecraft.item.ItemStack> getCurativeItems()
    {
		java.util.ArrayList<net.minecraft.item.ItemStack> ret = new java.util.ArrayList<net.minecraft.item.ItemStack>();
        return ret;
    }
}
