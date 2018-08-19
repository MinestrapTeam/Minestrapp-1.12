package minestrapp.jei.crusher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import minestrapp.crafting.CrusherRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CrusherRecipeMaker {
	
	private CrusherRecipeMaker() {
		
	}
	
	public static List<CrusherRecipeWrapper> getCrusherRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		
		List<CrusherRecipeWrapper> recipes = new ArrayList<>();
		
		Map<ItemStack, ItemStack> recipe = CrusherRecipes.instance().crushing;
		Map<ItemStack, ItemStack> extra = CrusherRecipes.instance().extra;
		
		for(Map.Entry<ItemStack, ItemStack> entry : recipe.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();
			
			ItemStack extraSlot = extra.get(entry.getKey());
			
			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			System.out.println(stackHelper.getSubtypes(input));
			List<ItemStack> outputs = stackHelper.getSubtypes(output);
			List<ItemStack> extras = stackHelper.getSubtypes(extraSlot);
			recipes.add(new CrusherRecipeWrapper(inputs, outputs, extras));
		}
		
		
		return recipes;
	}
}
