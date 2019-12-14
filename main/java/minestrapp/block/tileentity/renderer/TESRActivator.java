package minestrapp.block.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import minestrapp.block.tileentity.TileEntityActivator;
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

public class TESRActivator extends TileEntitySpecialRenderer<TileEntityActivator>{
	
	@Override
	public void render(TileEntityActivator te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		if(te.activatorContents.get(0) != ItemStack.EMPTY && te.hasWorld() && te.getWorld().isBlockPowered(te.getPos()))
		{
			ItemStack renderItem = te.activatorContents.get(0).copy();
			renderItem.setCount(1);
			EntityItem item = new EntityItem(te.getWorld(), 0, 0, 0, renderItem);
			item.hoverStart = 0;
			int angle = 0;
			double xOffset = 0;
			double yOffset = 0;
			double zOffset = 0;
			
			if(te.getAngle() >= 10)
			{
				angle = 0;
				xOffset = 0.5D;
				zOffset = 0.5D;
				yOffset = 1.15;
				
				if(te.getAngle() > 10)
					yOffset = -1.2D;
			}
			else
			{
				angle = te.getAngle() * 90;
				
				if(angle == 0)
				{
					xOffset = 0.5D;
					zOffset = -0.65D;
				}
				else if(angle == 90)
				{
					xOffset = 1.65D;
					zOffset = 0.5D;
				}
				else if(angle == 180)
				{
					xOffset = 0.5D;
					zOffset = 1.65D;
				}
				else if(angle == 270)
				{
					xOffset = -0.65D;
					zOffset = 0.5D;
				}
			}
			
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + xOffset, y + yOffset, z + zOffset);
			GlStateManager.rotate(angle-90, 0, 1, 0);
			GlStateManager.scale(1.5F, 1.5F, 1.5F);
			Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0, 0, 0, 0, 0, true);
			GlStateManager.popMatrix();
		}
	}

}
