package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMud extends BlockBase
{
	protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
	
	public BlockMud()
	{
		super("mud", Material.CLAY, MapColor.BROWN_STAINED_HARDENED_CLAY, SoundType.SLIME, 0.65F, "shovel", 0);
		this.setCreativeTab(MTabs.environment);
		this.setTickRandomly(true);
	}

	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return MUD_AABB;
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        entityIn.motionX *= 0.15D;
        entityIn.motionY *= 0.2D;
        entityIn.motionZ *= 0.15D;
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return MItems.mud_ball;
    }
	
	public int quantityDropped(Random random)
    {
        return 4;
    }
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
            case Plains: return true;
            case Beach: return true;
            default: break;
        }

        return false;
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
        	float temp = worldIn.getBiome(pos).getDefaultTemperature();
        	boolean sky = true;
        	for(int i = pos.up().getY() ; i < 256 ; i++)
        	{
        		BlockPos newpos = new BlockPos(pos.getX(), i, pos.getZ());
        		if(!worldIn.isAirBlock(newpos))
        		{
        			sky = false;
        			break;
        		}
        	}
        	//System.out.println("Temp: " + (temp > 0.9F));
        	//System.out.println("Sky: " + worldIn.canSeeSky(pos));
        	//System.out.println("Rain: " + (worldIn.isRaining()));
        	//System.out.println("Day: " + (worldIn.isDaytime()));
        	//System.out.println("Wet: " + (worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER));
        	if(temp > 0.9F && sky && !worldIn.isRaining() && worldIn.isDaytime() && worldIn.getBlockState(pos.up()).getMaterial() != Material.WATER)
        	{
        		worldIn.setBlockState(pos, MBlocks.dried_mud.getDefaultState());
        		this.spawnParticles(worldIn, pos.up());
        	}
        }
    }
	
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	if(worldIn.provider.doesWaterVaporize())
    	{
    		this.spawnParticles(worldIn, pos.up());
    		return MBlocks.dried_mud.getDefaultState();
    	}
    	else
    		return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }
    
    public void spawnParticles(World worldIn, BlockPos pos)
    {
    	int l = pos.getX();
        int i = pos.getY();
        int j = pos.getZ();
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        for (int k = 0; k < 8; ++k)
        {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)l + Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}
