package minestrapp.block;

import minestrapp.Minestrapp;
import net.minecraft.util.ResourceLocation;

public class BlockDoubleStoneSlab1 extends BlockStoneSlab1
{
	public BlockDoubleStoneSlab1(String name)
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
