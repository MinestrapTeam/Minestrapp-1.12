package minestrapp.item.tools;

import minestrapp.MTabs;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;

public class MSword extends ItemSword
{
	public MSword(ToolMaterial material, String name)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.combat);
	}
}
