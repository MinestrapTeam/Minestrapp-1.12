package minestrapp.block.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MItemBlock extends ItemBlock
{
	private int burnTime;
	private List<Integer> burnMeta = new ArrayList<Integer>();
	
	public MItemBlock(Block block)
	{
		super(block);
		this.burnTime = 0;
	}
	
	public MItemBlock setBurnTime(int time)
	{
		return setBurnTime(time, OreDictionary.WILDCARD_VALUE);
	}
	
	public MItemBlock setBurnTime(int time, int meta)
	{
		this.burnTime = time;
		this.burnMeta.add(meta);
		return this;
	}
	
	public int getItemBurnTime(ItemStack itemStack)
    {
		if(this.burnTime > 0 && (this.burnMeta.contains(itemStack.getMetadata()) || this.burnMeta.contains(OreDictionary.WILDCARD_VALUE)))
			return this.burnTime;
		else
			return 0;
    }
}
