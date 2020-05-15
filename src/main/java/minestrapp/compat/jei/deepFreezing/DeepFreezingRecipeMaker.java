package minestrapp.compat.jei.deepFreezing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import minestrapp.crafting.FreezingRecipes;
import net.minecraft.item.ItemStack;

public class DeepFreezingRecipeMaker {
	
	private DeepFreezingRecipeMaker() {
		
	}
	
	public static List<DeepFreezingRecipeWrapper> getDeepFreezingRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		
		List<DeepFreezingRecipeWrapper> recipes = new ArrayList<>();
		Map<ItemStack, ItemStack> recipe = FreezingRecipes.instance().getDeepFreezingList(true);
		
		for(Map.Entry<ItemStack, ItemStack> map : recipe.entrySet()) {
			ItemStack input = map.getKey();
			ItemStack output = map.getValue();
			
			recipes.add(new DeepFreezingRecipeWrapper(input, output));
		}
		
		return recipes;
	}
}
