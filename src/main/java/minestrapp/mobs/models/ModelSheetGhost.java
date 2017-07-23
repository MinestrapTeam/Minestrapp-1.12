package minestrapp.mobs.models;

import java.util.HashMap;

import minestrapp.mobs.entitys.EntitySheetGhost;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;


public class ModelSheetGhost extends ModelBase {


ModelRenderer bod;
ModelRenderer arm2;
ModelRenderer arm1;

public ModelSheetGhost()
{

textureWidth = 23;
textureHeight = 19;

bod = new ModelRenderer(this, 0, 0);
bod.mirror = false;
bod.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5);
bod.setRotationPoint(0.0F, 0.0F, 0.0F);
bod.setTextureSize(textureWidth, textureHeight);

arm2 = new ModelRenderer(this, 0, 0);
arm2.mirror = false;
arm2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
arm2.setRotationPoint(2.0F, 4.0F, 5.0F);
arm2.setTextureSize(textureWidth, textureHeight);

arm1 = new ModelRenderer(this, 0, 0);
arm1.mirror = false;
arm1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
arm1.setRotationPoint(2.0F, 4.0F, -1.0F);
arm1.setTextureSize(textureWidth, textureHeight);
}

@Override
public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) 
{
EntitySheetGhost entity = (EntitySheetGhost)par1Entity;

//Render every non-child part
bod.render(par7);
arm2.render(par7);
arm1.render(par7);
}
@Override
public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {}

}