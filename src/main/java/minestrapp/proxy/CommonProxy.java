package minestrapp.proxy;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.gui.MGuiHandler;
import minestrapp.tileentity.TileEntityVessel;
import minestrapp.worldgen.MOreGen;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
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
		
		GameRegistry.registerTileEntity(TileEntityBarrel.class, "TileEntityBarrel");
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Minestrapp5.instance, new MGuiHandler());

	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
