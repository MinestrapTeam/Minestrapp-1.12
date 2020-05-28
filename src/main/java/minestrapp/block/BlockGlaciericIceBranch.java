package minestrapp.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlaciericIceBranch extends BlockBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool BASE = PropertyBool.create("base");
	
	private int stage;
	
	public BlockGlaciericIceBranch(int stage)
	{
		super("glacieric_ice_branch_" + stage, Material.ROCK, MapColor.ICE, SoundType.GLASS, 0.7F, "pickaxe", 3);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(BASE, false));
		this.stage = stage;
		this.setSlipperiness(0.99F);
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
		this.setCreativeTab(MTabs.environment);
	}
	
	public static BlockGlaciericIceBranch getBranchBlock(int index)
	{
		if(index == 7)
			return MBlocks.glacieric_ice_branch_7;
		else if(index == 6)
			return MBlocks.glacieric_ice_branch_6;
		else if(index == 5)
			return MBlocks.glacieric_ice_branch_5;
		else if(index == 4)
			return MBlocks.glacieric_ice_branch_4;
		else if(index == 3)
			return MBlocks.glacieric_ice_branch_3;
		else if(index == 2)
			return MBlocks.glacieric_ice_branch_2;
		else if(index == 1)
			return MBlocks.glacieric_ice_branch_1;
		else
			return MBlocks.glacieric_ice_branch_0;
	}
	
	public int getStage()
	{
		return this.stage;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		int offset = 0;
		int height = 16;
		
		if(this.stage < 7)
		{
			offset = 7 - this.stage;
			height = 15;
		}
		
        if(blockState.getValue(FACING) == EnumFacing.UP)
        {
        	if(this.stage < 7)
        		offset--;
        	offset *= -1;
        	return BlockUtil.createBoundingBoxColumn((this.stage + 1) * 2, height, offset);
        }
        else if(blockState.getValue(FACING) == EnumFacing.DOWN)
        {
        	return BlockUtil.createBoundingBoxColumn((this.stage + 1) * 2, height, offset);
        }
        else if(blockState.getValue(FACING) == EnumFacing.NORTH)
        {
        	return BlockUtil.createBoundingBoxColumnNS((this.stage + 1) * 2, height, offset);
        }
        else if(blockState.getValue(FACING) == EnumFacing.SOUTH)
        {
        	if(this.stage < 7)
        		offset--;
        	offset *= -1;
        	return BlockUtil.createBoundingBoxColumnNS((this.stage + 1) * 2, height, offset);
        }
        else if(blockState.getValue(FACING) == EnumFacing.EAST)
        {
        	if(this.stage < 7)
        		offset--;
        	offset *= -1;
        	return BlockUtil.createBoundingBoxColumnEW((this.stage + 1) * 2, height, offset);
        }
        else if(blockState.getValue(FACING) == EnumFacing.WEST)
        {
        	return BlockUtil.createBoundingBoxColumnEW((this.stage + 1) * 2, height, offset);
        }
        else
        {
        	return FULL_BLOCK_AABB;
        }
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		if(!(worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock() instanceof BlockGlaciericIceBranch))
			return state.withProperty(BASE, true);
		else
			return state;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
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
        return new BlockStateContainer(this, new IProperty[] {FACING, BASE});
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(MBlocks.glacieric_ice_branch_0);
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote && playerIn.getHeldItem(hand).getItem() == MItems.gems && playerIn.getHeldItem(hand).getMetadata() == 6 && this.stage != 7 && getBranchBlock(this.stage + 1).canPlaceBlock(worldIn, pos, state.getValue(FACING)))
        {
        	if(!playerIn.capabilities.isCreativeMode)
        		playerIn.getHeldItem(hand).shrink(1);
        	worldIn.setBlockState(pos, getBranchBlock(this.stage + 1).getDefaultState().withProperty(FACING, state.getValue(FACING)));
        	return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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
    
    private boolean canPlaceBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
        BlockPos blockpos = pos.offset(direction.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == BlockFaceShape.SOLID;
        Block block = iblockstate.getBlock();

        if(iblockstate.getBlock() instanceof BlockGlaciericIceBranch && ((BlockGlaciericIceBranch)iblockstate.getBlock()).getStage() > this.stage)
        	return true;
        if (direction == EnumFacing.UP)
            return iblockstate.getBlock() == MBlocks.glacieric_ice_deposit || iblockstate.isTopSolid() || !isExceptionBlockForAttaching(block) && flag;
        else
            return !isExceptBlockForAttachWithPiston(block) && flag;
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
            worldIn.setBlockToAir(pos);
            return false;
        }
    }
}
