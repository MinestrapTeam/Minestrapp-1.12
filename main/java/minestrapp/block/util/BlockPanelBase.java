package minestrapp.block.util;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPanelBase extends BlockBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(@Nullable EnumFacing p_apply_1_)
        {
            return p_apply_1_ != EnumFacing.DOWN;
        }
    });
	
	/**
	 
	The quadrant to which the block is connected. Quadrants are assigned an int value as follows:
	0 = Not Connected
	
	     N
	   1 | 2
	 W --+-- E
	   3 | 4
	     S
	     
	**/
	public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 5);
	
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0.5D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.5D, 1D, 1D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.5D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.5D, 0D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SE = new AxisAlignedBB(0.5D, 0D, 0.5D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SW = new AxisAlignedBB(0D, 0D, 0.5D, 0.5D, 1D, 1D);
	protected static final AxisAlignedBB AABB_NE = new AxisAlignedBB(0.5D, 0D, 0D, 1D, 1D, 0.5D);
	protected static final AxisAlignedBB AABB_NW = new AxisAlignedBB(0D, 0D, 0D, 0.5D, 1D, 0.5D);
	
	private final Block block;
	private final int meta;
	
	public BlockPanelBase(Block block, CreativeTabs tab)
	{
		super("panel_" + block.getUnlocalizedName().substring(5), block.getMaterial(block.getDefaultState()), MapColor.AIR, block.getSoundType(), 0F, block.getHarvestTool(block.getDefaultState()), block.getHarvestLevel(block.getDefaultState()));
		this.block = block;
		this.meta = 0;
		this.setLightOpacity(255);
		this.useNeighborBrightness = true;
		this.setCreativeTab(tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CONNECTED, Integer.valueOf(0)));
	}
	
	public BlockPanelBase(Block block, CreativeTabs tab, int meta, String variant)
	{
		super("panel_" + block.getUnlocalizedName().substring(5) + "_" + variant, block.getStateFromMeta(meta).getMaterial(), MapColor.AIR, block.getSoundType(), 0F, block.getHarvestTool(block.getStateFromMeta(meta)), block.getHarvestLevel(block.getStateFromMeta(meta)));
		this.block = block;
		this.meta = meta;
		this.setLightOpacity(255);
		this.useNeighborBrightness = true;
		this.setCreativeTab(tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CONNECTED, Integer.valueOf(0)));
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        	state = state.getActualState(worldIn, pos);
        
        EnumFacing facing = state.getValue(FACING);
        int connected = Integer.valueOf(state.getValue(CONNECTED));
        
        if(facing == EnumFacing.UP)
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
        else if(facing == EnumFacing.NORTH)
        {
        	if(connected != 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected != 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected == 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected == 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
        else if(facing == EnumFacing.SOUTH)
        {
        	if(connected == 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected == 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected != 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected != 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
        else if(facing == EnumFacing.EAST)
        {
        	if(connected == 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected != 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected == 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected != 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
        else if(facing == EnumFacing.WEST)
        {
        	if(connected != 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected == 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected != 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected == 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
    }
	
	private static List<AxisAlignedBB> getCollisionBoxList(IBlockState state)
    {
		List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
		EnumFacing facing = state.getValue(FACING);
        int connected = Integer.valueOf(state.getValue(CONNECTED));
        
        if(facing == EnumFacing.UP)
        	list.add(FULL_BLOCK_AABB);
        else if(facing == EnumFacing.NORTH)
        {
        	if(connected != 2)
				list.add(AABB_NW);
        	if(connected != 1)
        		list.add(AABB_NE);
        	if(connected == 3)
        		list.add(AABB_SW);
        	if(connected == 4)
        		list.add(AABB_SE);
        }
        else if(facing == EnumFacing.SOUTH)
        {
        	if(connected == 1)
        		list.add(AABB_NW);
        	if(connected == 2)
        		list.add(AABB_NE);
        	if(connected != 4)
        		list.add(AABB_SW);
        	if(connected != 3)
        		list.add(AABB_SE);
        }
        else if(facing == EnumFacing.EAST)
        {
        	if(connected == 1)
        		list.add(AABB_NW);
        	if(connected != 4)
        		list.add(AABB_NE);
        	if(connected == 3)
        		list.add(AABB_SW);
        	if(connected != 2)
        		list.add(AABB_SE);
        }
        else if(facing == EnumFacing.WEST)
        {
        	if(connected != 3)
        		list.add(AABB_NW);
        	if(connected == 2)
        		list.add(AABB_NE);
        	if(connected != 1)
        		list.add(AABB_SW);
        	if(connected == 4)
        		list.add(AABB_SE);
        }
        
        return list;
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		IBlockState state = this.getActualState(blockState, worldIn, pos);
		EnumFacing facing = blockState.getValue(FACING);
		int connected = state.getValue(CONNECTED);
		
		if(facing == EnumFacing.NORTH)
        {
        	if(connected == 0)
        		return AABB_NORTH;
        	if(connected == 1)
        		return AABB_NW;
        	if(connected == 2)
        		return AABB_NE;
        }
		if(facing == EnumFacing.SOUTH)
        {
        	if(connected == 0)
        		return AABB_SOUTH;
        	if(connected == 3)
        		return AABB_SW;
        	if(connected == 4)
        		return AABB_SE;
        }
		if(facing == EnumFacing.EAST)
        {
        	if(connected == 0)
        		return AABB_EAST;
        	if(connected == 2)
        		return AABB_NE;
        	if(connected == 4)
        		return AABB_SE;
        }
		if(facing == EnumFacing.WEST)
        {
        	if(connected == 0)
        		return AABB_WEST;
        	if(connected == 1)
        		return AABB_NW;
        	if(connected == 3)
        		return AABB_SW;
        }
        return FULL_BLOCK_AABB;
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        int quadrant = 0;
        EnumFacing facing = state.getValue(FACING);
        
        if (facing == EnumFacing.UP)
        	return state;
        
        boolean connectedLeft = false;
        IBlockState leftState = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
        boolean connectedRight = false;
        IBlockState rightState = worldIn.getBlockState(pos.offset(facing.rotateY()));
        
        if(leftState.getBlock() instanceof BlockPanelBase && leftState.getValue(FACING) == facing)
        	connectedLeft = true;
        if(rightState.getBlock() instanceof BlockPanelBase && rightState.getValue(FACING) == facing)
        	connectedRight = true;
        
        EnumFacing facingFace = null;
        IBlockState facingState = worldIn.getBlockState(pos.offset(facing));
        EnumFacing oppositeFace = null;
        IBlockState oppositeState = worldIn.getBlockState(pos.offset(facing.getOpposite()));
        
        if(facingState.getBlock() instanceof BlockPanelBase)
        	facingFace = facingState.getValue(FACING);
        if(oppositeState.getBlock() instanceof BlockPanelBase)
        	oppositeFace = oppositeState.getValue(FACING);
        
        if(oppositeFace != null && oppositeFace != EnumFacing.UP && oppositeFace.getAxis() != facing.getAxis())
        {
        	if(facing == EnumFacing.NORTH)
        	{
        		if(oppositeFace == EnumFacing.EAST)
        			quadrant = 4;
        		else if(oppositeFace == EnumFacing.WEST)
        			quadrant = 3;
        	}
        	else if(facing == EnumFacing.SOUTH)
        	{
        		if(oppositeFace == EnumFacing.EAST)
        			quadrant = 2;
        		else if(oppositeFace == EnumFacing.WEST)
        			quadrant = 1;
        	}
        	else if(facing == EnumFacing.EAST)
        	{
        		if(oppositeFace == EnumFacing.NORTH)
        			quadrant = 1;
        		else if(oppositeFace == EnumFacing.SOUTH)
        			quadrant = 3;
        	}
        	else if(facing == EnumFacing.WEST)
        	{
        		if(oppositeFace == EnumFacing.NORTH)
        			quadrant = 2;
        		else if(oppositeFace == EnumFacing.SOUTH)
        			quadrant = 4;
        	}
        }
        else if(!(connectedLeft && connectedRight) && facingFace != null && facingFace != EnumFacing.UP && facingFace.getAxis() != facing.getAxis())
        {
        	if(facing == EnumFacing.NORTH)
        	{
        		if(facingFace == EnumFacing.EAST && !connectedLeft)
        			quadrant = 2;
        		else if(facingFace == EnumFacing.WEST && !connectedRight)
        			quadrant = 1;
        	}
        	else if(facing == EnumFacing.SOUTH)
        	{
        		if(facingFace == EnumFacing.EAST && !connectedRight)
        			quadrant = 4;
        		else if(facingFace == EnumFacing.WEST && !connectedLeft)
        			quadrant = 3;
        	}
        	else if(facing == EnumFacing.EAST)
        	{
        		if(facingFace == EnumFacing.NORTH && !connectedRight)
        			quadrant = 2;
        		else if(facingFace == EnumFacing.SOUTH && !connectedLeft)
        			quadrant = 4;
        	}
        	else if(facing == EnumFacing.WEST)
        	{
        		if(facingFace == EnumFacing.NORTH && !connectedLeft)
        			quadrant = 1;
        		else if(facingFace == EnumFacing.SOUTH && !connectedRight)
        			quadrant = 3;
        	}
        }
        
        return state.withProperty(CONNECTED, Integer.valueOf(quadrant));
    }
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
		if(state.getValue(FACING) == EnumFacing.UP || state.getValue(FACING) == face)
			return BlockFaceShape.SOLID;
		else
			return BlockFaceShape.UNDEFINED;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return state.getValue(FACING) == EnumFacing.UP;
    }

    public boolean isFullCube(IBlockState state)
    {
    	return state.getValue(FACING) == EnumFacing.UP;
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        return i;
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		if(facing.getAxis().isHorizontal())
			return this.getDefaultState().withProperty(FACING, facing.getOpposite());
		else
		{
			EnumFacing playerDir = placer.getHorizontalFacing();
			if(playerDir.getAxis() == EnumFacing.Axis.Z)
			{
				if(hitZ > 0.5F)
					return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
				else
					return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
			}
			else
			{
				if(hitX > 0.5F)
					return this.getDefaultState().withProperty(FACING, EnumFacing.EAST);
				else
					return this.getDefaultState().withProperty(FACING, EnumFacing.WEST);
			}
		}
    }
    
	public static EnumFacing getFacing(int meta)
    {
    	return EnumFacing.getFront(meta & 7);
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
        return new BlockStateContainer(this, new IProperty[] {FACING, CONNECTED});
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        this.block.randomDisplayTick(stateIn, worldIn, pos, rand);
    }

    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        this.block.onBlockClicked(worldIn, pos, playerIn);
    }

    /**
     * Called after a player destroys this Block - the posiiton pos may no longer hold the state indicated.
     */
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
        this.block.onBlockDestroyedByPlayer(worldIn, pos, state);
    }

    @SideOnly(Side.CLIENT)
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return this.block.getStateFromMeta(meta).getPackedLightmapCoords(source, pos);
    }

    @Deprecated
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return this.block.getBlockHardness(this.block.getStateFromMeta(this.meta), worldIn, pos);
    }
    
    /**
     * Returns how much this block can resist explosions from the passed in entity.
     */
    public float getExplosionResistance(Entity exploder)
    {
        return this.block.getExplosionResistance(exploder);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World worldIn)
    {
        return this.block.tickRate(worldIn);
    }

    public Vec3d modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3d motion)
    {
        return this.block.modifyAcceleration(worldIn, pos, entityIn, motion);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return this.block.getBlockLayer();
    }

    /**
     * Returns if this block is collidable. Only used by fire, although stairs return that of the block that the stair
     * is made of (though nobody's going to make fire stairs, right?)
     */
    public boolean isCollidable()
    {
        return this.block.isCollidable();
    }

    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return this.block.canCollideCheck(state, hitIfLiquid);
    }

    /**
     * Checks if this block can be placed exactly at the given position.
     */
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return this.block.canPlaceBlockAt(worldIn, pos);
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.block.getStateFromMeta(meta).neighborChanged(worldIn, pos, Blocks.AIR, pos);
        this.block.onBlockAdded(worldIn, pos, this.block.getStateFromMeta(meta));
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        this.block.breakBlock(worldIn, pos, this.block.getStateFromMeta(meta));
    }

    /**
     * Called when the given entity walks on this Block
     */
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        this.block.onEntityWalk(worldIn, pos, entityIn);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.block.updateTick(worldIn, pos, state, rand);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return this.block.onBlockActivated(worldIn, pos, this.block.getStateFromMeta(meta), playerIn, hand, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Called when this Block is destroyed by an Explosion
     */
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        this.block.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return this.block.getMapColor(this.block.getStateFromMeta(meta), worldIn, pos);
    }
    
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end)
    {
        List<RayTraceResult> list = Lists.<RayTraceResult>newArrayList();

        for (AxisAlignedBB axisalignedbb : getCollisionBoxList(this.getActualState(blockState, worldIn, pos)))
        {
            list.add(this.rayTrace(pos, start, end, axisalignedbb));
        }

        RayTraceResult raytraceresult1 = null;
        double d1 = 0.0D;

        for (RayTraceResult raytraceresult : list)
        {
            if (raytraceresult != null)
            {
                double d0 = raytraceresult.hitVec.squareDistanceTo(end);

                if (d0 > d1)
                {
                    raytraceresult1 = raytraceresult;
                    d1 = d0;
                }
            }
        }

        return raytraceresult1;
    }
    
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (!worldIn.isRemote && !worldIn.restoringBlockSnapshots)
        {
        	if(state.getValue(FACING) == EnumFacing.UP)
        		spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 2, 0));
        	else
        		spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1, 0));
        }
    }
    
    public boolean canSilkHarvest()
	{
		return false;
	}
    
    public BlockPanelBase setFlammable(int flammability, int fireSpread, int meta)
	{
		super.setFlammable(flammability, fireSpread, meta);
		return this;
	}
    
    public BlockPanelBase setFlammable(int flammability, int fireSpread)
	{
		super.setFlammable(flammability, fireSpread);
		return this;
	}
}
