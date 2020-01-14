package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockBaseNonSolid;
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

public class BlockCandleBase extends BlockBaseNonSolid
{
	private String type;
	
	protected static final AxisAlignedBB AABB_CANDLE = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.5D, 0.75D);
	
	public BlockCandleBase(String name, String type)
	{
		super(name, Material.CORAL, MapColor.SNOW, SoundType.WOOD, 0.2F);
		if(type == "unlit")
			this.setCreativeTab(MTabs.dye);
		else if(type == "fire")
			this.setLightLevel(0.85F);
		else if(type == "ender")
			this.setLightLevel(0.6F);
		this.type = type;
	}
	
	public boolean isLit() {return this.type != "unlit";}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		return AABB_CANDLE;
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
