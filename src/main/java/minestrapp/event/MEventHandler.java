package minestrapp.event;

import minestrapp.worldgen.MWorldDecorator;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MEventHandler
{
	@SubscribeEvent
    public static void decorateChunks(PopulateChunkEvent.Post event)
	{
        MWorldDecorator.generate(event.getWorld(), event.getChunkX(), event.getChunkZ(), event.getRand());
    }
}
