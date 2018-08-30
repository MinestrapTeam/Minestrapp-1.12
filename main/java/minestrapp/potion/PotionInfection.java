package minestrapp.potion;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;

public class PotionInfection extends MPotion{

	private Random rand = new Random();
	
	protected PotionInfection(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndexX, int iconIndexY) {
		super(isBadEffectIn, liquidColorIn, name, iconIndexX, iconIndexY);
	}
	
	@Override
	public void performEffect(EntityLivingBase living, int amplifier) {
		World world =  living.getEntityWorld();
		BlockPos blockPosBelow = living.getPosition().down();
		IBlockState blockBelow = world.getBlockState(blockPosBelow);
		
		if(blockBelow == Blocks.GRASS.getDefaultState()) {
			float mushroomSpawnChance = rand.nextFloat();
			float mushroomTypeChance = rand.nextFloat();
			if(mushroomSpawnChance >= 0.75F) {
				if(mushroomTypeChance >= 0.65F) {
					world.setBlockState(living.getPosition(), Blocks.RED_MUSHROOM.getDefaultState());
				} else if(mushroomTypeChance >= 0.3F){
					world.setBlockState(living.getPosition(), Blocks.BROWN_MUSHROOM.getDefaultState());
				} else if(mushroomTypeChance >= 0.22F){
					world.setBlockState(living.getPosition(), MBlocks.blue_glowshroom.getDefaultState());
				} else if(mushroomTypeChance >= 0.14F){
					world.setBlockState(living.getPosition(), MBlocks.purple_glowshroom.getDefaultState());
				} else if(mushroomTypeChance >= 0.06F){
					world.setBlockState(living.getPosition(), MBlocks.green_glowshroom.getDefaultState());
				} else {
					world.setBlockState(living.getPosition(), MBlocks.infected_mushroom.getDefaultState());
				}
				
			}
			world.setBlockState(blockPosBelow, Blocks.MYCELIUM.getDefaultState());
		} else if(blockBelow == Blocks.MYCELIUM.getDefaultState()) {
			living.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 200));
		}
		
    }

}
