package minestrapp.item;

import java.util.List;

import minestrapp.MTabs;
import minestrapp.crafting.SieveRecipes;
import minestrapp.item.util.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class ItemSieve extends ItemBase
{
	private ItemStack repairItem;
	private ItemStack breakItem = ItemStack.EMPTY;
	private boolean unbreakable = true;
	
	public ItemSieve(String name)
	{
		super(name);
	}
	
	public ItemSieve(String name, int damage, ItemStack repairItem)
	{
		this(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(damage);
		this.setCreativeTab(MTabs.tools);
		this.repairItem = repairItem;
		this.unbreakable = false;
	}
	
	public ItemSieve(String name, int damage, ItemStack repairItem, ItemStack breakItem)
	{
		this(name, damage, repairItem);
		this.breakItem = breakItem;
	}
	
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
		if(!this.unbreakable)
			return repair.getItem() == this.repairItem.getItem() && repair.getMetadata() == this.repairItem.getMetadata();
		else
			return false;
    }
	
	public boolean isUnbreakable() {return this.unbreakable;}
	
	public ItemStack getBreakItem() {return this.breakItem;}
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack itemstack = player.getHeldItem(hand);
		
		if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
		else
        {
			IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            LootTable table = LootTable.EMPTY_LOOT_TABLE;
            if(!worldIn.isRemote && SieveRecipes.instance().getSieveResult(iblockstate) != null)
            	table = worldIn.getLootTableManager().getLootTableFromLocation(SieveRecipes.instance().getSieveResult(iblockstate));
            
            if(table != null && table != LootTable.EMPTY_LOOT_TABLE)
            {
            	worldIn.destroyBlock(pos, false);
            	if(!this.unbreakable)
            	{
            		if(itemstack.getItemDamage() == itemstack.getMaxDamage() && this.breakItem != ItemStack.EMPTY)
	            		worldIn.spawnEntity(new EntityItem(worldIn, player.posX, player.posY, player.posZ, this.breakItem.copy()));
	            	itemstack.damageItem(1, player);
            	}
            	LootContext.Builder context = new LootContext.Builder((WorldServer) worldIn);
            	List<ItemStack> stackList = table.generateLootForPools(worldIn.rand, context.build());
            	
            	if(stackList.size() > 0)
            		worldIn.spawnEntity(new EntityItem(worldIn, player.posX, player.posY, player.posZ, stackList.get(0)));
            	return EnumActionResult.SUCCESS;
            }
			else
            {
                return EnumActionResult.PASS;
            }
        }
    }
	
	
}
