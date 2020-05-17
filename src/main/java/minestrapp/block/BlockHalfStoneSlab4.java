package minestrapp.block;

import minestrapp.Minestrapp;
import net.minecraft.util.ResourceLocation;

public class BlockHalfStoneSlab4 extends BlockStoneSlab4
{
	public BlockHalfStoneSlab4(String name)
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
