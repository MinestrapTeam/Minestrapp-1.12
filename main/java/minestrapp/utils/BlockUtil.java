package minestrapp.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtil {
	
	public static void replaceBlock(World world, IBlockState oldState, IBlockState newState, BlockPos pos) {
		if(world.getBlockState(pos) == oldState) {
			world.setBlockState(pos, newState);
		}
	}

}
