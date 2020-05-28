package minestrapp.compat.jei.pressurizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class PressurizerRecipeWrapper implements IRecipeWrapper
{
		public final List<ItemStack> input1;
		public final List<ItemStack> input2;
		public final List<ItemStack> input3;
		public final List<ItemStack> input4;
		public final ItemStack output;
		
		public PressurizerRecipeWrapper(List<ItemStack> input1, List<ItemStack>input2, List<ItemStack>input3, List<ItemStack>input4, ItemStack output)
		{
			this.input1 = input1;
			this.input2 = input2;
			this.input3 = input3;
			this.input4 = input4;
			this.output = output;
		}

		@Override
		public void getIngredients(IIngredients ingredients)
		{
			List<List<ItemStack>> inputs = new ArrayList<>();
			inputs.add(input1);
			inputs.add(input2);
			inputs.add(input3);
			inputs.add(input4);
			ingredients.setInputLists(ItemStack.class, inputs);
			ingredients.setOutput(ItemStack.class, output);
		}
}
