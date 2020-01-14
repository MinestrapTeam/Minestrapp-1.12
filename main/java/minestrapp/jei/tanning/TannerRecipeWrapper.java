package minestrapp.jei.tanning;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import minestrapp.Minestrapp5;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TannerRecipeWrapper implements IRecipeWrapper{
	public List<ItemStack> input;
	public ItemStack output;
	public List<ItemStack> tool;
	public int time;
	
	public boolean needSun;
	
	public TannerRecipeWrapper(List<ItemStack> input, ItemStack output, List<ItemStack> tool, int time, boolean sun) {
		this.input = input;
		this.output = output;
		this.tool = tool;
		this.time = time;
		this.needSun = sun;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> inputs = new ArrayList<>();
		inputs.add(this.input);
		inputs.add(this.tool);
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.output);
	}
	
	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY){
		if(this.needSun) {
			Gui.drawModalRectWithCustomSizedTexture(1, 1, 176, 0, 16, 16, 256, 256);
		}
		
		minecraft.fontRenderer.drawString(Integer.toString(this.time)+"s", 29 - Integer.toString(this.time).length(), 29, Color.ORANGE.getRGB());
		
	}
}
