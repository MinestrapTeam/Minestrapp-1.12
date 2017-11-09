package minestrapp.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import minestrapp.MBlocks;
import minestrapp.block.magnetpiston.BlockMagnetPistonBase;
import minestrapp.block.magnetpiston.BlockMagnetPistonExtension;
import minestrapp.tileentity.TileEntityMagnetPiston;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonExtension.EnumPistonType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityMagnetPistonRenderer extends TileEntitySpecialRenderer<TileEntityMagnetPiston>
{

	private BlockRendererDispatcher blockRenderer;

	@Override
	public void render(TileEntityMagnetPiston tileentity, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		if (blockRenderer == null) blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher(); //Forge: Delay this from constructor to allow us to change it later
		BlockPos pos = tileentity.getPos();
		IBlockState state = tileentity.getPistonState();
		Block block = state.getBlock();

		if (state.getMaterial() != Material.AIR && tileentity.getProgress(partialTicks) < 1.0F)
		{
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.enableBlend();
			GlStateManager.disableCull();

			if (Minecraft.isAmbientOcclusionEnabled())
			{
				GlStateManager.shadeModel(GL11.GL_SMOOTH); // GlStateManager.shadeModel(7425);
			}
			else
			{
				GlStateManager.shadeModel(GL11.GL_FLAT); // GlStateManager.shadeModel(7424);
			}

			bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
			bufferbuilder.setTranslation(x - (double)pos.getX() + (double)tileentity.getOffsetX(partialTicks), y - (double)pos.getY() + (double)tileentity.getOffsetY(partialTicks), z - (double)pos.getZ() + (double)tileentity.getOffsetZ(partialTicks));
			World world = this.getWorld();

			if (block == MBlocks.magnet_piston_head && tileentity.getProgress(partialTicks) <= 0.25F)
			{
				state = state.withProperty(BlockMagnetPistonExtension.SHORT, true);
				this.renderStateModel(pos, state, bufferbuilder, world, true);
			}
			else if (tileentity.shouldPistonHeadBeRendered() && !tileentity.isExtending())
			{
				EnumPistonType pistonType = EnumPistonType.DEFAULT;
				IBlockState iblockstate1 = MBlocks.magnet_piston_head.getDefaultState()
					.withProperty(BlockMagnetPistonExtension.TYPE, pistonType)
					.withProperty(BlockMagnetPistonExtension.FACING, state.getValue(BlockMagnetPistonBase.FACING))
					.withProperty(BlockMagnetPistonExtension.SHORT, Boolean.valueOf(tileentity.getProgress(partialTicks) >= 0.5F));

				this.renderStateModel(pos, iblockstate1, bufferbuilder, world, true);
				bufferbuilder.setTranslation(x - (double)pos.getX(), y - (double)pos.getY(), z - (double)pos.getZ());
				state = state.withProperty(BlockMagnetPistonBase.EXTENDED, Boolean.valueOf(true));
				this.renderStateModel(pos, state, bufferbuilder, world, true);
			}
			else
			{
				this.renderStateModel(pos, state, bufferbuilder, world, false);
			}

			bufferbuilder.setTranslation(0.0D, 0.0D, 0.0D);
			tessellator.draw();
			RenderHelper.enableStandardItemLighting();
		}
	}

	private boolean renderStateModel(BlockPos pos, IBlockState state, BufferBuilder buffer, World world, boolean checkSides)
	{
		return this.blockRenderer.getBlockModelRenderer().renderModel(world, this.blockRenderer.getModelForState(state), state, pos, buffer, checkSides);
	}

}