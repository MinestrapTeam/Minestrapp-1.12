package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDriedMud extends BlockBase
{
	public BlockDriedMud()
	{
		super("dried_mud", Material.ROCK, MapColor.WOOD, SoundType.GROUND, 0.7F, "pickaxe", 0);
		this.setCreativeTab(MTabs.environment);
	}
	
	public void fillWithRain(World worldIn, BlockPos pos)
    {
        worldIn.setBlockState(pos, MBlocks.mud.getDefaultState());
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		boolean hasWater = (worldIn.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.south()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER);
        if(hasWater)
        	worldIn.setBlockState(pos, MBlocks.mud.getDefaultState());
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		boolean hasWater = (worldIn.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.south()).getMaterial() == Material.WATER ||
                worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER);
		if(hasWater)
        	return MBlocks.mud.getDefaultState();
		else
			return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
            case Desert: return true;
            default: break;
        }

        return false;
    }
}
