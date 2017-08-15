package minestrapp.item.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MItemBowlFood extends MItemsFood
{
	private final ItemStack container;
	
	public MItemBowlFood(int amount, float saturation, boolean isWolfFood, String string, ItemStack container, boolean stackable)
	{
		super(amount, saturation, isWolfFood, string);
		this.container = container;
		if(!stackable)
			this.setMaxStackSize(1);
	}
	
	public MItemBowlFood(int amount, float saturation, boolean isWolfFood, String string)
	{
		this(amount, saturation, isWolfFood, string, new ItemStack(Items.BOWL), false);
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {	
		if (!player.capabilities.isCreativeMode && this.container != null && !worldIn.isRemote)
        {
			if (!player.inventory.addItemStackToInventory(new ItemStack(container.getItem(), 1, container.getMetadata())))
            {
                player.dropItem(new ItemStack(container.getItem(), 1, container.getMetadata()), false);
            }
        }
		
		super.onFoodEaten(stack, worldIn, player);
    }
}
