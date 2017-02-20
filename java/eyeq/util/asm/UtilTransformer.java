package eyeq.util.asm;

import net.minecraft.launchwrapper.IClassTransformer;

public class UtilTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        return basicClass;
    }
}
