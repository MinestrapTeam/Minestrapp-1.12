package minestrapp.compat.jei;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import minestrapp.Minestrapp;

public abstract class MinestrappRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>{

	private final IDrawable background;
	private final String localizedName;
	
	public MinestrappRecipeCategory(IDrawable background, String unlocalizedName){
		this.background = background;
		this.localizedName = Translator.translateToLocal(unlocalizedName);
	}
	
	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public String getModName() {
		return Minestrapp.NAME;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

}
