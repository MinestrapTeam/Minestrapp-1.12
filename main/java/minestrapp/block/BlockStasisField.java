package minestrapp.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStasisField extends BlockBase
{
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);
	
	public BlockStasisField()
	{
		super("stasis_field", Material.IRON, MapColor.PURPLE, SoundType.METAL, 5F, "pickaxe", 2);
		this.setNonSolid();
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false).withProperty(AXIS, EnumFacing.Axis.Y));
		this.setCreativeTab(MTabs.utility);
	}
	
	public int tickRate(World worldIn)
    {
        return 0;
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        update(worldIn, pos);
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.getValue(POWERED) != null && stateIn.getValue(POWERED).booleanValue())
            this.spawnParticles(worldIn, pos);
    }
	
	private void spawnParticles(World worldIn, BlockPos pos)
    {
        Random random = worldIn.rand;
        EnumFacing.Axis axis = EnumFacing.Axis.Y;
        
        if(worldIn.getBlockState(pos).getValue(AXIS) != null)
        	axis = worldIn.getBlockState(pos).getValue(AXIS);
        
        for(int i = 0 ; i < 50 ; i++)
        {
        	double maxVariance1 = 2 - random.nextInt(5) + random.nextFloat();
        	double maxVariance2 = 2 - random.nextInt(5) + random.nextFloat();
        	double minVariance = random.nextDouble();
        	
        	double x = pos.getX();
        	double y = pos.getY();
        	double z = pos.getZ();
        	
        	if(axis == EnumFacing.Axis.X)
        	{
        		x += minVariance;
        		y += maxVariance1;
        		z += maxVariance2;
        	}
        	else if(axis == EnumFacing.Axis.Y)
        	{
        		x += maxVariance1;
        		y += minVariance;
        		z += maxVariance2;
        	}
        	else if(axis == EnumFacing.Axis.Z)
        	{
        		x += maxVariance1;
        		y += maxVariance2;
        		z += minVariance;
        	}
        	
        	worldIn.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, x, y, z, 0.0D, 0.0D, 0.0D);
        }
        
        /*double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube())
            {
                d2 = (double)pos.getY() + 0.0625D + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube())
            {
                d2 = (double)pos.getY() - 0.0625D;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() + 0.0625D + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() - 0.0625D;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube())
            {
                d1 = (double)pos.getX() + 0.0625D + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube())
            {
                d1 = (double)pos.getX() - 0.0625D;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                worldIn.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }*/
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		if(!state.getValue(POWERED).booleanValue())
			worldIn.scheduleBlockUpdate(pos, worldIn.getBlockState(pos).getBlock(), 0, 0);
    }
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
		worldIn.scheduleBlockUpdate(pos, worldIn.getBlockState(pos).getBlock(), 0, 0);
    }
	
	public AxisAlignedBB getAABBForAxis(World world, BlockPos pos, EnumFacing.Axis axis)
	{
		if(axis == EnumFacing.Axis.X)
			return new AxisAlignedBB (pos.getX(), pos.getY() - 2, pos.getZ() - 2, pos.getX() + 1, pos.getY() + 3, pos.getZ() + 3);
		if(axis == EnumFacing.Axis.Y)
			return new AxisAlignedBB (pos.getX() - 2, pos.getY(), pos.getZ() - 2, pos.getX() + 3, pos.getY() + 1, pos.getZ() + 3);
		if(axis == EnumFacing.Axis.Z)
			return new AxisAlignedBB (pos.getX() - 2, pos.getY() - 2, pos.getZ(), pos.getX() + 3, pos.getY() + 3, pos.getZ() + 1);
		return new AxisAlignedBB (pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2, pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3);
	}
	
	public void update(World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		
		if(state.getBlock() instanceof BlockStasisField)
		{
			if(!worldIn.isBlockPowered(pos))
			{
				worldIn.setBlockState(pos, state.withProperty(POWERED, false));
			}
			else
			{
				EnumFacing.Axis axis = state.getValue(AXIS);
				List<net.minecraft.entity.Entity> entities = worldIn.getEntitiesWithinAABB(net.minecraft.entity.Entity.class, getAABBForAxis(worldIn, pos, axis));
				
				for(net.minecraft.entity.Entity entity : entities)
				{
					if(axis == EnumFacing.Axis.X)
						entity.motionX = 0;
					else if(axis == EnumFacing.Axis.Y)
					{
						entity.motionY = 0;
						entity.fallDistance = 0;
					}
					else if(axis == EnumFacing.Axis.Z)
						entity.motionZ = 0;
					
					entity.velocityChanged = true;
				}
				
				if(!state.getValue(POWERED).booleanValue())
					worldIn.setBlockState(pos, state.withProperty(POWERED, true));
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
			}
		}
	}
	
	@Override
    public boolean rotateBlock(net.minecraft.world.World world, BlockPos pos, EnumFacing axis)
    {
        net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
        for (net.minecraft.block.properties.IProperty<?> prop : state.getProperties().keySet())
        {
            if (prop.getName().equals("axis"))
            {
                world.setBlockState(pos, state.cycleProperty(prop));
                return true;
            }
        }
        return false;
    }
	
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:

                switch ((EnumFacing.Axis)state.getValue(AXIS))
                {
                    case X:
                        return state.withProperty(AXIS, EnumFacing.Axis.Z);
                    case Z:
                        return state.withProperty(AXIS, EnumFacing.Axis.X);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
		boolean powered = false;
		EnumFacing.Axis axis = EnumFacing.Axis.Y;
		
		if(meta > 2)
			powered = true;
		
		if(powered)
			meta -= 3;
		
		if(meta == 0)
			axis = EnumFacing.Axis.X;
		else if(meta == 2)
			axis = EnumFacing.Axis.Z;
		
		return this.getDefaultState().withProperty(POWERED, powered).withProperty(AXIS, axis);
    }
	
	public int getMetaFromState(IBlockState state)
    {
		int meta = 0;
		
		if(state.getValue(AXIS) == EnumFacing.Axis.Y)
			meta = 1;
		else if(state.getValue(AXIS) == EnumFacing.Axis.Z)
			meta = 2;
		
		if(state.getValue(POWERED).booleanValue())
			meta += 3;
		
		return meta;
    }
	
	protected BlockStateContainer createBlockState()
    {
		return new BlockStateContainer(this, new IProperty[] {POWERED, AXIS});
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, facing.getAxis());
    }
}
