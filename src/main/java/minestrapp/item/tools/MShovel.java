package minestrapp.item.tools;

import net.minecraft.item.Item.ToolMaterial;
import minestrapp.MTabs;
import net.minecraft.item.ItemSpade;

public class MShovel extends ItemSpade
{
	public MShovel(ToolMaterial material, String name)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.tools);
	}
}
