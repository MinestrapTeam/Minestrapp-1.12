package minestrapp.block.crops;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.Minestrapp;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class CornPlant extends BlockCrops
{
	public CornPlant(String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Minestrapp.MODID, unlocalizedName));
	}
	
	@Override
	protected Item getSeed()
	{
		return MItems.corn;
	}
	
	@Override
	protected Item getCrop()
	{
		return MItems.corn;
	}
	
	@Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
		return EnumPlantType.Crop;
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.FARMLAND || state == this.getDefaultState().withProperty(AGE, this.getMaxAge());
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge())
            {
                float f = getGrowthChance(this, worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
            else if(worldIn.isAirBlock(pos.offset(EnumFacing.UP)) && worldIn.getBlockState(pos.offset(EnumFacing.DOWN, 2)).getBlock() != this)
            {
        		float f = getGrowthChance(this, worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.up(), state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                	worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(AGE, 0));
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos.up(), state, worldIn.getBlockState(pos.up()));
                }
            }
        }
    }
	
	public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
		if(this.getAge(state) < this.getMaxAge())
		{
	        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
	        int j = this.getMaxAge();
	
	        if (i > j)
	        {
	            i = j;
	        }
	
	        worldIn.setBlockState(pos, this.withAge(i), 2);
		}
		else if(worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos.offset(EnumFacing.DOWN, 2)).getBlock() != this)
		{
			worldIn.setBlockState(pos.up(), this.withAge(0));
		}
    }
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return !this.isMaxAge(state) || (worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos.offset(EnumFacing.DOWN, 2)).getBlock() != this);
    }
}
