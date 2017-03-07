package eyeq.util.client.renderer;

import net.minecraft.util.ResourceLocation;

public class GuiContainerResourceLocation extends ResourceLocation {
    public GuiContainerResourceLocation(String resourceDomain, String resourcePath) {
        super(resourceDomain, "textures/gui/container/" + resourcePath + ".png");
    }
}
