package eyeq.util.block.state.pattern;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UBlockPattern implements IBlockPattern {
    private final BlockPattern pattern;
    private final Block key;
    private final IBlockState keyState;

    private UBlockPattern(BlockPattern pattern, Block key, IBlockState keyState) {
        this.pattern = pattern;
        this.key = key;
        this.keyState = keyState;
    }

    public UBlockPattern(BlockPattern pattern, Block key) {
        this(pattern, key, null);
    }

    public UBlockPattern(BlockPattern pattern, IBlockState key) {
        this(pattern, key.getBlock(), key);
    }

    @Override
    public boolean isKey(IBlockState state) {
        if(keyState != null && !keyState.getProperties().equals(state.getProperties())) {
            return false;
        }
        return key == state.getBlock();
    }

    @Override
    public Block getKey() {
        return key;
    }

    @Override
    public int getFingerLength() {
        return pattern.getFingerLength();
    }

    @Override
    public int getThumbLength() {
        return pattern.getThumbLength();
    }

    @Override
    public int getPalmLength() {
        return pattern.getPalmLength();
    }

    @Override
    public BlockPattern.PatternHelper match(World world, BlockPos pos) {
        return pattern.match(world, pos);
    }
}
