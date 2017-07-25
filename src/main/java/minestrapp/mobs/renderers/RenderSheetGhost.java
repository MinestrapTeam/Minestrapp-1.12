package minestrapp.mobs.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import org.lwjgl.opengl.GL11;

import minestrapp.Minestrapp5;
import minestrapp.mobs.entitys.EntitySheetGhost;
import minestrapp.mobs.models.ModelSheetGhost;

public class RenderSheetGhost extends RenderLiving {

	@SideOnly(Side.CLIENT)
	
	
	public static final ResourceLocation SheetGhost_texture = new ResourceLocation(Minestrapp5.MODID, "textures/entity/sheetghost.png");

	    public RenderSheetGhost(RenderManager sheetmod)
	    {
	        super(sheetmod, new ModelSheetGhost(), 0.1F);
	    }

	    /**
	     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	     */
	    protected ResourceLocation getEntityTexture(EntitySheetGhost entity)
	    {
	        return SheetGhost_texture;
	    }

		@Override
		protected ResourceLocation getEntityTexture(Entity entity) {
			// TODO Auto-generated method stub
			return SheetGhost_texture;
		}
	
}