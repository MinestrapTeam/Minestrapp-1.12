package minestrapp.block;

import javax.annotation.Nullable;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMiteHoney extends BlockBase
{
	protected static final AxisAlignedBB HONEY_AABB = new AxisAlignedBB(0.0625D, 0.0625D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
	
	public BlockMiteHoney()
	{
		super("block_mite_honey", Material.CLAY, MapColor.MAGENTA, SoundType.SLIME, 0.8F);
		this.setSlipperiness(0.9F);
		this.setCreativeTab(MTabs.resource);
		this.setLightLevel(0.65F);
	}
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return HONEY_AABB;
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		entityIn.setInWeb();
        entityIn.motionX *= 0D;
        entityIn.motionY *= 0D;
        entityIn.motionZ *= 0D;
    }
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
