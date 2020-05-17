package minestrapp.block;

import minestrapp.Minestrapp;
import net.minecraft.util.ResourceLocation;

public class BlockDoubleMiscStoneSlab1 extends BlockMiscStoneSlab1
{
	public BlockDoubleMiscStoneSlab1(String name)
	{
		super(name);
		this.setRegistryName(new ResourceLocation(Minestrapp.MODID, this.getUnlocalizedName().substring(5) + "_double"));
	}

	@Override
	public boolean isDouble()
	{
		return true;
	}
}
