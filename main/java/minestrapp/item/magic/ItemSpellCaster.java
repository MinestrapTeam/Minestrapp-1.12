package minestrapp.item.magic;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MTabs;
import minestrapp.item.util.ItemBase;
import minestrapp.magic.IMagicItem;
import minestrapp.magic.entitys.EntitySpellProjectile;
import minestrapp.magic.spells.SpellBase;
import minestrapp.magic.spells.SpellRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpellCaster extends ItemBase implements IMagicItem{

	public ItemSpellCaster(String name) {
		super(name);
		this.setCreativeTab(MTabs.combat);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn){
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("potency", 1);
		} 
	}
	
	public void setPotency(ItemStack stack, int potency) {
		stack.getTagCompound().setInteger("potency", potency);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
		SpellBase spell = SpellRegistry.getSpellByName("test2");
		
		
		ItemStack stack = player.getHeldItem(hand);
		
		//For debugging remove
		stack.getTagCompound().setInteger("potency", 1);
		
		if(spell.canCast(player)) {
			spell.onCast(world, player);
			if(!world.isRemote) {
				world.spawnEntity(new EntitySpellProjectile(world, player, spell, player.posX + player.getLookVec().x * 1.5, player.posY + player.getLookVec().y + player.eyeHeight, player.posZ + player.getLookVec().z * 1.5, stack.getTagCompound().getInteger("potency")));
			}
		} else {
			if(world.isRemote) {
				Random rand = new Random();
				double x = player.posX + player.getLookVec().x;
				double y = (player.posY + 1.5D) + player.getLookVec().y;
				double z = player.posZ + player.getLookVec().z;
				
				for(int i = 0; i < 3; i++) {
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + (rand.nextDouble() - 0.5), y + (rand.nextDouble() - 0.5), z + (rand.nextDouble() - 0.5), 0.0D, 0.0D, 0.0D);
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + (rand.nextDouble() - 0.5), y + (rand.nextDouble() - 0.5), z + (rand.nextDouble() - 0.5), 0.0D, 0.0D, 0.0D);
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + (rand.nextDouble() - 0.5), y + (rand.nextDouble() - 0.5), z + (rand.nextDouble() - 0.5), 0.0D, 0.0D, 0.0D);
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + (rand.nextDouble() - 0.5), y + (rand.nextDouble() - 0.5), z + (rand.nextDouble() - 0.5), 0.0D, 0.0D, 0.0D);
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + (rand.nextDouble() - 0.5), y + (rand.nextDouble() - 0.5), z + (rand.nextDouble() - 0.5), 0.0D, 0.0D, 0.0D);
				}
			}
		}
		
		return super.onItemRightClick(world, player, hand);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		} else {
			tooltip.add("Potency: " + Integer.toString(stack.getTagCompound().getInteger("potency")));
		}
	}
	
}
