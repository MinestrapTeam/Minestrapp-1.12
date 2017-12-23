package minestrapp.block;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.MTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoulEyes extends BlockSunstoneDeposit
{
	public BlockSoulEyes(String name)
	{
		super(name);
		this.setLightLevel(0.5F);
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.CYAN;
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return MItems.gem_soul;
    }
	
	@Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
		Random rand = world instanceof World ? ((World)world).rand : new Random();
	    if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
	    {
	        return MathHelper.getInt(rand, 3, 9);
	    }
	    return 0;
    }
}
