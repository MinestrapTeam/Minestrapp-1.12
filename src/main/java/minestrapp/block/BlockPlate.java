package minestrapp.block;

import minestrapp.MTabs;
import minestrapp.block.tileentity.TileEntityPlate;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemFood;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlate extends BlockBase implements ITileEntityProvider
{
	private final int renderHeight;
	private final int BBHeight;

	public BlockPlate(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, int renderHeight, int BBHeight)
	{
		super(name, material, mapColor, soundType, hardness);
		this.setCreativeTab(MTabs.decor);
		this.renderHeight = renderHeight;
		this.BBHeight = BBHeight;
	}
	
	public BlockPlate(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, int renderHeight, int BBHeight, String tool, int harvestLevel)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
		this.setCreativeTab(MTabs.decor);
		this.renderHeight = renderHeight;
		this.BBHeight = BBHeight;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		return new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, this.BBHeight/16D, 0.9375D);
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityPlate tep = (TileEntityPlate) world.getTileEntity(pos);
	
		if(!player.isSneaking()) {
			if(!player.getHeldItem(hand).isEmpty() && (player.getHeldItem(hand).getItem() instanceof ItemFood && tep.tryToAddItem(player.getHeldItem(hand))))
				player.getHeldItem(hand).shrink(1);
			else
				tep.takeItem();
		}
		else
			tep.eatOffPlate(player);
		
		if(player.getHorizontalFacing() == EnumFacing.NORTH)
    		tep.angle = 180;
		if(player.getHorizontalFacing() == EnumFacing.SOUTH)
    		tep.angle = 0;
    	if(player.getHorizontalFacing() == EnumFacing.EAST)
    		tep.angle = 270;
    	if(player.getHorizontalFacing() == EnumFacing.WEST)
    		tep.angle = 90;
    	tep.markDirty();
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityPlate tep = (TileEntityPlate) worldIn.getTileEntity(pos);
		tep.takeItem();
        super.breakBlock(worldIn, pos, state);
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
	
	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPlate().setHeight(this.renderHeight);
	}

	public int getRenderHeight()
	{
		return this.renderHeight;
	}
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		Block block = worldIn.getBlockState(pos.down()).getBlock();
		
		if(worldIn.isSideSolid(pos.down(), EnumFacing.UP))
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
}
