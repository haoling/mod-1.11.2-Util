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

public class NeighborChangedMethodTransformer extends MethodTransformer implements Opcodes {
    public static final String[] TARGET_METHOD_NAME = {"neighborChanged", "func_189540_a"};
    public static final String TARGET_METHOD_DESC = ClassNames.toDesc(void.class, ClassNames.DESC_I_BLOCK_STATE, ClassNames.DESC_WORLD, ClassNames.DESC_BLOCK_POS, ClassNames.DESC_BLOCK, ClassNames.DESC_BLOCK_POS);

    public NeighborChangedMethodTransformer(String targetClass) {
        super(targetClass, TARGET_METHOD_NAME, TARGET_METHOD_DESC);
    }

    @Override
    public void transform(MethodNode methodNode) {
        // BlockDoor.neighborChanged()
        // BlockTrapDoor.neighborChanged()
        // BlockFenceGate.neighborChanged()
        InsnList overrideList = new InsnList();
        overrideList.add(new VarInsnNode(ALOAD, 2));
        overrideList.add(new VarInsnNode(ALOAD, 3));
        overrideList.add(new VarInsnNode(ALOAD, 1));
        overrideList.add(new VarInsnNode(ALOAD, 0));
        overrideList.add(new MethodInsnNode(INVOKESTATIC, UtilEventFactory.THIS_CLASS_NAME, "onBlockDoorOpenedEvent",
                ClassNames.toDesc(void.class, ClassNames.DESC_WORLD, ClassNames.DESC_BLOCK_POS, ClassNames.DESC_I_BLOCK_STATE, ClassNames.DESC_BLOCK)));

        InsnList insnList = methodNode.instructions;
        insnList.insert(NodeUtils.getLast(insnList, INVOKEVIRTUAL), overrideList);
    }
}
