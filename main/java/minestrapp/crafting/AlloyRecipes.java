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

public class AlloyRecipes {
	private static final AlloyRecipes SMELTING = new AlloyRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> alloySmelting = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static AlloyRecipes instance() {
		return SMELTING;
	}
	
	private AlloyRecipes() {
		this.addAlloyRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MItems.gems, 1, 1), new ItemStack(Blocks.STONE, 1, 3), 0.1F);
		this.addAlloyRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Items.QUARTZ), new ItemStack(Blocks.STONE, 1, 3), 0.1F);
		this.addAlloyRecipe(new ItemStack(MBlocks.cobblestone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MItems.gems, 1, 1), new ItemStack(Blocks.STONE, 1, 3), 0.1F);
		this.addAlloyRecipe(new ItemStack(MBlocks.cobblestone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.QUARTZ), new ItemStack(Blocks.STONE, 1, 3), 0.1F);
		this.addAlloyRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.STONE, 1, 3), new ItemStack(Blocks.STONE, 1, 5), 0.1F);
		this.addAlloyRecipe(new ItemStack(MBlocks.cobblestone, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.STONE, 1, 3), new ItemStack(Blocks.STONE, 1, 5), 0.1F);
		this.addAlloyRecipe(new ItemStack(Blocks.STONE, 1, 3), new ItemStack(MItems.gems, 1, 1), new ItemStack(Blocks.STONE, 1, 1), 0.1F);
		this.addAlloyRecipe(new ItemStack(Blocks.STONE, 1, 3), new ItemStack(Items.QUARTZ), new ItemStack(Blocks.STONE, 1, 1), 0.1F);
		this.addAlloyRecipe(new ItemStack(Blocks.CLAY, 1), new ItemStack(MItems.gems, 1, 1), new ItemStack(MBlocks.decor_stone, 1, 6), 0.1F);
		this.addAlloyRecipe(new ItemStack(Blocks.CLAY, 1), new ItemStack(Items.QUARTZ), new ItemStack(MBlocks.decor_stone, 1, 6), 0.1F);
		this.addAlloyRecipe(new ItemStack(MItems.ingots, 3, 0), new ItemStack(MItems.ingots, 3, 1), new ItemStack(MItems.ingots, 1, 2), 1.4F);
		this.addAlloyRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL, 6, 0), new ItemStack(MItems.ingots, 1, 3), 1.4F);
		this.addAlloyRecipe(new ItemStack(MItems.ingots, 1, 4), new ItemStack(MItems.gems, 4, 6), new ItemStack(MItems.ingots, 1, 6), 2F);
		this.addAlloyRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(MItems.gems, 4, 5), new ItemStack(MItems.ingots, 1, 7), 2F);
		this.addAlloyRecipe(new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.irradium, 3), new ItemStack(MItems.gems, 1, 3), 3F);
		this.addAlloyRecipe(new ItemStack(Items.QUARTZ), new ItemStack(MItems.irradium, 3), new ItemStack(MItems.gems, 1, 3), 3F);
		this.addAlloyRecipe(new ItemStack(MBlocks.block_sunstone), new ItemStack(MItems.irradium), new ItemStack(MBlocks.block_irradiant_sunstone, 1), 1.2F);
		this.addAlloyRecipe(new ItemStack(MItems.gems, 1, 1), new ItemStack(Blocks.SOUL_SAND, 4), new ItemStack(MBlocks.soul_glass, 2, 0), 0.4F);
		this.addAlloyRecipe(new ItemStack(Items.QUARTZ), new ItemStack(Blocks.SOUL_SAND, 4), new ItemStack(MBlocks.soul_glass, 2, 0), 0.4F);
		this.addAlloyRecipe(new ItemStack(MItems.gems, 1, 5), new ItemStack(Blocks.SOUL_SAND, 4), new ItemStack(MBlocks.blazed_soul_glass, 2, 0), 0.4F);
		this.addAlloyRecipe(new ItemStack(MItems.corn_on_the_cob), new ItemStack(MItems.fat), new ItemStack(MItems.grilled_corn), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(Items.BOWL), new ItemStack(MItems.bread_bowl), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(Items.SUGAR), new ItemStack(MItems.sugar_cookie, 8), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage()), new ItemStack(Items.COOKIE, 8), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(MItems.pepper_seeds), new ItemStack(MItems.bun, 2), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(Blocks.IRON_BARS), new ItemStack(MItems.dry_spaghetti), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.pie_crust), new ItemStack(Blocks.PUMPKIN), new ItemStack(Items.PUMPKIN_PIE), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.pie_crust), new ItemStack(Items.APPLE), new ItemStack(MItems.apple_pie), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.pie_crust), new ItemStack(MItems.blueberry, 4), new ItemStack(MItems.blueberry_pie), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.pie_crust), new ItemStack(MItems.blackberry, 4), new ItemStack(MItems.blackberry_pie), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.pie_crust), new ItemStack(MItems.raspberry, 4), new ItemStack(MItems.raspberry_pie), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.pie_crust), new ItemStack(MItems.strawberry, 4), new ItemStack(MItems.strawberry_pie), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.pie_crust), new ItemStack(MItems.voidberry, 4), new ItemStack(MItems.voidberry_pie), 0.35F);
		this.addAlloyRecipe(new ItemStack(Items.EGG), new ItemStack(Items.BOWL), new ItemStack(MItems.fried_egg), 0.35F);
		this.addAlloyRecipe(new ItemStack(Items.EGG), new ItemStack(MItems.bread_bowl), new ItemStack(MItems.bread_fried_egg), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.tomato_sauce), new ItemStack(MItems.fat), new ItemStack(MItems.tomato_soup), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.bread_tomato_sauce), new ItemStack(MItems.fat), new ItemStack(MItems.bread_tomato_soup), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.tomato_sauce), new ItemStack(MItems.dry_spaghetti), new ItemStack(MItems.spaghetti), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.bread_tomato_sauce), new ItemStack(MItems.dry_spaghetti), new ItemStack(MItems.bread_spaghetti), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.corn), new ItemStack(Items.BOWL), new ItemStack(MItems.popcorn), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.corn), new ItemStack(MItems.bread_bowl), new ItemStack(MItems.bread_popcorn), 0.35F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.LONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.STRONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.LONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(Items.ROTTEN_FLESH), PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.STRONG_REGENERATION) , new ItemStack(MItems.flesh), 0.85F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.POTATO), new ItemStack(MItems.fries), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.POISONOUS_POTATO), new ItemStack(MItems.fries), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.FISH, 1, 0), new ItemStack(MItems.fried_fish), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.FISH, 1, 1), new ItemStack(MItems.fried_salmon), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.PORKCHOP), new ItemStack(MItems.bacon), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.CHICKEN), new ItemStack(MItems.salted_chicken), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.RABBIT), new ItemStack(MItems.salted_rabbit), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.MUTTON), new ItemStack(MItems.salted_mutton), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.PORKCHOP), new ItemStack(MItems.salted_porkchop), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.BEEF), new ItemStack(MItems.salted_steak), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.mite_honey, 12), new ItemStack(Items.SPECKLED_MELON, 4, 1), new ItemStack(MItems.candy_red), 0.8F);
		this.addAlloyRecipe(new ItemStack(MItems.mite_honey, 12), new ItemStack(MItems.natural_ingredients, 4, 1), new ItemStack(MItems.candy_blue), 0.8F);
		this.addAlloyRecipe(new ItemStack(MItems.mite_honey, 12), new ItemStack(MBlocks.hanging_glow_moss, 4), new ItemStack(MItems.candy_yellow), 0.8F);
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
	
	public ItemStack getSlotOne(ItemStack input1, ItemStack input2) {
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloySmelting.columnMap().entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)entry.getKey();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public ItemStack getSlotTwo(ItemStack input1, ItemStack input2) {
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloySmelting.columnMap().entrySet()) {
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getKey();
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
