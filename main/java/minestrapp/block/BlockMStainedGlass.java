package minestrapp.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMStainedGlass extends BlockMGlowDyed
{
	public BlockMStainedGlass()
	{
		super("m_stained_glass", Material.GLASS, MapColor.AIR, SoundType.GLASS, 0.3F);
		this.setDropsItem(ItemStack.EMPTY, 0, 0, 0, true, false);
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
		this.setIgnoresSimilarity();
	}
	
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}
