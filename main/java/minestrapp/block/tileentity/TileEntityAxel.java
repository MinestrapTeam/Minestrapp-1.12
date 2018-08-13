package minestrapp.block.tileentity;

import javax.annotation.Nullable;

import minestrapp.block.BlockAxel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAxel extends TileEntity
{
	private int power;
	
	public TileEntityAxel()
    {
    }

    public TileEntityAxel(int power)
    {
        this.power = power;
    }
    
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("Power", this.power);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        this.power = compound.getInteger("Power");
    }

    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 5, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void setLevel(int level)
    {
        this.power = level;
    }

    public int getLevel()
    {
        return this.power;
    }
    
    public void onLoad()
    {
        ((BlockAxel)this.getBlockType()).updatePower(world, pos);
    }
}
