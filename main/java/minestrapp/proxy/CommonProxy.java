package minestrapp.proxy;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.block.tileentity.TileEntityAxel;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityMagnetPiston;
import minestrapp.block.tileentity.TileEntityPipe;
import minestrapp.block.tileentity.TileEntitySorter;
import minestrapp.block.tileentity.TileEntityStoneCutter;
import minestrapp.block.tileentity.TileEntityTanningRack;
import minestrapp.block.tileentity.TileEntityVessel;
import minestrapp.crafting.FurnaceRecipes;
import minestrapp.crafting.OreDictRegistry;
import minestrapp.event.MEventHandler;
import minestrapp.gui.MGuiHandler;
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
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/bat/wing_sinew"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/pig/fat"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/flesh"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/husk/salt"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/squid/squid_tentacle"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/witch/wand"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "mob/candy"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_basic_dungeon"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_abandoned_mineshaft"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_desert_temple"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_end_city"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_igloo"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_jungle_temple"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_jungle_dispenser"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_nether_fortress"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_bonus_chest"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_stronghold_corridor"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_stronghold_crossing"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_stronghold_library"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_blacksmith"));
		LootTableList.register(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_woodland_mansion"));
	}
	
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new MOreGen(), 0);
		
		GameRegistry.registerTileEntity(TileEntityAxel.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityAxel"));
		GameRegistry.registerTileEntity(TileEntityVessel.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityVessel"));
		GameRegistry.registerTileEntity(TileEntityBarrel.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityBarrel"));
		GameRegistry.registerTileEntity(TileEntityStoneCutter.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityStoneCutter"));
		GameRegistry.registerTileEntity(TileEntityAlloy.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityAlloy"));
		GameRegistry.registerTileEntity(TileEntityCrusher.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityCrusher"));
		GameRegistry.registerTileEntity(TileEntityMagnetPiston.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityMagnetPiston"));
		GameRegistry.registerTileEntity(TileEntityPipe.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityPipe"));
		GameRegistry.registerTileEntity(TileEntitySorter.class, new ResourceLocation(Minestrapp5.MODID, "TileEntitySorter"));
		GameRegistry.registerTileEntity(TileEntityTanningRack.class, new ResourceLocation(Minestrapp5.MODID, "TileEntityTanningRack"));
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Minestrapp5.instance, new MGuiHandler());
		
		MinecraftForge.EVENT_BUS.register(new MEventHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
