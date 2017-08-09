package minestrapp.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.MBlockTileEntity;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.gui.MGuiHandler;

import javax.annotation.Nullable;

public class BlockBarrel extends MBlockTileEntity<TileEntityBarrel> {

	public BlockBarrel(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
  				player.openGui(Minestrapp5.instance, MGuiHandler.BARREL, world, pos.getX(), pos.getY(), pos.getZ());  			
  		}
  		return true;

	}
	
	@Override
	public Class<TileEntityBarrel> getTileEntityClass() {
		return TileEntityBarrel.class;
	}
	
	@Nullable
	@Override
	public TileEntityBarrel createTileEntity(World world, IBlockState state) {
		return new TileEntityBarrel();
	}

}