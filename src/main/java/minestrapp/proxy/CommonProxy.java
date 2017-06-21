package minestrapp.proxy;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.worldgen.MOreGen;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		MBlocks.init();
		MItems.init();
	}
	
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new MOreGen(), 0);
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
