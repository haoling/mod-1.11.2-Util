package eyeq.util.event.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class BlockDoorOpenedEvent extends BlockEvent {
    public Block block;

    public BlockDoorOpenedEvent(World world, BlockPos pos, IBlockState state, Block block) {
        super(world, pos, state);
        this.block = block;
    }
}
