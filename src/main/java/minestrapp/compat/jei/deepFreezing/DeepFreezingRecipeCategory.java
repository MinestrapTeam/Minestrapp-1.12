package minestrapp.compat.jei.deepFreezing;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import minestrapp.Minestrapp;
import minestrapp.compat.jei.MinestrappRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DeepFreezingRecipeCategory extends MinestrappRecipeCategory{

	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp.MODID, "textures/gui/freezing.png");
	
	public DeepFreezingRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.drawableBuilder(guiTexture, 0, 0, 82, 26).build(), "jei.deep_freezing.name");
	}
	
	private static final int input1 = 0;
	private static final int output = 1;

	@Override
	public String getUid() {
		return "deep_freezing";
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(input1, true, 0, 4);
		guiItemStacks.init(output, false, 60, 4);
		guiItemStacks.set(input1, ingredients.getInputs(ItemStack.class).get(0));
		guiItemStacks.set(output, ingredients.getOutputs(ItemStack.class).get(0));
		guiItemStacks.set(ingredients);
	}

}
