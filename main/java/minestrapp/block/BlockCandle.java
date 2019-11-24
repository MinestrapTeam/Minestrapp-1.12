package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandle extends BlockBase implements IMetaBlockName
{
	private String type;
	
	protected static final AxisAlignedBB AABB_CANDLE = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.5D, 0.75D);
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	
	public BlockCandle(String name, String type)
	{
		super(name, Material.CORAL, MapColor.SNOW, SoundType.WOOD, 0.2F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
		if(type == "unlit")
			this.setCreativeTab(MTabs.decor);
		else if(type == "fire")
			this.setLightLevel(0.85F);
		else if(type == "ender")
			this.setLightLevel(0.6F);
		this.type = type;
		if(type != "unlit")
			this.setDropsItem(new ItemStack(MBlocks.candle), 0, 0, 0, false, false);
	}
	
	public boolean isLit() {return this.type != "unlit";}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		return AABB_CANDLE;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
	
	public int damageDropped(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
        {
            items.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.getBlockColor((EnumDyeColor)state.getValue(COLOR));
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {COLOR});
    }
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(MBlocks.candle, 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		Block block = worldIn.getBlockState(pos.down()).getBlock();
		
		if(worldIn.isSideSolid(pos.down(), EnumFacing.UP) || block.getBlockFaceShape(worldIn, worldIn.getBlockState(pos.down()), pos, EnumFacing.UP) == BlockFaceShape.CENTER || block.getBlockFaceShape(worldIn, worldIn.getBlockState(pos.down()), pos, EnumFacing.UP) == BlockFaceShape.CENTER_BIG)
			return true;
		
		return false;
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if(!worldIn.isRemote && !this.canPlaceBlockAt(worldIn, pos))
        {
        	this.dropBlockAsItem(worldIn, pos, state, 0);
        	worldIn.setBlockToAir(pos);
        }
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumDyeColor.byMetadata(stack.getMetadata()).getName();
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack itemstack = playerIn.getHeldItem(hand);
		
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return false;
        }
		else if(this.type == "unlit" && itemstack.getItem() instanceof ItemFlintAndSteel)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, MBlocks.candle_fire.getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.damageItem(1, playerIn);
			}
			return true;
		}
		else if(this.type == "unlit" && itemstack.getItem() == Items.DRAGON_BREATH)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, MBlocks.candle_ender.getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		else if((this.type == "fire" || this.type == "ender") && itemstack.getItem() == Items.POTIONITEM)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, MBlocks.candle.getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		
		return false;
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.5D;
        double d2 = (double)pos.getZ() + 0.5D;

        if(this.type == "fire")
        	worldIn.spawnParticle(EnumParticleTypes.FLAME, true, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        else if(this.type == "ender")
        	worldIn.spawnParticle(EnumParticleTypes.REDSTONE, true, d0, d1, d2, 1.0D, 0.0D, 1.0D);
    }
}
