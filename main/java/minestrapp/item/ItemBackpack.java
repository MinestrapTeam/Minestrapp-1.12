package minestrapp.item;

import java.awt.Desktop.Action;

import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.gui.MGuiHandler;
import minestrapp.inventories.InventoryBackpack;
import minestrapp.item.util.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBackpack extends ItemBase{

	public int type;
	
	public ItemBackpack(String name, int type) {
		super(name);
		this.maxStackSize = 1;
		this.type = type;
		this.setCreativeTab(MTabs.tools);
	}
	
	public int getType() {
		return this.type;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemBackpack item = (ItemBackpack) player.getHeldItem(hand).getItem();

		if (!world.isRemote) {
			if (!player.isSneaking()) {
					player.openGui(Minestrapp5.instance, MGuiHandler.BACKPACK, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			} else {
				new InventoryBackpack(player.getHeldItem(hand));
			}
		}
		return super.onItemRightClick(world, player, hand);
	}
}
