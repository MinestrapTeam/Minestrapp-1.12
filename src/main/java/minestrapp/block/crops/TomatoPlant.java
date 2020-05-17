package minestrapp.block.crops;

import minestrapp.Minestrapp;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import minestrapp.MItems;


public class TomatoPlant extends BlockCrops {

	
	public TomatoPlant(String unlocalizedName){
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Minestrapp.MODID, unlocalizedName));
	}
	
	@Override
	protected Item getSeed(){
		return MItems.tomato_seeds;
	}
	
	@Override
	protected Item getCrop(){
		return MItems.tomato;
	}
	
	
	@Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
		return EnumPlantType.Crop;
    }
}
