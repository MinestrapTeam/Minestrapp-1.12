package minestrapp.proxy;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.block.tileentity.TileEntityStoneCutter;
import minestrapp.crafting.FurnaceRecipes;
import minestrapp.crafting.OreDictRegistry;
import minestrapp.event.MEventHandler;
import minestrapp.gui.MGuiHandler;
import minestrapp.tileentity.TileEntityVessel;
import minestrapp.worldgen.MOreGen;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		MBlocks.init();
		MItems.init();
		OreDictRegistry.register();
		FurnaceRecipes.register();
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/animal_bones"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/pig/fat"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/flesh"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/husk/salt"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/squid/squid_tentacle"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/witch/wand"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/candy"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_basic_dungeon"));
	}
	
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new MOreGen(), 0);
		
		GameRegistry.registerTileEntity(TileEntityVessel.class, "TileEntityVessel");
		
		GameRegistry.registerTileEntity(TileEntityBarrel.class, "TileEntityBarrel");
		
		GameRegistry.registerTileEntity(TileEntityStoneCutter.class, "TileEntityStoneCutter");
		
		GameRegistry.registerTileEntity(TileEntityAlloy.class, "TileEntityAlloy");
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Minestrapp5.instance, new MGuiHandler());
		
		MinecraftForge.EVENT_BUS.register(new MEventHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
