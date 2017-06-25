package minestrapp.proxy;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.tileentity.TileEntityVessel;
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
		
		GameRegistry.registerTileEntity(TileEntityVessel.class, "TileEntityVessel");
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
