package minestrapp.event;

import java.awt.Color;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import minestrapp.compat.CompatAbstract;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.registries.IForgeRegistry;
import org.lwjgl.input.Mouse;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MMaterials;
import minestrapp.Minestrapp5;
import minestrapp.config.MConfig;
import minestrapp.crafting.FreezingRecipes;
import minestrapp.entity.mob.EntityBurfalaunt;
import minestrapp.item.ItemSeedBag;
import minestrapp.item.util.MItemsSeedFood;
import minestrapp.mobs.models.ModelSheetGhost;
import minestrapp.potion.MPotions;
import minestrapp.utils.EntityUtil;
import minestrapp.utils.NBTUtil;
import minestrapp.worldgen.MWorldDecorator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFrostedIce;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
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
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@EventBusSubscriber
public class MEventHandler
{
	private static final FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();
	private static final FreezingRecipes freezingRecipes = FreezingRecipes.instance();


	@SubscribeEvent
	public static void onPlayerJoin (EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			NBTTagCompound nbt = NBTUtil.getPersistedPlayerTag(player);
			
			player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(MConfig.startingHealth);

			if (nbt.hasKey("health"))
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(nbt.getDouble("health"));
			}
		}
	}
	
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
	    	ResourceLocation leatherHelmet = new ResourceLocation("minecraft:leather_helmet");
	    	ResourceLocation leatherChestplate = new ResourceLocation("minecraft:leather_chestplate");
	    	ResourceLocation leatherLeggings = new ResourceLocation("minecraft:leather_leggings");
	    	ResourceLocation leatherBoots = new ResourceLocation("minecraft:leather_boots");
	    	ResourceLocation itemFrame = new ResourceLocation("minecraft:item_frame");
	    	ResourceLocation book = new ResourceLocation("minecraft:book");
	    	ResourceLocation iron_bars = new ResourceLocation("minecraft:iron_bars");
	    	ResourceLocation daylight_detector = new ResourceLocation("minecraft:daylight_detector");
	    	ResourceLocation comparator = new ResourceLocation("minecraft:comparator");
	    	ResourceLocation observer = new ResourceLocation("minecraft:observer");
	    	ResourceLocation cake = new ResourceLocation("minecraft:cake");
	    	ResourceLocation leather = new ResourceLocation("minecraft:leather");
	    	ResourceLocation sandstone = new ResourceLocation("minecraft:sandstone");
	    	ResourceLocation red_sandstone = new ResourceLocation("minecraft:red_sandstone");
	    	ResourceLocation magma = new ResourceLocation("minecraft:magma");
	    	ResourceLocation fire_charge = new ResourceLocation("minecraft:fire_charge");
	    	ResourceLocation ender_eye = new ResourceLocation("minecraft:ender_eye");
	
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
	        modRegistry.remove(leatherHelmet);
	        modRegistry.remove(leatherChestplate);
	        modRegistry.remove(leatherLeggings);
	        modRegistry.remove(leatherBoots);
	        modRegistry.remove(itemFrame);
	        modRegistry.remove(book);
	        modRegistry.remove(iron_bars);
	        modRegistry.remove(daylight_detector);
	        modRegistry.remove(comparator);
	        modRegistry.remove(observer);
	        modRegistry.remove(cake);
	        modRegistry.remove(leather);
	        modRegistry.remove(sandstone);
	        modRegistry.remove(red_sandstone);
	        modRegistry.remove(magma);
	        modRegistry.remove(fire_charge);
	        modRegistry.remove(ender_eye);
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
	public static void onPlayerEat (LivingEntityUseItemEvent.Finish event)
	{
		if(event.getEntityLiving() instanceof EntityPlayer && ((EntityPlayer)event.getEntityLiving()).getFoodStats().getFoodLevel() == 20)
		{
			if(event.getItem().getItem() == Items.PUMPKIN_PIE)
			{
				event.getEntityLiving().addPotionEffect(new PotionEffect(MPotions.wellFed, 1800, 0, true, false));
			}
			else if(event.getItem().getItem() == Items.RABBIT_STEW)
			{
				event.getEntityLiving().addPotionEffect(new PotionEffect(MPotions.wellFed, 2400, 0, true, false));
			}
		}
	}
	
	@SubscribeEvent
	public static void onLivingUpdate (LivingUpdateEvent event)
	{
		EntityLivingBase living = event.getEntityLiving();
		
			if(living.getHeldItemMainhand().getItem() == MItems.hang_glider_dimensium || living.getHeldItemOffhand().getItem() == MItems.hang_glider_dimensium)
			{
				living.motionY = 0;
			}
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
		if(event.getState().getBlock() == Blocks.BEDROCK && tool != null && tool instanceof ItemTool)
		{
			event.getHarvester().getHeldItem(EnumHand.MAIN_HAND).damageItem(4000, event.getHarvester());
			event.getDrops().add(new ItemStack(Blocks.BEDROCK));
		}
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
		LootEntry villager_skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/villager/skull_rare"), 1, 1, new LootCondition[0], "villager_skull_entry");
		LootPool villager_skull_pool = new LootPool(new LootEntry[] {villager_skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "villager_skull_pool");
		LootEntry human_skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/skeleton/skull_rare"), 1, 1, new LootCondition[0], "human_skull_entry");
		LootPool human_skull_pool = new LootPool(new LootEntry[] {human_skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "human_skull_pool");
		LootEntry pig_skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/pig/skull_rare"), 1, 1, new LootCondition[0], "pig_skull_entry");
		LootPool pig_skull_pool = new LootPool(new LootEntry[] {pig_skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "pig_skull_pool");
		LootEntry sinew_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/bat/wing_sinew"), 1, 1, new LootCondition[0], "sinew_entry");
		LootPool sinew_pool = new LootPool(new LootEntry[] {sinew_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "sinew_pool");
		LootEntry heart_piece_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/heart_piece"), 1, 1, new LootCondition[0], "heart_piece_entry");
		LootPool heart_piece_pool = new LootPool(new LootEntry[] {heart_piece_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "heart_piece_pool");
		LootEntry skeletal_hand_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/skeleton/skeletal_hand"), 1, 1, new LootCondition[0], "skeletal_hand_entry");
		LootPool skeletal_hand_pool = new LootPool(new LootEntry[] {skeletal_hand_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "skeletal_hand_pool");
		
		if(event.getName().equals(LootTableList.ENTITIES_PIG))
		{
			LootEntry fat_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/pig/fat"), 1, 1, new LootCondition[0], "fat_entry");
			LootPool fat_pool = new LootPool(new LootEntry[] {fat_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "fat_pool");
			LootEntry pig_skin_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/pig/pig_skin"), 1, 1, new LootCondition[0], "pig_skin_entry");
			LootPool pig_skin_pool = new LootPool(new LootEntry[] {pig_skin_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "pig_skin_pool");
			
			event.getTable().addPool(pig_skull_pool);
			event.getTable().addPool(fat_pool);
			event.getTable().addPool(pig_skin_pool);
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_ZOMBIE_PIGMAN))
		{
			event.getTable().addPool(pig_skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_COW) || event.getName().equals(LootTableList.ENTITIES_MUSHROOM_COW))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/cow/skull_rare"), 1, 1, new LootCondition[0], "cow_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "cow_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(animal_bones_pool);
			
			if(event.getName().equals(LootTableList.ENTITIES_MUSHROOM_COW))
			{
				event.getTable().removePool("main");
				
				LootEntry hide_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/mooshroom/mooshroom_hide"), 1, 1, new LootCondition[0], "mooshroom_hide_entry");
				LootPool hide_pool = new LootPool(new LootEntry[] {hide_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "mooshroom_hide_pool");
				LootEntry beef_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/mooshroom/raw_beef"), 1, 1, new LootCondition[0], "mooshroom_beef_entry");
				LootPool beef_pool = new LootPool(new LootEntry[] {beef_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "mooshroom_beef_pool");
				
				event.getTable().addPool(hide_pool);
				//event.getTable().addPool(beef_pool);
			}
		}
		else if(event.getName().equals(LootTableList.ENTITIES_SHEEP))
		{
			LootEntry sheep_hoof_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/sheep/sheep_hoof"), 1, 1, new LootCondition[0], "sheep_hoof_entry");
			LootPool sheep_hoof_pool = new LootPool(new LootEntry[] {sheep_hoof_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "sheep_hoof_pool");
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/sheep/skull_rare"), 1, 1, new LootCondition[0], "sheep_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "sheep_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(sheep_hoof_pool);
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(EntityBurfalaunt.LOOT_TABLE))
		{
			LootEntry burfalaunt_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/burfalaunt/burfalaunt"), 1, 1, new LootCondition[0], "burfalaunt_entry");
			LootPool burfalaunt_pool = new LootPool(new LootEntry[] {burfalaunt_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "burfalaunt_pool");
			
			event.getTable().addPool(burfalaunt_pool);
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_DONKEY) || event.getName().equals(LootTableList.ENTITIES_HORSE) || event.getName().equals(LootTableList.ENTITIES_MULE))
		{
			event.getTable().removePool("main");
			
			LootEntry horse_hide_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/horse/horse_hide"), 1, 1, new LootCondition[0], "horse_hide_entry");
			LootPool horse_hide_pool = new LootPool(new LootEntry[] {horse_hide_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "horse_hide_pool");
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/horse/skull_rare"), 1, 1, new LootCondition[0], "horse_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "horse_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(horse_hide_pool);
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_LLAMA))
		{
			event.getTable().removePool("main");
			
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/llama/skull_rare"), 1, 1, new LootCondition[0], "llama_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "llama_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_WOLF))
		{
			LootEntry wolf_hide_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/wolf/wolf_hide"), 1, 1, new LootCondition[0], "wolf_hide_entry");
			LootPool wolf_hide_pool = new LootPool(new LootEntry[] {wolf_hide_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "wolf_hide_pool");
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/wolf/skull_rare"), 1, 1, new LootCondition[0], "wolf_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "wolf_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(wolf_hide_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_POLAR_BEAR))
		{
			LootEntry polar_bear_hide_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/polar_bear/polar_bear_hide"), 1, 1, new LootCondition[0], "polar_bear_hide_entry");
			LootPool polar_bear_hide_pool = new LootPool(new LootEntry[] {polar_bear_hide_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "polar_bear_hide_pool");
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/polar_bear/skull_rare"), 1, 1, new LootCondition[0], "polar_bear_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "polar_bear_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(polar_bear_hide_pool);
			event.getTable().addPool(animal_bones_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_SQUID))
		{
			LootEntry tentacle_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/squid/squid_tentacle"), 1, 1, new LootCondition[0], "tentacle_entry");
			LootPool tentacle_pool = new LootPool(new LootEntry[] {tentacle_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "tentacle_pool");
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/squid/skull_rare"), 1, 1, new LootCondition[0], "squid_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "squid_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(tentacle_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_SPIDER) || event.getName().equals(LootTableList.ENTITIES_CAVE_SPIDER))
		{
			LootEntry spider_leg_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/spider/spider_leg"), 1, 1, new LootCondition[0], "spider_leg_entry");
			LootPool spider_leg_pool = new LootPool(new LootEntry[] {spider_leg_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "spider_leg_pool");
			
			event.getTable().addPool(spider_leg_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_VILLAGER) || event.getName().equals(LootTableList.ENTITIES_VINDICATION_ILLAGER))
		{
			event.getTable().addPool(villager_skull_pool);
			event.getTable().addPool(flesh_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_EVOCATION_ILLAGER))
		{
			event.getTable().addPool(villager_skull_pool);
			event.getTable().addPool(flesh_pool);
			event.getTable().addPool(heart_piece_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_WITCH))
		{
			LootEntry wand_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/witch/wand"), 1, 1, new LootCondition[0], "wand_entry");
			LootPool wand_pool = new LootPool(new LootEntry[] {wand_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "wand_pool");
			
			event.getTable().addPool(villager_skull_pool);
			event.getTable().addPool(wand_pool);
			event.getTable().addPool(flesh_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_ZOMBIE_VILLAGER))
		{
			event.getTable().addPool(villager_skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_HUSK))
		{
			LootEntry salt_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/husk/salt"), 1, 1, new LootCondition[0], "salt_entry");
			LootPool salt_pool = new LootPool(new LootEntry[] {salt_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "salt_pool");
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/husk/skull_rare"), 1, 1, new LootCondition[0], "husk_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "husk_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(salt_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_BAT))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/bat/skull_rare"), 1, 1, new LootCondition[0], "bat_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "bat_skull_pool");
			
			event.getTable().addPool(sinew_pool);
			event.getTable().addPool(skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_VEX))
		{
			LootEntry effervexcense_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/vex/effervexcense"), 1, 1, new LootCondition[0], "effervexcense_entry");
			LootPool effervexcense_pool = new LootPool(new LootEntry[] {effervexcense_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "effervexcense_pool");
			
			event.getTable().addPool(effervexcense_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_PARROT))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/parrot/skull_rare"), 1, 1, new LootCondition[0], "parrot_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "parrot_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(sinew_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_CHICKEN))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/chicken/skull_rare"), 1, 1, new LootCondition[0], "chicken_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "chicken_skull_pool");
			
			event.getTable().addPool(skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_RABBIT))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/rabbit/skull_rare"), 1, 1, new LootCondition[0], "rabbit_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "rabbit_skull_pool");
			
			event.getTable().addPool(skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_OCELOT))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/ocelot/skull_rare"), 1, 1, new LootCondition[0], "ocelot_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "ocelot_skull_pool");
			
			event.getTable().addPool(skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_STRAY))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/stray/skull_rare"), 1, 1, new LootCondition[0], "stray_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "stray_skull_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(skeletal_hand_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_CREEPER))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/creeper/skull_rare"), 1, 1, new LootCondition[0], "creeper_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "creeper_skull_pool");
			LootEntry hide_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/creeper/creeper_carapace"), 1, 1, new LootCondition[0], "creeper_carapace_entry");
			LootPool hide_pool = new LootPool(new LootEntry[] {hide_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "creeper_carapace_pool");
			
			event.getTable().addPool(skull_pool);
			event.getTable().addPool(hide_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_GUARDIAN))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/guardian/skull_rare"), 1, 1, new LootCondition[0], "guardian_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "guardian_skull_pool");
			
			event.getTable().addPool(skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_ENDERMAN))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/enderman/skull_rare"), 1, 1, new LootCondition[0], "enderman_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "enderman_skull_pool");
			
			event.getTable().addPool(skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_SHULKER))
		{
			LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/shulker/skull_rare"), 1, 1, new LootCondition[0], "shulker_skull_entry");
			LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "shulker_skull_pool");
			
			event.getTable().addPool(skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_ZOMBIE))
		{
			event.getTable().addPool(human_skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_SKELETON))
		{
			event.getTable().addPool(skeletal_hand_pool);
			event.getTable().addPool(human_skull_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_WITHER_SKELETON))
		{
			event.getTable().addPool(skeletal_hand_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_ELDER_GUARDIAN))
		{
			event.getTable().addPool(heart_piece_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_ENDERMITE))
		{
			LootEntry honey_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/endermite/mite_honey"), 1, 1, new LootCondition[0], "honey_entry");
			LootPool honey_pool = new LootPool(new LootEntry[] {honey_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "honey_pool");
			
			event.getTable().addPool(honey_pool);
		}
		else if(event.getName().equals(LootTableList.ENTITIES_GHAST))
		{
			LootEntry hide_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/ghast/ghast_hide"), 1, 1, new LootCondition[0], "ghast_hide_entry");
			LootPool hide_pool = new LootPool(new LootEntry[] {hide_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "ghast_hide_pool");
			
			event.getTable().addPool(hide_pool);
		}
		
		//Halloween
		if(calendar.get(2) + 1 == 10 && calendar.get(5) >= 10 && calendar.get(5) <= 31)
		{
			if(event.getName().equals(LootTableList.ENTITIES_BAT) || event.getName().equals(LootTableList.ENTITIES_CAVE_SPIDER) || event.getName().equals(LootTableList.ENTITIES_HUSK) || event.getName().equals(LootTableList.ENTITIES_SKELETON) || event.getName().equals(LootTableList.ENTITIES_SKELETON_HORSE) || event.getName().equals(LootTableList.ENTITIES_SPIDER) || event.getName().equals(LootTableList.ENTITIES_STRAY) || event.getName().equals(LootTableList.ENTITIES_WITCH) || event.getName().equals(LootTableList.ENTITIES_WITHER_SKELETON) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE_HORSE) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE_VILLAGER))
			{
				event.getTable().addPool(candy_pool);
			}
			
			if(event.getName().getResourcePath().startsWith("chests/"))
			{
				LootEntry halloween_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/halloween"), 1, 1, new LootCondition[0], "halloween_chest_loot");
		    	LootPool halloween_pool = new LootPool(new LootEntry[] {halloween_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "halloween_chest_pool");

		    	event.getTable().addPool(halloween_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_BAT))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/bat/skull_event"), 1, 1, new LootCondition[0], "bat_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "bat_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_PARROT))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/parrot/skull_event"), 1, 1, new LootCondition[0], "parrot_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "parrot_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_CHICKEN))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/chicken/skull_event"), 1, 1, new LootCondition[0], "chicken_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "chicken_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_RABBIT))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/rabbit/skull_event"), 1, 1, new LootCondition[0], "rabbit_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "rabbit_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_PIG) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE_PIGMAN))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/pig/skull_event"), 1, 1, new LootCondition[0], "pig_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "pig_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_SHEEP))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/sheep/skull_event"), 1, 1, new LootCondition[0], "sheep_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "sheep_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_COW) || event.getName().equals(LootTableList.ENTITIES_MUSHROOM_COW))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/cow/skull_event"), 1, 1, new LootCondition[0], "cow_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "cow_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_LLAMA))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/llama/skull_event"), 1, 1, new LootCondition[0], "llama_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "llama_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_HORSE) || event.getName().equals(LootTableList.ENTITIES_MULE) || event.getName().equals(LootTableList.ENTITIES_DONKEY) || event.getName().equals(LootTableList.ENTITIES_SKELETON_HORSE))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/horse/skull_event"), 1, 1, new LootCondition[0], "horse_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "horse_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_OCELOT))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/ocelot/skull_event"), 1, 1, new LootCondition[0], "ocelot_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "ocelot_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_WOLF))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/wolf/skull_event"), 1, 1, new LootCondition[0], "wolf_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "wolf_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_SQUID))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/squid/skull_event"), 1, 1, new LootCondition[0], "squid_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "squid_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_POLAR_BEAR))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/polar_bear/skull_event"), 1, 1, new LootCondition[0], "polar_bear_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "polar_bear_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_VILLAGER) || event.getName().equals(LootTableList.ENTITIES_VINDICATION_ILLAGER) || event.getName().equals(LootTableList.ENTITIES_EVOCATION_ILLAGER) || event.getName().equals(LootTableList.ENTITIES_WITCH) || event.getName().equals(LootTableList.ENTITIES_ZOMBIE_VILLAGER))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/villager/skull_event"), 1, 1, new LootCondition[0], "villager_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "villager_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_ZOMBIE) || event.getName().equals(LootTableList.ENTITIES_SKELETON))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/skeleton/skull_event"), 1, 1, new LootCondition[0], "skeleton_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "skeleton_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_HUSK))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/husk/skull_event"), 1, 1, new LootCondition[0], "husk_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "husk_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_STRAY))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/stray/skull_event"), 1, 1, new LootCondition[0], "stray_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "stray_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_CREEPER))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/creeper/skull_event"), 1, 1, new LootCondition[0], "creeper_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "creeper_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_GUARDIAN))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/guardian/skull_event"), 1, 1, new LootCondition[0], "guardian_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "guardian_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_ENDERMAN))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/enderman/skull_event"), 1, 1, new LootCondition[0], "enderman_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "enderman_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
			else if(event.getName().equals(LootTableList.ENTITIES_SHULKER))
			{
				LootEntry skull_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":mob/shulker/skull_event"), 1, 1, new LootCondition[0], "shulker_event_skull_entry");
				LootPool skull_pool = new LootPool(new LootEntry[] {skull_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "shulker_event_skull_pool");
				
				event.getTable().addPool(skull_pool);
			}
		}
		//New Year
		if((calendar.get(2) + 1 == 12 && calendar.get(5) >= 26 && calendar.get(5) <= 31) || (calendar.get(2) + 1 == 1 && calendar.get(5) >= 0 && calendar.get(5) <= 7))
		{
			if(event.getName().getResourcePath().startsWith("chests/"))
			{
				LootEntry new_year_entry = new LootEntryTable(new ResourceLocation(Minestrapp5.MODID + ":dungeon/new_year"), 1, 1, new LootCondition[0], "new_year_chest_loot");
		    	LootPool new_year_pool = new LootPool(new LootEntry[] {new_year_entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "new_year_chest_pool");

		    	event.getTable().addPool(new_year_pool);
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
	
	/*@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(PlayerTickEvent event)
    {
		
        if (event.phase == TickEvent.Phase.START && event.player.world.isRemote) // only proceed if START phase otherwise, will execute twice per tick
        {
            EntityPlayer thePlayer = event.player;
            Minestrapp5.proxy.handleMaterialAcceleration(thePlayer, MBlocks.liquid_crystalfloe.getDefaultState().getMaterial());           
        }
    }
	
	@SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=true)
	public void onEvent(WorldTickEvent event)
	{
	    if (event.phase == TickEvent.Phase.END) // only proceed if START phase otherwise, will execute twice per tick
		{
		    if(event.world.loadedEntityList != null && !event.world.loadedEntityList.isEmpty())
		    {
				List entityList = event.world.loadedEntityList;
				Iterator<Entity> iterator = entityList.iterator();
				
				while(iterator.hasNext())
				{
					Entity theEntity = iterator.next();
				   
					/* 
					 * Update all motion of all entities except players that may be inside your fluid
					 *
					Minestrapp5.proxy.handleMaterialAcceleration(theEntity, MBlocks.liquid_crystalfloe.getDefaultState().getMaterial());
				}
		    }
		}
	}*/
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(FogDensity event)
    {
		 if (event.getEntity().isInsideOfMaterial(MMaterials.CRYSTALFLOE))
		 {
			 event.setCanceled(true);
			 event.setDensity(200F);
		 }
		  
		 //event.setCanceled(true); // must cancel event for event handler to take effect
    }
		
		    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(FogColors event)
    {
		 if (event.getEntity().isInsideOfMaterial(MMaterials.CRYSTALFLOE))
		 {
			 event.setRed(188);
			 event.setGreen(213);
			 event.setBlue(202);
		 }
    }
    
    @SubscribeEvent
	public static void onPickUp(EntityItemPickupEvent event)
	{
		if (!event.isCanceled() && (event.getItem().getItem().getItem() instanceof ItemSeeds || event.getItem().getItem().getItem() == MItems.peanuts || (event.getItem().getItem().getItem() == Items.DYE && event.getItem().getItem().getMetadata() == EnumDyeColor.BROWN.getDyeDamage())))
		{
			boolean setSlot = false;
			EntityPlayer player = event.getEntityPlayer();
			ItemStack eventStack = event.getItem().getItem();
			boolean tryFill = true;
			
			for (int i = 0; i < player.inventory.getSizeInventory(); i++)
			{
				ItemStack stack = player.inventory.getStackInSlot(i);

				if (stack.getItem() == eventStack.getItem() && stack.getMetadata() == eventStack.getMetadata() && stack.getCount() < stack.getMaxStackSize())
				{
					tryFill = false;
					break;
				}
			}
			
			if(tryFill)
			{
				for (int i = 0; i < player.inventory.getSizeInventory(); i++)
				{
					ItemStack stack = player.inventory.getStackInSlot(i);
	
					if (stack.getItem() == MItems.seed_bag_empty)
					{
						player.inventory.setInventorySlotContents(i, new ItemStack(MItems.seed_bag_filled));
						event.getItem().setDead();
						event.setCanceled(true);
						break;
						/*ItemBagOfHoldingProvider handler = ItemBagOfHoldingProvider.GetFromStack(stack);
						
						// Only auto-pickup if this bag is set to open and the stack is valid for the bag of holding.
						if (handler.opened && BagOfHoldingContainer.validForContainer(eventStack))
						{
							int firstEmptySlot = -1;
	
							for (int j = 0; j < handler.getSlots(); j++)
							{
								ItemStack pouchStack = handler.getStackInSlot(j);
	
								if (pouchStack.isEmpty() && firstEmptySlot == -1)
								{
									// Found an empty slot, maybe use this later.
									firstEmptySlot = j;
								}
	
								if (!pouchStack.isEmpty())
								{
									// This has an item in it of some kind, determine if they are the same.
									if (pouchStack.areItemsEqual(pouchStack, eventStack))
									{
										int updatedSize = pouchStack.getCount() + eventStack.getCount();
	
										// Make sure we don't go above the stack limit for this slot.
										// If there is too much, just move onto the next slot.
										if (updatedSize <= handler.getSlotLimit(j))
										{
											setSlot = true;
											pouchStack.setCount(updatedSize);
											handler.setStackInSlot(j, pouchStack);
											break;
										}
									}
								}
							}
	
							if (!setSlot && firstEmptySlot != -1)
							{
								// There was not a matching stack, place it in the first empty slot.
								handler.setStackInSlot(firstEmptySlot, eventStack);
								setSlot = true;
							}
	
							if (setSlot)
							{
								// We set an inventory slot, set this event to canceled and break out of the loop.
								player.onItemPickup(event.getItem(), event.getItem().getItem().getCount());
								player.addStat(StatList.getObjectsPickedUpStats(event.getItem().getItem().getItem()),
									event.getItem().getItem().getCount());
								event.getItem().setDead();
								event.setCanceled(true);
								
								handler.UpdateStack(stack);
								ItemBagOfHolding.RefreshItemStack(player, stack);
								break;
							}
						}*/
					}
				}
			}
		}
	}
    
    @SubscribeEvent
	public static void onLightningStrike(EntityStruckByLightningEvent event)
	{
    	Entity entity = event.getEntity();
    	World world = entity.getEntityWorld();
    	
    	if(entity instanceof EntityItem)
    	{
    		ItemStack stack = ((EntityItem)entity).getItem();
    		if(stack.getItem() == MItems.spaghetti && !world.isRemote)
    		{
    			EntityItem newEntity = new EntityItem(world, entity.posX, entity.posY + 2, entity.posZ, new ItemStack(MItems.b_ball_pasta));
    			newEntity.setEntityInvulnerable(true);
    			//newEntity.setVelocity(world.rand.nextInt(20) - world.rand.nextInt(20), 10, world.rand.nextInt(20) - world.rand.nextInt(20));
    			world.spawnEntity(newEntity);
    		}
    	}
	}

	
	public static NBTTagList getEnchantments(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        return nbttagcompound != null ? nbttagcompound.getTagList("ench", 10) : new NBTTagList();
    }
}
