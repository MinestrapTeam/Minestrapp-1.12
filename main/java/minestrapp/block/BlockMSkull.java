package minestrapp.block;

import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMSkull extends BlockBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool WALL = PropertyBool.create("wall");
	public static final PropertyEnum<BlockMSkull.EnumAngle> ANGLE = PropertyEnum.<BlockMSkull.EnumAngle>create("angle", BlockMSkull.EnumAngle.class);
	
	private static AxisAlignedBB FLOOR_AABB = BlockUtil.createBoundingBoxColumn(8, 8, 0);
	private static AxisAlignedBB NORTH_AABB = BlockUtil.createBoundingBox(4, 4, 0, 12, 12, 8);
	private static AxisAlignedBB EAST_AABB = BlockUtil.createBoundingBox(8, 4, 4, 16, 12, 12);
	private static AxisAlignedBB SOUTH_AABB = BlockUtil.createBoundingBox(4, 4, 8, 12, 12, 16);
	private static AxisAlignedBB WEST_AABB = BlockUtil.createBoundingBox(0, 4, 4, 8, 12, 12);
	
	public BlockMSkull(String name, int width, int height)
	{
		super(name, Material.CIRCUITS, MapColor.AIR, SoundType.STONE, 1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WALL, false).withProperty(ANGLE, BlockMSkull.EnumAngle.ANGLE0));
		this.FLOOR_AABB = BlockUtil.createBoundingBoxColumn(width, height, 0);
		this.NORTH_AABB = BlockUtil.createBoundingBox(width / 2, height / 2, 0, 8 + (width / 2), 8 + (height / 2), width);
		this.EAST_AABB = BlockUtil.createBoundingBox(width, height / 2, width / 2, 16, 8 + (height / 2), 8 + (width / 2));
		this.SOUTH_AABB = BlockUtil.createBoundingBox(width / 2, height / 2, width, 8 + (width / 2), 8 + (height / 2), 16);
		this.WEST_AABB = BlockUtil.createBoundingBox(0, height / 2, width / 2, width, 8 + (height / 2), 8 + (width / 2));
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
