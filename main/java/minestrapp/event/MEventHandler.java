package minestrapp.event;

import minestrapp.MItems;
import minestrapp.worldgen.MWorldDecorator;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@EventBusSubscriber
public class MEventHandler
{
	@SubscribeEvent
    public static void populateChunks (PopulateChunkEvent.Post event)
	{
        MWorldDecorator.generate(event.getWorld(), event.getChunkX(), event.getChunkZ(), event.getRand());
    }
	
	@SubscribeEvent
    public static void registerRecipes (RegistryEvent.Register<IRecipe> event)
    {
    	ResourceLocation bread = new ResourceLocation("minecraft:bread");
    	ResourceLocation diamondPickaxe = new ResourceLocation("minecraft:diamond_pickaxe");
    	ResourceLocation diamondAxe = new ResourceLocation("minecraft:diamond_axe");
    	ResourceLocation diamondShovel = new ResourceLocation("minecraft:diamond_shovel");
    	ResourceLocation diamondHoe = new ResourceLocation("minecraft:diamond_hoe");
    	ResourceLocation diamondSword = new ResourceLocation("minecraft:diamond_sword");

    	IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
        
        modRegistry.remove(bread);
        modRegistry.remove(diamondPickaxe);
        modRegistry.remove(diamondAxe);
        modRegistry.remove(diamondShovel);
        modRegistry.remove(diamondHoe);
        modRegistry.remove(diamondSword);
    }
	
	@SubscribeEvent
	public static void onBlockBreak (BlockEvent.HarvestDropsEvent event)
	{
		if(event.getState() == Blocks.RED_FLOWER.getStateFromMeta(2) && !event.isSilkTouching())
		{
			int chance = event.getWorld().rand.nextInt(100);
			if(chance <= 6 + (3 * (1 + event.getFortuneLevel())))
			{
				event.getDrops().clear();
				event.getDrops().add(new ItemStack(MItems.onion));
			}
		}
	}
}
