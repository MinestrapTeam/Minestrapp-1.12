package minestrapp.mobs.registry;

import minestrapp.mobs.entitys.EntitySheetGhost;
import minestrapp.mobs.models.ModelSheetGhost;
import minestrapp.mobs.renderers.RenderSheetGhost;
import minestrapp.Minestrapp5;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class MobRegistry 
{
	
	
	public static void register()
	{
		MobRegistry.registerRender();
		MobRegistry.registerEntity();
	}
	
	public static void registerRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntitySheetGhost.class, new RenderSheetGhost(Minecraft.getMinecraft().getRenderManager(), new ModelSheetGhost(), 0.5F));
	}
	
	public static void registerEntity()
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp5.MODID, "SheetGhost"), EntitySheetGhost.class, "SheetGhost", 450, Minestrapp5.instance, 64, 1, true, 0x0f0f0f, 0xf0f0f0);
	}
}