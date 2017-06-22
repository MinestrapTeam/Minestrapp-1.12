package minestrapp.item.tools;

import minestrapp.MTabs;
import net.minecraft.item.ItemPickaxe;

public class MPickaxe extends ItemPickaxe
{
	public MPickaxe(ToolMaterial material, String name)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.tools);
	}
}
