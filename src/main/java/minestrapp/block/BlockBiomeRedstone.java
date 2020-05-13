package minestrapp.block;

import java.util.Random;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBiomeRedstone extends BlockBase
{
	private int tickRate;
	private int power;
	private Block swapBlock;
	private float r;
	private float g;
	private float b;
	
	public BlockBiomeRedstone(String name, MapColor mapColor, int power, int duration, Block swapBlock, float r, float g, float b)
	{
		super(name, Material.IRON, mapColor, SoundType.METAL, 5F, "pickaxe", 0);
		this.setResistance(10F);
		if(power > 0)
			this.setCreativeTab(MTabs.resource);
		this.power = power;
		this.tickRate = duration;
		this.swapBlock = swapBlock;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setSwapBlock(Block swap)
	{
		this.swapBlock = swap;
	}
	
	public int tickRate(World worldIn)
    {
        return this.tickRate;
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		/*if (worldIn instanceof WorldServer)
        {
			boolean turningOn = true;
			if(this.power > 0)
				turningOn = false;
	    	//this.spawnParticles(worldIn, pos, worldIn.rand, turningOn);
			EnumParticleTypes particle = EnumParticleTypes.REDSTONE;
			float x1 = pos.getX() + 0.5F;
			float y1 = pos.getY() + rand.nextFloat() * 0.9F - 0.3F;
			float z1 = pos.getZ() + 0.5F;
			float f = rand.nextFloat() * 0.9F - 0.3F;
			float x2 = x1 + f;
			float z2 = z1 + f;
			
			float colormultiplier = 1F;
			if(!turningOn)
				colormultiplier = 0.2F;
			
			float r = colormultiplier * this.r / 255;
	        float g = colormultiplier * this.g / 255;
	        float b = colormultiplier * this.b / 255;
	        
	        for(int i = 0 ; i < 10 ; i++)
	        {
		        if(rand.nextInt(10) == 1)
					((WorldServer)worldIn).spawnParticle(EnumParticleTypes.REDSTONE, x1 - 0.52F, y1, z2, (double)r, (double)g, (double)b);
				if(rand.nextInt(10) == 1)
					((WorldServer)worldIn).spawnParticle(EnumParticleTypes.REDSTONE, x1 + 0.52F, y1, z2, (double)r, (double)g, (double)b);
				if(rand.nextInt(10) == 1)
					((WorldServer)worldIn).spawnParticle(EnumParticleTypes.REDSTONE, x2, y1, z1 - 0.52F, (double)r, (double)g, (double)b);
				if(rand.nextInt(10) == 1)
					((WorldServer)worldIn).spawnParticle(EnumParticleTypes.REDSTONE, x2, y1, z1 + 0.52F, (double)r, (double)g, (double)b);
	        }
        }*/
		worldIn.setBlockState(pos, this.swapBlock.getDefaultState());
    }
	
	public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return this.power;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if(this.power > 0)
        	return Item.getItemFromBlock(this);
        else
        	return Item.getItemFromBlock(this.swapBlock);
    }
    
    public boolean canSilkHarvest()
	{
		return false;
	}
    
	public void spawnParticles(World worldIn, BlockPos pos, Random random, boolean turningOn)
	{
		EnumParticleTypes particle = EnumParticleTypes.REDSTONE;
		float x1 = pos.getX() + 0.5F;
		float y1 = pos.getY() + random.nextFloat() * 0.9F - 0.3F;
		float z1 = pos.getZ() + 0.5F;
		float f = random.nextFloat() * 0.9F - 0.3F;
		float x2 = x1 + f;
		float z2 = z1 + f;
		
		float colormultiplier = 1F;
		if(!turningOn)
			colormultiplier = 0.2F;
		
		float r = colormultiplier * this.r / 255;
        float g = colormultiplier * this.g / 255;
        float b = colormultiplier * this.b / 255;
        
        for(int i = 0 ; i < 10 ; i++)
        {
	        if(random.nextInt(10) == 1)
				worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x1 - 0.52F, y1, z2, (double)r, (double)g, (double)b);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x1 + 0.52F, y1, z2, (double)r, (double)g, (double)b);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x2, y1, z1 - 0.52F, (double)r, (double)g, (double)b);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x2, y1, z1 + 0.52F, (double)r, (double)g, (double)b);
        }
	}
}
