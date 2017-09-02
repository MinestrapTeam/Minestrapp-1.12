package minestrapp.item;

import minestrapp.item.util.MItemsFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemPBJ extends MItemsFood
{
	public ItemPBJ(int amount, float saturation, boolean isWolfFood, String string)
	{
		super(amount, saturation, isWolfFood, string);
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	if(worldIn.rand.nextFloat() < 0.6F)
        		player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 500, 0));
        	if(worldIn.rand.nextFloat() < 0.6F)
        		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 500, 0));
        	if(worldIn.rand.nextFloat() < 0.6F)
        		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 500, 0));
        	if(worldIn.rand.nextFloat() < 0.6F)
        		player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
        }
        
        super.onFoodEaten(stack, worldIn, player);
    }
}
