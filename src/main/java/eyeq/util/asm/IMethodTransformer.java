package eyeq.util.asm;

import org.objectweb.asm.tree.MethodNode;

public interface IMethodTransformer {
    boolean isTargetClass(String className);

    String[] getTargetMethod();

    String getTargetMethodDesc();

    void transform(MethodNode methodNode);
}
