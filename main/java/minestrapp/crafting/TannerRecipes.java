package minestrapp.crafting;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TannerRecipes {
	
	public static TannerRecipes instance = new TannerRecipes();
	public Map<Item, TannerRecipe> recipes = new HashMap<Item, TannerRecipe>();
	
	public TannerRecipes() {
		this.addRecipe(new ItemStack(Items.RABBIT_HIDE), new ItemStack(Items.LEATHER), 5);
	}
	
	
	public void addRecipe(ItemStack input, ItemStack output, int time) {
		this.recipes.put(input.getItem(), new TannerRecipe(input, output, time));
	}
	
	
	public class TannerRecipe {
		
		public ItemStack input;
		public ItemStack output;
		public int time; // in seconds
		
		public TannerRecipe(ItemStack input, ItemStack output, int time) {
			this.input = input;
			this.output = output;
			this.time = time;
		}
	}

}
