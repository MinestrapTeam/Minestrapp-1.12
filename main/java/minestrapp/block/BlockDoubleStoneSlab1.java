package minestrapp.block;

import minestrapp.Minestrapp5;
import net.minecraft.util.ResourceLocation;

public class BlockDoubleStoneSlab1 extends BlockStoneSlab1
{
	public BlockDoubleStoneSlab1(String name)
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
