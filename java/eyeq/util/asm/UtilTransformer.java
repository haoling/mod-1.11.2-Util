package eyeq.util.asm;

import eyeq.util.asm.block.NeighborChangedMethodTransformer;
import eyeq.util.asm.block.OnBlockActivatedMethodTransformer;
import eyeq.util.asm.block.ToggleDoorMethodTransformer;
import eyeq.util.asm.block.TryCatchFireMethodTransformer;
import net.minecraft.launchwrapper.IClassTransformer;

public class UtilTransformer implements IClassTransformer {
    public static final String BLOCK_DOOR_CLASS_NAME = "net.minecraft.block.BlockDoor";
    public static final String BLOCK_TRAP_DOOR_CLASS_NAME = "net.minecraft.block.BlockTrapDoor";
    public static final String BLOCK_FENCE_GATE_CLASS_NAME = "net.minecraft.block.BlockFenceGate";

    public final NeighborChangedMethodTransformer blockDoorNeighborChanged = new NeighborChangedMethodTransformer(BLOCK_DOOR_CLASS_NAME);
    public final NeighborChangedMethodTransformer blockTrapDoorNeighborChanged = new NeighborChangedMethodTransformer(BLOCK_TRAP_DOOR_CLASS_NAME);
    public final NeighborChangedMethodTransformer blockFenceGateNeighborChanged = new NeighborChangedMethodTransformer(BLOCK_FENCE_GATE_CLASS_NAME);

    public final OnBlockActivatedMethodTransformer onBlockDoorActivated = new OnBlockActivatedMethodTransformer(BLOCK_DOOR_CLASS_NAME);
    public final OnBlockActivatedMethodTransformer onBlockTrapDoorActivated = new OnBlockActivatedMethodTransformer(BLOCK_TRAP_DOOR_CLASS_NAME);
    public final OnBlockActivatedMethodTransformer onBlockFenceGateActivated = new OnBlockActivatedMethodTransformer(BLOCK_FENCE_GATE_CLASS_NAME);

    public final ToggleDoorMethodTransformer toggleDoor = new ToggleDoorMethodTransformer();

    public final TryCatchFireMethodTransformer tryCatchFire = new TryCatchFireMethodTransformer();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        basicClass = NodeUtils.transform(name, transformedName, basicClass, blockDoorNeighborChanged);
        basicClass = NodeUtils.transform(name, transformedName, basicClass, blockTrapDoorNeighborChanged);
        basicClass = NodeUtils.transform(name, transformedName, basicClass, blockFenceGateNeighborChanged);

        basicClass = NodeUtils.transform(name, transformedName, basicClass, onBlockDoorActivated);
        basicClass = NodeUtils.transform(name, transformedName, basicClass, onBlockTrapDoorActivated);
        basicClass = NodeUtils.transform(name, transformedName, basicClass, onBlockFenceGateActivated);

        basicClass = NodeUtils.transform(name, transformedName, basicClass, toggleDoor);

        basicClass = NodeUtils.transform(name, transformedName, basicClass, tryCatchFire);
        return basicClass;
    }
}
