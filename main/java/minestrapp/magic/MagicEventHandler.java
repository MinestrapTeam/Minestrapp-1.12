package minestrapp.magic;

import minestrapp.Minestrapp5;
import minestrapp.magic.capability.MagicProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class MagicEventHandler {
	
	private ResourceLocation magic_texture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/magic.png");

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGameOverlayRender(RenderGameOverlayEvent.Post e){
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		if (e.getType() == ElementType.TEXT && !player.isCreative()){
			if(!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() instanceof IMagicItem){
				
				int manaXPos = (e.getResolution().getScaledWidth() / 2  - 96);
				int manaYPos = e.getResolution().getScaledHeight() - 45;
				
				// move the mana bar up if the player is wearing armor
				if(player.getTotalArmorValue() > 0) {
					manaYPos = e.getResolution().getScaledHeight() - 55;
				}

				/**
				//Draw the numbers below the mana bar
				for(int i = 1; i < EnumMagicType.values().length; i++) {
					String amount = Integer.toString(MagicProvider.get(player).getManaForType(EnumMagicType.getById(i)));
					Minecraft.getMinecraft().fontRenderer.drawString(amount, (10 * i + 20) + amount.length(), 5, EnumMagicType.getById(i).getColor());
				}**/
				
				Minecraft.getMinecraft().renderEngine.bindTexture(magic_texture);

				GlStateManager.pushMatrix();
				GlStateManager.color(1, 1, 1, 1);
				
				//Draw the empty mana bars
				Gui.drawModalRectWithCustomSizedTexture(manaXPos, manaYPos - 1, 0, 0, 193, 5, 256, 256);
				//Draw the filled portion of the bar
				for(int i = 0; i < EnumMagicType.values().length; i++) {
					Gui.drawModalRectWithCustomSizedTexture(manaXPos + i * 24, manaYPos, (24 * i) + 1, 6, scaledMana(player, EnumMagicType.getById(i + 1)), 3, 256, 256);
				}

				GlStateManager.popMatrix();
			}
		}
	}
	
	private int scaledMana(EntityPlayer player, EnumMagicType type) {
		int barSize = 23;	
		
		int manaAmount = MagicProvider.get(player).getManaForType(type);
		int maxManaAmount = MagicProvider.get(player).getMaxManaForType(type);
		
		int scaledAmount = 0;
		
		if(manaAmount == maxManaAmount) {
			scaledAmount = barSize;
		} else {
			scaledAmount = (manaAmount * barSize) / maxManaAmount;
		}
		
		return Math.round(MathHelper.clamp(scaledAmount, 0, barSize));
	}
	
	@SubscribeEvent
	public void playerJoinEvent(EntityJoinWorldEvent e){
		//Sync capability data from server to client when the player joins
		if(e.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) e.getEntity();
			player.getCapability(MagicProvider.MagicCapability, null).dataChanged(player);
		}
	}

	@SubscribeEvent
	public void playerRespawn(PlayerRespawnEvent e) {
		//Reset the players mana on respawn
		if(!e.player.getEntityWorld().isRemote) {
			MagicProvider.get(e.player).resetMana(e.player);
		}
	}
}
