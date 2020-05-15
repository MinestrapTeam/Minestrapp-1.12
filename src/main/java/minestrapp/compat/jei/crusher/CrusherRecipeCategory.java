package minestrapp.compat.jei.crusher;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import minestrapp.Minestrapp5;
import minestrapp.compat.jei.MinestrappRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrusherRecipeCategory extends MinestrappRecipeCategory<CrusherRecipeWrapper>{

	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/crusher.png");
	
	public CrusherRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.drawableBuilder(guiTexture, 55, 16, 82, 54).build(), "tile.crusher.name");
	}

	private static final int input = 0;
	private static final int output = 1;
	private static final int outputExtra = 2;
	
	@Override
	public String getUid() {
		return "crusher";
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(input, true, 0, 0);
		guiItemStacks.init(output, false, 60, 8);
		guiItemStacks.init(outputExtra, false, 60, 32);
		
		guiItemStacks.set(output, ingredients.getOutputs(ItemStack.class).get(0));
		guiItemStacks.set(outputExtra, ingredients.getOutputs(ItemStack.class).get(1));
		
		guiItemStacks.set(ingredients);
	}

	
}
