package minestrapp.magic.entitys;

import minestrapp.mobs.entitys.EntityTheInfected;
import minestrapp.mobs.renderers.RenderTheInfected;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSpellProjectile extends Render<EntitySpellProjectile>{

	protected RenderSpellProjectile(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpellProjectile entity) {
		return null;
	}
	
	public static class Factory implements IRenderFactory<EntitySpellProjectile> {

		@Override
		public Render<? super EntitySpellProjectile> createRenderFor(RenderManager manager) {
			return new RenderSpellProjectile(manager);
		}
		
	}

}
