package eyeq.util.client;

import eyeq.util.asm.UtilContainer;
import net.minecraftforge.fml.client.FMLFileResourcePack;
import net.minecraftforge.fml.common.ModContainer;

public class UtilFileResourcePack extends FMLFileResourcePack {
    public UtilFileResourcePack(ModContainer container) {
        super(container);
    }

    @Override
    public String getPackName() {
        return UtilContainer.MOD_ID + ":" + getFMLContainer().getName();
    }
}
