package minestrapp.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import minestrapp.MBlocks;
import minestrapp.MItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.oredict.OreDictionary;

public class CrusherRecipes {
	private static final CrusherRecipes CRUSHING = new CrusherRecipes();
	private final Map<ItemStack, ItemStack> crushing = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, ItemStack> extra = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Integer> chance = Maps.<ItemStack, Integer>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static CrusherRecipes instance() {
		return CRUSHING;
	}
	
	private CrusherRecipes() {
		//Input, Result, Extra, Chance, Exp
		this.addCrusherRecipe(new ItemStack(MItems.grease), new ItemStack(Items.FISH, 1, 1), new ItemStack(MItems.fried_salmon), 50, 0.35F);
		this.addCrusherRecipe(new ItemStack(Items.IRON_INGOT, 2), new ItemStack(Items.IRON_NUGGET, 9), new ItemStack(Items.PUMPKIN_PIE), 50, 0.35F);

	}
	public void addCrusherRecipe(ItemStack input1, ItemStack result, ItemStack extra, Integer chance, float experience) {
		this.crushing.put(input1, result);
		this.extra.put(input1, extra);
		this.chance.put(input1, chance);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getCrusherResult(ItemStack input1) {
		for(Entry<ItemStack, ItemStack> ent : this.crushing.entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)ent.getKey())) {
				return (ItemStack)ent.getValue();
			}
		}	
	return ItemStack.EMPTY;
	}

	public ItemStack getSlotOne(ItemStack input1) {
		for(Entry<ItemStack, ItemStack> ent : this.crushing.entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)ent.getKey())) {
				return (ItemStack)ent.getKey();
			}
		}	
	return ItemStack.EMPTY;
	}
	
	
	public ItemStack getExtra(ItemStack input1) {
		for(Entry<ItemStack, ItemStack> ent : this.extra.entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)ent.getKey())) {
				return (ItemStack)ent.getValue();
			}
		}	
	return ItemStack.EMPTY;
	}

	public Integer getChance(ItemStack input1) {
		for(Entry<ItemStack, Integer> ent : this.chance.entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)ent.getKey())) {
				return (Integer)ent.getValue();
			}
		}	
	return 0;
	}
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 630 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public float getCrusherExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
