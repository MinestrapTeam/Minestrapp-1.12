package minestrapp.block;

import minestrapp.Minestrapp;
import net.minecraft.util.ResourceLocation;

public class BlockHalfMiscStoneSlab1 extends BlockMiscStoneSlab1
{
	public BlockHalfMiscStoneSlab1(String name)
	{
		super(name);
		this.setRegistryName(new ResourceLocation(Minestrapp.MODID, this.getUnlocalizedName().substring(5)));
	}

	@Override
	public boolean isDouble()
	{
		return false;
	}
}
