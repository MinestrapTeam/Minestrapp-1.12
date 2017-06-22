package minestrapp.item.tools;

import minestrapp.MTabs;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item.ToolMaterial;

public class MHoe extends ItemHoe
{
	public MHoe(ToolMaterial material, String name)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.tools);
	}
}
