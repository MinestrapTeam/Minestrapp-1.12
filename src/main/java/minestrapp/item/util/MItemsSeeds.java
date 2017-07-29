package minestrapp.item.util;

import minestrapp.MTabs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MItemsSeeds extends ItemSeeds
{

	public MItemsSeeds(Block crops, Block soil, String string) {
		super(crops, soil);
        this.setUnlocalizedName(string);
        this.setRegistryName(string);
        this.setCreativeTab(MTabs.food);
	}
	
}

