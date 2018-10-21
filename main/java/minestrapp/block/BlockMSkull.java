package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.tileentity.TileEntityMSkull;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMSkull extends BlockBase implements ITileEntityProvider
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool WALL = PropertyBool.create("wall");
	public static final PropertyEnum<BlockMSkull.EnumAngle> ANGLE = PropertyEnum.<BlockMSkull.EnumAngle>create("angle", BlockMSkull.EnumAngle.class);
	
	private AxisAlignedBB FLOOR_AABB = BlockUtil.createBoundingBoxColumn(8, 8, 0);
	private AxisAlignedBB NORTH_AABB = BlockUtil.createBoundingBox(4, 4, 0, 12, 12, 8);
	private AxisAlignedBB EAST_AABB = BlockUtil.createBoundingBox(8, 4, 4, 16, 12, 12);
	private AxisAlignedBB SOUTH_AABB = BlockUtil.createBoundingBox(4, 4, 8, 12, 12, 16);
	private AxisAlignedBB WEST_AABB = BlockUtil.createBoundingBox(0, 4, 4, 8, 12, 12);
	
	public BlockMSkull(String name, double width, double height, double wallOffset)
	{
		super(name, Material.CIRCUITS, MapColor.AIR, SoundType.STONE, 1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WALL, false).withProperty(ANGLE, BlockMSkull.EnumAngle.ANGLE0));
		this.FLOOR_AABB = BlockUtil.createBoundingBoxColumn(width, height, 0);
		this.NORTH_AABB = BlockUtil.createBoundingBox(8 - (width / 2), 8 - (height / 2) + wallOffset, 0, 8 + (width / 2), 8 + (height / 2) + wallOffset, width);
		this.EAST_AABB = BlockUtil.createBoundingBox(16 - width, 8 - (height / 2) + wallOffset, 8 - (width / 2), 16, 8 + (height / 2) + wallOffset, 8 + (width / 2));
		this.SOUTH_AABB = BlockUtil.createBoundingBox(8 - (width / 2), 8 - (height / 2) + wallOffset, 16 - width, 8 + (width / 2), 8 + (height / 2) + wallOffset, 16);
		this.WEST_AABB = BlockUtil.createBoundingBox(0, 8 - (height / 2) + wallOffset, 8 - (width / 2), width, 8 + (height / 2) + wallOffset, 8 + (width / 2));
		if(this != MBlocks.skull_squid)
			this.setCreativeTab(MTabs.decor);
		this.hasTileEntity = true;
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        TileEntity tileentity = worldIn instanceof ChunkCache ? ((ChunkCache)worldIn).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : worldIn.getTileEntity(pos);
        BlockMSkull.EnumAngle angle = BlockMSkull.EnumAngle.ANGLE0;
        
        if (tileentity instanceof TileEntityMSkull)
        {
            TileEntityMSkull tileentityskull = (TileEntityMSkull)tileentity;
            angle = tileentityskull.getAngle();
        }
        
        return state.withProperty(ANGLE, angle);
    }
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	if(state.getValue(WALL).booleanValue() == false)
    		return FLOOR_AABB;
    	else
    	{
	        switch ((EnumFacing)state.getValue(FACING))
	        {
	            default:
	                return FLOOR_AABB;
	            case NORTH:
	                return NORTH_AABB;
	            case SOUTH:
	                return SOUTH_AABB;
	            case WEST:
	                return WEST_AABB;
	            case EAST:
	                return EAST_AABB;
	        }
    	}
    }
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
    	BlockMSkull.EnumAngle angle = BlockMSkull.EnumAngle.ANGLE0;
    	
    	if(state.getValue(WALL).booleanValue() == false)
    	{
    		double yaw = placer.rotationYaw;
			int rotOffset = 0;
			
			if(yaw >= 270)
				rotOffset = 270;
			else if(yaw >= 180)
				rotOffset = 180;
			else if(yaw >= 90)
				rotOffset = 90;
			
			double rotAngle = yaw - rotOffset;
			
			if(rotAngle >= 67.5)
				angle = BlockMSkull.EnumAngle.ANGLE3;
			else if(rotAngle >= 45)
				angle = BlockMSkull.EnumAngle.ANGLE2;
			else if(rotAngle >= 22.5)
				angle = BlockMSkull.EnumAngle.ANGLE1;
    	}
    	
    	if(worldIn.getTileEntity(pos) != null)
    	{
    		((TileEntityMSkull)worldIn.getTileEntity(pos)).setAngle(angle);
    	}
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	boolean onWall = true;
    	
    	if(facing.getAxis() == Axis.Y)
    		onWall = false;
    	
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(WALL, Boolean.valueOf(onWall));
    }
    
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {	
        return new TileEntityMSkull();
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(WALL, Boolean.valueOf((meta & 8) > 0));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(WALL)).booleanValue())
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
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, WALL, ANGLE});
    }
    
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        
        if(tileentity != null && tileentity instanceof TileEntityMSkull)
        	((TileEntityMSkull)tileentity).setAngle(state.getValue(ANGLE));
        	
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
    }
	
	public static enum EnumAngle implements IStringSerializable
    {
        ANGLE0(0, "angle0"),
        ANGLE1(1, "angle1"),
        ANGLE2(2, "angle2"),
        ANGLE3(3, "angle3");

        private static final BlockMSkull.EnumAngle[] META_LOOKUP = new BlockMSkull.EnumAngle[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumAngle(int metaIn, String nameIn)
        {
            this(metaIn, nameIn, nameIn);
        }

        private EnumAngle(int metaIn, String nameIn, String unlocalizedNameIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockMSkull.EnumAngle byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (BlockMSkull.EnumAngle blockplanks$enumtype : values())
            {
                META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
            }
        }
    }
}
