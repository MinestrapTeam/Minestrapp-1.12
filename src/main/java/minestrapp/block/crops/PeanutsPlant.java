package minestrapp.block.crops;

import minestrapp.Minestrapp;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import minestrapp.MItems;


public class PeanutsPlant extends BlockCrops {

	
	public PeanutsPlant(String unlocalizedName){
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Minestrapp.MODID, unlocalizedName));
	}
	
	@Override
	protected Item getSeed(){
		return MItems.peanuts;
	}
	
	@Override
	protected Item getCrop(){
		return MItems.peanuts;
	}
	
	@Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
		return EnumPlantType.Crop;
    }
}
