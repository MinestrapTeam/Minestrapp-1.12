package minestrapp.block.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import minestrapp.block.tileentity.TileEntityCrusher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class TESRCrusher extends TileEntitySpecialRenderer<TileEntityCrusher>{

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
		//North
		if(te.itemAngel == 0) {
			GlStateManager.translate(x+.5, y+.2,  z+.16);
		}
		//South
		else if(te.itemAngel == 90) {
			GlStateManager.translate(x+.85, y+.2,  z+.49);
		} else if(te.itemAngel == 180) {
			GlStateManager.translate(x+.51, y+.2,  z+.85);
		} else if(te.itemAngel == 270) {
			GlStateManager.translate(x+.14, y+.2,  z+.5);
		}
		GlStateManager.rotate(90, 1, 0, 0);
		GlStateManager.rotate(te.itemAngel, 0, 0, 1);
		Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0, 0, 0, 0, 0, true); 
		GL11.glPopMatrix();
	}

}
