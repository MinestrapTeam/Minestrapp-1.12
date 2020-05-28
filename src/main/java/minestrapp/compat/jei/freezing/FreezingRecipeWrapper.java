package minestrapp.compat.jei.freezing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class FreezingRecipeWrapper implements IRecipeWrapper
{
		public final ItemStack input;
		public final ItemStack output;
		
		public FreezingRecipeWrapper(ItemStack input, ItemStack output)
		{
			this.input = input;
			this.output = output;
		}

		@Override
		public void getIngredients(IIngredients ingredients)
		{
			ingredients.setInput(ItemStack.class, this.input);
			ingredients.setOutput(ItemStack.class, output);
		}
}
