package minestrapp.item;

import minestrapp.item.util.MItemBowlFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemJamBottle extends MItemBowlFood
{
	public ItemJamBottle(int amount, float saturation, boolean isWolfFood, String string, ItemStack container, boolean stackable)
	{
		super(amount, saturation, isWolfFood, string, container, stackable);
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	if(worldIn.rand.nextFloat() < 0.4F)
        		player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 440, 0));
        	if(worldIn.rand.nextFloat() < 0.4F)
        		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 440, 0));
        	if(worldIn.rand.nextFloat() < 0.4F)
        		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 440, 0));
        	if(worldIn.rand.nextFloat() < 0.4F)
        		player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
        }
        
        super.onFoodEaten(stack, worldIn, player);
    }
}
