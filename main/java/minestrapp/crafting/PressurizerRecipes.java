package minestrapp.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.utils.ItemUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PressurizerRecipes
{
	private static final PressurizerRecipes PRESSURIZING = new PressurizerRecipes();
	public final Map<List<ItemStack>, ItemStack> pressurizing = Maps.<List<ItemStack>, ItemStack>newHashMap();
	private final Map<ItemStack, Float> expValues = Maps.<ItemStack, Float>newHashMap();
	
	public static PressurizerRecipes instance()
	{
		return PRESSURIZING;
	}
	
	private PressurizerRecipes()
	{
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.stone, 1, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(MBlocks.stone, 1, 2), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.stone, 1, 4), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.stone, 1, 6), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.stone, 1, 8), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE, 3), new ItemStack(Blocks.SAND), new ItemStack(Items.CLAY_BALL), new ItemStack(Items.IRON_NUGGET), new ItemStack(MBlocks.stone, 4, 0), 0.4F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE, 4), new ItemStack(Items.SNOWBALL), new ItemStack(MItems.nuggets, 1, 0), new ItemStack(MItems.nuggets, 1, 1), new ItemStack(MBlocks.stone, 4, 3), 0.4F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 3, 3), new ItemStack(Blocks.ICE), new ItemStack(Items.SNOWBALL), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.stone, 4, 5), 0.4F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE, 4), new ItemStack(Items.CLAY_BALL), new ItemStack(MItems.salt), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.stone, 4, 7), 0.4F);
	}
	
	public void addPressurizerRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack output, float exp)
	{
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.add(input1);
		inputs.add(input2);
		inputs.add(input3);
		inputs.add(input4);
		
		this.pressurizing.put(inputs, output);
		this.expValues.put(output, exp);
	}
	
	public ItemStack getPressurizingResult(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4)
	{
		Entry<List<ItemStack>, ItemStack> entry = getEntryFromIngredients(input1, input2, input3, input4);
		
		if(entry != null)
			return ((ItemStack)entry.getValue());
		return ItemStack.EMPTY;
	}
	
	public float getPressurizingExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.expValues.entrySet())
		{
			if(ItemUtil.compareStacks(stack, (ItemStack)entry.getKey()))
				return ((Float)entry.getValue()).floatValue();
		}
		
		return 0.0F;
	}
	
	public Map<List<ItemStack>, ItemStack> getPressurizing()
	{
		return this.pressurizing;
	}
	
	public ItemStack getIngredient(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, int index)
	{
		Entry<List<ItemStack>, ItemStack> entry = getEntryFromIngredients(input1, input2, input3, input4);
		
		if(entry != null)
			return ((ItemStack)entry.getKey().get(index));
		return ItemStack.EMPTY;
	}
	
	public Entry<List<ItemStack>, ItemStack> getEntryFromIngredients(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4)
	{
		for(Entry<List<ItemStack>, ItemStack> entry : this.pressurizing.entrySet())
		{
			boolean match1 = false;
			boolean match2 = false;
			boolean match3 = false;
			List<Integer> matchedItems = new ArrayList<Integer>();
			
			for(int i = 0 ; i < 4 ; i++)
			{
				if(ItemUtil.compareStacks(input1, ((List<ItemStack>)entry.getKey()).get(i)))
				{
					match1 = true;
					matchedItems.add(i);
					break;
				}
			}
			if(match1)
			{
				for(int i = 0 ; i < 4 ; i++)
				{
					if(!matchedItems.contains(i) && ItemUtil.compareStacks(input2, ((List<ItemStack>)entry.getKey()).get(i)))
					{
						match2 = true;
						matchedItems.add(i);
						break;
					}
				}
				if(match2)
				{
					for(int i = 0 ; i < 4 ; i++)
					{
						if(!matchedItems.contains(i) && ItemUtil.compareStacks(input3, ((List<ItemStack>)entry.getKey()).get(i)))
						{
							match3 = true;
							matchedItems.add(i);
							break;
						}
					}
					if(match3)
					{
						for(int i = 0 ; i < 4 ; i++)
						{
							if(!matchedItems.contains(i) && ItemUtil.compareStacks(input4, ((List<ItemStack>)entry.getKey()).get(i)))
							{
								return entry;
							}
						}
					}
				}
			}
		}
		
		return null;
	}
}
