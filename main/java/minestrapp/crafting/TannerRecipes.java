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

public class TannerRecipes {
	
	public static TannerRecipes instance = new TannerRecipes();
	public Map<ItemStack, TannerRecipe> recipes = new HashMap<ItemStack, TannerRecipe>();
	
	public TannerRecipes()
	{
		this.addRecipe(new ItemStack(Items.RABBIT_HIDE), new ItemStack(Items.LEATHER), new ItemStack(MItems.copper_sword), 0, false, SoundEvents.ENTITY_ARMORSTAND_HIT, EnumParticleTypes.ITEM_CRACK, true);
		this.addRecipe(new ItemStack(Items.POTATO), new ItemStack(Items.BAKED_POTATO), new ItemStack(MItems.salt), 15, true, SoundEvents.BLOCK_FIRE_EXTINGUISH, EnumParticleTypes.ITEM_CRACK, false);
	}
	
	
	public void addRecipe(ItemStack input, ItemStack output, ItemStack tool, int time, boolean consumeItem, SoundEvent sound, EnumParticleTypes particle, boolean sun)
	{
		this.recipes.put(input, new TannerRecipe(input, output, tool, time, consumeItem, sound, particle, sun));
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
		
		public TannerRecipe(ItemStack input, ItemStack output, ItemStack tool, int time, boolean consumeItem, SoundEvent sound, EnumParticleTypes particle, boolean sun) {
			this.input = input;
			this.output = output;
			this.tool = tool;
			this.time = time;
			this.consumeItem = consumeItem;
			this.sound = sound;
			this.particle = particle;
			this.sun = sun;
		}
	}
}
