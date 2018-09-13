package minestrapp.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;


public class ModelBurfalaunt extends ModelBase
{
	ModelRenderer	mane;
	ModelRenderer	body;
	ModelRenderer	backRightLeg;
	ModelRenderer	frontRightLeg;
	ModelRenderer	frontLeftLeg;
	ModelRenderer	backLeftLeg;
	ModelRenderer	head;
	ModelRenderer	noseBottom;
	ModelRenderer	noseTop;
	ModelRenderer	rightHornBottom;
	ModelRenderer	leftHornBottom;
	ModelRenderer	rightHornTop;
	ModelRenderer	leftHornTop;
	ModelRenderer	tailBase;
	ModelRenderer	tail;
	private float	headRotation;
	
	public ModelBurfalaunt()
	{


		this.textureWidth = 128;
		this.textureHeight = 64;
		
		this.mane = new ModelRenderer(this, 0, 24);
		this.mane.addBox(0F, 0F, 0F, 14, 14, 8);
		this.mane.setRotationPoint(-7F, 3F, -9F);
		this.mane.setTextureSize(128, 64);
		this.mane.mirror = true;
		setRotation(this.mane, -0.0698132F, 0F, 0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(-6F, 3F, -1F, 12, 12, 12);
		this.body.setRotationPoint(0F, 2F, 0F);
		this.body.setTextureSize(128, 64);
		this.body.mirror = true;
		setRotation(this.body, -0.0698132F, 0F, 0F);
		this.backRightLeg = new ModelRenderer(this, 48, 0);
		this.backRightLeg.addBox(0F, 0F, 0F, 5, 14, 5);
		this.backRightLeg.setRotationPoint(-7F, 10F, 4F);
		this.backRightLeg.setTextureSize(128, 64);
		this.backRightLeg.mirror = true;
		setRotation(this.backRightLeg, 0F, 0F, 0F);
		this.frontRightLeg = new ModelRenderer(this, 48, 0);
		this.frontRightLeg.addBox(0F, 0F, 0F, 5, 14, 5);
		this.frontRightLeg.setRotationPoint(-6F, 10F, -8F);
		this.frontRightLeg.setTextureSize(128, 64);
		this.frontRightLeg.mirror = true;
		setRotation(this.frontRightLeg, 0F, 0F, 0F);
		this.frontLeftLeg = new ModelRenderer(this, 48, 0);
		this.frontLeftLeg.addBox(0F, 0F, 0F, 5, 14, 5);
		this.frontLeftLeg.setRotationPoint(1F, 10F, -8F);
		this.frontLeftLeg.setTextureSize(128, 64);
		this.frontLeftLeg.mirror = true;
		setRotation(this.frontLeftLeg, 0F, 0F, 0F);
		this.backLeftLeg = new ModelRenderer(this, 48, 0);
		this.backLeftLeg.addBox(0F, 0F, 0F, 5, 14, 5);
		this.backLeftLeg.setRotationPoint(2F, 10F, 4F);
		this.backLeftLeg.setTextureSize(128, 64);
		this.backLeftLeg.mirror = true;
		setRotation(this.backLeftLeg, 0F, 0F, 0F);
		this.head = new ModelRenderer(this, 16, 46);
		this.head.addBox(-4F, -4F, -7F, 8, 8, 8);
		this.head.setRotationPoint(0F, 10F, -7F);
		this.head.setTextureSize(128, 64);
		this.head.mirror = true;
		setRotation(this.head, 0F, 0F, 0F);
		this.noseBottom = new ModelRenderer(this, 48, 32);
		this.noseBottom.addBox(-2F, 1F, -8F, 4, 1, 1);
		this.noseBottom.setRotationPoint(0F, 10F, -7F);
		this.noseBottom.setTextureSize(128, 64);
		this.noseBottom.mirror = true;
		setRotation(this.noseBottom, 0F, 0F, 0F);
		this.noseTop = new ModelRenderer(this, 50, 30);
		this.noseTop.addBox(0F, 0F, 0F, 2, 1, 1);
		this.noseTop.setRotationPoint(-1F, 10F, -15F);
		this.noseTop.setTextureSize(128, 64);
		this.noseTop.mirror = true;
		setRotation(this.noseTop, 0F, 0F, 0F);
		this.rightHornBottom = new ModelRenderer(this, 68, 0);
		this.rightHornBottom.addBox(0F, 0F, 0F, 4, 1, 1);
		this.rightHornBottom.setRotationPoint(-6F, 6F, -12F);
		this.rightHornBottom.setTextureSize(128, 64);
		this.rightHornBottom.mirror = true;
		setRotation(this.rightHornBottom, 0F, 0F, 0.5585054F);
		this.leftHornBottom = new ModelRenderer(this, 68, 0);
		this.leftHornBottom.addBox(0F, -1F, 0F, 4, 1, 1);
		this.leftHornBottom.setRotationPoint(6F, 6F, -12F);
		this.leftHornBottom.setTextureSize(128, 64);
		this.leftHornBottom.mirror = true;
		setRotation(this.leftHornBottom, 0F, 0F, 2.583087F);
		this.rightHornTop = new ModelRenderer(this, 68, 2);
		this.rightHornTop.addBox(0F, 0F, 0F, 3, 1, 1);
		this.rightHornTop.setRotationPoint(-6F, 6F, -12F);
		this.rightHornTop.setTextureSize(128, 64);
		this.rightHornTop.mirror = true;
		setRotation(this.rightHornTop, 0F, 0F, -1.012291F);
		this.leftHornTop = new ModelRenderer(this, 68, 2);
		this.leftHornTop.addBox(0F, -1F, 0F, 3, 1, 1);
		this.leftHornTop.setRotationPoint(6F, 6F, -12F);
		this.leftHornTop.setTextureSize(128, 64);
		this.leftHornTop.mirror = true;
		setRotation(this.leftHornTop, 0F, 0F, -2.164208F);
		this.tailBase = new ModelRenderer(this, 0, 46);
		this.tailBase.addBox(0F, 0F, 0F, 4, 4, 4);
		this.tailBase.setRotationPoint(-2F, 8F, 9F);
		this.tailBase.setTextureSize(128, 64);
		this.tailBase.mirror = true;
		setRotation(this.tailBase, 0F, 0F, 0F);
		this.tail = new ModelRenderer(this, 0, 54);
		this.tail.addBox(0F, 0F, 0F, 2, 2, 2);
		this.tail.setRotationPoint(-1F, 8F, 12F);
		this.tail.setTextureSize(128, 64);
		this.tail.mirror = true;
		setRotation(this.tail, 0.2268928F, 0F, 0F);
	}
	
	@Override
	public void render(Entity entity, float x, float y, float z, float yaw, float pitch, float partialTickTime)
	{
		super.render(entity, x, y, z, yaw, pitch, partialTickTime);
		setRotationAngles(x, y, z, yaw, pitch, partialTickTime, entity);
		this.mane.render(partialTickTime);
		this.body.render(partialTickTime);
		this.backRightLeg.render(partialTickTime);
		this.frontRightLeg.render(partialTickTime);
		this.frontLeftLeg.render(partialTickTime);
		this.backLeftLeg.render(partialTickTime);
		this.head.render(partialTickTime);
		this.noseBottom.render(partialTickTime);
		this.noseTop.render(partialTickTime);
		this.rightHornBottom.render(partialTickTime);
		this.leftHornBottom.render(partialTickTime);
		this.rightHornTop.render(partialTickTime);
		this.leftHornTop.render(partialTickTime);
		this.tailBase.render(partialTickTime);
		this.tail.render(partialTickTime);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float x, float y, float z, float yaw, float pitch, float partialTickTime, Entity entity)
	{
		super.setRotationAngles(x, y, z, yaw, pitch, partialTickTime, entity);
		this.backLeftLeg.rotateAngleX = MathHelper.cos(x * 0.6662F) * 1.2F * y;
		this.backRightLeg.rotateAngleX = MathHelper.cos(x * 0.6662F + 3.141593F) * y;
		this.frontLeftLeg.rotateAngleX = MathHelper.cos(x * 0.6662F + 3.141593F) * y;
		this.frontRightLeg.rotateAngleX = MathHelper.cos(x * 0.6662F) * 1.2F * y;

	}
}