package minestrapp.jei.tanning;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class TannerRecipeWrapper implements IRecipeWrapper{
	
	public List<List<ItemStack>> input;
	public ItemStack output;
	public ItemStack tool;
	public int time;
	
	public TannerRecipeWrapper(List<ItemStack> input, ItemStack output, ItemStack tool, int time) {
		this.input = Collections.singletonList(input);
		this.output = output;
		this.tool = tool;
		this.time = time;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, this.input);
		ingredients.setOutput(ItemStack.class, this.output);
	}
	
	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY){
		RenderItem tool = minecraft.getRenderItem();
	    tool.renderItemIntoGUI(this.tool, 15, 15);    
		minecraft.fontRenderer.drawString(Integer.toString(this.time)+"s", 55, 10, Color.DARK_GRAY.getRGB());
	}
}
