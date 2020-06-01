package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSpotlight extends BlockBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	private boolean isSource;
	
	public BlockSpotlight(String name, boolean isSource)
	{
		super(name, Material.WOOD, MapColor.BROWN, SoundType.WOOD, 3F, "axe", 0);
		this.isSource = isSource;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(POWERED, Boolean.valueOf(false)));
		this.setCreativeTab(MTabs.utility);
	}
	
	public boolean isSource()
	{
		return this.isSource;
	}
	
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
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
		int power = 0;
		
		if(isSource)
			power = worldIn.getStrongPower(pos);
		else
		{
			for(EnumFacing facing : EnumFacing.values())
			{
				for(int i = 1 ; i < 15 ; i++)
				{
					IBlockState checkState = worldIn.getBlockState(pos.offset(facing, i));
					
					if(checkState.getBlock() != MBlocks.spotlight_beam)
					{
						if(checkState.getBlock() instanceof BlockSpotlight && checkState.getValue(POWERED).booleanValue() == true && checkState.getValue(FACING) == facing.getOpposite())
							power = 15;
						
						break;
					}
				}
				
				if(power == 15)
					break;
			}
		}
		
		EnumFacing facing = state.getValue(FACING);
		
		if(power > 0)
		{
			if(state.getValue(POWERED).booleanValue() == false)
				worldIn.setBlockState(pos, state.withProperty(POWERED, true));
			else if(worldIn.isAirBlock(pos.offset(facing)) && worldIn.getBlockState(pos.offset(facing)) != MBlocks.spotlight_beam.getDefaultState().withProperty(BlockSpotlightBeam.POWER, power))
				worldIn.setBlockState(pos.offset(facing), MBlocks.spotlight_beam.getDefaultState().withProperty(BlockSpotlightBeam.POWER, power));
		}
		else
		{
			if(state.getValue(POWERED).booleanValue() == true)
				worldIn.setBlockState(pos, state.withProperty(POWERED, false));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING, POWERED });
	}
	
	public static EnumFacing getFacing(int meta)
    {
        int i = meta & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(POWERED, Boolean.valueOf((meta & 8) > 0));
    }
	
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(POWERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
}
