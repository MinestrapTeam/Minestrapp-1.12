package minestrapp.compat.jei.pressurizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import minestrapp.crafting.PressurizerRecipes;
import net.minecraft.item.ItemStack;

public class PressurizerRecipeMaker
{
	private PressurizerRecipeMaker() {}
	
	public static List<PressurizerRecipeWrapper> getPressurizerRecipes(IJeiHelpers helpers)
	{
		IStackHelper stackHelper = helpers.getStackHelper();
		
		List<PressurizerRecipeWrapper> recipes = new ArrayList<>();
		Map<List<ItemStack>, ItemStack> recipe = PressurizerRecipes.instance().pressurizing;
		
		for(Entry<List<ItemStack>, ItemStack> entry : recipe.entrySet())
		{
			List<ItemStack> input1 = stackHelper.getSubtypes(((List<ItemStack>)entry.getKey()).get(0));
			List<ItemStack> input2 = stackHelper.getSubtypes(((List<ItemStack>)entry.getKey()).get(1));
			List<ItemStack> input3 = stackHelper.getSubtypes(((List<ItemStack>)entry.getKey()).get(2));
			List<ItemStack> input4 = stackHelper.getSubtypes(((List<ItemStack>)entry.getKey()).get(3));
			ItemStack output = (ItemStack)entry.getValue();
			
			recipes.add(new PressurizerRecipeWrapper(input1, input2, input3, input4, output));
		}
		
		return recipes;
	}
}
