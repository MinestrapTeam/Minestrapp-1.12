package minestrapp.crafting;

import java.util.ArrayList;
import java.util.List;

import minestrapp.MBlocks;
import minestrapp.Minestrapp;
import minestrapp.block.BlockColdSand;
import minestrapp.block.BlockMDirt;
import minestrapp.block.BlockMFarmland;
import net.minecraft.block.BlockConcretePowder;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class SieveRecipes
{
	private static final SieveRecipes SIEVE = new SieveRecipes();
	public final List<IBlockState> sieving = new ArrayList<IBlockState>();
	
	public static SieveRecipes instance()
	{
		return SIEVE;
	}
	
	private SieveRecipes()
	{
		this.addSieveRecipe(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
		this.addSieveRecipe(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT));
		this.addSieveRecipe(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL));
		this.addSieveRecipe(Blocks.GRASS.getDefaultState());
		this.addSieveRecipe(Blocks.GRASS_PATH.getDefaultState());
		this.addSieveRecipe(Blocks.MYCELIUM.getDefaultState());
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 0));
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 1));
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 2));
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 3));
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 4));
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 5));
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 6));
		this.addSieveRecipe(Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, 7));
		this.addSieveRecipe(Blocks.CLAY.getDefaultState());
		this.addSieveRecipe(MBlocks.clay_soil.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.DEFAULT));
		this.addSieveRecipe(MBlocks.clay_soil.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE));
		this.addSieveRecipe(MBlocks.clay_soil.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.PODZOL));
		this.addSieveRecipe(MBlocks.clay_grass.getDefaultState());
		this.addSieveRecipe(MBlocks.clay_grass_path.getDefaultState());
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 0));
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 1));
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 2));
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 3));
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 4));
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 5));
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 6));
		this.addSieveRecipe(MBlocks.clay_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 7));
		this.addSieveRecipe(MBlocks.permafrost.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.DEFAULT));
		this.addSieveRecipe(MBlocks.permafrost.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE));
		this.addSieveRecipe(MBlocks.permafrost.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.PODZOL));
		this.addSieveRecipe(MBlocks.lichen.getDefaultState());
		this.addSieveRecipe(MBlocks.lichen_path.getDefaultState());
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 0));
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 1));
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 2));
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 3));
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 4));
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 5));
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 6));
		this.addSieveRecipe(MBlocks.permafrost_farmland.getDefaultState().withProperty(BlockMFarmland.MOISTURE, 7));
		this.addSieveRecipe(MBlocks.mud.getDefaultState());
		this.addSieveRecipe(Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND));
		this.addSieveRecipe(Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND));
		this.addSieveRecipe(MBlocks.cold_sand.getDefaultState().withProperty(BlockColdSand.VARIANT, BlockColdSand.EnumType.SAND));
		this.addSieveRecipe(MBlocks.cold_sand.getDefaultState().withProperty(BlockColdSand.VARIANT, BlockColdSand.EnumType.RED_SAND));
		this.addSieveRecipe(Blocks.GRAVEL.getDefaultState());
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.WHITE));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.SILVER));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.GRAY));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.BLACK));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.BROWN));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.RED));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.ORANGE));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.YELLOW));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.LIME));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.GREEN));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.CYAN));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.BLUE));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.LIGHT_BLUE));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.PURPLE));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.MAGENTA));
		this.addSieveRecipe(Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, EnumDyeColor.PINK));
		this.addSieveRecipe(Blocks.SNOW.getDefaultState());
		this.addSieveRecipe(Blocks.SOUL_SAND.getDefaultState());
		this.addSieveRecipe(MBlocks.portal_dust.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.DEFAULT));
		this.addSieveRecipe(MBlocks.portal_dust.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE));
		this.addSieveRecipe(MBlocks.portal_dust.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.PODZOL));
		this.addSieveRecipe(MBlocks.fargrowth.getDefaultState());
		this.addSieveRecipe(MBlocks.fargrowth_path.getDefaultState());
	}
	
	public void addSieveRecipe(IBlockState state)
	{
		this.sieving.add(state);
	}
	
	public ResourceLocation getSieveResult(IBlockState state)
    {
		if(this.sieving.contains(state))
		{
			ResourceLocation location = new ResourceLocation(Minestrapp.MODID, "sieve/" + state.getBlock().getUnlocalizedName().substring(5) + "_" + state.getBlock().getMetaFromState(state));
			return location;
		}
		else
			return null;
    }
}
