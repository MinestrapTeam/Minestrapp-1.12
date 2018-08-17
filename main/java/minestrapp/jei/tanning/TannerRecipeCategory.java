package minestrapp.jei.tanning;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import minestrapp.Minestrapp5;
import minestrapp.jei.MinestrappRecipeCategory;
import minestrapp.jei.crusher.CrusherRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class TannerRecipeCategory extends MinestrappRecipeCategory<TannerRecipeWrapper>{

	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/crusher.png");
	
	public TannerRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.drawableBuilder(guiTexture, 48, 16, 100, 60).build(), "tile.tanning_rack.name");
	}
	
	private static final int input = 0;
	private static final int output = 1;
	
	@Override
	public String getUid() {
		return "tanning";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, TannerRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(input, true, 7, 0);
		guiItemStacks.init(output, false, 67, 8);
		
		guiItemStacks.set(ingredients);
	}

}
