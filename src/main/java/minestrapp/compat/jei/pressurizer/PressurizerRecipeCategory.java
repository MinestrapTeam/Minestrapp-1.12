package minestrapp.compat.jei.pressurizer;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import minestrapp.Minestrapp5;
import minestrapp.compat.jei.MinestrappRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PressurizerRecipeCategory extends MinestrappRecipeCategory{

	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/pressurizer.png");
	
	public PressurizerRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.drawableBuilder(guiTexture, 50, 14, 76, 86).build(), "tile.pressurizer.name");
	}
	
	private static final int input1 = 0;
	private static final int input2 = 1;
	private static final int input3 = 2;
	private static final int input4 = 3;
	private static final int output = 4;

	@Override
	public String getUid() {
		return "pressurizer";
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(input1, true, 2, 2);
		guiItemStacks.init(input2, true, 56, 2);
		guiItemStacks.init(input3, true, 2, 56);
		guiItemStacks.init(input4, true, 56, 56);
		guiItemStacks.init(output, false, 29, 29);
		guiItemStacks.set(input1, ingredients.getInputs(ItemStack.class).get(0));
		guiItemStacks.set(input2, ingredients.getInputs(ItemStack.class).get(1));
		guiItemStacks.set(input3, ingredients.getInputs(ItemStack.class).get(2));
		guiItemStacks.set(input4, ingredients.getInputs(ItemStack.class).get(3));
		guiItemStacks.set(output, ingredients.getOutputs(ItemStack.class).get(0));
		guiItemStacks.set(ingredients);
	}

}
