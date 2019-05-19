package minestrapp.item;

import minestrapp.Minestrapp5;
import minestrapp.gui.MGuiHandler;
import minestrapp.inventories.InventoryBackpack;
import minestrapp.item.util.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSeedBag extends ItemBase
{
	private boolean filled = false;
	
	public ItemSeedBag(String name, boolean filled)
	{
		super(name);
		this.filled = filled;
		this.setMaxStackSize(1);
	}
	
	public boolean isFilled()
	{
		return this.filled;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		if(filled)
			return 1;
		else
			return super.getMaxItemUseDuration(stack);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack itemstack = player.getHeldItem(hand);
		
		if(filled)
		{
			if(worldIn.isRemote)
				return EnumActionResult.SUCCESS;
			else if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
	            return EnumActionResult.FAIL;
			else
	        {
	            IBlockState iblockstate = worldIn.getBlockState(pos);
	            Block block = iblockstate.getBlock();
	            NBTTagCompound nbttagcompound = itemstack.getTagCompound();

	            if (nbttagcompound != null && nbttagcompound.hasKey("SeedItem", 10) && nbttagcompound.hasKey("SeedMeta", 10) && nbttagcompound.hasKey("SeedCount", 10))
	            {
	            	//Item item = nbttagcompound.getTag("Item");
		            if (block == Blocks.MOB_SPAWNER)
		            {
		            	
		            }
	            }
	        }
		}
		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
