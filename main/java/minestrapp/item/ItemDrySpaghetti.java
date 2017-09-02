package minestrapp.item;

import minestrapp.item.util.MItemsFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemDrySpaghetti extends MItemsFood
{
	public ItemDrySpaghetti(int amount, float saturation, boolean isWolfFood, String string)
	{
		super(amount, saturation, isWolfFood, string);
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
		if(!worldIn.isRemote)
		{
			player.attackEntityFrom(DamageSource.causePlayerDamage(player), 1);
		}
		super.onFoodEaten(stack, worldIn, player);
    }
}
