package minestrapp.crafting;

import java.util.HashMap;
import java.util.Map;

import minestrapp.MBlocks;
import minestrapp.MItems;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFallingDust;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.oredict.OreDictionary;

public class TannerRecipes {
	
	public static TannerRecipes instance = new TannerRecipes();
	public Map<ItemStack, TannerRecipe> recipes = new HashMap<ItemStack, TannerRecipe>();
	
	public TannerRecipes()
	{
		//Jerky
		this.addRecipe(new ItemStack(Items.CHICKEN), new ItemStack(MItems.chicken_jerky), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, true, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.RABBIT), new ItemStack(MItems.rabbit_jerky), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, true, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.MUTTON), new ItemStack(MItems.mutton_jerky), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, true, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.BEEF), new ItemStack(MItems.beef_jerky), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, true, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.PORKCHOP), new ItemStack(MItems.pork_jerky), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, true, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.FISH, 1, 0), new ItemStack(MItems.cod_jerky), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, true, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.FISH, 1, 1), new ItemStack(MItems.salmon_jerky), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, true, Item.getIdFromItem(MItems.salt), 0);
		
		//Curing
		this.addRecipe(new ItemStack(Items.RABBIT_HIDE), new ItemStack(MItems.leather, 1, 0), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.LEATHER), new ItemStack(MItems.leather, 1, 1), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 3), new ItemStack(MItems.leather, 1, 2), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 4), new ItemStack(MItems.leather, 1, 3), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 5), new ItemStack(MItems.leather, 1, 4), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 6), new ItemStack(MItems.leather, 1, 15), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.flesh), new ItemStack(MItems.leather, 1, 5), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(Items.ROTTEN_FLESH), new ItemStack(MItems.leather, 1, 6), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 9), new ItemStack(MItems.leather2, 1, 0), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 11), new ItemStack(MItems.leather2, 1, 1), new ItemStack(MItems.salt), 30, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.salt), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 6), new ItemStack(MItems.leather2, 1, 2), new ItemStack(MItems.smelling_salts), 45, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.smelling_salts), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 9), new ItemStack(MItems.leather2, 1, 3), new ItemStack(MItems.smelling_salts), 45, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.smelling_salts), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 10), new ItemStack(MItems.leather2, 1, 4), new ItemStack(MItems.smelling_salts), 45, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.smelling_salts), 0);
		this.addRecipe(new ItemStack(MItems.mob_loot, 1, 11), new ItemStack(MItems.leather2, 1, 5), new ItemStack(MItems.smelling_salts), 45, true, SoundEvents.BLOCK_SAND_BREAK, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.smelling_salts), 0);
		
		//Scudding
		this.addRecipe(new ItemStack(MItems.leather, 1, 0), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 0);
		this.addRecipe(new ItemStack(MItems.leather, 1, 0), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 0);
		this.addRecipe(new ItemStack(MItems.leather, 1, 0), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 0);
		this.addRecipe(new ItemStack(MItems.leather, 1, 0), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 0);
		this.addRecipe(new ItemStack(MItems.leather, 1, 0), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 0);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 1), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 1);
		this.addRecipe(new ItemStack(MItems.leather, 1, 1), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 1);
		this.addRecipe(new ItemStack(MItems.leather, 1, 1), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 1);
		this.addRecipe(new ItemStack(MItems.leather, 1, 1), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 1);
		this.addRecipe(new ItemStack(MItems.leather, 1, 1), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 1);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 2), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 2);
		this.addRecipe(new ItemStack(MItems.leather, 1, 2), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 2);
		this.addRecipe(new ItemStack(MItems.leather, 1, 2), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 2);
		this.addRecipe(new ItemStack(MItems.leather, 1, 2), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 2);
		this.addRecipe(new ItemStack(MItems.leather, 1, 2), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 2);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 3), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 3);
		this.addRecipe(new ItemStack(MItems.leather, 1, 3), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 3);
		this.addRecipe(new ItemStack(MItems.leather, 1, 3), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 3);
		this.addRecipe(new ItemStack(MItems.leather, 1, 3), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 3);
		this.addRecipe(new ItemStack(MItems.leather, 1, 3), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 3);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 4), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 4);
		this.addRecipe(new ItemStack(MItems.leather, 1, 4), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 4);
		this.addRecipe(new ItemStack(MItems.leather, 1, 4), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 4);
		this.addRecipe(new ItemStack(MItems.leather, 1, 4), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 4);
		this.addRecipe(new ItemStack(MItems.leather, 1, 4), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 4);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 5), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 5);
		this.addRecipe(new ItemStack(MItems.leather, 1, 5), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 5);
		this.addRecipe(new ItemStack(MItems.leather, 1, 5), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 5);
		this.addRecipe(new ItemStack(MItems.leather, 1, 5), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 5);
		this.addRecipe(new ItemStack(MItems.leather, 1, 5), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 5);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 6), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 6);
		this.addRecipe(new ItemStack(MItems.leather, 1, 6), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 6);
		this.addRecipe(new ItemStack(MItems.leather, 1, 6), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 6);
		this.addRecipe(new ItemStack(MItems.leather, 1, 6), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 6);
		this.addRecipe(new ItemStack(MItems.leather, 1, 6), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 6);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 15), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 15);
		this.addRecipe(new ItemStack(MItems.leather, 1, 15), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 15);
		this.addRecipe(new ItemStack(MItems.leather, 1, 15), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 15);
		this.addRecipe(new ItemStack(MItems.leather, 1, 15), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 15);
		this.addRecipe(new ItemStack(MItems.leather, 1, 15), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather), 15);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 0), new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 0);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 0), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 0);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 0), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 0);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 0), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 0);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 0), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 0);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 1), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 1);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 1), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 1);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 1), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 1);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 1), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 1);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 1), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 1);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 2), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 2);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 2), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 2);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 2), new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 2);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 2), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 2);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 2), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 2);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 3), new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 3);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 3), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 3);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 3), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 3);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 3), new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 3);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 3), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 3);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 4), new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 4);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 4), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 4);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 4), new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 4);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 4), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 4);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 4), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 4);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 5), new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.wooden_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 5);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 5), new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.stone_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 5);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 5), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.iron_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 5);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 5), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.diamond_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 5);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 5), new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.titanium_dagger), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, false, Item.getIdFromItem(MItems.leather2), 5);
		
		//Tanning
		this.addRecipe(new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.leather, 1, 11), new ItemStack(MItems.tannic, 1, 0), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.leather, 1, 11), new ItemStack(MItems.tannic, 1, 1), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 7), new ItemStack(MItems.leather, 1, 12), new ItemStack(MItems.tannic, 1, 2), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.leather, 1, 11), new ItemStack(MItems.tannic, 1, 0), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.leather, 1, 12), new ItemStack(MItems.tannic, 1, 1), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 8), new ItemStack(MItems.leather, 1, 13), new ItemStack(MItems.tannic, 1, 2), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.leather, 1, 12), new ItemStack(MItems.tannic, 1, 0), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.leather, 1, 13), new ItemStack(MItems.tannic, 1, 1), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 9), new ItemStack(MItems.leather, 1, 14), new ItemStack(MItems.tannic, 1, 2), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		
		this.addRecipe(new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.leather, 1, 13), new ItemStack(MItems.tannic, 1, 0), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.leather, 1, 14), new ItemStack(MItems.tannic, 1, 1), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather, 1, 10), new ItemStack(MItems.leather, 1, 14), new ItemStack(MItems.tannic, 1, 2), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.leather, 1, 12), new ItemStack(MItems.tannic, 1, 0), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.leather, 1, 13), new ItemStack(MItems.tannic, 1, 1), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.leather, 1, 14), new ItemStack(MItems.tannic, 1, 2), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.leather2, 1, 8), new ItemStack(MItems.void_jam), 75, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 6), new ItemStack(MItems.leather2, 1, 9), new ItemStack(MBlocks.glow_paste), 75, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		
		this.addRecipe(new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.leather, 1, 13), new ItemStack(MItems.tannic, 1, 0), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.leather, 1, 14), new ItemStack(MItems.tannic, 1, 1), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.leather2, 1, 8), new ItemStack(MItems.tannic, 1, 2), 60, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.leather2, 1, 9), new ItemStack(MItems.void_jam), 75, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
		this.addRecipe(new ItemStack(MItems.leather2, 1, 7), new ItemStack(MItems.leather2, 1, 9), new ItemStack(MBlocks.glow_paste), 75, true, SoundEvents.BLOCK_BREWING_STAND_BREW, EnumParticleTypes.DRIP_WATER, true, null);
	}
	
	
	public void addRecipe(ItemStack input, ItemStack output, ItemStack tool, int time, boolean consumeItem, SoundEvent sound, EnumParticleTypes particle, boolean sun, int... particleParams)
	{
		this.recipes.put(input, new TannerRecipe(input, output, tool, time, consumeItem, sound, particle, sun, particleParams));
	}
	
	
	public class TannerRecipe
	{	
		public ItemStack input;
		public ItemStack output;
		public ItemStack tool;
		public int time; // in seconds
		public boolean consumeItem;
		public SoundEvent sound;
		public EnumParticleTypes particle;
		public boolean sun;
		public int[] particleParams;
		
		public TannerRecipe(ItemStack input, ItemStack output, ItemStack tool, int time, boolean consumeItem, SoundEvent sound, EnumParticleTypes particle, boolean sun, int... particleParams) {
			this.input = input;
			this.output = output;
			this.tool = tool;
			this.time = time;
			this.consumeItem = consumeItem;
			this.sound = sound;
			this.particle = particle;
			this.sun = sun;
			this.particleParams = particleParams;
		}
	}
}
