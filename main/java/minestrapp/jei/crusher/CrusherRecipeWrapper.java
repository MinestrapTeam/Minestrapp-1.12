package minestrapp.jei.crusher;

import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class CrusherRecipeWrapper implements IRecipeWrapper{
	
	public final List<List<ItemStack>> input;
	public final ItemStack output;
	public final ItemStack extra;
	
	public CrusherRecipeWrapper(List<ItemStack> input, ItemStack output, ItemStack extra){
		this.input = Collections.singletonList(input);
		this.output = output;
		this.extra = extra;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		//ingredients.setInput(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}

}
