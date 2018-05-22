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
	public static final String CATEGORY_VANILLA_TWEAKS = "vanilla_tweaks";
	
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
	
	public static boolean generateClaySoil;
	public static boolean generatePermafrost;
	public static boolean generateMud;
	public static boolean generateRedRock;
	public static boolean generateColdstone;
	public static boolean generateIcestone;
	public static boolean generateOceanstone;
	public static boolean generateDeepstone;
	public static boolean generateInvincium;
	public static boolean generatePortalDust;
	
	public static boolean generateMoss;
	public static boolean generateSavannaGrass;
	public static boolean generateTundraGrass;
	public static boolean generateBerryBushes;
	public static boolean generateManaBushes;
	public static boolean generateGlowshrooms;
	public static boolean generateGlowMoss;
	public static boolean generateClutchthorn;
	public static boolean generateTerracreep;
	public static boolean generateLavaSponge;
	
	public static boolean generateMiteHive;
	
	public static boolean removeVanillaRecipes;
	
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
		//propertyOrderBlocks.add(propertyMinableGlacialInvincium.getName());
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
		
		Property propertyClaySoilGen = config.get(CATEGORY_WORLDGEN, "generate_clay_soil", true);
		registerProperty(propertyClaySoilGen, CATEGORY_WORLDGEN, "generate_clay_soil");
		Property propertyPermafrostGen = config.get(CATEGORY_WORLDGEN, "generate_permafrost", true);
		registerProperty(propertyPermafrostGen, CATEGORY_WORLDGEN, "generate_permafrost");
		Property propertyMudGen = config.get(CATEGORY_WORLDGEN, "generate_mud", true);
		registerProperty(propertyMudGen, CATEGORY_WORLDGEN, "generate_mud");
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
		Property propertyPortalDustGen = config.get(CATEGORY_WORLDGEN, "generate_portal_dust", true);
		registerProperty(propertyPortalDustGen, CATEGORY_WORLDGEN, "generate_portal_dust");
		
		Property propertyMossGen = config.get(CATEGORY_WORLDGEN, "generate_moss", true);
		registerProperty(propertyMossGen, CATEGORY_WORLDGEN, "generate_moss");
		Property propertySavannaGrassGen = config.get(CATEGORY_WORLDGEN, "generate_savanna_grass", true);
		registerProperty(propertySavannaGrassGen, CATEGORY_WORLDGEN, "generate_savanna_grass");
		Property propertyTundraGrassGen = config.get(CATEGORY_WORLDGEN, "generate_tundra_grass", true);
		registerProperty(propertyTundraGrassGen, CATEGORY_WORLDGEN, "generate_tundra_grass");
		Property propertyBerryBushGen = config.get(CATEGORY_WORLDGEN, "generate_berry_bushes", true);
		registerProperty(propertyBerryBushGen, CATEGORY_WORLDGEN, "generate_berry_bushes");
		Property propertyManaBushGen = config.get(CATEGORY_WORLDGEN, "generate_mana_bushes", true);
		registerProperty(propertyManaBushGen, CATEGORY_WORLDGEN, "generate_mana_bushes");
		Property propertyGlowshroomGen = config.get(CATEGORY_WORLDGEN, "generate_glowshrooms", true);
		registerProperty(propertyGlowshroomGen, CATEGORY_WORLDGEN, "generate_glowshrooms");
		Property propertyGlowMossGen = config.get(CATEGORY_WORLDGEN, "generate_glow_moss", true);
		registerProperty(propertyGlowMossGen, CATEGORY_WORLDGEN, "generate_glow_moss");
		Property propertyClutchthornGen = config.get(CATEGORY_WORLDGEN, "generate_clutchthorn", true);
		registerProperty(propertyClutchthornGen, CATEGORY_WORLDGEN, "generate_clutchthorn");
		Property propertyTerracreepGen = config.get(CATEGORY_WORLDGEN, "generate_terracreep", true);
		registerProperty(propertyTerracreepGen, CATEGORY_WORLDGEN, "generate_terracreep");
		Property propertyLavaSpongeGen = config.get(CATEGORY_WORLDGEN, "generate_lava_sponge", true);
		registerProperty(propertyLavaSpongeGen, CATEGORY_WORLDGEN, "generate_lava_sponge");
		
		Property propertyMiteHiveGen = config.get(CATEGORY_WORLDGEN, "generate_mite_hive", true);
		registerProperty(propertyMiteHiveGen, CATEGORY_WORLDGEN, "generate_mite_hive");
		
		List<String> propertyOrderWorldgen = new ArrayList<String>();
		//propertyOrderWorldgen.add(propertyInvinciumGen.getName());
		config.setCategoryPropertyOrder(CATEGORY_WORLDGEN, propertyOrderWorldgen);
		
		Property propertyRemoveVanillaRecipes = config.get(CATEGORY_VANILLA_TWEAKS, "remove_vanilla_recipes", true);
		registerProperty(propertyRemoveVanillaRecipes, CATEGORY_VANILLA_TWEAKS, "remove_vanilla_recipes", true, true);
		
		List<String> propertyOrderVanillaTweaks = new ArrayList<String>();
		//propertyOrderVanillaTweaks.add(propertyMinableGlacialInvincium.getName());
		config.setCategoryPropertyOrder(CATEGORY_VANILLA_TWEAKS, propertyOrderVanillaTweaks);
		
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
			
			generateClaySoil = propertyClaySoilGen.getBoolean();
			generatePermafrost = propertyPermafrostGen.getBoolean();
			generateMud = propertyMudGen.getBoolean();
			generateRedRock = propertyRedRockGen.getBoolean();
			generateColdstone = propertyColdstoneGen.getBoolean();
			generateIcestone = propertyIcestoneGen.getBoolean();
			generateOceanstone = propertyOceanstoneGen.getBoolean();
			generateDeepstone = propertyDeepstoneGen.getBoolean();
			generateInvincium = propertyInvinciumGen.getBoolean();
			generatePortalDust = propertyPortalDustGen.getBoolean();
			
			generateMoss = propertyMossGen.getBoolean();
			generateSavannaGrass = propertySavannaGrassGen.getBoolean();
			generateTundraGrass = propertyTundraGrassGen.getBoolean();
			generateBerryBushes = propertyBerryBushGen.getBoolean();
			generateManaBushes = propertyManaBushGen.getBoolean();
			generateGlowshrooms = propertyGlowshroomGen.getBoolean();
			generateGlowMoss = propertyGlowMossGen.getBoolean();
			generateClutchthorn = propertyClutchthornGen.getBoolean();
			generateTerracreep = propertyTerracreepGen.getBoolean();
			generateLavaSponge = propertyLavaSpongeGen.getBoolean();
			
			generateMiteHive = propertyMiteHiveGen.getBoolean();
			
			removeVanillaRecipes = propertyRemoveVanillaRecipes.getBoolean();
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
		
		propertyClaySoilGen.set(generateClaySoil);
		propertyPermafrostGen.set(generatePermafrost);
		propertyMudGen.set(generateMud);
		propertyRedRockGen.set(generateRedRock);
		propertyColdstoneGen.set(generateColdstone);
		propertyIcestoneGen.set(generateIcestone);
		propertyOceanstoneGen.set(generateOceanstone);
		propertyDeepstoneGen.set(generateDeepstone);
		propertyInvinciumGen.set(generateInvincium);
		propertyPortalDustGen.set(generatePortalDust);
		
		propertyMossGen.set(generateMoss);
		propertySavannaGrassGen.set(generateSavannaGrass);
		propertyTundraGrassGen.set(generateTundraGrass);
		propertyBerryBushGen.set(generateBerryBushes);
		propertyManaBushGen.set(generateManaBushes);
		propertyGlowshroomGen.set(generateGlowshrooms);
		propertyGlowMossGen.set(generateGlowMoss);
		propertyClutchthornGen.set(generateClutchthorn);
		propertyTerracreepGen.set(generateTerracreep);
		propertyLavaSpongeGen.set(generateLavaSponge);
		
		propertyMiteHiveGen.set(generateMiteHive);
		
		propertyRemoveVanillaRecipes.set(removeVanillaRecipes);
		
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
