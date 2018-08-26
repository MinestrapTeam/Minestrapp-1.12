package minestrapp.event;

import java.util.Calendar;
import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.config.MConfig;
import minestrapp.crafting.FreezingRecipes;
import minestrapp.mobs.models.ModelSheetGhost;
import minestrapp.utils.EntityUtil;
import minestrapp.worldgen.MWorldDecorator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFrostedIce;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@Mod.EventBusSubscriber
public class MEventHandler
{
	private static final FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();
	private static final FreezingRecipes freezingRecipes = FreezingRecipes.instance();
	
	@SubscribeEvent
    public static void populateChunks (PopulateChunkEvent.Post event)
	{
        MWorldDecorator.generate(event.getWorld(), event.getChunkX(), event.getChunkZ(), event.getRand());
    }
	
	@SubscribeEvent
    public static void registerRecipes (RegistryEvent.Register<IRecipe> event)
    {
		if(MConfig.removeVanillaRecipes)
		{
			ResourceLocation redstone_block = new ResourceLocation("minecraft:redstone_block");
			ResourceLocation hopper = new ResourceLocation("minecraft:hopper");
			ResourceLocation mossyCobble = new ResourceLocation("minecraft:mossy_cobblestone");
			ResourceLocation mossyStoneBricks = new ResourceLocation("minecraft:mossy_stonebrick");
			ResourceLocation chiseledStone = new ResourceLocation("minecraft:chiseled_stonebrick");
			ResourceLocation granite = new ResourceLocation("minecraft:granite");
			ResourceLocation diorite = new ResourceLocation("minecraft:diorite");
			ResourceLocation andesite = new ResourceLocation("minecraft:andesite");
			ResourceLocation granite_polished = new ResourceLocation("minecraft:polished_granite");
			ResourceLocation diorite_polished = new ResourceLocation("minecraft:polished_diorite");
			ResourceLocation andesite_polished = new ResourceLocation("minecraft:polished_andesite");
			ResourceLocation lit_pumpkin = new ResourceLocation("minecraft:lit_pumpkin");
			ResourceLocation cookie = new ResourceLocation("minecraft:cookie");
	    	ResourceLocation bread = new ResourceLocation("minecraft:bread");
	    	ResourceLocation pumpkinPie = new ResourceLocation("minecraft:pumpkin_pie");
	    	ResourceLocation diamondPickaxe = new ResourceLocation("minecraft:diamond_pickaxe");
	    	ResourceLocation diamondAxe = new ResourceLocation("minecraft:diamond_axe");
	    	ResourceLocation diamondShovel = new ResourceLocation("minecraft:diamond_shovel");
	    	ResourceLocation diamondHoe = new ResourceLocation("minecraft:diamond_hoe");
	    	ResourceLocation diamondSword = new ResourceLocation("minecraft:diamond_sword");
	    	ResourceLocation endCrystal = new ResourceLocation("minecraft:end_crystal");
	
	    	IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
	        
	    	modRegistry.remove(redstone_block);
	    	modRegistry.remove(hopper);
	    	modRegistry.remove(mossyCobble);
	    	modRegistry.remove(mossyStoneBricks);
	    	modRegistry.remove(chiseledStone);
	    	modRegistry.remove(granite);
	    	modRegistry.remove(diorite);
	    	modRegistry.remove(andesite);
	    	modRegistry.remove(granite_polished);
	    	modRegistry.remove(diorite_polished);
	    	modRegistry.remove(andesite_polished);
	    	modRegistry.remove(lit_pumpkin);
	    	modRegistry.remove(cookie);
	        modRegistry.remove(bread);
	        modRegistry.remove(pumpkinPie);
	        modRegistry.remove(diamondPickaxe);
	        modRegistry.remove(diamondAxe);
	        modRegistry.remove(diamondShovel);
	        modRegistry.remove(diamondHoe);
	        modRegistry.remove(diamondSword);
	        modRegistry.remove(endCrystal);
		}
    }
	
	@SubscribeEvent
	public static void onLivingAttack (LivingAttackEvent event)
	{
		if("mob".equals(event.getSource().damageType) || "player".equals(event.getSource().damageType))
		{
			if(EntityUtil.isWearingArmor(event.getEntityLiving(), MItems.fire_helm, MItems.fire_chest, MItems.fire_legs, MItems.fire_feet)) 
			{
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2, 0, true, false));
				Entity living = event.getSource().getTrueSource();
				living.setFire(10);
			}					
		}
		else if(event.getSource().isFireDamage())
		{
			if(EntityUtil.isWearingArmor(event.getEntityLiving(), MItems.meurodite_helm, MItems.meurodite_chest, MItems.meurodite_legs, MItems.meurodite_feet))
			{
				Random rand = new Random();
					
				if(rand.nextInt(10) == 1)
				{
					EntityEquipmentSlot slot = EntityEquipmentSlot.HEAD;
					int chance = rand.nextInt(4);
					if(chance == 1)
						slot = EntityEquipmentSlot.CHEST;
					else if(chance == 2)
						slot = EntityEquipmentSlot.LEGS;
					else if(chance == 3)
						slot = EntityEquipmentSlot.FEET;
						
					event.getEntityLiving().getItemStackFromSlot(slot).damageItem(1, event.getEntityLiving());
				}
			}	
		}
	}
	
	@SubscribeEvent
	public static void onLivingUpdate (LivingUpdateEvent event)
	{
		EntityLivingBase living = event.getEntityLiving();
		
			if(EntityUtil.isWearingArmor(event.getEntityLiving(), MItems.torite_helm, MItems.torite_chest, MItems.torite_legs, MItems.torite_feet))
			{
				if(living.getActivePotionEffect(MobEffects.REGENERATION) == null)
				living.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50, 0, true, false));
			}
			else if(EntityUtil.isWearingArmor(event.getEntityLiving(), MItems.titanium_helm, MItems.titanium_chest, MItems.titanium_legs, MItems.titanium_feet))
			{
				living.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 10, 0, true, false));
			}
			else if(EntityUtil.isWearingArmor(event.getEntityLiving(), MItems.meurodite_helm, MItems.meurodite_chest, MItems.meurodite_legs, MItems.meurodite_feet))
			{
				living.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 10, 0, true, false));
			}
			else if(EntityUtil.isWearingArmor(event.getEntityLiving(), MItems.ice_helm, MItems.ice_chest, MItems.ice_legs, MItems.ice_feet))
			{
				if(!living.world.isRemote)
				{
					BlockPos basePos = new BlockPos(living.posX, living.posY, living.posZ);
					float chanceModifier = living.world.getBiome(basePos).getTemperature(basePos);
					int r = 4 - Math.round(chanceModifier);
					
					for(int i = Math.round((float)living.posX - r) ; i <= Math.round((float)living.posX + r) ; i++)
					{
						for(int j = Math.round((float)living.posZ - r) ; j <= Math.round((float)living.posZ + r) ; j++)
						{
							if(living.world.rand.nextInt(15 + Math.round(chanceModifier * 10)) == 1)
							{
								if(((living.posX - i) * (living.posX - i)) + ((living.posZ - j) * (living.posZ - j)) < (r * r))
								{
									World world = living.getEntityWorld();
									BlockPos pos = new BlockPos(i, living.posY - 1, j);
									IBlockState state = world.getBlockState(pos);
									if(!world.isAirBlock(pos))
									{
										Item result = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "physical").getItem();
										int meta = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "physical").getMetadata();
										
										if(Block.getBlockFromItem(result) != null && freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "physical") != ItemStack.EMPTY)
										{
											world.setBlockState(pos, Block.getBlockFromItem(result).getStateFromMeta(meta));
										}
										else
										{
											boolean frostWalker = false;
											
											NBTTagList nbttaglist = getEnchantments(event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET));
		
									        for (int m = 0; m < nbttaglist.tagCount(); ++m)
									        {
									            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(m);
									            int n = nbttagcompound.getShort("id");
									            Enchantment enchantment = Enchantment.getEnchantmentByID(n);
									            
									            if(n == Enchantment.getEnchantmentID(Enchantments.FROST_WALKER))
									            	frostWalker = true;
											}
											
											if(frostWalker)
											{
												result = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "deep").getItem();
												meta = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "deep").getMetadata();
												
												if(Block.getBlockFromItem(result) != null && freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "deep") != ItemStack.EMPTY)
												{
													world.setBlockState(pos, Block.getBlockFromItem(result).getStateFromMeta(meta));
												}
												else
												{
													result = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "light").getItem();
													meta = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "light").getMetadata();
													
													if(Block.getBlockFromItem(result) != null && freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "light") != ItemStack.EMPTY)
													{
														world.setBlockState(pos, Block.getBlockFromItem(result).getStateFromMeta(meta));
													}
													else if(state.getBlock() instanceof BlockStaticLiquid)
													{
														if(state.getMaterial() == Material.WATER)
															world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
														else if(state.getMaterial() == Material.LAVA)
															world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
													}
													else if(state.getBlock() instanceof BlockFrostedIce)
													{
														world.setBlockState(pos, Blocks.ICE.getDefaultState());
													}
												}
											}
											else
											{
												result = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "light").getItem();
												meta = freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "light").getMetadata();
												
												if(Block.getBlockFromItem(result) != null && freezingRecipes.getFreezingResult(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)), "light") != ItemStack.EMPTY)
												{
													world.setBlockState(pos, Block.getBlockFromItem(result).getStateFromMeta(meta));
												}
												else if(state.getBlock() instanceof BlockStaticLiquid)
												{
													if(state.getMaterial() == Material.WATER)
														world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
													else if(state.getMaterial() == Material.LAVA)
														world.setBlockState(pos, Blocks.MAGMA.getDefaultState());
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
	}
	
	@SubscribeEvent
	public static void onBlockBreak (BlockEvent.HarvestDropsEvent event)
	{
		Item tool = null;
		if(event.getHarvester() != null)
			tool = event.getHarvester().getHeldItem(EnumHand.MAIN_HAND).getItem();
		ItemStack smeltStack = furnaceRecipes.getSmeltingResult(new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().getMetaFromState(event.getState())));
		ItemStack freezeStack = freezingRecipes.getFreezingResult(new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().getMetaFromState(event.getState())), "light");
		if(tool != null && (tool == MItems.fire_axe || tool == MItems.fire_dagger || tool == MItems.fire_hoe || tool == MItems.fire_pickaxe || tool == MItems.fire_shovel || tool == MItems.fire_sword) && smeltStack != ItemStack.EMPTY && event.getDrops().size() > 0 && smeltStack.getItem() != event.getDrops().get(0).getItem())
		{
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(smeltStack.getItem(), smeltStack.getCount(), smeltStack.getMetadata()));
		}
		else if(tool != null && (tool == MItems.ice_axe || tool == MItems.ice_dagger || tool == MItems.ice_hoe || tool == MItems.ice_pickaxe || tool == MItems.ice_shovel || tool == MItems.ice_sword) && freezeStack != ItemStack.EMPTY && event.getDrops().size() > 0 && freezeStack.getItem() != event.getDrops().get(0).getItem())
		{
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(freezeStack.getItem(), freezeStack.getCount(), freezeStack.getMetadata()));
		}
		else if(event.getState() == Blocks.RED_FLOWER.getStateFromMeta(2) && !event.isSilkTouching())
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

	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent event)
	{
		Calendar calendar = Calendar.getInstance();
		LootEntry animal_bones_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/animal_bones"), 1, 1, new LootCondition[0], "animal_bones_entry"); // weight doesn't matter since it's the only entry in the pool. Other params set as you wish.
		LootPool animal_bones_pool = new LootPool(new LootEntry[] {animal_bones_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "animal_bones_pool"); // Other params set as you wish.
		LootEntry flesh_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/flesh"), 1, 1, new LootCondition[0], "flesh_entry");
		LootPool flesh_pool = new LootPool(new LootEntry[] {flesh_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "flesh_pool");
		LootEntry candy_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/candy"), 1, 1, new LootCondition[0], "candy_entry");
		LootPool candy_pool = new LootPool(new LootEntry[] {candy_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "candy_pool");
		
		if(event.getName().equals(LootTableList.ENTITIES_PIG))
		{
			LootEntry fat_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/pig/fat"), 1, 1, new LootCondition[0], "fat_entry");
			LootPool fat_pool = new LootPool(new LootEntry[] {fat_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "fat_pool");

			event.getTable().addPool(fat_pool);
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_COW) || event.getName().equals(LootTableList.ENTITIES_MUSHROOM_COW))
		{
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_SHEEP))
		{
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_DONKEY) || event.getName().equals(LootTableList.ENTITIES_HORSE) || event.getName().equals(LootTableList.ENTITIES_MULE) || event.getName().equals(LootTableList.ENTITIES_LLAMA))
		{
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_POLAR_BEAR))
		{
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_SQUID))
		{
			LootEntry tentacle_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/squid/squid_tentacle"), 1, 1, new LootCondition[0], "tentacle_entry");
			LootPool tentacle_pool = new LootPool(new LootEntry[] {tentacle_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "tentacle_pool");

			event.getTable().addPool(tentacle_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_VILLAGER) || event.getName().equals(LootTableList.ENTITIES_VINDICATION_ILLAGER) || event.getName().equals(LootTableList.ENTITIES_EVOCATION_ILLAGER))
		{
			event.getTable().addPool(flesh_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_WITCH))
		{
			LootEntry wand_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/witch/wand"), 1, 1, new LootCondition[0], "wand_entry");
			LootPool wand_pool = new LootPool(new LootEntry[] {wand_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "wand_pool");
			
			event.getTable().addPool(wand_pool);
			event.getTable().addPool(flesh_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_HUSK))
		{
			LootEntry salt_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/husk/salt"), 1, 1, new LootCondition[0], "salt_entry");
			LootPool salt_pool = new LootPool(new LootEntry[] {salt_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "salt_pool");
			
			event.getTable().addPool(salt_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_BAT))
		{
			LootEntry sinew_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/bat/wing_sinew"), 1, 1, new LootCondition[0], "sinew_entry");
			LootPool sinew_pool = new LootPool(new LootEntry[] {sinew_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "sinew_pool");
			
			event.getTable().addPool(sinew_pool);
		}
		
		if(calendar.get(2) + 1 == 10 && calendar.get(5) >= 15 && calendar.get(5) <= 31)
		{
			if(event.getName().equals(LootTableList.ENTITIES_BAT) || event.getName().equals(LootTableList.ENTITIES_CAVE_SPIDER) || event.getName().equals(LootTableList.ENTITIES_HUSK) || event.getName().equals(LootTableList.ENTITIES_SKELETON) || event.getName().equals(LootTableList.ENTITIES_SKELETON_HORSE) || event.getName().equals(LootTableList.ENTITIES_SPIDER) || event.getName().equals(LootTableList.ENTITIES_STRAY) || event.getName().equals(LootTableList.ENTITIES_WITCH) || event.getName().equals(LootTableList.ENTITIES_WITHER_SKELETON) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE_HORSE) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE_VILLAGER))
			{
				event.getTable().addPool(candy_pool);
			}
		}
			
	    if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON))
	    {
	    	LootEntry basic_dungeon = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_basic_dungeon"), 1, 1, new LootCondition[0], "m_basic_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {basic_dungeon}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_basic_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT))
	    {
	    	LootEntry mineshaft = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_abandoned_mineshaft"), 1, 1, new LootCondition[0], "m_mineshaft_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {mineshaft}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_mineshaft_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID))
	    {
	    	LootEntry pyramid = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_desert_temple"), 1, 1, new LootCondition[0], "m_pyramid_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {pyramid}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_pyramid_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_END_CITY_TREASURE))
	    {
	    	LootEntry endCity = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_end_city"), 1, 1, new LootCondition[0], "m_end_city_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {endCity}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_end_city_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_IGLOO_CHEST))
	    {
	    	LootEntry igloo = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_igloo"), 1, 1, new LootCondition[0], "m_igloo_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {igloo}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_igloo_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE))
	    {
	    	LootEntry jungleTemple = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_jungle_temple"), 1, 1, new LootCondition[0], "m_jungle_temple_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {jungleTemple}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_jungle_temple_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER))
	    {
	    	LootEntry jungleDispenser = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_jungle_dispenser"), 1, 1, new LootCondition[0], "m_jungle_dispenser_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {jungleDispenser}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_jungle_dispenser_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE))
	    {
	    	LootEntry netherFortress = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_nether_fortress"), 1, 1, new LootCondition[0], "m_nether_fortress_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {netherFortress}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_nether_fortress_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_SPAWN_BONUS_CHEST))
	    {
	    	LootEntry bonusChest = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_bonus_chest"), 1, 1, new LootCondition[0], "m_bonus_chest_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {bonusChest}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_bonus_chest_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR))
	    {
	    	LootEntry corridor = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_stronghold_corridor"), 1, 1, new LootCondition[0], "m_stronghold_corridor_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {corridor}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_stronghold_corridor_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING))
	    {
	    	LootEntry crossing = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_stronghold_crossing"), 1, 1, new LootCondition[0], "m_stronghold_crossing_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {crossing}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_stronghold_crossing_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY))
	    {
	    	LootEntry library = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_stronghold_library"), 1, 1, new LootCondition[0], "m_stronghold_library_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {library}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_stronghold_library_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH))
	    {
	    	LootEntry blacksmith = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_blacksmith"), 1, 1, new LootCondition[0], "m_blacksmith_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {blacksmith}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_blacksmith_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    if (event.getName().equals(LootTableList.CHESTS_WOODLAND_MANSION))
	    {
	    	LootEntry mansion = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/m_woodland_mansion"), 1, 1, new LootCondition[0], "m_woodland_mansion_loot");

	    	LootPool pool = new LootPool(new LootEntry[] {mansion}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "m_woodland_mansion_pool");

	    	event.getTable().addPool(pool);
	    }
	    
	    //Command for spawning test chests w/ loot tables: /setblock ~ ~ ~ minecraft:chest 2 replace {LootTable:"chests/simple_dungeon"}*/
	}
	
	public static NBTTagList getEnchantments(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        return nbttagcompound != null ? nbttagcompound.getTagList("ench", 10) : new NBTTagList();
    }
}
