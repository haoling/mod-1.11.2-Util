package eyeq.util.block.state.pattern;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockPattern {
    int getFingerLength();

    int getThumbLength();

    int getPalmLength();

    boolean isKey(IBlockState state);

    Block getKey();

    BlockPattern.PatternHelper match(World world, BlockPos pos);
}
