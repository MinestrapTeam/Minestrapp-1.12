package minestrapp.block;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.block.util.BlockBaseNonSolid;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpotlightBeam extends BlockBaseNonSolid
{
	public static final PropertyInteger POWER = PropertyInteger.create("power", 1, 15);
	public static final PropertyInteger POWER_U = PropertyInteger.create("power_up", 1, 15);
	public static final PropertyInteger POWER_D = PropertyInteger.create("power_down", 0, 15);
	public static final PropertyInteger POWER_N = PropertyInteger.create("power_north", 0, 15);
	public static final PropertyInteger POWER_S = PropertyInteger.create("power_south", 0, 15);
	public static final PropertyInteger POWER_E = PropertyInteger.create("power_east", 0, 15);
	public static final PropertyInteger POWER_W = PropertyInteger.create("power_west", 0, 15);
	
	public BlockSpotlightBeam()
	{
		super("spotlight_beam", Material.AIR, MapColor.AIR, SoundType.GLASS, -1F);
		this.setPushReaction(EnumPushReaction.DESTROY);
		this.setBlockUnbreakable();
		this.setGlowing();
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 15).withProperty(POWER_U, 0).withProperty(POWER_D, 0).withProperty(POWER_N, 0).withProperty(POWER_S, 0).withProperty(POWER_E, 0).withProperty(POWER_W, 0));
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
		this.setLightOpacity(0);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
	{
		if (!world.isRemote)
		{
			this.updateBeamStatus(world, pos, state);
		}
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			this.updateBeamStatus(world, pos, state);
		}
	}
	
	public void updateBeamStatus(World worldIn, BlockPos pos, IBlockState state)
	{
		int power = state.getValue(POWER).intValue();
		int powerU = 0;
		int powerD = 0;
		int powerN = 0;
		int powerS = 0;
		int powerE = 0;
		int powerW = 0;
		
		for(EnumFacing facing : EnumFacing.values())
		{
			IBlockState checkState = worldIn.getBlockState(pos.offset(facing));
			int powerFromSide = 0;
			
			if(checkState.getBlock() == MBlocks.spotlight_beam)
			{
				if(checkState.getValue(POWER) > state.getValue(POWER))
				{
					boolean isSourceFacing = false;
					
					for(int i = 2 ; i < 15 ; i++)
					{
						IBlockState beamState = worldIn.getBlockState(pos.offset(facing, i));
						if(beamState.getBlock() != MBlocks.spotlight_beam)
						{
							if(beamState.getBlock() instanceof BlockSpotlight && beamState.getValue(BlockSpotlight.POWERED).booleanValue() == true && beamState.getValue(BlockSpotlight.FACING) == facing.getOpposite())
								isSourceFacing = true;
							break;
						}
					}
					
					if(isSourceFacing)
						powerFromSide = checkState.getValue(POWER).intValue() - 1;
				}
			}
			else if(checkState.getBlock() instanceof BlockSpotlight && checkState.getValue(BlockSpotlight.POWERED).booleanValue() == true && checkState.getValue(BlockSpotlight.FACING) == facing.getOpposite())
			{
				if(((BlockSpotlight) checkState.getBlock()).isSource() && worldIn.getStrongPower(pos.offset(facing)) > 0)
				{
					powerFromSide = worldIn.getStrongPower(pos.offset(facing));
				}
				else
				{
					powerFromSide = 15;
				}
			}
			
			if(facing == EnumFacing.UP)
				powerU = powerFromSide;
			else if(facing == EnumFacing.DOWN)
				powerD = powerFromSide;
			else if(facing == EnumFacing.NORTH)
				powerN = powerFromSide;
			else if(facing == EnumFacing.SOUTH)
				powerS = powerFromSide;
			else if(facing == EnumFacing.EAST)
				powerE = powerFromSide;
			else if(facing == EnumFacing.WEST)
				powerW = powerFromSide;
		}
		
		int strongestPower = power;
		
		if(powerU > strongestPower)
			strongestPower = powerU;
		if(powerD > strongestPower)
			strongestPower = powerD;
		if(powerN > strongestPower)
			strongestPower = powerN;
		if(powerS > strongestPower)
			strongestPower = powerS;
		if(powerE > strongestPower)
			strongestPower = powerE;
		if(powerW > strongestPower)
			strongestPower = powerW;
		
		if(strongestPower != power)
		{
			if(strongestPower == 0)
				worldIn.setBlockToAir(pos);
			else
				worldIn.setBlockState(pos, state.withProperty(POWER, strongestPower));
		}
		
		if(strongestPower > 1)
		{
			if(powerU > 1 && worldIn.isAirBlock(pos.down()))
				worldIn.setBlockState(pos.down(), MBlocks.spotlight_beam.getDefaultState().withProperty(POWER, powerU - 1));
			if(powerD > 1 && worldIn.isAirBlock(pos.up()))
				worldIn.setBlockState(pos.up(), MBlocks.spotlight_beam.getDefaultState().withProperty(POWER, powerD - 1));
			if(powerN > 1 && worldIn.isAirBlock(pos.south()))
				worldIn.setBlockState(pos.south(), MBlocks.spotlight_beam.getDefaultState().withProperty(POWER, powerN - 1));
			if(powerS > 1 && worldIn.isAirBlock(pos.north()))
				worldIn.setBlockState(pos.north(), MBlocks.spotlight_beam.getDefaultState().withProperty(POWER, powerS - 1));
			if(powerE > 1 && worldIn.isAirBlock(pos.west()))
				worldIn.setBlockState(pos.west(), MBlocks.spotlight_beam.getDefaultState().withProperty(POWER, powerE - 1));
			if(powerW > 1 && worldIn.isAirBlock(pos.east()))
				worldIn.setBlockState(pos.east(), MBlocks.spotlight_beam.getDefaultState().withProperty(POWER, powerW - 1));
		}
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		int powerU = 0;
		int powerD = 0;
		int powerN = 0;
		int powerS = 0;
		int powerE = 0;
		int powerW = 0;
		
		for(EnumFacing facing : EnumFacing.values())
		{
			IBlockState checkState = worldIn.getBlockState(pos.offset(facing));
			int powerFromSide = 0;
			
			if(checkState.getBlock() == MBlocks.spotlight_beam)
			{
				if(checkState.getValue(POWER) > state.getValue(POWER))
				{
					boolean isSourceFacing = false;
					
					for(int i = 2 ; i < 15 ; i++)
					{
						IBlockState beamState = worldIn.getBlockState(pos.offset(facing, i));
						if(beamState.getBlock() != MBlocks.spotlight_beam)
						{
							if(beamState.getBlock() instanceof BlockSpotlight && beamState.getValue(BlockSpotlight.POWERED).booleanValue() == true && beamState.getValue(BlockSpotlight.FACING) == facing.getOpposite())
								isSourceFacing = true;
							break;
						}
					}
					
					if(isSourceFacing)
						powerFromSide = checkState.getValue(POWER).intValue() - 1;
				}
			}
			else if(checkState.getBlock() instanceof BlockSpotlight && checkState.getValue(BlockSpotlight.POWERED).booleanValue() == true && checkState.getValue(BlockSpotlight.FACING) == facing.getOpposite())
			{
				if(((BlockSpotlight) checkState.getBlock()).isSource())
				{
					powerFromSide = state.getValue(POWER);
				}
				else
				{
					powerFromSide = 15;
				}
			}
			
			if(facing == EnumFacing.UP)
				powerU = powerFromSide;
			else if(facing == EnumFacing.DOWN)
				powerD = powerFromSide;
			else if(facing == EnumFacing.NORTH)
				powerN = powerFromSide;
			else if(facing == EnumFacing.SOUTH)
				powerS = powerFromSide;
			else if(facing == EnumFacing.EAST)
				powerE = powerFromSide;
			else if(facing == EnumFacing.WEST)
				powerW = powerFromSide;
		}
		
		return state.withProperty(POWER_U, powerU).withProperty(POWER_D, powerD).withProperty(POWER_N, powerN).withProperty(POWER_S, powerS).withProperty(POWER_E, powerE).withProperty(POWER_W, powerW);
    }
	
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return false;
    }
	
	public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }
	
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
    	return true;
    }
    
    public boolean canSpawnInBlock()
    {
        return true;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return new AxisAlignedBB(0D, 0D, 0D, 0D, 0D, 0D);
    }
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(POWER, meta + 1);
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	return state.getValue(POWER).intValue() - 1;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWER, POWER_U, POWER_D, POWER_N, POWER_S, POWER_E, POWER_W});
    }
}
