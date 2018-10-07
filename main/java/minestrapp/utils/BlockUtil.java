package minestrapp.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtil {
	
	public static void replaceBlock(World world, IBlockState oldState, IBlockState newState, BlockPos pos)
	{
		if(world.getBlockState(pos) == oldState) {
			world.setBlockState(pos, newState);
		}
	}
	
	public static AxisAlignedBB createBoundingBox(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		double x3 = x1 / 16;
		double y3 = y1 / 16;
		double z3 = z1 / 16;
		double x4 = x2 / 16;
		double y4 = y2 / 16;
		double z4 = z2 / 16;
		
		return new AxisAlignedBB(x3, y3, z3, x4, y4, z4);
	}
	
	public static AxisAlignedBB createBoundingBoxColumn(double width, double height, double yoffset)
	{
		double corner1 = (8 - (width / 2)) / 16;
		double corner2 = (8 + (width / 2)) / 16;
		double bottom = yoffset / 16;
		double top = (yoffset + height) / 16;
		
		return new AxisAlignedBB(corner1, bottom, corner1, corner2, top, corner2);
	}
}
