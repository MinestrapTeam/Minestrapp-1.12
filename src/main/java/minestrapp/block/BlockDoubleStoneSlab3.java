package minestrapp.block;

import minestrapp.Minestrapp5;
import net.minecraft.util.ResourceLocation;

public class BlockDoubleStoneSlab3 extends BlockStoneSlab3
{
	public BlockDoubleStoneSlab3(String name)
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
