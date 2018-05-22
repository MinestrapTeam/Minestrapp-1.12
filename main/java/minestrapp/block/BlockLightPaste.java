package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLightPaste extends BlockBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public BlockLightPaste()
	{
		super("glow_paste", Material.AIR, MapColor.AIR, SoundType.SLIME, -1F);
		this.setPushReaction(EnumPushReaction.DESTROY);
		this.setBlockUnbreakable();
		this.setLightLevel(1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		this.setCreativeTab(MTabs.ingredients);
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }
	
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return false;
    }
	
	public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos)
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
        return NULL_AABB;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
    
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return canPlaceBlock(worldIn, pos, side);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (canPlaceBlock(worldIn, pos, enumfacing))
            {
                return true;
            }
        }

        return false;
    }
    
    public static boolean canPlaceBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
        BlockPos blockpos = pos.offset(direction.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == BlockFaceShape.SOLID;
        Block block = iblockstate.getBlock();

        if (direction == EnumFacing.UP)
        {
            return iblockstate.isTopSolid() || !isExceptionBlockForAttaching(block) && flag;
        }
        else
        {
            return !isExceptBlockForAttachWithPiston(block) && flag;
        }
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return canPlaceBlock(worldIn, pos, facing) ? this.getDefaultState().withProperty(FACING, facing) : this.getDefaultState().withProperty(FACING, EnumFacing.DOWN);
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (this.checkForDrop(worldIn, pos, state) && !canPlaceBlock(worldIn, pos, (EnumFacing)state.getValue(FACING)))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
    }

    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.canPlaceBlockAt(worldIn, pos))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;
        IBlockState state = this.getDefaultState();
        
        if(meta == 0)
        	enumfacing = EnumFacing.UP;
        else if(meta == 1)
        	enumfacing = EnumFacing.DOWN;
        else if(meta == 2)
        	enumfacing = EnumFacing.NORTH;
        else if(meta == 3)
        	enumfacing = EnumFacing.SOUTH;
        else if(meta == 4)
        	enumfacing = EnumFacing.EAST;
        else
        	enumfacing = EnumFacing.WEST;
        
        return state.withProperty(FACING, enumfacing);
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	int i;
    	EnumFacing enumfacing = state.getValue(FACING);
    	
    	if(enumfacing == EnumFacing.UP)
    		i = 0;
    	else if(enumfacing == EnumFacing.DOWN)
    		i = 1;
    	else if(enumfacing == EnumFacing.NORTH)
    		i = 2;
    	else if(enumfacing == EnumFacing.SOUTH)
    		i = 3;
    	else if(enumfacing == EnumFacing.EAST)
    		i = 4;
    	else
    		i = 5;
    	
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

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    }
    
    public int quantityDropped(Random random)
    {
		return 0;
    }
    
    public boolean canSilkHarvest()
	{
		return false;
	}
}
