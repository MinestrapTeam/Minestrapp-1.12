package minestrapp.item.util;

import java.util.List;

import javax.annotation.Nullable;

import minestrapp.MTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBase extends Item
{
	private boolean foiled;
	private int burnTime;
	private int burnMeta;
	private boolean beaconFuel;
	private String desc = null;
	
	public ItemBase(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.foiled = false;
		this.burnTime = 0;
		this.burnMeta = OreDictionary.WILDCARD_VALUE;
		this.beaconFuel = false;
	}
	
	public ItemBase setFoiled()
	{
		this.foiled = true;
		return this;
	}
	
	public ItemBase setBurnTime(int time)
	{
		return setBurnTime(time, 0);
	}
	
	public ItemBase setBurnTime(int time, int meta)
	{
		this.burnTime = time;
		this.burnMeta = meta;
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
		if(this.foiled)
			return true;
		else
			return super.hasEffect(stack);
	}
	
	public boolean isBeaconPayment(ItemStack stack)
    {
		return this.beaconFuel;
    }
	
	public int getItemBurnTime(ItemStack itemStack)
    {
		if(this.burnTime > 0 && this.burnMeta == itemStack.getMetadata())
			return this.burnTime;
		else
			return 0;
    }
	
	public ItemBase addDescription(String desc)
	{
		this.desc = desc;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if(this.desc != null)
			tooltip.add(desc);
    }
}
