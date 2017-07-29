package minestrapp.block.crops;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.block.item.ItemBlockMultistate;



public class CabbagePlant extends BlockCrops {

	
	public CabbagePlant(String unlocalizedName){
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Minestrapp5.MODID, unlocalizedName));
	}
	
	@Override
	protected Item getSeed(){
		return MItems.cabbage_seeds;
	}
	
	@Override
	protected Item getCrop(){
		return MItems.cabbage;
	}
	
	@Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
		return EnumPlantType.Crop;
    }
}
