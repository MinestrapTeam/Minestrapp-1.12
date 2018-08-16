package minestrapp.block.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import minestrapp.block.tileentity.TileEntityCrusher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class TESRCrusher extends TileEntitySpecialRenderer<TileEntityCrusher>{

	//TODO item doesnt continue rendering after block changes from inactive to active and vise versa
	@Override
	public void render(TileEntityCrusher te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ItemStack renderItem;
		if(!te.inventory.get(0).isEmpty()) {
			renderItem = te.inventory.get(0).copy();
		} else {
			renderItem = te.inventory.get(2).copy();
		}
		
		renderItem.setCount(1);
		EntityItem item = new EntityItem(te.getWorld(), 0, 0, 0, renderItem);
		item.hoverStart = 0;
		GL11.glPushMatrix();
		if(te.itemAngel == 0) {
			GlStateManager.translate(x+.5, y+0.2,  z+.16);
		} else if(te.itemAngel == 90) {
			GlStateManager.translate(x+.5, y+0.2,  z+.2);
		} else if(te.itemAngel == 180) {
			GlStateManager.translate(x+.55, y+0.2,  z+.15);
		} else if(te.itemAngel == 270) {
			GlStateManager.translate(x+.55, y+1.2,  z+.15);
		}
		GlStateManager.rotate(90, 1, 0, 0);
		GlStateManager.rotate(te.itemAngel, 0, 1, 0);
		Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0, 0, 0, 0, 0, true); 
		GL11.glPopMatrix();
	}

}
