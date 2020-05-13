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

public class BlockCandleGlowing extends BlockCandleBase implements IMetaBlockName
{
	private String type;
	
	public static final PropertyEnum<BlockMGlowDyed.EnumGlowDye> GLOWCOLOR = PropertyEnum.<BlockMGlowDyed.EnumGlowDye>create("color", BlockMGlowDyed.EnumGlowDye.class);
	
	public BlockCandleGlowing(String name, String type)
	{
		super(name, type);
		this.setDefaultState(this.blockState.getBaseState().withProperty(GLOWCOLOR, BlockMGlowDyed.EnumGlowDye.WHITE));
		this.setGlowing();
		this.type = type;
		if(type != "unlit")
			this.setDropsItem(new ItemStack(MBlocks.candle2), 0, 0, 0, false, false);
		else
			this.setLightLevel(0.3F);
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockMGlowDyed.EnumGlowDye)state.getValue(GLOWCOLOR)).getMetadata();
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockMGlowDyed.EnumGlowDye enumdyecolor : BlockMGlowDyed.EnumGlowDye.values())
        {
            items.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.getValue(GLOWCOLOR).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(GLOWCOLOR, BlockMGlowDyed.EnumGlowDye.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockMGlowDyed.EnumGlowDye)state.getValue(GLOWCOLOR)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {GLOWCOLOR});
    }
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(MBlocks.candle2, 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockMGlowDyed.EnumGlowDye.byMetadata(stack.getMetadata()).getName();
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
				worldIn.setBlockState(pos, MBlocks.candle2_fire.getDefaultState().withProperty(GLOWCOLOR, worldIn.getBlockState(pos).getValue(GLOWCOLOR)));
				itemstack.damageItem(1, playerIn);
			}
			return true;
		}
		else if(this.type == "unlit" && itemstack.getItem() == Items.DRAGON_BREATH)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, MBlocks.candle2_ender.getDefaultState().withProperty(GLOWCOLOR, worldIn.getBlockState(pos).getValue(GLOWCOLOR)));
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		else if((this.type == "fire" || this.type == "ender") && itemstack.getItem() == Items.POTIONITEM)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, MBlocks.candle2.getDefaultState().withProperty(GLOWCOLOR, worldIn.getBlockState(pos).getValue(GLOWCOLOR)));
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
