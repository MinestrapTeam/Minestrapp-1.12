package minestrapp.utils;

import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
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
	
	/**+X = East, +Z = South**/
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
	
	public static AxisAlignedBB createBoundingBoxColumnNS(double width, double height, double yoffset)
	{
		double corner1 = (8 - (width / 2)) / 16;
		double corner2 = (8 + (width / 2)) / 16;
		double bottom = yoffset / 16;
		double top = (yoffset + height) / 16;
		
		return new AxisAlignedBB(corner1, corner1, bottom, corner2, corner2, top);
	}
	
	public static AxisAlignedBB createBoundingBoxColumnEW(double width, double height, double yoffset)
	{
		double corner1 = (8 - (width / 2)) / 16;
		double corner2 = (8 + (width / 2)) / 16;
		double bottom = yoffset / 16;
		double top = (yoffset + height) / 16;
		
		return new AxisAlignedBB(bottom, corner1, corner1, top, corner2, corner2);
	}
	
	/**Rotates the AABB 90 degrees a specified number of times along the specified axis (looking from the positive end of the axis).**/
	/*
	public static AxisAlignedBB rotateBoundingBox(AxisAlignedBB aabb, EnumAxis axis, int rotations)
	{
		double x1 = aabb.minX;
		double x2 = aabb.maxX;
		double y1 = aabb.minY;
		double y2 = aabb.maxY;
		double z1 = aabb.minZ;
		double z2 = aabb.maxZ;
		
		for(int i = 0 ; i < rotations ; i++)
		{
			double x1Old = x1;
			double x2Old = x2;
			double y1Old = y1;
			double y2Old = y2;
			double z1Old = z1;
			double z2Old = z2;
			
			if(axis == EnumAxis.X)
			{
				y1 = z2Old;
				y2 = z1Old;
				z1 = y2Old;
				z2 = y1Old;
			}
			else if(axis == EnumAxis.Y)
			{
				x1 = z2Old;
				x2 = z1Old;
				z1 = x2Old;
				z2 = x1Old;
			}
			else if(axis == EnumAxis.Z)
			{
				x1 = y2Old;
				x2 = y1Old;
				y1 = x2Old;
				y2 = x1Old;
			}
		}
		
		return new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
	}*/
}
