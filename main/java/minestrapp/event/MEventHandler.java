package minestrapp.event;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.worldgen.MWorldDecorator;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
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
		ResourceLocation chiseledStone = new ResourceLocation("minecraft:chiseled_stonebrick");
		ResourceLocation granite = new ResourceLocation("minecraft:granite");
		ResourceLocation diorite = new ResourceLocation("minecraft:diorite");
		ResourceLocation andesite = new ResourceLocation("minecraft:andesite");
		ResourceLocation granite_polished = new ResourceLocation("minecraft:polished_granite");
		ResourceLocation diorite_polished = new ResourceLocation("minecraft:polished_diorite");
		ResourceLocation andesite_polished = new ResourceLocation("minecraft:polished_andesite");
		ResourceLocation cookie = new ResourceLocation("minecraft:cookie");
    	ResourceLocation bread = new ResourceLocation("minecraft:bread");
    	ResourceLocation pumpkinPie = new ResourceLocation("minecraft:pumpkin_pie");
    	ResourceLocation diamondPickaxe = new ResourceLocation("minecraft:diamond_pickaxe");
    	ResourceLocation diamondAxe = new ResourceLocation("minecraft:diamond_axe");
    	ResourceLocation diamondShovel = new ResourceLocation("minecraft:diamond_shovel");
    	ResourceLocation diamondHoe = new ResourceLocation("minecraft:diamond_hoe");
    	ResourceLocation diamondSword = new ResourceLocation("minecraft:diamond_sword");

    	IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
        
    	modRegistry.remove(chiseledStone);
    	modRegistry.remove(granite);
    	modRegistry.remove(diorite);
    	modRegistry.remove(andesite);
    	modRegistry.remove(granite_polished);
    	modRegistry.remove(diorite_polished);
    	modRegistry.remove(andesite_polished);
    	modRegistry.remove(cookie);
        modRegistry.remove(bread);
        modRegistry.remove(pumpkinPie);
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
		else if(event.getState() == Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.FERN))
		{
			if(event.getHarvester() != null && event.getHarvester().getHeldItem(EnumHand.MAIN_HAND).getItem() != Items.SHEARS)
			{
				event.getDrops().clear();
				int chance = event.getWorld().rand.nextInt(100);
				
				if(chance < 5)
				{
					if(event.getWorld().getBiome(event.getPos()).getDefaultTemperature() < 0.4)
					{
						if(event.getWorld().rand.nextInt(2) == 0)
							event.getDrops().add(new ItemStack(MItems.lettuce));
						else
							event.getDrops().add(new ItemStack(MItems.celery_seeds));
					}
					else
					{
						if(event.getWorld().rand.nextInt(2) == 0)
							event.getDrops().add(new ItemStack(MItems.pepper_seeds));
						else
							event.getDrops().add(new ItemStack(MItems.tomato_seeds));
					}
				}
			}
		}
		else if(event.getState() == Blocks.NETHER_WART.getDefaultState().withProperty(BlockNetherWart.AGE, 3) && event.getWorld().getBlockState(event.getPos().down()).getBlock() == MBlocks.ore_soul)
		{
			int chance = event.getWorld().rand.nextInt(100);
			if(chance <= 3)
				event.getDrops().add(new ItemStack(MItems.gem_soul));
		}
	}
	
	//TODO: Figure out the fuck dungeon loot works. It's a goddamn mystery.
/*
	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent event)
	{
	    if (event.getName().toString().equals("minecraft:chests/simple_dungeon"))
	    {
	    	LootEntry basic_dungeon = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID, "dungeon/m_basic_dungeon"), 1, 0, new LootCondition[0], "m_basic_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {basic_dungeon}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_basic_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    //Command for spawning test chests w/ loot tables: /setblock ~ ~ ~ minecraft:chest 2 replace {LootTable:"chests/simple_dungeon"}
	}
*/
}
