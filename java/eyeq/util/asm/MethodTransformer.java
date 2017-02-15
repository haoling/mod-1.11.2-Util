package eyeq.util.asm;

import org.objectweb.asm.tree.MethodNode;

public abstract class MethodTransformer implements IMethodTransformer {
    private final String TARGET_CLASS;
    private final String[] TARGET_METHOD;
    private final String TARGET_METHOD_DESC;

    public MethodTransformer(String targetClass, String[] targetMethod, String targetMethodDesc) {
        this.TARGET_CLASS = targetClass;
        this.TARGET_METHOD = targetMethod;
        this.TARGET_METHOD_DESC = targetMethodDesc;
    }

    public boolean isTargetClass(String className) {
        return TARGET_CLASS.equals(className);
    }

    public String[] getTargetMethod() {
        return TARGET_METHOD;
    }

    public String getTargetMethodDesc() {
        return TARGET_METHOD_DESC;
    }

    public abstract void transform(MethodNode methodNode);
}
