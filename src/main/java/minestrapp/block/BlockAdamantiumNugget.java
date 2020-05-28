package minestrapp.block;

import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAdamantiumNugget extends BlockBaseNonSolid
{
	public BlockAdamantiumNugget()
	{
		super("adamantium_nugget", Material.IRON, MapColor.AIR, SoundType.METAL, -1F);
		this.setResistance(1600000F);
		this.setPushReaction(EnumPushReaction.BLOCK);
		this.setLightLevel(1F);
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
		this.setGlowing();
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		return BlockUtil.createBoundingBoxColumn(3, 3, 6.5D);
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
