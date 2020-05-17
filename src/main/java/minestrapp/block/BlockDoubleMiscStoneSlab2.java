package minestrapp.block;

import minestrapp.Minestrapp;
import net.minecraft.util.ResourceLocation;

public class BlockDoubleMiscStoneSlab2 extends BlockMiscStoneSlab2
{
	public BlockDoubleMiscStoneSlab2(String name)
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
