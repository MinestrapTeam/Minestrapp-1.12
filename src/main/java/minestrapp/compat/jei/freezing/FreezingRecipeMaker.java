package minestrapp.compat.jei.freezing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import minestrapp.crafting.FreezingRecipes;
import net.minecraft.item.ItemStack;

public class FreezingRecipeMaker {
	
	private FreezingRecipeMaker() {
		
	}
	
	public static List<FreezingRecipeWrapper> getFreezingRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		
		List<FreezingRecipeWrapper> recipes = new ArrayList<>();
		Map<ItemStack, ItemStack> recipe = FreezingRecipes.instance().getLightFreezingList(true);
		
		for(Map.Entry<ItemStack, ItemStack> map : recipe.entrySet()) {
			ItemStack input = map.getKey();
			ItemStack output = map.getValue();
			
			recipes.add(new FreezingRecipeWrapper(input, output));
		}
		
		return recipes;
	}
}
