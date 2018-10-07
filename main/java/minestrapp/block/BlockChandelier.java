package minestrapp.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChandelier extends BlockBase implements IMetaBlockName
{
	private String type;
	private Block unlit;
	private Block fire;
	private Block ender;
	private static double stemWidth;
	private static double stemHeight;
	private static double stemOffset;
	private static double baseWidth;
	private static double baseHeight;
	private static double baseOffset;
	
	//protected static final AxisAlignedBB AABB_STEM = new AxisAlignedBB(0.3125D, 0.125D, 0.3125D, 0.6875D, 1D, 0.6875D);
	//protected static final AxisAlignedBB AABB_BASE = new AxisAlignedBB(-0.125D, -0.0625D, -0.125D, 1.125D, 0.125D, 1.125D);
	
	protected static final AxisAlignedBB AABB_STEM = BlockUtil.createBoundingBoxColumn(6, 14, 2);
	protected static final AxisAlignedBB AABB_BASE = BlockUtil.createBoundingBoxColumn(20, 4, -2);
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	
	public BlockChandelier(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int level, String type, Block unlit, Block fire, Block ender)
	{
		super(name, material, mapColor, soundType, hardness, tool, level);
		this.type = type;
		if(type == "unlit")
			this.setCreativeTab(MTabs.decor);
		else
		{	
			if(type == "fire")
				this.setLightLevel(0.9F);
			else if(type == "ender")
				this.setLightLevel(0.75F);
		}
		
		stemWidth = 2;
		stemHeight = 8;
		stemOffset = 8;
		baseWidth = 16;
		baseHeight = 2;
		baseOffset = 6;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public BlockChandelier setUnlitBlock(Block block)
	{
		this.unlit = block;
		this.setDropsItem(new ItemStack(this.unlit), 0, 0, 0, false, false);
		return this;
	}
	
	public BlockChandelier setFireBlock(Block block)
	{
		this.fire = block;
		return this;
	}
	
	public BlockChandelier setEnderBlock(Block block)
	{
		this.ender = block;
		return this;
	}
	
	public Block getReactionBlock(String type)
	{
		if(type == "unlit")
			return this.unlit;
		else if(type == "fire")
			return this.fire;
		else
			return this.ender;
	}
	
	public BlockChandelier setHitboxDimensions(double stemWidth, double stemHeight, double stemOffset, double baseWidth, double baseHeight, double baseOffset)
	{
		this.stemWidth = stemWidth;
		this.stemHeight = stemHeight;
		this.stemOffset = stemOffset;
		this.baseWidth = baseWidth;
		this.baseHeight = baseHeight;
		this.baseOffset = baseOffset;
		
		return this;
	}
	
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, BlockUtil.createBoundingBoxColumn(stemWidth, stemHeight, stemOffset));
        addCollisionBoxToList(pos, entityBox, collidingBoxes, BlockUtil.createBoundingBoxColumn(baseWidth, baseHeight, baseOffset));
    }
	
	private static List<AxisAlignedBB> getCollisionBoxList(IBlockState state)
    {
		List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
		
		list.add(BlockUtil.createBoundingBoxColumn(stemWidth, stemHeight, stemOffset));
		list.add(BlockUtil.createBoundingBoxColumn(baseWidth, baseHeight, baseOffset));
		
		return list;
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		return FULL_BLOCK_AABB;
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
		return new ItemStack(this.unlit, 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		Block block = worldIn.getBlockState(pos.down()).getBlock();
		
		if((worldIn.isSideSolid(pos.up(), EnumFacing.DOWN) || block.getBlockFaceShape(worldIn, worldIn.getBlockState(pos.up()), pos, EnumFacing.DOWN) == BlockFaceShape.CENTER || block.getBlockFaceShape(worldIn, worldIn.getBlockState(pos.up()), pos, EnumFacing.DOWN) == BlockFaceShape.CENTER_BIG) && worldIn.getBlockState(pos.north()).getBlock().isReplaceable(worldIn, pos.north()) && worldIn.getBlockState(pos.east()).getBlock().isReplaceable(worldIn, pos.east()) && worldIn.getBlockState(pos.south()).getBlock().isReplaceable(worldIn, pos.south()) && worldIn.getBlockState(pos.west()).getBlock().isReplaceable(worldIn, pos.west()) && worldIn.getBlockState(pos.down()).getBlock().isReplaceable(worldIn, pos.down()))
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
		System.out.println("activated");
		ItemStack itemstack = playerIn.getHeldItem(hand);
		
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return false;
        }
		else if(this.type == "unlit" && itemstack.getItem() instanceof ItemFlintAndSteel)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, this.fire.getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.damageItem(1, playerIn);
			}
			return true;
		}
		else if(this.type == "unlit" && itemstack.getItem() == Items.DRAGON_BREATH)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, this.ender.getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		else if((this.type == "fire" || this.type == "ender") && itemstack.getItem() == Items.POTIONITEM)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, this.unlit.getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
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
