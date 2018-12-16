package minestrapp.block;

import java.util.List;

import minestrapp.MBlocks;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpike extends BlockBase
{
	private int harvestLevel;
	private float baseDamage;
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	protected static final AxisAlignedBB AABB_UP = BlockUtil.createBoundingBox(0, 0, 0, 16, 15, 16);
	protected static final AxisAlignedBB AABB_DOWN = BlockUtil.createBoundingBox(0, 1, 0, 16, 16, 16);
	protected static final AxisAlignedBB AABB_SOUTH = BlockUtil.createBoundingBox(0, 0, 0, 16, 16, 15);
	protected static final AxisAlignedBB AABB_WEST = BlockUtil.createBoundingBox(1, 0, 0, 16, 16, 16);
	protected static final AxisAlignedBB AABB_NORTH = BlockUtil.createBoundingBox(0, 0, 1, 16, 16, 16);
	protected static final AxisAlignedBB AABB_EAST = BlockUtil.createBoundingBox(0, 0, 0, 15, 16, 16);
	
	public BlockSpike(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel, ToolMaterial toolmat)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
		this.harvestLevel = toolmat.getHarvestLevel();
		this.baseDamage = 3 + toolmat.getAttackDamage();
	}

	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if(blockState.getValue(FACING) == EnumFacing.UP)
        	return AABB_UP;
        if(blockState.getValue(FACING) == EnumFacing.DOWN)
        	return AABB_DOWN;
        if(blockState.getValue(FACING) == EnumFacing.NORTH)
        	return AABB_NORTH;
        if(blockState.getValue(FACING) == EnumFacing.EAST)
        	return AABB_EAST;
        if(blockState.getValue(FACING) == EnumFacing.SOUTH)
        	return AABB_SOUTH;
        if(blockState.getValue(FACING) == EnumFacing.WEST)
        	return AABB_WEST;
        else
        	return AABB_UP;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing);
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
    
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
    	if(worldIn.getBlockState(pos).getValue(this.FACING) == EnumFacing.UP)
    		entityIn.fall(fallDistance + this.baseDamage, 1.5F);
    	else
    		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    }
    
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	if(!(entityIn instanceof EntityItem))
    		entityIn.attackEntityFrom(DamageSource.CACTUS, this.baseDamage / 2);
    }
    
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	IBlockState toBreak = worldIn.getBlockState(pos.offset(state.getValue(this.FACING)));
    	
    	for(int i = 0 ; i < 12 ; i++)
    	{
    		IBlockState behind = worldIn.getBlockState(pos.offset(state.getValue(this.FACING).getOpposite(), i));
    		
    		if(behind.getBlock() == Blocks.PISTON_EXTENSION || behind.getBlock() == MBlocks.magnet_piston_extension)
    		{
    			if(toBreak.getBlockHardness(worldIn, pos.offset(state.getValue(this.FACING))) >= 0 && toBreak.getBlock().getHarvestLevel(toBreak) <= this.harvestLevel && toBreak.getBlock() != Blocks.AIR && !(toBreak.getBlock() instanceof BlockSpike))
    				worldIn.destroyBlock(pos.offset(state.getValue(this.FACING)), true);
    			
    			int x = pos.getX();
    			int y = pos.getY();
    			int z = pos.getZ();
    			List<EntityLiving> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, BlockUtil.createBoundingBox(0, 0, 0, 16, 16, 16).offset(pos.offset(state.getValue(this.FACING))));
    			List<EntityPlayer> players = worldIn.getEntitiesWithinAABB(EntityPlayer.class, BlockUtil.createBoundingBox(0, 0, 0, 16, 16, 16).offset(pos.offset(state.getValue(this.FACING))));
    			
    			for(EntityLiving living : entities)
    			{
    				living.attackEntityFrom(DamageSource.CACTUS, this.baseDamage * 2.5F);
    			}
    			for(EntityPlayer player : players)
    			{
    				player.attackEntityFrom(DamageSource.CACTUS, this.baseDamage * 2.5F);
    			}
    			
    			break;
    		}
    	}
    }
}
