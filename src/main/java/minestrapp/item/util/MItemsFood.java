package minestrapp.item.util;

import minestrapp.MTabs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class MItemsFood extends ItemFood
{
	public MItemsFood(int amount, float saturation, boolean isWolfFood, String string) {
		super(amount, saturation, isWolfFood);
        this.setUnlocalizedName(string);
        this.setRegistryName(string);
        this.setCreativeTab(MTabs.food);
	}
}