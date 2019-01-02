package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMFenceGate extends BlockFenceGate
{
	private BlockMPlanks.EnumType type;
	
	public BlockMFenceGate(BlockMPlanks.EnumType type)
	{
		super(BlockPlanks.EnumType.OAK);
		String name = type.getUnlocalizedName() + "_fence_gate";
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setHardness(2F);
		this.setResistance(5F);
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(MTabs.wood);
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return type.getMapColor();
    }
	
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(this.type != BlockMPlanks.EnumType.CHARWOOD)
			return 20;
		else
			return super.getFlammability(world, pos, face);
    }
	
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(this.type != BlockMPlanks.EnumType.CHARWOOD)
			return 5;
		else
			return super.getFireSpreadSpeed(world, pos, face);
    }
}
