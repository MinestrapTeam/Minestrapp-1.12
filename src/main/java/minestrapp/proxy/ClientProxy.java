package minestrapp.proxy;

import minestrapp.MBlocks;
import minestrapp.Minestrapp5;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		MBlocks.registerRenders();
	}
	
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.cold_sand), new ResourceLocation(Minestrapp5.MODID, "cold_sand_default"), new ResourceLocation(Minestrapp5.MODID, "cold_sand_red"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_copper), new ResourceLocation(Minestrapp5.MODID, "ore_copper_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deep_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_stone"));
	}
}
