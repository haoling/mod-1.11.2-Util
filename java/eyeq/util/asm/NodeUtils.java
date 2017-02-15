package eyeq.util.asm;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

public class NodeUtils {
    public static String mapMethodName(String owner, String name, String desc) {
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(owner, name, desc);
    }

    public static String mapMethodDesc(String desc) {
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(desc);
    }

    public static ClassNode read(byte[] bytes, int flags) {
        ClassNode classNode = new ClassNode();
        (new ClassReader(bytes)).accept(classNode, flags);
        return classNode;
    }

    public static ClassNode read(byte[] bytes) {
        return read(bytes, 0);
    }

    public static FieldNode findField(ClassNode classNode, String desc) {
        for(FieldNode fieldNode : classNode.fields) {
            if(desc.equals(fieldNode.desc)) {
                return fieldNode;
            }
        }
        return null;
    }

    public static MethodNode find(ClassNode classNode, String className, String methodName, String desc) {
        for(MethodNode methodNode : classNode.methods) {
            if(methodName.equals(mapMethodName(className, methodNode.name, methodNode.desc)) && desc.equals(mapMethodDesc(methodNode.desc))) {
                return methodNode;
            }
        }
        return null;
    }

    public static byte[] transform(String className, String transformedClassName, byte[] basicClass, IMethodTransformer transformer) {
        if(basicClass == null) {
            return null;
        }
        if(!transformer.isTargetClass(transformedClassName)) {
            return basicClass;
        }
        String desc = transformer.getTargetMethodDesc();
        for(String methodName : transformer.getTargetMethod()) {
            ClassNode classNode = NodeUtils.read(basicClass);
            MethodNode methodNode = NodeUtils.find(classNode, className, methodName, desc);
            if(methodNode != null) {
                transformer.transform(methodNode);
                basicClass = NodeUtils.write(classNode);
            }
        }
        return basicClass;
    }

    public static AbstractInsnNode getFirst(InsnList insnList, int opcode, int num) {
        AbstractInsnNode insnNode = insnList.getFirst();
        while(true) {
            while(insnNode != null && insnNode.getOpcode() != opcode) {
                insnNode = insnNode.getNext();
            }
            if(num < 1) {
                return insnNode;
            }
            if(insnNode == null) {
                return null;
            }
            insnNode = insnNode.getNext();
            --num;
        }
    }

    public static AbstractInsnNode getFirst(InsnList insnList, int opcode) {
        return getFirst(insnList, opcode, 0);
    }

    public static AbstractInsnNode getLast(InsnList insnList, int opcode, int num) {
        AbstractInsnNode insnNode = insnList.getLast();
        while(true) {
            while(insnNode != null && insnNode.getOpcode() != opcode) {
                insnNode = insnNode.getPrevious();
            }
            if(num < 1) {
                return insnNode;
            }
            if(insnNode == null) {
                return null;
            }
            insnNode = insnNode.getPrevious();
            --num;
        }
    }

    public static AbstractInsnNode getLast(InsnList insnList, int opcode) {
        return getLast(insnList, opcode, 0);
    }

    public static byte[] write(ClassNode classNode, int flags) {
        ClassWriter classWriter = new ClassWriter(flags);
        classNode.accept(classWriter);
        return classWriter.toByteArray();
    }

    public static byte[] write(ClassNode classNode) {
        return write(classNode, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }
}
