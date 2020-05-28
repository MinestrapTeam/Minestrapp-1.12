package minestrapp.item;

import minestrapp.item.util.IItemVariants;
import minestrapp.item.util.MItemBowlFood;
import minestrapp.item.util.MItemsFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemTannic extends MItemBowlFood implements IItemVariants
{
	public ItemTannic()
	{
		super(0, 0, false, "tannic", new ItemStack(Items.GLASS_BOTTLE), true);
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	player.addPotionEffect(new PotionEffect(MobEffects.POISON, 440 * (stack.getMetadata() + 1), 1));
        	player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 440 * (stack.getMetadata() + 1), 0));
        	player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 440 * (stack.getMetadata() + 1), 0));
        	player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 1));
        	player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 440 * (stack.getMetadata() + 1), 0));
        	player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 440 * (stack.getMetadata() + 1), 0));
        	player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 440 * (stack.getMetadata() + 1), 0));
        	player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 440 * (stack.getMetadata() + 1), 0));
        	player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 440 * (stack.getMetadata() + 1), 0));
        	player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 440 * (stack.getMetadata() + 1), 0));
        	if(worldIn.rand.nextInt(2) == 0)
        		player.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 440, 0));
        	else
        		player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 440, 0));
        }
        
        super.onFoodEaten(stack, worldIn, player);
    }
	
	@Override
	public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> tab)
	{
		for(int i = 0 ; i < 4 ; i++)
		{
			if (this.isInCreativeTab(tabs))
	        {
				tab.add(new ItemStack(this, 1, i));
	        }
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName(stack) + "_" + stack.getItemDamage();
	}
	
	@Override
	public int getMaxVariants()
	{
		return 4;
	}
}
