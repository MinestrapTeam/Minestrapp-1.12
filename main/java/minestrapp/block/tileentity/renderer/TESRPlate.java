package minestrapp.block.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import minestrapp.block.tileentity.TileEntityPlate;
import minestrapp.block.tileentity.TileEntityTanningRack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class TESRPlate extends TileEntitySpecialRenderer<TileEntityPlate>
{	
	@Override
	public void render(TileEntityPlate te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		double height = te.getHeight() / 16D;
		
		ItemStack renderItem = te.inventory.get(0);
		EntityItem item = new EntityItem(te.getWorld(), 0, 0, 0, renderItem);
		item.hoverStart = 0;
		
		
		GL11.glPushMatrix();
		if(te.angle == 0){
			GlStateManager.translate(x+.5, y + height,  z);
		}
		if(te.angle == 90){
			GlStateManager.translate(x+1, y + height,  z+0.5);
		}
		if(te.angle == 180){
			GlStateManager.translate(x+.5, y + height,  z+1);
		}
		if(te.angle == 270){
			GlStateManager.translate(x, y + height,  z+0.5);
		}
		
		GlStateManager.rotate(90, 1, 0, 0);
		GlStateManager.rotate(te.angle, 0, 0, 1);					
		
		Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0, 0, 0, 0, 0, true);
		GL11.glPopMatrix();
				
	}

}
