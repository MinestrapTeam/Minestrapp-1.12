package minestrapp.network;

import io.netty.buffer.ByteBuf;
import minestrapp.magic.capability.MagicProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MagicUpdateMessage implements IMessage{
	private NBTTagCompound tags;
	private int playerID;
	
	public MagicUpdateMessage() {};
	
	public MagicUpdateMessage(EntityPlayer player, NBTTagCompound tag){
		this.tags = tag;
		this.playerID = player.getEntityId();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		tags = ByteBufUtils.readTag(buf);
		playerID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, tags);
		buf.writeInt(playerID);
	}

    public static class CapsMessageHolder implements IMessageHandler<MagicUpdateMessage,IMessage> {
    	@Override
		public IMessage onMessage( final MagicUpdateMessage message, final MessageContext ctx) {
			IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().player.getEntityWorld();
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                	MagicProvider.get((EntityPlayer)Minecraft.getMinecraft().world.getEntityByID(message.playerID)).loadNBTData(message.tags);
                }
            });
            return null;
		}
    }

}
