package minestrapp.mobs.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import minestrapp.Minestrapp5;
import minestrapp.mobs.entitys.EntityLumpGoat;
import minestrapp.mobs.models.ModelLumpGoat;

public class RenderLumpGoat extends RenderLiving {

	public static final ResourceLocation LumpGoat_texture = new ResourceLocation(Minestrapp5.MODID, "textures/entity/lumpgoat.png");
	public static ModelLumpGoat modelLumpGoat = new ModelLumpGoat();	
	public static float modelHeight = 0.5F;
	
	public RenderLumpGoat(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn)
    {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }
	
	public void doRender(EntityLiving _entity, double posX, double posY, double posZ, float var8, float var9) {
		EntityLumpGoat entity = (EntityLumpGoat) _entity;
				
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		super.doRender(_entity, posX, posY, posZ, var8, var9);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glRotatef(180F, 0, 1F, 0F);
		GL11.glRotatef(180F, 0, 0, 1F);
		GL11.glTranslatef(0, modelHeight, 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return LumpGoat_texture;
	}
}