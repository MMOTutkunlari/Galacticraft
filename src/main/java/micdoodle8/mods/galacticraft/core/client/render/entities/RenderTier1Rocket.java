package micdoodle8.mods.galacticraft.core.client.render.entities;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderTier1Rocket extends Render<EntitySpaceshipBase>
{
    private ResourceLocation spaceshipTexture;

    protected ModelBase modelSpaceship;

    public RenderTier1Rocket(ModelBase spaceshipModel, String textureDomain, String texture)
    {
        this(new ResourceLocation(textureDomain, "textures/model/" + texture + ".png"));
        this.modelSpaceship = spaceshipModel;
    }

    private RenderTier1Rocket(ResourceLocation texture)
    {
        super(FMLClientHandler.instance().getClient().getRenderManager());
        this.spaceshipTexture = texture;
        this.shadowSize = 0.9F;
    }

    protected ResourceLocation func_110779_a(Entity entity)
    {
        return this.spaceshipTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySpaceshipBase par1Entity)
    {
        return this.func_110779_a(par1Entity);
    }

    @Override
    public void doRender(EntitySpaceshipBase entity, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        final float var24 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9;
        final float var25 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9;

        GL11.glTranslatef((float) par2, (float) par4 + entity.getRenderOffsetY(), (float) par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-var25, 0.0F, 1.0F, 0.0F);
        final float var28 = entity.rollAmplitude - par9;

        if (var28 > 0.0F)
        {
            final float i = entity.getLaunched() ? (5 - MathHelper.floor_double(entity.timeUntilLaunch / 85)) / 10F : 0.3F;
            GL11.glRotatef(MathHelper.sin(var28) * var28 * i * par9, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(MathHelper.sin(var28) * var28 * i * par9, 1.0F, 0.0F, 1.0F);
        }

        this.bindEntityTexture(entity);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelSpaceship.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
    }
}
