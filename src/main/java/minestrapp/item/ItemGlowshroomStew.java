package minestrapp.item;

import minestrapp.item.util.MItemsFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGlowshroomStew extends MItemsFood
{
	private boolean isBread;
	
	public ItemGlowshroomStew(int amount, float saturation, boolean isWolfFood, String string, boolean bread)
	{
		super(amount, saturation, isWolfFood, string);
		this.isBread = bread;
		if(bread)
			this.setMaxStackSize(1);
	}

	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
		if (!player.capabilities.isCreativeMode && this.isBread && !worldIn.isRemote)
        {
			if (!player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL, 1)))
            {
                player.dropItem(new ItemStack(Items.BOWL, 1), false);
            }
        }
		if (!worldIn.isRemote)
        {
        	player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 400, 3));
        	player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 3));
        	player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 3));
        	player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 400, 3));
        	player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 400, 3));
        }
		
		super.onFoodEaten(stack, worldIn, player);
    }
}
