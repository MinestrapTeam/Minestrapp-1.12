package minestrapp.compat.jei.sawmill;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import minestrapp.MBlocks;
import minestrapp.Minestrapp5;
import minestrapp.crafting.SawmillRecipes;
import minestrapp.compat.jei.MinestrappRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class SawmillRecipeCategory extends MinestrappRecipeCategory<SawmillRecipeWrapper>
{
	private final static ResourceLocation guiTexture = new ResourceLocation(Minestrapp5.MODID, "textures/gui/sawmill_jei.png");
	
	public SawmillRecipeCategory(IGuiHelper guiHelper)
	{
		super(guiHelper.drawableBuilder(guiTexture, 0, 0, 90, 46).build(), "jei.sawmill.name");
	}

	private static final int input = 0;
	private static final int sawmill = 1;
	private static final int output = 2;
	private static final int outputExtra = 3;
	
	@Override
	public String getUid()
	{
		return "sawmill";
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SawmillRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(input, true, 4, 0);
		guiItemStacks.init(sawmill, true, 4, 24);
		guiItemStacks.init(output, false, 68, 4);
		guiItemStacks.init(outputExtra, false, 68, 28);
		
		for(EnumFacing facing : EnumFacing.values())
		{
			if(SawmillRecipes.instance().isStone(facing, ingredients.getInputs(ItemStack.class).get(0).get(0)))
				guiItemStacks.set(sawmill, new ItemStack(MBlocks.hacky_jei_fix_sawmill_stone));
			else
				guiItemStacks.set(sawmill, new ItemStack(MBlocks.hacky_jei_fix_sawmill_all));
		}
		
		guiItemStacks.set(output, ingredients.getOutputs(ItemStack.class).get(0));
		guiItemStacks.set(outputExtra, ingredients.getOutputs(ItemStack.class).get(1));
		
		guiItemStacks.set(ingredients);
	}

	
}
