package minestrapp.gui;

import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.gui.GuiBarrel;
import minestrapp.container.ContainerAlloy;
import minestrapp.container.ContainerBarrel;
import minestrapp.container.ContainerCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class MGuiHandler implements IGuiHandler {
	public static final int BARREL = 0;
	public static final int ALLOY = 1;
	public static final int CRUSHER = 2;
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case BARREL:
				return new ContainerBarrel(player.inventory, (TileEntityBarrel)world.getTileEntity(new BlockPos(x, y, z)));
			case ALLOY:
				return new ContainerAlloy(player.inventory, (TileEntityAlloy)world.getTileEntity(new BlockPos(x, y, z)));
			case CRUSHER:
				return new ContainerCrusher(player.inventory, (TileEntityCrusher)world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case BARREL:
				return new GuiBarrel(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			case ALLOY:
				return new GuiAlloy((ContainerAlloy) getServerGuiElement(ID, player, world, x, y, z), ((ContainerAlloy) getServerGuiElement(ID, player, world, x, y, z)).getTE(), player.inventory);
			case CRUSHER:
				return new GuiCrusher((ContainerCrusher) getServerGuiElement(ID, player, world, x, y, z), ((ContainerCrusher) getServerGuiElement(ID, player, world, x, y, z)).getTE(), player.inventory);

			default:
				return null;
		}
	}
}
