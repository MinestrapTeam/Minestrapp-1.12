package minestrapp.block;

import java.util.Random;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlacierite extends BlockBase
{
	public BlockGlacierite()
	{
		super("block_glacierite", Material.IRON, MapColor.LAPIS, SoundType.METAL, 5F, "pickaxe", 2);
		this.setBeaconBase();
		this.setCreativeTab(MTabs.resource);
		this.setTickRandomly(true);
	}
	
	public int tickRate(World worldIn)
    {
        return 30;
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		int x = 3 - rand.nextInt(7);
		int z = 3 - rand.nextInt(7);
		
		if(worldIn.getBiome(pos).getTemperature(pos) < 0.8F && Math.abs(x) + Math.abs(z) < 5)
		{
			for(int i = -1 ; i < 2 ; i++)
			{
				BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + i, pos.getZ() + z);
				
				if(worldIn.isAirBlock(newPos) && Blocks.SNOW_LAYER.canPlaceBlockAt(worldIn, newPos))
				{
					worldIn.setBlockState(newPos, Blocks.SNOW_LAYER.getDefaultState());
					this.spawnParticles(worldIn, newPos, rand);
					break;
				}
		        else if(worldIn.getBlockState(newPos).getMaterial() == Material.WATER && ((Integer)worldIn.getBlockState(newPos).getValue(BlockLiquid.LEVEL)).intValue() == 0)
		        {
		        	worldIn.setBlockState(newPos, Blocks.ICE.getDefaultState());
		        	this.spawnParticles(worldIn, newPos, rand);
		        	worldIn.playSound((EntityPlayer)null, newPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
		        	break;
		        }
		        else if(worldIn.getBlockState(newPos).getMaterial() == Material.LAVA && ((Integer)worldIn.getBlockState(newPos).getValue(BlockLiquid.LEVEL)).intValue() == 0)
		        {
		        	worldIn.setBlockState(newPos, Blocks.OBSIDIAN.getDefaultState());
		        	this.spawnParticles(worldIn, newPos, rand);
		        	worldIn.playSound((EntityPlayer)null, newPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
		        	break;
		        }
		        else if(worldIn.getBlockState(newPos).getMaterial() == Material.LAVA)
		        {
		        	worldIn.setBlockState(newPos, Blocks.COBBLESTONE.getDefaultState());
		        	this.spawnParticles(worldIn, newPos, rand);
		        	worldIn.playSound((EntityPlayer)null, newPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
		        	break;
		        }
			}
		}
    }
	
	public boolean requiresUpdates()
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random random)
	{
		spawnParticles(worldIn, pos, random);
	}
	
	public void spawnParticles(World worldIn, BlockPos pos, Random random)
	{
		EnumParticleTypes particle = EnumParticleTypes.END_ROD;
		float offsetPatch = 0F;
		
		if(worldIn.getBiome(pos).getTemperature(pos) >= 0.8F)
		{
			particle = EnumParticleTypes.DRIP_WATER;
			offsetPatch = 0.2F;
		}
		
		for (int i = 0; i < 3; i++)
		{
			float x1 = pos.getX() + 0.5F;
			float y1 = pos.getY() + random.nextFloat() * 0.9F - 0.3F;
			float z1 = pos.getZ() + 0.5F;
			float f = random.nextFloat() * 0.9F - 0.3F;
			float x2 = x1 + f;
			float z2 = z1 + f;

			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x1 - 0.52F, y1, z2, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x1 + 0.52F + offsetPatch, y1, z2, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x2, y1, z1 - 0.52F, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x2, y1, z1 + 0.52F + offsetPatch, 0.0D, 0.0D, 0.0D);
		}
	}
}
