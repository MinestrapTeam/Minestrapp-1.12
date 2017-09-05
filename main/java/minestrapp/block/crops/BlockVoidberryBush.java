package minestrapp.block.crops;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.BlockSavannaGrass;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVoidberryBush extends BlockBush implements IGrowable
{
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 5);
	public static final PropertyBool STEM = PropertyBool.create("stem");
	public static final PropertyEnum<BlockSavannaGrass.EnumGrassSection> SECTION = PropertyEnum.<BlockSavannaGrass.EnumGrassSection>create("section", BlockSavannaGrass.EnumGrassSection.class);
	
	protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
	protected static final AxisAlignedBB STEM_AABB = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 1D, 0.75D);
	
	public BlockVoidberryBush(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)).withProperty(STEM, true));
		this.setTickRandomly(true);
		this.setCreativeTab(MTabs.plant);
		this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.MAGENTA;
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		if(state.getValue(STEM).booleanValue() == true)
			return STEM_AABB;
		else
			return BUSH_AABB;
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
		Block block = state.getBlock();
		return block == Blocks.END_STONE || block == this;
    }
	
	protected PropertyInteger getAgeProperty()
    {
        return AGE;
    }
	
	public int getMaxAge()
    {
        return 5;
    }
	
	protected int getAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue();
    }

    public IBlockState withAge(int age)
    {
        return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean isMaxAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue() >= this.getMaxAge();
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        
        int age = this.getAge(state);
        
        if(age < this.getMaxAge())
        {
        	float f = getGrowthChance(this, worldIn, pos);

            if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
            {
                worldIn.setBlockState(pos, state.withProperty(AGE, age + 1), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }
        else if(state.getValue(STEM).booleanValue() == true && worldIn.getBlockState(pos.up()).getBlock() != MBlocks.voidberry_bush)
        {
        	int height = 0;
        	
        	for(int i = 0 ; i < 3 ; i++)
        	{
        		if(worldIn.getBlockState(pos.offset(EnumFacing.DOWN, i)).getBlock() == MBlocks.voidberry_bush)
        			height++;
        		else
        			break;
        	}
        	
        	int chance = rand.nextInt(4 - height);
        	
        	if (chance == 0 || !worldIn.isAirBlock(pos.up()))
        		worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, 0).withProperty(STEM, false));
        	else
        		worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(AGE, 0).withProperty(STEM, true));
        }
    }
    
    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j)
        {
            i = j;
        }

        worldIn.setBlockState(pos, state.withProperty(AGE, i), 2);
    }
    
    protected int getBonemealAgeIncrease(World worldIn)
    {
        return MathHelper.getInt(worldIn.rand, 1, 2);
    }
    
    protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                IBlockState iblockstate = worldIn.getBlockState(blockpos.add(i, 0, j));

                if (iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)blockIn))
                {
                    f1 = 1.0F;

                    if (iblockstate.getBlock().isFertile(worldIn, blockpos.add(i, 0, j)))
                    {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();

        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();

            if (flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }
    
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }
	
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return !this.isMaxAge(state);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state);
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
    	int modifier = 0;
    	if(meta > 5)
    		modifier = 6;
        return this.withAge(meta - modifier).withProperty(STEM, meta < 6);
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	int modifier = 0;
    	if(state.getValue(STEM) == false)
    		modifier = 6;
        return this.getAge(state) + modifier;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE, STEM, SECTION});
    }
    
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockVoidberryBush)
        {
        	if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockVoidberryBush)
        		return state.withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.MIDDLE);
        	else
        		return state.withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.TOP);
        }
        else
        {
        	if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockVoidberryBush)
        		return state.withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.BOTTOM);
        	else
        		return state.withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.SHORT);
        }
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(!worldIn.isRemote && state.getValue(STEM).booleanValue() == false && this.getAge(state) == this.getMaxAge())
    	{
    		EntityItem item = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(MItems.voidberry));
    		worldIn.spawnEntity(item);
    		worldIn.setBlockState(pos, state.withProperty(AGE, 0));
    		return true;
    	}
    	else
    		return false;
    }
    
    public int getLightValue(IBlockState state)
    {
    	if(state.getValue(STEM).booleanValue() == false)
    		return 15;
    	else
    		return 0;
    }
}