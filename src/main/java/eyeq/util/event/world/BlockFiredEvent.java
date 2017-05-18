package eyeq.util.event.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class BlockFiredEvent extends BlockEvent {
    public BlockFiredEvent(World world, BlockPos pos, IBlockState state) {
        super(world, pos, state);
    }
}
