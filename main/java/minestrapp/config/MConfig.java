package minestrapp.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import minestrapp.Minestrapp5;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MConfig
{
	private static Configuration config;
	
	public static final String CATEGORY_BLOCKS = "blocks";
	public static final String CATEGORY_WORLDGEN = "worldgen";
	
	public static boolean minableGlacialInvincium;
	
	public static boolean generateSalt;
	public static boolean generateCopper;
	public static boolean generateTin;
	public static boolean generateMeurodite;
	public static boolean generateTorite;
	public static boolean generateIrradium;
	public static boolean generateSunstone;
	public static boolean generateIceMounds;
	public static boolean generateTitanium;
	public static boolean generateBlazium;
	public static boolean generateSoulOre;
	public static boolean generateDimensium;
	
	public static boolean generateRedRock;
	public static boolean generateColdstone;
	public static boolean generateIcestone;
	public static boolean generateOceanstone;
	public static boolean generateDeepstone;
	public static boolean generateInvincium;
	
	public static void preInit()
	{
		File configFile = new File(Loader.instance().getConfigDir(), "Minestrappolation.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}
	
	public static void clientPreInit()
	{
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}
	
	public static Configuration getConfig()
	{
		return config;
	}
	
	public static void syncFromFiles()
	{
		syncConfig(true, true);
	}
	
	public static void syncFromGUI()
	{
		syncConfig(false, true);
	}
	
	public static void syncFromFields()
	{
		syncConfig(false, false);
	}
	
	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig)
	{
		if(loadFromConfigFile)
			config.load();
		
		Property propertyMinableGlacialInvincium = config.get(CATEGORY_BLOCKS, "minable_glacial_invincium", false);
		registerProperty(propertyMinableGlacialInvincium, CATEGORY_BLOCKS, "minable_glacial_invincium", true, true);
		
		List<String> propertyOrderBlocks = new ArrayList<String>();
		propertyOrderBlocks.add(propertyMinableGlacialInvincium.getName());
		config.setCategoryPropertyOrder(CATEGORY_BLOCKS, propertyOrderBlocks);
		
		Property propertySaltGen = config.get(CATEGORY_WORLDGEN, "generate_salt", true);
		registerProperty(propertySaltGen, CATEGORY_WORLDGEN, "generate_salt");
		Property propertyCopperGen = config.get(CATEGORY_WORLDGEN, "generate_copper", true);
		registerProperty(propertyCopperGen, CATEGORY_WORLDGEN, "generate_copper");
		Property propertyTinGen = config.get(CATEGORY_WORLDGEN, "generate_tin", true);
		registerProperty(propertyTinGen, CATEGORY_WORLDGEN, "generate_tin");
		Property propertyMeuroditeGen = config.get(CATEGORY_WORLDGEN, "generate_meurodite", true);
		registerProperty(propertyMeuroditeGen, CATEGORY_WORLDGEN, "generate_meurodite");
		Property propertyToriteGen = config.get(CATEGORY_WORLDGEN, "generate_torite", true);
		registerProperty(propertyToriteGen, CATEGORY_WORLDGEN, "generate_torite");
		Property propertyIrradiumGen = config.get(CATEGORY_WORLDGEN, "generate_irradium", true);
		registerProperty(propertyIrradiumGen, CATEGORY_WORLDGEN, "generate_irradium");
		Property propertySunstoneGen = config.get(CATEGORY_WORLDGEN, "generate_sunstone", true);
		registerProperty(propertySunstoneGen, CATEGORY_WORLDGEN, "generate_sunstone");
		Property propertyIceMoundGen = config.get(CATEGORY_WORLDGEN, "generate_ice_mounds", true);
		registerProperty(propertyIceMoundGen, CATEGORY_WORLDGEN, "generate_ice_mounds");
		Property propertyTitaniumGen = config.get(CATEGORY_WORLDGEN, "generate_titanium", true);
		registerProperty(propertyTitaniumGen, CATEGORY_WORLDGEN, "generate_titanium");
		Property propertyBlaziumGen = config.get(CATEGORY_WORLDGEN, "generate_blazium", true);
		registerProperty(propertyBlaziumGen, CATEGORY_WORLDGEN, "generate_blazium");
		Property propertySoulOreGen = config.get(CATEGORY_WORLDGEN, "generate_soul_ore", true);
		registerProperty(propertySoulOreGen, CATEGORY_WORLDGEN, "generate_soul_ore");
		Property propertyDimensiumGen = config.get(CATEGORY_WORLDGEN, "generate_dimensium", true);
		registerProperty(propertyDimensiumGen, CATEGORY_WORLDGEN, "generate_dimensium");
		
		Property propertyRedRockGen = config.get(CATEGORY_WORLDGEN, "generate_red_rock", true);
		registerProperty(propertyRedRockGen, CATEGORY_WORLDGEN, "generate_red_rock");
		Property propertyColdstoneGen = config.get(CATEGORY_WORLDGEN, "generate_coldstone", true);
		registerProperty(propertyColdstoneGen, CATEGORY_WORLDGEN, "generate_coldstone");
		Property propertyIcestoneGen = config.get(CATEGORY_WORLDGEN, "generate_icestone", true);
		registerProperty(propertyIcestoneGen, CATEGORY_WORLDGEN, "generate_icestone");
		Property propertyOceanstoneGen = config.get(CATEGORY_WORLDGEN, "generate_oceanstone", true);
		registerProperty(propertyOceanstoneGen, CATEGORY_WORLDGEN, "generate_oceanstone");
		Property propertyDeepstoneGen = config.get(CATEGORY_WORLDGEN, "generate_deepstone", true);
		registerProperty(propertyDeepstoneGen, CATEGORY_WORLDGEN, "generate_deepstone");
		Property propertyInvinciumGen = config.get(CATEGORY_WORLDGEN, "generate_invincium", true);
		registerProperty(propertyInvinciumGen, CATEGORY_WORLDGEN, "generate_invincium");
		
		List<String> propertyOrderWorldgen = new ArrayList<String>();
		propertyOrderWorldgen.add(propertyInvinciumGen.getName());
		config.setCategoryPropertyOrder(CATEGORY_WORLDGEN, propertyOrderWorldgen);
		
		if(readFieldsFromConfig)
		{
			minableGlacialInvincium = propertyMinableGlacialInvincium.getBoolean();
			
			generateSalt = propertySaltGen.getBoolean();
			generateCopper = propertyCopperGen.getBoolean();
			generateTin = propertyTinGen.getBoolean();
			generateMeurodite = propertyMeuroditeGen.getBoolean();
			generateTorite = propertyToriteGen.getBoolean();
			generateIrradium = propertyIrradiumGen.getBoolean();
			generateSunstone = propertySunstoneGen.getBoolean();
			generateIceMounds = propertyIceMoundGen.getBoolean();
			generateTitanium = propertyTitaniumGen.getBoolean();
			generateBlazium = propertyBlaziumGen.getBoolean();
			generateSoulOre = propertySoulOreGen.getBoolean();
			generateDimensium = propertyDimensiumGen.getBoolean();
			
			generateRedRock = propertyRedRockGen.getBoolean();
			generateColdstone = propertyColdstoneGen.getBoolean();
			generateIcestone = propertyIcestoneGen.getBoolean();
			generateOceanstone = propertyOceanstoneGen.getBoolean();
			generateDeepstone = propertyDeepstoneGen.getBoolean();
			generateInvincium = propertyInvinciumGen.getBoolean();
		}
		
		propertyMinableGlacialInvincium.set(minableGlacialInvincium);
		
		propertySaltGen.set(generateSalt);
		propertyCopperGen.set(generateCopper);
		propertyTinGen.set(generateTin);
		propertyMeuroditeGen.set(generateMeurodite);
		propertyToriteGen.set(generateTorite);
		propertyIrradiumGen.set(generateIrradium);
		propertySunstoneGen.set(generateSunstone);
		propertyIceMoundGen.set(generateIceMounds);
		propertyTitaniumGen.set(generateTitanium);
		propertyBlaziumGen.set(generateBlazium);
		propertySoulOreGen.set(generateSoulOre);
		propertyDimensiumGen.set(generateDimensium);
		
		propertyRedRockGen.set(generateRedRock);
		propertyColdstoneGen.set(generateColdstone);
		propertyIcestoneGen.set(generateIcestone);
		propertyOceanstoneGen.set(generateOceanstone);
		propertyDeepstoneGen.set(generateDeepstone);
		propertyInvinciumGen.set(generateInvincium);
		
		if(config.hasChanged())
		{
			config.save();
		}
	}
	
	private static void registerProperty(Property property, String category, String name)
	{
		property.setLanguageKey("gui.config." + category + "." + name + ".name");
		property.setComment(I18n.format("gui.config." + category + "." + name + ".name"));
	}
	
	private static void registerProperty(Property property, String category, String name, boolean requiresWorldRestart, boolean requiresMCRestart)
	{
		registerProperty(property, category, name);
		property.setRequiresWorldRestart(requiresWorldRestart);
		property.setRequiresMcRestart(requiresMCRestart);
	}
	
	public static class ConfigEventHandler
	{
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if(event.getModID() == Minestrapp5.MODID)
			{
				syncFromGUI();
			}
		}
	}
}
