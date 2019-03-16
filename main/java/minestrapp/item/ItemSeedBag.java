package minestrapp.item;

import minestrapp.Minestrapp5;
import minestrapp.gui.MGuiHandler;
import minestrapp.inventories.InventoryBackpack;
import minestrapp.item.util.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSeedBag extends ItemBase
{
	private boolean filled = false;
	
	public ItemSeedBag(String name, boolean filled)
	{
		super(name);
		this.filled = filled;
	}
	
	public boolean isFilled()
	{
		return this.filled;
	}
}
