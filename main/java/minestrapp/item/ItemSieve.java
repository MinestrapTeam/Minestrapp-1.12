package minestrapp.item;

import java.util.List;

import javax.annotation.Nullable;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.crafting.SieveRecipes;
import minestrapp.item.util.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if(this.unbreakable)
			tooltip.add("Creative-only. Unbreakable.");
    }
	
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
            if(!worldIn.isRemote && SieveRecipes.instance().getSieveResult(iblockstate, worldIn) != null)
            	table = worldIn.getLootTableManager().getLootTableFromLocation(SieveRecipes.instance().getSieveResult(iblockstate, worldIn));
            
            if(table != null && table != LootTable.EMPTY_LOOT_TABLE)
            {
            	worldIn.destroyBlock(pos, false);
            	if(!this.unbreakable)
            	{
	            	if(this.breakItem == ItemStack.EMPTY || itemstack.getItemDamage() < itemstack.getMaxDamage())
	            		itemstack.damageItem(1, player);
	            	else
	            		player.setHeldItem(hand, this.breakItem);
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
