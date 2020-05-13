package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEndermiteHiveHusk extends BlockBase
{
	public static final PropertyEnum<BlockEndermiteHiveHusk.EnumType> VARIANT = PropertyEnum.<BlockEndermiteHiveHusk.EnumType>create("variant", BlockEndermiteHiveHusk.EnumType.class);
	
	private boolean honeyed;
	
	public BlockEndermiteHiveHusk(String name, boolean honeyed)
	{
		super(name, Material.GOURD, MapColor.PINK, SoundType.WOOD, 0.4F, "axe", 0);
		this.setCreativeTab(MTabs.environment);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.ALL_OUTSIDE));
		this.honeyed = honeyed;
		this.setFlammable(20, 5);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MBlocks.mite_comb);
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        switch ((BlockEndermiteHiveHusk.EnumType)state.getValue(VARIANT))
        {
            case ALL_INSIDE:
                return MapColor.MAGENTA_STAINED_HARDENED_CLAY;
            default:
                return super.getMapColor(state, worldIn, pos);
        }
    }

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockEndermiteHiveHusk.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case CLOCKWISE_180:

                switch ((BlockEndermiteHiveHusk.EnumType)state.getValue(VARIANT))
                {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_EAST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.EAST);
                    case EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.WEST);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_EAST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_WEST);
                    default:
                        return state;
                }

            case COUNTERCLOCKWISE_90:

                switch ((BlockEndermiteHiveHusk.EnumType)state.getValue(VARIANT))
                {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_WEST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.WEST);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH);
                    case EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_EAST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.EAST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_EAST);
                    default:
                        return state;
                }

            case CLOCKWISE_90:

                switch ((BlockEndermiteHiveHusk.EnumType)state.getValue(VARIANT))
                {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_EAST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.EAST);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_EAST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH);
                    case EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_WEST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.WEST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_WEST);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }
	
	@SuppressWarnings("incomplete-switch")
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        BlockEndermiteHiveHusk.EnumType BlockEndermiteHiveHusk$enumtype = (BlockEndermiteHiveHusk.EnumType)state.getValue(VARIANT);

        switch (mirrorIn)
        {
            case LEFT_RIGHT:

                switch (BlockEndermiteHiveHusk$enumtype)
                {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_WEST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_EAST);
                    case WEST:
                    case EAST:
                    default:
                        return super.withMirror(state, mirrorIn);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_WEST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_EAST);
                }

            case FRONT_BACK:

                switch (BlockEndermiteHiveHusk$enumtype)
                {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_EAST);
                    case NORTH:
                    case SOUTH:
                    default:
                        break;
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.EAST);
                    case EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.WEST);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_EAST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_WEST);
                }
        }

        return super.withMirror(state, mirrorIn);
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
    {
        IBlockState state = world.getBlockState(pos);
        for (IProperty prop : (java.util.Set<IProperty<?>>)state.getProperties().keySet())
        {
            if (prop.getName().equals("variant"))
            {
                world.setBlockState(pos, state.cycleProperty(prop));
                return true;
            }
        }
        return false;
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote && state.getBlock() == MBlocks.mite_hive_honeyed)
		{
			EntityItem item = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(MItems.mite_honey));
			
			worldIn.spawnEntity(item);
			worldIn.setBlockState(pos, MBlocks.mite_hive.getDefaultState().withProperty(this.VARIANT, state.getValue(VARIANT)));
			
			return true;
		}
		
		return false;
    }
	
	public static enum EnumType implements IStringSerializable
    {
        NORTH_WEST(1, "north_west"),
        NORTH(2, "north"),
        NORTH_EAST(3, "north_east"),
        WEST(4, "west"),
        CENTER(5, "center"),
        EAST(6, "east"),
        SOUTH_WEST(7, "south_west"),
        SOUTH(8, "south"),
        SOUTH_EAST(9, "south_east"),
        ALL_INSIDE(10, "all_inside"),
        ALL_OUTSIDE(0, "all_outside");

        private static final BlockEndermiteHiveHusk.EnumType[] META_LOOKUP = new BlockEndermiteHiveHusk.EnumType[16];
        private final int meta;
        private final String name;

        private EnumType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockEndermiteHiveHusk.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            BlockEndermiteHiveHusk.EnumType blockEndermiteHiveHusk$enumtype = META_LOOKUP[meta];
            return blockEndermiteHiveHusk$enumtype == null ? META_LOOKUP[0] : blockEndermiteHiveHusk$enumtype;
        }

        public String getName()
        {
            return this.name;
        }

        static
        {
            for (BlockEndermiteHiveHusk.EnumType blockEndermiteHiveHusk$enumtype : values())
            {
                META_LOOKUP[blockEndermiteHiveHusk$enumtype.getMetadata()] = blockEndermiteHiveHusk$enumtype;
            }
        }
    }
}
