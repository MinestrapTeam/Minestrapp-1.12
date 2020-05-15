package minestrapp.compat.jei.alloy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Table;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import minestrapp.crafting.AlloyRecipes;
import net.minecraft.item.ItemStack;

public class AlloyRecipeMaker {
	
	private AlloyRecipeMaker() {
		
	}
	
	public static List<AlloyRecipeWrapper> getAlloyRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		
		List<AlloyRecipeWrapper> recipes = new ArrayList<>();
		Table<ItemStack, ItemStack, ItemStack> recipe = AlloyRecipes.instance().alloySmelting;
		
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> map : recipe.rowMap().entrySet()) {
			ItemStack input1 = map.getKey();
			for(Entry<ItemStack, ItemStack> map2 : map.getValue().entrySet()) {
				ItemStack input2 = map2.getKey();
				ItemStack output = map2.getValue();
				List<ItemStack> inputs1 = stackHelper.getSubtypes(input1);
				List<ItemStack> inputs2 = stackHelper.getSubtypes(input2);
				recipes.add(new AlloyRecipeWrapper(inputs1, inputs2, output));
			}
		}
		
		return recipes;
	}
}
