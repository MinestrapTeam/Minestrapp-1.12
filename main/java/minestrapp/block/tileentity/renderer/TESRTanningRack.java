package minestrapp.block.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import minestrapp.block.tileentity.TileEntityTanningRack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class TESRTanningRack extends TileEntitySpecialRenderer<TileEntityTanningRack>{
	
	@Override
	public void render(TileEntityTanningRack te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ItemStack renderItem = te.hide.get(0);
		EntityItem item = new EntityItem(te.getWorld(), 0, 0, 0, renderItem);
		item.hoverStart = 0;

		GlStateManager.pushMatrix();
		if(te.angle == 0) {
			GlStateManager.translate(x+.5, y-.4, z+.45);
		}
		if(te.angle == 90) {
			GlStateManager.translate(x+.55, y-.4, z+.5);
		}
		if(te.angle == 180) {
			GlStateManager.translate(x+.5, y-.4, z+.55);
		}
		if(te.angle == 270) {
			GlStateManager.translate(x+.45, y-.4, z+.45);
		}
		
		GlStateManager.rotate(te.angle, 0, 1, 0);
		GlStateManager.scale(2F, 2F, 2F);
		Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0, 0, 0, 0, 0, true);
		GlStateManager.popMatrix();
	}

}
