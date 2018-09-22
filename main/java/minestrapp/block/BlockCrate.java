package minestrapp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.MBlockTileEntity;
import minestrapp.block.tileentity.TileEntityCrate;
import minestrapp.gui.MGuiHandler;

import javax.annotation.Nullable;

public class BlockCrate extends BlockContainer
{
	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);
	
	public BlockCrate()
	{
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
		this.setHardness(1F);
		this.setHarvestLevel("axe", 0);
		this.setResistance(2F);
		this.setCreativeTab(MTabs.utility);
		this.setUnlocalizedName("crate");
		this.setRegistryName("crate");
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.WOOD;
    }
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
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
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);

            if (ilockablecontainer != null)
            {
                playerIn.openGui(Minestrapp5.instance, MGuiHandler.CRATE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
    
    @Nullable
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos)
    {
        return this.getContainer(worldIn, pos, false);
    }
    
    @Nullable
    public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityCrate))
        {
            return null;
        }
        else
        {
            ILockableContainer ilockablecontainer = (TileEntityCrate)tileentity;

            return ilockablecontainer;
        }
    }

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityCrate)
            {
                ((TileEntityCrate)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCrate();
	}
	
	public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
	
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstoneFromInventory(this.getLockableContainer(worldIn, pos));
    }
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
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
        EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Y;
        int i = meta & 12;

        if (i == 4)
        {
            enumfacing$axis = EnumFacing.Axis.X;
        }
        else if (i == 8)
        {
            enumfacing$axis = EnumFacing.Axis.Z;
        }

        return this.getDefaultState().withProperty(AXIS, enumfacing$axis);
    }
	
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);

        if (enumfacing$axis == EnumFacing.Axis.X)
        {
            i |= 4;
        }
        else if (enumfacing$axis == EnumFacing.Axis.Z)
        {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AXIS});
    }

    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this));
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, facing.getAxis());
    }
}