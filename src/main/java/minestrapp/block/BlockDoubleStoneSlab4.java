package minestrapp.block;

import minestrapp.Minestrapp5;
import net.minecraft.util.ResourceLocation;

public class BlockDoubleStoneSlab4 extends BlockStoneSlab4
{
	public BlockDoubleStoneSlab4(String name)
	{
		super(name);
		this.setRegistryName(new ResourceLocation(Minestrapp5.MODID, this.getUnlocalizedName().substring(5) + "_double"));
	}

	@Override
	public boolean isDouble()
	{
		return true;
	}
}
