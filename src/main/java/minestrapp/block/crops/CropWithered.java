package minestrapp.block.crops;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import minestrapp.MBlocks;
import minestrapp.Minestrapp;


public class CropWithered extends BlockBush implements IPlantable
{
	private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	
	public CropWithered(String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Minestrapp.MODID, unlocalizedName));
		this.setCreativeTab((CreativeTabs)null);
		this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CROPS_AABB;
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.FARMLAND || state.getBlock() == MBlocks.permafrost_farmland || state.getBlock() == MBlocks.clay_farmland;
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }
	
	@Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
		return EnumPlantType.Crop;
    }
}
