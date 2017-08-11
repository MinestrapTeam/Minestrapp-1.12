package minestrapp.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import minestrapp.MItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlloyRecipes {
	private static final AlloyRecipes SMELTING = new AlloyRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> alloySmelting = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static AlloyRecipes instance() {
		return SMELTING;
	}
	
	private AlloyRecipes() {
		//Adds Double itemStacks
		this.addAlloyRecipe(new ItemStack(MItems.ingots, 1, 0), new ItemStack(MItems.ingots, 1, 1), new ItemStack(MItems.ingots, 1, 2), 10.0F);		
		//Adds with at least one block
		this.addAlloyRecipeForBlock(Blocks.TNT, Blocks.JUKEBOX, new ItemStack(Items.EGG, 10), 20.0F);
		//Adds everything else
		this.addAlloy(Items.EGG, Items.BOWL, new ItemStack(MItems.fried_egg), 10.0F);
	}
	
	public void addAlloyRecipeForBlock(Block input1, Block input2, ItemStack result, float experience) {
		this.addAlloy(Item.getItemFromBlock(input1), Item.getItemFromBlock(input2), result, experience);
		this.addAlloy(Item.getItemFromBlock(input2), Item.getItemFromBlock(input1), result, experience);
	}
	
	public void addAlloyRecipeForBlock(Block input1, Item input2, ItemStack result, float experience) {
		this.addAlloy(Item.getItemFromBlock(input1), input2, result, experience);
		this.addAlloy(input2, Item.getItemFromBlock(input1), result, experience);
	}
	
	public void addAlloyRecipeForBlock(Item input1, Block input2, ItemStack result, float experience) {
		this.addAlloy(input1, Item.getItemFromBlock(input2), result, experience);
		this.addAlloy(Item.getItemFromBlock(input2), input1, result, experience);
	}
	
	public void addAlloy(Item input1, Item input2, ItemStack result, float experience) {
		this.addAlloyRecipe(new ItemStack(input1, 1, 630), new ItemStack(input2, 1, 630), result, experience);
		this.addAlloyRecipe(new ItemStack(input2, 1, 630), new ItemStack(input1, 1, 630), result, experience);
	}
	
	public void addAlloy(Item input1, ItemStack input2, ItemStack result, float experience) {
		this.addAlloyRecipe(new ItemStack(input1, 1, 630), input2, result, experience);
		this.addAlloyRecipe(input2, new ItemStack(input1, 1, 630), result, experience);
	}
		
	public void addAlloy(ItemStack input1, Item input2, ItemStack result, float experience) {
		this.addAlloyRecipe(input1, new ItemStack(input2, 1, 630), result, experience);
		this.addAlloyRecipe(new ItemStack(input2, 1, 630), input1, result, experience);
	}

	public void addAlloyRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		this.alloySmelting.put(input1, input2, result);
		this.alloySmelting.put(input2, input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getAlloyResult(ItemStack input1, ItemStack input2) {
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloySmelting.columnMap().entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 630 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getAlloySmelting() {
		return this.alloySmelting;
	}
	
	public float getAlloyExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
