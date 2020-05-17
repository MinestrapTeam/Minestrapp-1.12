package minestrapp.mobs.renderers;

import minestrapp.Minestrapp;
import minestrapp.mobs.models.ModelBroodMother;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBroodMother extends RenderLiving {
	
	public static final ResourceLocation BROOD_MOTHER_TEXTURE = new ResourceLocation(Minestrapp.MODID, "textures/entity/broodmother.png");
	public static ModelBroodMother MODEL_BROOD_MOTHER = new ModelBroodMother();	
	public static final float SHADOW_SIZE = 1F; 

	public RenderBroodMother(RenderManager renderManager) {
		super(renderManager, MODEL_BROOD_MOTHER, SHADOW_SIZE);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return BROOD_MOTHER_TEXTURE;
	}

}
