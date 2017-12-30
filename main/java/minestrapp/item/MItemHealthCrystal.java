package minestrapp.item;

import minestrapp.MTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.tools.nsc.doc.model.Public;

public class MItemHealthCrystal extends Item
{
	
	public MItemHealthCrystal(String unlocalizedName){
		maxStackSize=1;
		setMaxDamage(1);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
	}

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack itemstack = playerIn.getHeldItem(handIn);	
 		playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("instant_health"), 1));
 		//It won't break unless they are spaced out, I'm sure there is a better way
 		itemstack.damageItem(1, playerIn);
 		itemstack.damageItem(1, playerIn);

    	return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}