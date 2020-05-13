package minestrapp.gui;

import minestrapp.block.tileentity.TileEntityActivator;
import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.block.tileentity.TileEntityCrate;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityPipe;
import minestrapp.block.tileentity.TileEntityPressurizer;
import minestrapp.block.tileentity.TileEntitySawmill;
import minestrapp.block.tileentity.TileEntitySorter;
import minestrapp.container.ContainerActivator;
import minestrapp.container.ContainerAlloy;
import minestrapp.container.ContainerBackpack;
import minestrapp.container.ContainerBarrel;
import minestrapp.container.ContainerCrate;
import minestrapp.container.ContainerCrusher;
import minestrapp.container.ContainerPipe;
import minestrapp.container.ContainerPressurizer;
import minestrapp.container.ContainerSawmill;
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
	public static final int CRATE = 6;
	public static final int ACTIVATOR = 7;
	public static final int PRESSURIZER = 8;
	public static final int SAWMILL = 9;
	
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
			case CRATE:
				return new ContainerCrate(player.inventory, ((TileEntityCrate)world.getTileEntity(new BlockPos(x, y, z))), player);
			case ACTIVATOR:
				return new ContainerActivator(player.inventory, ((TileEntityActivator)world.getTileEntity(new BlockPos(x, y, z))), player);
			case PRESSURIZER:
				return new ContainerPressurizer(player.inventory, ((TileEntityPressurizer)world.getTileEntity(new BlockPos(x, y, z))));
			case SAWMILL:
				return new ContainerSawmill(player.inventory, ((TileEntitySawmill)world.getTileEntity(new BlockPos(x, y, z))), player);
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
			case CRATE:
				return new GuiCrate(player.inventory, ((TileEntityCrate)world.getTileEntity(new BlockPos(x, y, z))));
			case ACTIVATOR:
				return new GuiActivator(player.inventory, ((TileEntityActivator)world.getTileEntity(new BlockPos(x, y, z))));
			case PRESSURIZER:
				return new GuiPressurizer((ContainerPressurizer) getServerGuiElement(ID, player, world, x, y, z), ((ContainerPressurizer) getServerGuiElement(ID, player, world, x, y, z)).getTE(), player.inventory);
			case SAWMILL:
				return new GuiSawmill(player.inventory, ((TileEntitySawmill)world.getTileEntity(new BlockPos(x, y, z))));
			default:
				return null;
		}
	}
}
