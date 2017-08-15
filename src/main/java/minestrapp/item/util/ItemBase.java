package minestrapp.item.util;

import minestrapp.MTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item
{
	private boolean foiled;
	private boolean beaconFuel;
	
	public ItemBase(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.foiled = false;
		this.beaconFuel = false;
	}
	
	public ItemBase setFoiled()
	{
		this.foiled = true;
		return this;
	}
	
	public ItemBase setBeaconPayment()
	{
		this.beaconFuel = true;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return this.foiled;
	}
	
	public boolean isBeaconPayment(ItemStack stack)
    {
		return this.beaconFuel;
    }
}
