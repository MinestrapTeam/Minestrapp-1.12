package minestrapp.block.crops;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.block.item.ItemBlockMultistate;



public class PeanutsPlant extends BlockCrops {

	
	public PeanutsPlant(String unlocalizedName){
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Minestrapp5.MODID, unlocalizedName));
	}
	
	@Override
	protected Item getSeed(){
		return MItems.peanuts;
	}
	
	@Override
	protected Item getCrop(){
		return MItems.onion;
	}
	
	
	
}
