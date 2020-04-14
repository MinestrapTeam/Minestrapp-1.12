package minestrapp.block.tileentity;

import java.util.ArrayList;
import java.util.List;
import minestrapp.block.BlockPressurizer;
import minestrapp.crafting.PressurizerRecipes;
import minestrapp.utils.ItemUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityPressurizer extends TileEntityLockable implements ISidedInventory, ITickable
{
	private static final int[] SLOTS_TOP = new int[] {4};
    private static final int[] SLOTS_BOTTOM = new int[] {4, 5};
    private static final int[] SLOTS_BACK = new int[] {0};
    private static final int[] SLOTS_RIGHT = new int[] {1};
    private static final int[] SLOTS_LEFT = new int[] {2};
    private static final int[] SLOTS_FRONT = new int[] {3};
	
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	
	private static final float FUELMULT = 0.5F;
	private String customName;
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;
	
	@Override
	public int getInventoryStackLimit() { return 64; }
	
	@Override
	public int getSizeInventory() { return this.inventory.size(); }
	
	@Override
	public boolean isEmpty()
	{
		for(ItemStack stack : this.inventory)
			if(!stack.isEmpty())
				return false;
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) { return (ItemStack)this.inventory.get(index); }
	
	@Override
	public ItemStack decrStackSize(int index, int count) { return ItemStackHelper.getAndSplit(this.inventory, index, count); }
	
	@Override
	public ItemStack removeStackFromSlot(int index) { return ItemStackHelper.getAndRemove(this.inventory, index); }
	
	@Override
	public void setInventorySlotContents(int index, ItemStack newStack)
	{
		ItemStack currentStack = (ItemStack)this.inventory.get(index);
		boolean mergingStacks = !newStack.isEmpty() && newStack.isItemEqual(currentStack) && ItemStack.areItemStackTagsEqual(newStack, currentStack);
		
		this.inventory.set(index, newStack);
		
		if(newStack.getCount() > this.getInventoryStackLimit())
			newStack.setCount(this.getInventoryStackLimit());
		
		if(index < 4 && !mergingStacks)
		{
			this.totalCookTime = this.getCookTime(this.getStackInSlot(0), this.getStackInSlot(1), this.getStackInSlot(2), this.getStackInSlot(3));
			this.cookTime = 0;
			this.markDirty();
		}
	}
	
	@Override
	public String getName() { return this.hasCustomName() ? this.customName : "container.pressurizer"; }
	
	@Override
	public boolean hasCustomName() { return this.customName != null && !this.customName.isEmpty(); }
	
	public void setCustomName(String customName) { this.customName = customName; }
	
	@Override
	public ITextComponent getDisplayName() { return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName()); }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.inventory.get(4));
		
		if(compound.hasKey("CustomName", 8))
			this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName())
			compound.setString("CustomName", this.customName);
		
		return compound;
	}
	
	public boolean isBurning() { return this.burnTime > 0; }
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) { return inventory.getField(0) > 0; }
	
	public void update()
	{
		boolean wasBurning = this.isBurning();
		boolean shouldMarkDirty = false;
		
		if(this.isBurning())
			--this.burnTime;
		
		if(!this.world.isRemote)
		{
			ItemStack fuelStack = (ItemStack)this.inventory.get(4);
			
			if(this.isBurning() || !fuelStack.isEmpty() && !(((ItemStack)this.inventory.get(0)).isEmpty() || ((ItemStack)this.inventory.get(1)).isEmpty() || ((ItemStack)this.inventory.get(2)).isEmpty() || ((ItemStack)this.inventory.get(3)).isEmpty()))
			{
				if(!this.isBurning() && this.canPressurize())
				{
					this.burnTime = getItemBurnTime(fuelStack);
					this.currentBurnTime = this.burnTime;
					
					if(this.isBurning())
					{
						shouldMarkDirty = true;
						
						if(!fuelStack.isEmpty())
						{
							Item fuelItem = fuelStack.getItem();
							fuelStack.shrink(1);
							
							if(fuelStack.isEmpty())
							{
								ItemStack fuelContainerItem = fuelItem.getContainerItem(fuelStack);
								this.inventory.set(4, fuelContainerItem);
							}
						}
					}
				}
				
				if(this.isBurning() && this.canPressurize())
				{
					++this.cookTime;
					
					if(this.cookTime == this.totalCookTime)
					{
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime((ItemStack)this.inventory.get(0), (ItemStack)this.inventory.get(1), (ItemStack)this.inventory.get(2), (ItemStack)this.inventory.get(3));
						this.pressurizeItem();
						shouldMarkDirty = true;
					}
				}
				else
					this.cookTime = 0;
			}
			else if(!this.isBurning() && this.cookTime > 0) 
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			
			if(wasBurning != this.isBurning())
			{
				shouldMarkDirty = true;
				BlockPressurizer.setState(this.isBurning(), this.world, this.pos);
			}
		}
		
		if(shouldMarkDirty)
			this.markDirty();
	}
	
	public int getCookTime(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4)
	{
		return 200;
	}
	
	private boolean canPressurize()
	{
		ItemStack slot1 = (ItemStack)this.inventory.get(0);
		ItemStack slot2 = (ItemStack)this.inventory.get(1);
		ItemStack slot3 = (ItemStack)this.inventory.get(2);
		ItemStack slot4 = (ItemStack)this.inventory.get(3);
		
		if(slot1.isEmpty() || slot2.isEmpty() || slot3.isEmpty() || slot4.isEmpty())
			return false;
		else
		{
			ItemStack result = PressurizerRecipes.instance().getPressurizingResult(slot1, slot2, slot3, slot4);
			
			if(result.isEmpty())
				return false;
			
			List<Integer> matchedItems = new ArrayList<Integer>();
			
			for(int slot = 0 ; slot < 4 ; slot++)
			{
				for(int ingredIndex = 0 ; ingredIndex < 4 ; ingredIndex++)
				{
					if(!matchedItems.contains(ingredIndex))
					{
						ItemStack ingredient = PressurizerRecipes.instance().getIngredient(slot1, slot2, slot3, slot4, ingredIndex);
						
						if(ItemUtil.compareStacks(((ItemStack)this.inventory.get(slot)), ingredient) && ((ItemStack)this.inventory.get(slot)).getCount() >= ingredient.getCount())
						{
							matchedItems.add(ingredIndex);
							break;
						}
					}
				}
			}
			
			if(matchedItems.size() < 4)
				return false;
			
			ItemStack output = (ItemStack)this.inventory.get(5);
			if(output.isEmpty()) return true;
			if(!output.isItemEqual(result)) return false;
			int resTotal = output.getCount() + result.getCount();
			return resTotal <= getInventoryStackLimit() && resTotal <= output.getMaxStackSize();
		}
	}
	
	public void pressurizeItem()
	{
		ItemStack slot1 = (ItemStack)this.inventory.get(0);
		ItemStack slot2 = (ItemStack)this.inventory.get(1);
		ItemStack slot3 = (ItemStack)this.inventory.get(2);
		ItemStack slot4 = (ItemStack)this.inventory.get(3);
		
		ItemStack result = PressurizerRecipes.instance().getPressurizingResult(slot1, slot2, slot3, slot4);
		ItemStack output = (ItemStack)this.inventory.get(5);
		
		if(output.isEmpty())
			this.inventory.set(5, result.copy());
		else if(ItemUtil.compareStacks(result, output))
			output.grow(result.getCount());
		
		List<Integer> matchedItems = new ArrayList<Integer>();
		
		for(int slot = 0 ; slot < 4 ; slot++)
		{
			for(int ingredIndex = 0 ; ingredIndex < 4 ; ingredIndex++)
			{
				if(!matchedItems.contains(ingredIndex))
				{
					ItemStack ingredient = PressurizerRecipes.instance().getIngredient(slot1, slot2, slot3, slot4, ingredIndex);
					
					if(ItemUtil.compareStacks(((ItemStack)this.inventory.get(slot)), ingredient) && ((ItemStack)this.inventory.get(slot)).getCount() >= ingredient.getCount())
					{
						matchedItems.add(ingredIndex);
						((ItemStack)this.inventory.get(slot)).shrink(ingredient.getCount());
						break;
					}
				}
			}
		}
	}
	
	public static int getItemBurnTime(ItemStack fuel)
	{
		if(fuel.isEmpty())
			return 0;
		else
		{
			int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(fuel);
            if (burnTime >= 0) return Math.round(burnTime * FUELMULT);
			Item item = fuel.getItem();

			if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
                return Math.round(150 * FUELMULT);
            else if (item == Item.getItemFromBlock(Blocks.WOOL) || item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON) || item == Items.STICK || item == Item.getItemFromBlock(Blocks.SAPLING) || item == Items.BOWL)
                return Math.round(100 * FUELMULT);
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
                return Math.round(67 * FUELMULT);
            else if (item == Item.getItemFromBlock(Blocks.LADDER) || Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD || item == Items.BOW || item == Items.FISHING_ROD)
                return Math.round(300 * FUELMULT);
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
                return Math.round(16000 * FUELMULT);
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()) || item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()) || item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()) || item == Items.SIGN || (item instanceof ItemDoor && item != Items.IRON_DOOR))
                return Math.round(200 * FUELMULT);
            else if (item == Items.COAL)
                return Math.round(1600 * FUELMULT);
            else if (item == Items.LAVA_BUCKET)
                return Math.round(20000 * FUELMULT);
            else if (item == Items.BLAZE_ROD)
                return Math.round(2400 * FUELMULT);
            else if (item instanceof ItemBoat)
                return Math.round(400 * FUELMULT);
            else
            	return Math.round(GameRegistry.getFuelValue(fuel) * FUELMULT);
		}
	}
	
	public static boolean isItemFuel(ItemStack fuel) { return getItemBurnTime(fuel) > 0; }
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		if (this.world.getTileEntity(this.pos) != this)
            return false;
        else
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if(index == 5)
			return false;
		else if(index != 4)
			return true;
		else
		{
			ItemStack currentStack = this.inventory.get(4);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && currentStack.getItem() != Items.BUCKET;
		}
	}
	
	public String getGuiID()
	{
		return "minestrapp:pressurizer";
	}
	
	public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        if(side != EnumFacing.UP)
        {
        	EnumFacing blockFacing = EnumFacing.NORTH;
        	
        	if(world.getBlockState(pos).getBlock() instanceof BlockPressurizer)
        		blockFacing = world.getBlockState(pos).getValue(BlockPressurizer.FACING);
        	
        	if(side == blockFacing)
        		return SLOTS_FRONT;
        	if(side == blockFacing.rotateYCCW())
        		return SLOTS_RIGHT;
        	if(side == blockFacing.rotateY())
        		return SLOTS_LEFT;
        	if(side == blockFacing.getOpposite())
        		return SLOTS_BACK;
        }
        
        return SLOTS_TOP;
    }
	
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) { return this.isItemValidForSlot(index, itemStackIn); }
	
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 4)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }
	
	@Override
	public int getField(int id)
	{
		switch(id)
		{
			case 0:
				return this.burnTime;
			case 1:
				return this.currentBurnTime;
			case 2:
				return this.cookTime;
			case 3:
				return this.totalCookTime;
			default:
				return 0;
		}
	}

	@Override
	public void setField(int id, int value)
	{
		switch(id)
		{
			case 0:
				this.burnTime = value;
				break;
			case 1:
				this.currentBurnTime = value;
				break;
			case 2:
				this.cookTime = value;
				break;
			case 3:
				this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount() { return 4; }

	@Override
	public void clear() { this.inventory.clear(); }

	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) { return null; }
	
	net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerNorth = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.NORTH);
    net.minecraftforge.items.IItemHandler handlerEast = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.EAST);
    net.minecraftforge.items.IItemHandler handlerSouth = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.SOUTH);
    net.minecraftforge.items.IItemHandler handlerWest = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else if(facing == EnumFacing.NORTH)
                return (T) handlerNorth;
            else if(facing == EnumFacing.EAST)
                return (T) handlerEast;
            else if(facing == EnumFacing.SOUTH)
                return (T) handlerSouth;
            else if(facing == EnumFacing.WEST)
                return (T) handlerWest;
        return super.getCapability(capability, facing);
    }
}