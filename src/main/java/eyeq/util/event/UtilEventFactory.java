package eyeq.util.event;

import eyeq.util.common.Utils;
import eyeq.util.event.world.BlockDoorOpenedEvent;
import eyeq.util.event.world.BlockFiredEvent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UtilEventFactory {
    public static final String THIS_CLASS_NAME = "eyeq/util/event/UtilEventFactory";

    public static void onBlockDoorOpenedEvent(World world, BlockPos pos, IBlockState state, Block block) {
        Utils.EVENT_BUS.post(new BlockDoorOpenedEvent(world, pos, state, block));
    }

    public static void onBlockFiredEvent(World world, BlockPos pos, IBlockState state) {
        Utils.EVENT_BUS.post(new BlockFiredEvent(world, pos, state));
    }
}
