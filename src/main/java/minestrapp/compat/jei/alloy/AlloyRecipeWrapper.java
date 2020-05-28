package minestrapp.compat.jei.alloy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class AlloyRecipeWrapper implements IRecipeWrapper{
		public final List<ItemStack> input1;
		public final List<ItemStack> input2;
		public final ItemStack output;
		
		public AlloyRecipeWrapper(List<ItemStack> input1, List<ItemStack>input2, ItemStack output){
			this.input1 = input1;
			this.input2 = input2;
			this.output = output;
		}

		@Override
		public void getIngredients(IIngredients ingredients) {
			List<List<ItemStack>> inputs = new ArrayList<>();
			inputs.add(input1);
			inputs.add(input2);
			ingredients.setInputLists(ItemStack.class, inputs);
			ingredients.setOutput(ItemStack.class, output);
		}
}
