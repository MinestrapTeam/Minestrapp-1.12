package minestrapp.mobs.registry;

import minestrapp.mobs.entitys.EntityTheInfected;
import minestrapp.mobs.renderers.RenderTheInfected;
import minestrapp.Minestrapp;
import minestrapp.entity.mob.EntityBurfalaunt;
import minestrapp.entity.model.ModelBurfalaunt;
import minestrapp.entity.render.RenderBurfalaunt;
import net.minecraft.util.ResourceLocation;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityTheInfected.class, new RenderTheInfected.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityBurfalaunt.class, m -> new RenderBurfalaunt(m, new ModelBurfalaunt(), 0.7F));
	}
	
	public static void registerEntity()
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp.MODID, "TheInfected"), EntityTheInfected.class, "TheInfected", 453, Minestrapp.instance, 64, 1, true, 0x002222, 0x40ffff);
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp.MODID, "Burfalaunt"), EntityBurfalaunt.class, "Burfalaunt", 452, Minestrapp.instance, 64, 1, true, 0xffff62, 0x40f03f);
		
	}
}