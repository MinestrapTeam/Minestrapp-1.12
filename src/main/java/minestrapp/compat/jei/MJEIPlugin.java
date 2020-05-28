package minestrapp.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import minestrapp.MBlocks;
import minestrapp.compat.jei.alloy.AlloyRecipeCategory;
import minestrapp.compat.jei.alloy.AlloyRecipeMaker;
import minestrapp.compat.jei.crusher.CrusherRecipeCategory;
import minestrapp.compat.jei.crusher.CrusherRecipeMaker;
import minestrapp.compat.jei.freezing.FreezingRecipeCategory;
import minestrapp.compat.jei.freezing.FreezingRecipeMaker;
import minestrapp.compat.jei.pressurizer.PressurizerRecipeCategory;
import minestrapp.compat.jei.sawmill.SawmillRecipeCategory;
import minestrapp.compat.jei.sawmill.SawmillRecipeMaker;
import minestrapp.compat.jei.tanning.TannerRecipeCategory;
import minestrapp.compat.jei.tanning.TannerRecipeMaker;
import minestrapp.compat.jei.deepFreezing.DeepFreezingRecipeCategory;
import minestrapp.compat.jei.deepFreezing.DeepFreezingRecipeMaker;
import minestrapp.compat.jei.pressurizer.PressurizerRecipeMaker;
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
		registry.addRecipeCategories(new FreezingRecipeCategory(guiHelper));
		registry.addRecipeCategories(new DeepFreezingRecipeCategory(guiHelper));
		registry.addRecipeCategories(new PressurizerRecipeCategory(guiHelper));
		registry.addRecipeCategories(new SawmillRecipeCategory(guiHelper));
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

		registry.addRecipes(FreezingRecipeMaker.getFreezingRecipes(jeiHelpers), "freezing");
		registry.addRecipeCatalyst(new ItemStack(MBlocks.hacky_jei_fix_light_freezing), "freezing");

		registry.addRecipes(DeepFreezingRecipeMaker.getDeepFreezingRecipes(jeiHelpers), "deep_freezing");
		registry.addRecipeCatalyst(new ItemStack(MBlocks.hacky_jei_fix_deep_freezing), "deep_freezing");
		
		registry.addRecipes(PressurizerRecipeMaker.getPressurizerRecipes(jeiHelpers), "pressurizer");
		registry.addRecipeCatalyst(new ItemStack(MBlocks.pressurizer), "pressurizer");
		
		registry.addRecipes(SawmillRecipeMaker.getSawmillRecipes(jeiHelpers), "sawmill");
		registry.addRecipeCatalyst(new ItemStack(MBlocks.sawmill), "sawmill");
	}
}
