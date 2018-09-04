package minestrapp.mobs.registry;

import minestrapp.Minestrapp5;
import minestrapp.magic.entitys.EntitySpellProjectile;
import minestrapp.magic.entitys.RenderSpellProjectile;
import minestrapp.mobs.entitys.EntityTheInfected;
import minestrapp.mobs.renderers.RenderTheInfected;
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
		RenderingRegistry.registerEntityRenderingHandler(EntitySpellProjectile.class, new RenderSpellProjectile.Factory());
	}
	
	public static void registerEntity()
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp5.MODID, "TheInfected"), EntityTheInfected.class, "TheInfected", 453, Minestrapp5.instance, 64, 1, true, 0x002222, 0x40ffff);
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp5.MODID, "Spell_Projectile"), EntitySpellProjectile.class, "magic_spell_projectile", 0, Minestrapp5.instance, 64, 1, true);

	}
}