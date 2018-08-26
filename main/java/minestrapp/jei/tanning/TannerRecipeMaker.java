package minestrapp.jei.tanning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import minestrapp.crafting.TannerRecipes;
import minestrapp.crafting.TannerRecipes.TannerRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TannerRecipeMaker {
	
	private TannerRecipeMaker() {
		
	}
	
	public static List<TannerRecipeWrapper> getTannerRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		
		List<TannerRecipeWrapper> recipes = new ArrayList<>();
		
		for(Map.Entry<ItemStack, TannerRecipe> entry: TannerRecipes.instance.recipes.entrySet()) {
			TannerRecipe temp = entry.getValue();
			
			List<ItemStack> inputs = stackHelper.getSubtypes(temp.input);
			List<ItemStack> tools = stackHelper.getSubtypes(temp.tool);
			recipes.add(new TannerRecipeWrapper(inputs, temp.output, tools, temp.time, temp.sun));
		}
		
		return recipes;
	}

}
