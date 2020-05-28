package minestrapp.mobs.renderers;

import minestrapp.Minestrapp;
import minestrapp.mobs.entitys.EntityTheInfected;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderTheInfected extends RenderBiped<EntityTheInfected>{

	public static final ResourceLocation INFECTED_TEXTURE = new ResourceLocation(Minestrapp.MODID, "textures/entity/theinfected.png");
	public static ModelZombie MODEL_INFECTED = new ModelZombie();	
	public static final float SHADOW_SIZE = 0.5F;
	
	public RenderTheInfected(RenderManager rendermanagerIn) {
		super(rendermanagerIn, MODEL_INFECTED, SHADOW_SIZE);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTheInfected entity) {
		return INFECTED_TEXTURE;
	}
	
	public static class Factory implements IRenderFactory<EntityTheInfected> {

		@Override
		public Render<? super EntityTheInfected> createRenderFor(RenderManager manager) {
			return new RenderTheInfected(manager);
		}
		
	}

}
