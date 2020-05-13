package minestrapp.jei.sawmill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Table;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import minestrapp.crafting.CrusherRecipes;
import minestrapp.crafting.SawmillRecipes;
import minestrapp.jei.alloy.AlloyRecipeWrapper;
import minestrapp.jei.crusher.CrusherRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class SawmillRecipeMaker
{
	private SawmillRecipeMaker() {}
	
	public static List<SawmillRecipeWrapper> getSawmillRecipes(IJeiHelpers helpers)
	{
		IStackHelper stackHelper = helpers.getStackHelper();
		
		List<SawmillRecipeWrapper> recipes = new ArrayList<>();
		
		Map<ItemStack, ItemStack> recipe = SawmillRecipes.instance().jeiMilling;
		Map<ItemStack, ItemStack> extra = SawmillRecipes.instance().jeiExtra;
		
		for(Map.Entry<ItemStack, ItemStack> entry : recipe.entrySet())
		{
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();
			
			ItemStack extraSlot = extra.get(entry.getKey());
			
			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			List<ItemStack> outputs = stackHelper.getSubtypes(output);
			List<ItemStack> extras = stackHelper.getSubtypes(extraSlot);
			recipes.add(new SawmillRecipeWrapper(inputs, outputs, extras));
		}
		
		
		return recipes;
	}
}
