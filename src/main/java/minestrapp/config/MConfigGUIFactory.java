package minestrapp.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import minestrapp.Minestrapp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;

public class MConfigGUIFactory implements IModGuiFactory
{
	@Override
	public void initialize(Minecraft minecraftInstance)
	{
		
	}
	
	@Override
	public boolean hasConfigGui()
	{
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen)
	{
		return new MConfigGUI(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	public static class MConfigGUI extends GuiConfig
	{
		public MConfigGUI(GuiScreen parentScreen)
		{
			super(parentScreen, getConfigElements(), Minestrapp.MODID, false, false, I18n.format("gui.config.main.title"));
		}

		private static List<IConfigElement> getConfigElements()
		{
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			list.add(new DummyCategoryElement(I18n.format("gui.config.category.blocks.title"), "gui.config.category.blocks.title", CategoryEntryBlocks.class));
			list.add(new DummyCategoryElement(I18n.format("gui.config.category.worldgen.title"), "gui.config.category.worldgen.title", CategoryEntryWorldgen.class));
			list.add(new DummyCategoryElement(I18n.format("gui.config.category.vanilla_tweaks.title"), "gui.config.category.vanilla_tweaks.title", CategoryEntryVanillaTweaks.class));
			return list;
		}
		
		public static class CategoryEntryBlocks extends CategoryEntry
		{
			public CategoryEntryBlocks(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement)
			{
				super(owningScreen, owningEntryList, configElement);
			}
			
			@Override
			protected GuiScreen buildChildScreen()
			{
				Configuration config = MConfig.getConfig();
				ConfigElement categoryBlocks = new ConfigElement(config.getCategory(MConfig.CATEGORY_BLOCKS));
				List<IConfigElement> propertiesOnScreen = categoryBlocks.getChildElements();
				String windowTitle = I18n.format("gui.config.category.blocks.title");
				return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
			}
		}
		
		public static class CategoryEntryWorldgen extends CategoryEntry
		{
			public CategoryEntryWorldgen(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement)
			{
				super(owningScreen, owningEntryList, configElement);
			}
			
			@Override
			protected GuiScreen buildChildScreen()
			{
				Configuration config = MConfig.getConfig();
				ConfigElement categoryWorldgen = new ConfigElement(config.getCategory(MConfig.CATEGORY_WORLDGEN));
				List<IConfigElement> propertiesOnScreen = categoryWorldgen.getChildElements();
				String windowTitle = I18n.format("gui.config.category.worldgen.title");
				return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
			}
		}
		
		public static class CategoryEntryVanillaTweaks extends CategoryEntry
		{
			public CategoryEntryVanillaTweaks(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement)
			{
				super(owningScreen, owningEntryList, configElement);
			}
			
			@Override
			protected GuiScreen buildChildScreen()
			{
				Configuration config = MConfig.getConfig();
				ConfigElement categoryVanillaTweaks = new ConfigElement(config.getCategory(MConfig.CATEGORY_VANILLA_TWEAKS));
				List<IConfigElement> propertiesOnScreen = categoryVanillaTweaks.getChildElements();
				String windowTitle = I18n.format("gui.config.category.vanilla_tweaks.title");
				return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
			}
		}
	}
}
