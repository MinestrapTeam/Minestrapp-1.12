package minestrapp.mobs.models;

import java.util.HashMap;

import minestrapp.mobs.entitys.EntityLumpGoat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;


public class ModelLumpGoat extends ModelBase
{
	ModelRenderer Elem13;//Cube*
	ModelRenderer Elem12;//Cube
	ModelRenderer Elem11;//Cube
	ModelRenderer Elem10;//Cube*
	ModelRenderer Elem9;//Cube*
	ModelRenderer Elem8;//Cube
	ModelRenderer Elem7;//Cube
	ModelRenderer Elem6;//Cube*
	ModelRenderer Elem5;//Cube
	ModelRenderer Elem4;//Cube

	public ModelLumpGoat()
	{
		textureWidth = 64;
		textureHeight = 32;

		//Cube*
		Elem13 = new ModelRenderer(this, 0, 0);
		Elem13.addBox(0F, 0F, 0F, 2, 2, 2);
		Elem13.setRotationPoint(6F, 22F, -6F);
		Elem13.setTextureSize(64, 32);
		setRotation(Elem13, -0F, -0F, -0F);
		//Elem13.rotateAngleX = -0F;
		//Elem13.rotateAngleY = -0F;
		//Elem13.rotateAngleZ = -0F;
		//Cube
		Elem12 = new ModelRenderer(this, 0, 0);
		Elem12.addBox(0F, 0F, 0F, 2, 2, 2);
		Elem12.setRotationPoint(-8F, 22F, -6F);
		Elem12.setTextureSize(64, 32);
		setRotation(Elem12, -0F, -0F, -0F);
		//Elem12.rotateAngleX = -0F;
		//Elem12.rotateAngleY = -0F;
		//Elem12.rotateAngleZ = -0F;
		//Cube
		Elem11 = new ModelRenderer(this, 0, 0);
		Elem11.addBox(0F, 0F, 0F, 2, 2, 2);
		Elem11.setRotationPoint(-1F, 17F, -11F);
		Elem11.setTextureSize(64, 32);
		setRotation(Elem11, -0F, -0F, -0F);
		//Elem11.rotateAngleX = -0F;
		//Elem11.rotateAngleY = -0F;
		//Elem11.rotateAngleZ = -0F;
		//Cube*
		Elem10 = new ModelRenderer(this, 0, 0);
		Elem10.addBox(0F, 0F, 0F, 4, 4, 4);
		Elem10.setRotationPoint(-2F, 18F, -10F);
		Elem10.setTextureSize(64, 32);
		setRotation(Elem10, -0F, -0F, -0F);
		//Elem10.rotateAngleX = -0F;
		//Elem10.rotateAngleY = -0F;
		//Elem10.rotateAngleZ = -0F;
		//Cube*
		Elem9 = new ModelRenderer(this, 0, 0);
		Elem9.addBox(0F, 0F, 0F, 1, 2, 1);
		Elem9.setRotationPoint(4F, 3F, 6F);
		Elem9.setTextureSize(64, 32);
		setRotation(Elem9, -0F, -0F, -0F);
		//Elem9.rotateAngleX = -0F;
		//Elem9.rotateAngleY = -0F;
		//Elem9.rotateAngleZ = -0F;
		//Cube
		Elem8 = new ModelRenderer(this, 0, 0);
		Elem8.addBox(0F, 0F, 0F, 1, 2, 1);
		Elem8.setRotationPoint(-5F, 3F, 6F);
		Elem8.setTextureSize(64, 32);
		setRotation(Elem8, -0F, -0F, -0F);
		//Elem8.rotateAngleX = -0F;
		//Elem8.rotateAngleY = -0F;
		//Elem8.rotateAngleZ = -0F;
		//Cube
		Elem7 = new ModelRenderer(this, 0, 0);
		Elem7.addBox(0F, 0F, 0F, 8, 8, 8);
		Elem7.setRotationPoint(-4F, 4F, 3F);
		Elem7.setTextureSize(64, 32);
		setRotation(Elem7, -0F, -0F, -0F);
		//Elem7.rotateAngleX = -0F;
		//Elem7.rotateAngleY = -0F;
		//Elem7.rotateAngleZ = -0F;
		//Cube*
		Elem6 = new ModelRenderer(this, 0, 0);
		Elem6.addBox(0F, 0F, 0F, 2, 12, 4);
		Elem6.setRotationPoint(7F, 12F, 4F);
		Elem6.setTextureSize(64, 32);
		setRotation(Elem6, -0F, -0F, -0F);
		//Elem6.rotateAngleX = -0F;
		//Elem6.rotateAngleY = -0F;
		//Elem6.rotateAngleZ = -0F;
		//Cube
		Elem5 = new ModelRenderer(this, 0, 0);
		Elem5.addBox(0F, 0F, 0F, 2, 12, 4);
		Elem5.setRotationPoint(-9F, 12F, 4F);
		Elem5.setTextureSize(64, 32);
		setRotation(Elem5, -0F, -0F, -0F);
		//Elem5.rotateAngleX = -0F;
		//Elem5.rotateAngleY = -0F;
		//Elem5.rotateAngleZ = -0F;
		//Cube
		Elem4 = new ModelRenderer(this, 0, 0);
		Elem4.addBox(0F, 0F, 0F, 14, 14, 14);
		Elem4.setRotationPoint(-7F, 9F, -7F);
		Elem4.setTextureSize(64, 32);
		setRotation(Elem4, -0F, -0F, -0F);
		//Elem4.rotateAngleX = -0F;
		//Elem4.rotateAngleY = -0F;
		//Elem4.rotateAngleZ = -0F;
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) 
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7);
		Elem13.render(par7);//Cube*
		Elem12.render(par7);//Cube
		Elem11.render(par7);//Cube
		Elem10.render(par7);//Cube*
		Elem9.render(par7);//Cube*
		Elem8.render(par7);//Cube
		Elem7.render(par7);//Cube
		Elem6.render(par7);//Cube*
		Elem5.render(par7);//Cube
		Elem4.render(par7);//Cube
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		
	}

}