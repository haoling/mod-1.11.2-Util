package eyeq.util.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions("eyeq.util.asm")
public class UtilLoadingPlugin implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"eyeq.util.asm.UtilTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return "eyeq.util.asm.UtilContainer";
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }
}
