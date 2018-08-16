package minestrapp.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import minestrapp.MBlocks;
import minestrapp.jei.alloy.AlloyRecipeCategory;
import minestrapp.jei.alloy.AlloyRecipeMaker;
import minestrapp.jei.crusher.CrusherRecipeCategory;
import minestrapp.jei.crusher.CrusherRecipeMaker;
import minestrapp.jei.tanning.TannerRecipeCategory;
import minestrapp.jei.tanning.TannerRecipeMaker;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class MJEIPlugin implements IModPlugin {
	public static IJeiHelpers helper;
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		
		registry.addRecipeCategories(new CrusherRecipeCategory(guiHelper));
		registry.addRecipeCategories(new AlloyRecipeCategory(guiHelper));
		registry.addRecipeCategories(new TannerRecipeCategory(guiHelper));
	}
	
	@Override
	public void register(IModRegistry registry) {
		IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		
		registry.addRecipes(CrusherRecipeMaker.getCrusherRecipes(jeiHelpers), "crusher");
		registry.addRecipeCatalyst(new ItemStack(MBlocks.crusher), "crusher");
		
		registry.addRecipes(AlloyRecipeMaker.getAlloyRecipes(jeiHelpers), "alloy");
		registry.addRecipeCatalyst(new ItemStack(MBlocks.alloy), "alloy");
		
		registry.addRecipes(TannerRecipeMaker.getTannerRecipes(jeiHelpers), "tanning");
		registry.addRecipeCatalyst(new ItemStack(MBlocks.tanning_rack), "tanning");

	}
}
