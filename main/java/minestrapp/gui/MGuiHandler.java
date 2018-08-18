package minestrapp.gui;

import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityPipe;
import minestrapp.block.tileentity.TileEntitySorter;
import minestrapp.container.ContainerAlloy;
import minestrapp.container.ContainerBackpack;
import minestrapp.container.ContainerBarrel;
import minestrapp.container.ContainerCrusher;
import minestrapp.container.ContainerPipe;
import minestrapp.container.ContainerSorter;
import minestrapp.inventories.InventoryBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class MGuiHandler implements IGuiHandler {
	public static final int BARREL = 0;
	public static final int ALLOY = 1;
	public static final int CRUSHER = 2;
	public static final int PIPE = 3;
	public static final int SORTER = 4;
	public static final int BACKPACK = 5;
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID) {
			case BARREL:
				return new ContainerBarrel(player.inventory, ((TileEntityBarrel)world.getTileEntity(new BlockPos(x, y, z))), player);
			case ALLOY:
				return new ContainerAlloy(player.inventory, (TileEntityAlloy)world.getTileEntity(new BlockPos(x, y, z)));
			case CRUSHER:
				return new ContainerCrusher(player.inventory, (TileEntityCrusher)world.getTileEntity(new BlockPos(x, y, z)));
			case PIPE:
				return new ContainerPipe(player.inventory, ((TileEntityPipe)world.getTileEntity(new BlockPos(x, y, z))), player);
			case SORTER:
				return new ContainerSorter(player.inventory, ((TileEntitySorter)world.getTileEntity(new BlockPos(x, y, z))), player);
			case BACKPACK:
				return new ContainerBackpack(player.inventory, new InventoryBackpack(player.getHeldItemMainhand()));
			default:
				return null;
		}
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID) {
			case BARREL:
				return new GuiBarrel(player.inventory, ((TileEntityBarrel)world.getTileEntity(new BlockPos(x, y, z))));
			case ALLOY:
				return new GuiAlloy((ContainerAlloy) getServerGuiElement(ID, player, world, x, y, z), ((ContainerAlloy) getServerGuiElement(ID, player, world, x, y, z)).getTE(), player.inventory);
			case CRUSHER:
				return new GuiCrusher((ContainerCrusher) getServerGuiElement(ID, player, world, x, y, z), ((ContainerCrusher) getServerGuiElement(ID, player, world, x, y, z)).getTE(), player.inventory);
			case PIPE:
				return new GuiPipe(player.inventory, ((TileEntityPipe)world.getTileEntity(new BlockPos(x, y, z))));
			case SORTER:
				return new GuiSorter(player.inventory, ((TileEntitySorter)world.getTileEntity(new BlockPos(x, y, z))));
			case BACKPACK:
				return new GuiBackpack(new ContainerBackpack(player.inventory, new InventoryBackpack(player.getHeldItemMainhand())));
			default:
				return null;
		}
	}
}
