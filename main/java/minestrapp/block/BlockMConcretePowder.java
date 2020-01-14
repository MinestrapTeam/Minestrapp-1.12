package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockMFalling;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMConcretePowder extends BlockMFalling implements IMetaBlockName
{
	public static final PropertyEnum<BlockMGlowDyed.EnumGlowDye> COLOR = PropertyEnum.<BlockMGlowDyed.EnumGlowDye>create("color", BlockMGlowDyed.EnumGlowDye.class);
	
	public BlockMConcretePowder()
	{
		super("m_concrete_powder", Material.SAND, SoundType.SAND, 0.5F, "shovel", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, BlockMGlowDyed.EnumGlowDye.WHITE));
		this.setGlowing();
		this.setLightLevel(0.3F);
		this.setCreativeTab(MTabs.dye);
	}
	
	public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_)
    {
        if (p_176502_4_.getMaterial().isLiquid())
        {
            worldIn.setBlockState(pos, MBlocks.glow_concrete.getDefaultState().withProperty(BlockMGlowDyed.COLOR, p_176502_3_.getValue(COLOR)), 3);
        }
    }
	
	protected boolean tryTouchWater(World worldIn, BlockPos pos, IBlockState state)
    {
        boolean flag = false;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (enumfacing != EnumFacing.DOWN)
            {
                BlockPos blockpos = pos.offset(enumfacing);

                if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER)
                {
                    flag = true;
                    break;
                }
            }
        }

        if (flag)
        {
            worldIn.setBlockState(pos, MBlocks.glow_concrete.getDefaultState().withProperty(BlockMGlowDyed.COLOR, state.getValue(COLOR)), 3);
        }

        return flag;
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!this.tryTouchWater(worldIn, pos, state))
        {
            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        }
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.tryTouchWater(worldIn, pos, state))
        {
            super.onBlockAdded(worldIn, pos, state);
        }
    }
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockMGlowDyed.EnumGlowDye)state.getValue(COLOR)).getMetadata();
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
        return state.getValue(COLOR).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, BlockMGlowDyed.EnumGlowDye.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockMGlowDyed.EnumGlowDye)state.getValue(COLOR)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {COLOR});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockMGlowDyed.EnumGlowDye.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        return state.getValue(COLOR).getParticleColor();
    }
}
