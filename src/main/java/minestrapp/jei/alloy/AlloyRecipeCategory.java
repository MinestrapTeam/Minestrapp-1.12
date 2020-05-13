package minestrapp.jei.alloy;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import minestrapp.Minestrapp5;
import minestrapp.jei.MinestrappRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AlloyRecipeCategory extends MinestrappRecipeCategory{

	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/alloy_furnace.png");
	
	public AlloyRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.drawableBuilder(guiTexture, 36, 16, 101, 54).build(), "tile.alloy.name");
	}
	
	private static final int input1 = 0;
	private static final int input2 = 1;
	private static final int output = 2;

	@Override
	public String getUid() {
		return "alloy";
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(input1, true, 0, 0);
		guiItemStacks.init(input2, true, 18, 0);
		guiItemStacks.init(output, false, 79, 18);
		guiItemStacks.set(input1, ingredients.getInputs(ItemStack.class).get(0));
		guiItemStacks.set(input2, ingredients.getInputs(ItemStack.class).get(1));
		guiItemStacks.set(output, ingredients.getOutputs(ItemStack.class).get(0));
		guiItemStacks.set(ingredients);
	}

}
