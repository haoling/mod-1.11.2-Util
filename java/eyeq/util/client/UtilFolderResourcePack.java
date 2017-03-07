package eyeq.util.client;

import eyeq.util.asm.UtilContainer;
import net.minecraftforge.fml.client.FMLFolderResourcePack;
import net.minecraftforge.fml.common.ModContainer;

public class UtilFolderResourcePack extends FMLFolderResourcePack {
    public UtilFolderResourcePack(ModContainer container) {
        super(container);
    }

    @Override
    public String getPackName() {
        return UtilContainer.MOD_ID + ":" + getFMLContainer().getName();
    }
}
