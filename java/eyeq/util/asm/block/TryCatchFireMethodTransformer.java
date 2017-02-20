package eyeq.util.asm.block;

import eyeq.util.asm.ClassNames;
import eyeq.util.asm.MethodTransformer;
import eyeq.util.asm.NodeUtils;
import eyeq.util.event.UtilEventFactory;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class TryCatchFireMethodTransformer extends MethodTransformer implements Opcodes {
    public static final String TARGET_CLASS_NAME = "net.minecraft.block.BlockFire";
    public static final String[] TARGET_METHOD_NAME = {"tryCatchFire"};
    public static final String TARGET_METHOD_DESC = ClassNames.toDesc(void.class, ClassNames.DESC_WORLD, ClassNames.DESC_BLOCK_POS, int.class, ClassNames.DESC_RANDOM, int.class, ClassNames.DESC_ENUM_FACING);

    public TryCatchFireMethodTransformer() {
        super(TARGET_CLASS_NAME, TARGET_METHOD_NAME, TARGET_METHOD_DESC);
    }

    @Override
    public void transform(MethodNode methodNode) {
        // BlockFire.tryCatchFire()
        InsnList overrideList = new InsnList();
        overrideList.add(new VarInsnNode(ALOAD, 1));
        overrideList.add(new VarInsnNode(ALOAD, 2));
        overrideList.add(new VarInsnNode(ALOAD, 8));
        overrideList.add(new MethodInsnNode(INVOKESTATIC, UtilEventFactory.THIS_CLASS_NAME, "onBlockFiredEvent",
                ClassNames.toDesc(void.class, ClassNames.DESC_WORLD, ClassNames.DESC_BLOCK_POS, ClassNames.DESC_I_BLOCK_STATE)));

        InsnList insnList = methodNode.instructions;
        insnList.insertBefore(NodeUtils.getLast(insnList, IF_ACMPNE), overrideList);
    }
}
