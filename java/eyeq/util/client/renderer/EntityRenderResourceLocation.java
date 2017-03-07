package eyeq.util.client.renderer;

import net.minecraft.util.ResourceLocation;

public class EntityRenderResourceLocation extends ResourceLocation {
    public EntityRenderResourceLocation(String resourceDomain, String resourcePath) {
        super(resourceDomain, "textures/entity/" + resourcePath + ".png");
    }
}
