package minestrapp.jei.sawmill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class SawmillRecipeWrapper implements IRecipeWrapper
{	
	public final List<List<ItemStack>> input;
	
	public final List<ItemStack> output;
	public final List<ItemStack> extra;
	
	public SawmillRecipeWrapper(List<ItemStack> input, List<ItemStack> output, List<ItemStack> extra)
	{
		this.input = Collections.singletonList(input);
		this.output =  output;
		this.extra = extra;
	}

	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInputLists(ItemStack.class, this.input);
		
		List<List<ItemStack>> outputs = new ArrayList<>();
		outputs.add(output);
		outputs.add(extra);
		ingredients.setOutputLists(ItemStack.class, outputs);
	}

}
