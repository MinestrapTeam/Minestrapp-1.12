package minestrapp.item;

import minestrapp.item.util.MItemsFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCandy extends MItemsFood
{
	private PotionEffect effect1;
	private PotionEffect effect2;
	private PotionEffect effect3;
	
	public ItemCandy(String string, PotionEffect effect1, PotionEffect effect2, PotionEffect effect3)
	{
		super(0, 0, false, string);
		this.effect1 = effect1;
		this.effect2 = effect2;
		this.effect3 = effect3;
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	player.addPotionEffect(new PotionEffect(effect1));
        	player.addPotionEffect(new PotionEffect(effect2));
        	player.addPotionEffect(new PotionEffect(effect3));
        }
        
        super.onFoodEaten(stack, worldIn, player);
    }
}
