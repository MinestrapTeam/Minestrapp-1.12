package minestrapp.item;

import minestrapp.MTabs;
import minestrapp.block.BlockSawmill;
import minestrapp.item.util.ItemBase;

public class ItemSawblade extends ItemBase
{
	private BlockSawmill.EnumBladeType bladeType;
	
	public ItemSawblade(BlockSawmill.EnumBladeType bladeType)
	{
		super("saw_blade_" + bladeType.getName());
		this.setMaxStackSize(1);
		if(bladeType.getDurability() > 0)
			this.setMaxDamage(bladeType.getDurability());
		this.setCreativeTab(MTabs.tools);
		this.bladeType = bladeType;
	}
	
	public BlockSawmill.EnumBladeType getBladeType()
	{
		return bladeType;
	}
}
