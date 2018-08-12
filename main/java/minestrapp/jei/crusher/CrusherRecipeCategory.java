package minestrapp.jei.crusher;

import javax.annotation.Nullable;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import minestrapp.Minestrapp5;
import minestrapp.jei.MinestrappRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class CrusherRecipeCategory extends MinestrappRecipeCategory<CrusherRecipeWrapper>{

	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/crusher.png");
	
	public CrusherRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.drawableBuilder(guiTexture, 48, 16, 100, 60).build(), "tile.crusher.name");
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
		guiItemStacks.init(input, true, 7, 0);
		guiItemStacks.init(output, false, 67, 8);
		guiItemStacks.init(outputExtra, false, 67, 32);
		guiItemStacks.set(outputExtra, recipeWrapper.extra);
		
		guiItemStacks.set(ingredients);
	}

	
}
