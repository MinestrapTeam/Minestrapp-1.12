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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TannerRecipeCategory extends MinestrappRecipeCategory<TannerRecipeWrapper>{

	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/tanning_rack.png");
	
	public TannerRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.drawableBuilder(guiTexture, 26, 11, 73, 42).build(), "tile.tanning_rack.name");
	}
	
	private static final int input = 0;
	private static final int tool = 1;
	private static final int output = 2;

	@Override
	public String getUid() {
		return "tanning";
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, TannerRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(input, true, 0, 24);
		guiItemStacks.set(input, ingredients.getInputs(ItemStack.class).get(0));
		guiItemStacks.init(tool, true, 27, 0);
		guiItemStacks.set(input, ingredients.getInputs(ItemStack.class).get(1));
		guiItemStacks.init(output, false, 55, 24);
		
		guiItemStacks.set(ingredients);
	}

}
