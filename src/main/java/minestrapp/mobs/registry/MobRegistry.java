package minestrapp.mobs.registry;

import minestrapp.mobs.entitys.EntityBroodMother;
import minestrapp.mobs.entitys.EntityLumpGoat;
import minestrapp.mobs.entitys.EntitySheetGhost;
import minestrapp.mobs.entitys.EntityTheInfected;
import minestrapp.mobs.models.ModelLumpGoat;
import minestrapp.mobs.models.ModelSheetGhost;
import minestrapp.mobs.renderers.RenderBroodMother;
import minestrapp.mobs.renderers.RenderLumpGoat;
import minestrapp.mobs.renderers.RenderSheetGhost;
import minestrapp.mobs.renderers.RenderTheInfected;
import minestrapp.Minestrapp5;
import minestrapp.entity.mob.EntityBurfalaunt;
import minestrapp.entity.model.ModelBurfalaunt;
import minestrapp.entity.render.RenderBurfalaunt;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityTheInfected.class, new RenderTheInfected.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityBurfalaunt.class, m -> new RenderBurfalaunt(m, new ModelBurfalaunt(), 0.7F));
	}
	
	public static void registerEntity()
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp5.MODID, "TheInfected"), EntityTheInfected.class, "TheInfected", 453, Minestrapp5.instance, 64, 1, true, 0x002222, 0x40ffff);
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp5.MODID, "Burfalaunt"), EntityBurfalaunt.class, "Burfalaunt", 452, Minestrapp5.instance, 64, 1, true, 0xffff62, 0x40f03f);
		
	}
}