package minestrapp.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import minestrapp.MBlocks;
import minestrapp.MFluids;
import minestrapp.MItems;
import minestrapp.fluid.FluidBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.oredict.OreDictionary;

public class AlloyRecipes
{
	private static final AlloyRecipes SMELTING = new AlloyRecipes();
	public final Table<ItemStack, ItemStack, ItemStack> alloySmelting = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	private final Map<ItemStack, Boolean> slot1ConsumeList = Maps.<ItemStack, Boolean>newHashMap();
	private final Map<ItemStack, Boolean> slot2ConsumeList = Maps.<ItemStack, Boolean>newHashMap();

	public static AlloyRecipes instance()
	{
		return SMELTING;
	}
	
	private AlloyRecipes()
	{
		this.addAlloyRecipe(new ItemStack(MItems.ingots, 3, 0), new ItemStack(MItems.ingots, 3, 1), new ItemStack(MItems.ingots, 1, 2), 1.4F);
		this.addAlloyRecipe(new ItemStack(MBlocks.block_copper, 3), new ItemStack(MBlocks.block_tin, 3), new ItemStack(MBlocks.block_bronze), 12.6F);
		this.addAlloyRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL, 6, 0), new ItemStack(MItems.ingots, 1, 3), 1.4F);
		this.addAlloyRecipe(new ItemStack(Blocks.IRON_BLOCK), new ItemStack(Blocks.COAL_BLOCK, 6), new ItemStack(MBlocks.block_steel), 12.6F);
		this.addAlloyRecipe(new ItemStack(MItems.ingots, 1, 4), new ItemStack(MItems.gems, 4, 6), new ItemStack(MItems.ingots, 1, 6), 2F);
		this.addAlloyRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(MItems.gems, 4, 5), new ItemStack(MItems.ingots, 1, 7), 2F);
		this.addAlloyRecipe(new ItemStack(MBlocks.block_sunstone), new ItemStack(MItems.irradium), new ItemStack(MBlocks.block_irradiant_sunstone, 1), 1.2F);
		this.addAlloyRecipe(new ItemStack(MItems.gems, 1, 1), new ItemStack(Blocks.SOUL_SAND, 4), new ItemStack(MBlocks.soul_glass, 2, 0), 0.4F);
		this.addAlloyRecipe(new ItemStack(Items.QUARTZ), new ItemStack(Blocks.SOUL_SAND, 4), new ItemStack(MBlocks.soul_glass, 2, 0), 0.4F);
		this.addAlloyRecipe(new ItemStack(MItems.gems, 1, 5), new ItemStack(Blocks.SOUL_SAND, 4), new ItemStack(MBlocks.blazed_soul_glass, 2, 0), 0.4F);
		this.addAlloyRecipe(new ItemStack(MItems.chunks, 1, 14), new ItemStack(Items.BUCKET), FluidUtil.getFilledBucket(new FluidStack(MFluids.crystalfloe, FluidBase.BUCKET_VOLUME)), 4F);
		this.addAlloyRecipe(new ItemStack(MBlocks.ore_shimmering), new ItemStack(Items.BUCKET), FluidUtil.getFilledBucket(new FluidStack(MFluids.crystalfloe, FluidBase.BUCKET_VOLUME)), 4F);
		this.addAlloyRecipe(new ItemStack(MItems.corn_on_the_cob), new ItemStack(MItems.fat), new ItemStack(MItems.grilled_corn), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(Items.BOWL), new ItemStack(MItems.bread_bowl), 0.35F, true, false);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(Items.SUGAR), new ItemStack(MItems.sugar_cookie, 8), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage()), new ItemStack(Items.COOKIE, 8), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(MItems.pepper_seeds), new ItemStack(MItems.bun, 2), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.dough), new ItemStack(MBlocks.steel_mesh), new ItemStack(MItems.dry_spaghetti), 0.35F, true, false);
		this.addAlloyRecipe(new ItemStack(MItems.corn_meal), new ItemStack(Blocks.IRON_BARS), new ItemStack(MItems.tortilla, 2), 0.35F, true, false);
		this.addAlloyRecipe(new ItemStack(MItems.tortilla, 2), new ItemStack(MItems.cheese, 3), new ItemStack(MBlocks.quesadilla), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.corn_meal), new ItemStack(MItems.grease), new ItemStack(MItems.tortilla_chips, 4), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.tortilla_chips), new ItemStack(MItems.cheese), new ItemStack(MItems.nachos), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.bun, 2), new ItemStack(MItems.cheese, 2), new ItemStack(MItems.grilled_cheese), 0.35F);
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
		this.addAlloyRecipe(new ItemStack(Items.MUSHROOM_STEW), new ItemStack(MItems.dry_spaghetti), new ItemStack(MItems.stroganoff), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.bread_mushroom_stew), new ItemStack(MItems.dry_spaghetti), new ItemStack(MItems.bread_stroganoff), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.glowshroom_stew), new ItemStack(MItems.dry_spaghetti), new ItemStack(MItems.glowganoff), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.bread_glowshroom_stew), new ItemStack(MItems.dry_spaghetti), new ItemStack(MItems.bread_glowganoff), 0.35F);
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
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.POTATO), new ItemStack(MItems.fries, 2), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.POISONOUS_POTATO), new ItemStack(MItems.fries), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.FISH, 1, 0), new ItemStack(MItems.fried_fish), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.FISH, 1, 1), new ItemStack(MItems.fried_salmon), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.grease), new ItemStack(Items.PORKCHOP), new ItemStack(MItems.bacon), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(MItems.peanuts, 2), new ItemStack(MItems.roasted_peanuts, 2), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.CHICKEN), new ItemStack(MItems.salted_chicken), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.RABBIT), new ItemStack(MItems.salted_rabbit), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.MUTTON), new ItemStack(MItems.salted_mutton), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.PORKCHOP), new ItemStack(MItems.salted_porkchop), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.salt), new ItemStack(Items.BEEF), new ItemStack(MItems.salted_steak), 0.35F);
		this.addAlloyRecipe(new ItemStack(MItems.mite_honey, 12), new ItemStack(Items.SPECKLED_MELON, 4), new ItemStack(MItems.candy_red), 0.8F);
		this.addAlloyRecipe(new ItemStack(MItems.mite_honey, 12), new ItemStack(MItems.natural_ingredients, 4, 1), new ItemStack(MItems.candy_blue), 0.8F);
		this.addAlloyRecipe(new ItemStack(MItems.mite_honey, 12), new ItemStack(MBlocks.hanging_glow_moss, 4), new ItemStack(MItems.candy_yellow), 0.8F);
		this.addAlloyRecipe(new ItemStack(MItems.mite_honey, 4), new ItemStack(Blocks.PUMPKIN), new ItemStack(Blocks.LIT_PUMPKIN), 0.35F);
		this.addAlloyRecipe(new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.BLAZE_POWDER), new ItemStack(Items.ENDER_EYE), 0.5F);
	}
	
	public void addAlloyRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
	{
		this.alloySmelting.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
		this.slot1ConsumeList.put(result, true);
		this.slot2ConsumeList.put(result, true);
	}
	
	public void addAlloyRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience, boolean consume1, boolean consume2)
	{
		this.alloySmelting.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
		this.slot1ConsumeList.put(result, consume1);
		this.slot2ConsumeList.put(result, consume2);
	}
	
	public ItemStack getAlloyResult(ItemStack input1, ItemStack input2)
	{
        for(Table.Cell<ItemStack, ItemStack, ItemStack> table : this.alloySmelting.cellSet())
        {
            if((this.compareItemStacks(input1, table.getRowKey()) && this.compareItemStacks(input2, table.getColumnKey())) || (this.compareItemStacks(input2, table.getRowKey()) && this.compareItemStacks(input1, table.getColumnKey())))
            {
                return table.getValue();
            }
        }
        return ItemStack.EMPTY;
	}
	
	public ItemStack getSlotOne(ItemStack input1, ItemStack input2)
	{
		for(Table.Cell<ItemStack, ItemStack, ItemStack> table : this.alloySmelting.cellSet())
		{
			 if((this.compareItemStacks(input1, table.getRowKey()) && this.compareItemStacks(input2, table.getColumnKey())) || (this.compareItemStacks(input2, table.getRowKey()) && this.compareItemStacks(input1, table.getColumnKey())))
			 {
	            if(this.compareItemStacks(input1, table.getRowKey())) {
	            	return table.getRowKey();
	            }
	            if(this.compareItemStacks(input1, table.getColumnKey())) {
	            	return table.getColumnKey();
	            }
	        }
	     }
	     return ItemStack.EMPTY;
	}
	
	public ItemStack getSlotTwo(ItemStack input1, ItemStack input2)
	{
		for(Table.Cell<ItemStack, ItemStack, ItemStack> table : this.alloySmelting.cellSet())
		{
			 if((this.compareItemStacks(input1, table.getRowKey()) && this.compareItemStacks(input2, table.getColumnKey())) || (this.compareItemStacks(input2, table.getRowKey()) && this.compareItemStacks(input1, table.getColumnKey())))
			 {
	            if(this.compareItemStacks(input2, table.getRowKey()))
	            {
	            	return table.getRowKey();
	            }
	            if(this.compareItemStacks(input2, table.getColumnKey()))
	            {
	            	return table.getColumnKey();
	            }
	        }
	     }
	     return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 630 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getAlloySmelting()
	{
		return this.alloySmelting;
	}
	
	public float getAlloyExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
	public boolean isSlot1Consumable(ItemStack stack)
	{
		for (Entry<ItemStack, Boolean> entry : this.slot1ConsumeList.entrySet())
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
			{
				return ((Boolean)entry.getValue()).booleanValue();
			}
		}
		return true;
	}
	
	public boolean isSlot2Consumable(ItemStack stack)
	{
		for (Entry<ItemStack, Boolean> entry : this.slot2ConsumeList.entrySet())
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
			{
				return ((Boolean)entry.getValue()).booleanValue();
			}
		}
		return true;
	}
}
