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
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class AlloyRecipes {
	private static final AlloyRecipes SMELTING = new AlloyRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> alloySmelting = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static AlloyRecipes instance() {
		return SMELTING;
	}
	
	private AlloyRecipes() {
		//Adds Double itemStacks
		this.addAlloyRecipe(new ItemStack(MItems.ingots, 3, 0), new ItemStack(MItems.ingots, 3, 1), new ItemStack(MItems.ingots, 1, 2), 1.4F);
		//Adds with at least one block
		//Always Remember. Never frogget. this.addAlloyRecipeForBlock(Blocks.TNT, Blocks.JUKEBOX, new ItemStack(Items.EGG, 10), 20.0F);
		//Adds everything else
		this.addAlloy(MItems.corn_on_the_cob, MItems.fat, new ItemStack(MItems.grilled_corn), 0.35F);
		this.addAlloy(MItems.dough, Items.BOWL, new ItemStack(MItems.bread_bowl), 0.35F);
		this.addAlloy(MItems.dough, Items.SUGAR, new ItemStack(MItems.sugar_cookie, 8), 0.35F);
		this.addAlloy(MItems.dough, new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage()), new ItemStack(Items.COOKIE, 8), 0.35F);
		this.addAlloyRecipeForBlock(MItems.pie_crust, Blocks.PUMPKIN, new ItemStack(Items.PUMPKIN_PIE), 0.35F);
		this.addAlloy(MItems.pie_crust, Items.APPLE, new ItemStack(MItems.apple_pie), 0.35F);
		this.addAlloy(MItems.pie_crust, new ItemStack(MItems.blueberry, 4), new ItemStack(MItems.blueberry_pie), 0.35F);
		this.addAlloy(MItems.pie_crust, new ItemStack(MItems.blackberry, 4), new ItemStack(MItems.blackberry_pie), 0.35F);
		this.addAlloy(MItems.pie_crust, new ItemStack(MItems.raspberry, 4), new ItemStack(MItems.raspberry_pie), 0.35F);
		this.addAlloy(MItems.pie_crust, new ItemStack(MItems.strawberry, 4), new ItemStack(MItems.strawberry_pie), 0.35F);
		this.addAlloy(MItems.pie_crust, new ItemStack(MItems.voidberry, 4), new ItemStack(MItems.voidberry_pie), 0.35F);
		this.addAlloy(Items.EGG, Items.BOWL, new ItemStack(MItems.fried_egg), 0.35F);
		this.addAlloy(Items.EGG, MItems.bread_bowl, new ItemStack(MItems.bread_fried_egg), 0.35F);
		this.addAlloy(MItems.tomato_sauce, MItems.fat, new ItemStack(MItems.tomato_soup), 0.35F);
		this.addAlloy(MItems.bread_tomato_sauce, MItems.fat, new ItemStack(MItems.bread_tomato_soup), 0.35F);
		this.addAlloy(MItems.tomato_sauce, MItems.dry_spaghetti, new ItemStack(MItems.spaghetti), 0.35F);
		this.addAlloy(MItems.bread_tomato_sauce, MItems.dry_spaghetti, new ItemStack(MItems.bread_spaghetti), 0.35F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.LONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.STRONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.LONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(Items.ROTTEN_FLESH, PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.STRONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloy(MItems.grease, Items.POTATO, new ItemStack(MItems.fries), 0.35F);
		this.addAlloy(MItems.grease, Items.POISONOUS_POTATO, new ItemStack(MItems.fries), 0.35F);
		this.addAlloy(MItems.grease, new ItemStack(Items.FISH, 1, 0), new ItemStack(MItems.fried_fish), 0.35F);
		this.addAlloy(MItems.grease, new ItemStack(Items.FISH, 1, 1), new ItemStack(MItems.fried_salmon), 0.35F);
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
