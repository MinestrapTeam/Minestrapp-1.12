package minestrapp.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBroodMother extends ModelBase {

	private ModelRenderer bodyRenderer;
	
	public ModelBroodMother(){
		this.textureWidth = 64;
		this.textureHeight = 32;
		initBody();
	}

	private void initBody() {
		this.bodyRenderer = new ModelRenderer(this, 0, 0);
		this.bodyRenderer.addBox(0, 0, 0, 1, 1, 1).setTextureSize(this.textureWidth, this.textureHeight);
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale){
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.bodyRenderer.render(1);
	}
	
}
