package minestrapp.entity.render;

import minestrapp.entity.mob.EntityBurfalaunt;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBurfalaunt extends RenderLiving<EntityBurfalaunt> {
    private static final ResourceLocation Burfalaunt_Texture = new ResourceLocation("minestrapp:textures/entity/burfalaunt.png");

	public RenderBurfalaunt(RenderManager manager, ModelBase model, float shadowSize) {
		super(manager, model, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBurfalaunt entity) {
		return Burfalaunt_Texture;
	}
}