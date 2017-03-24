package eyeq.util.block.portal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class UPortalPattern implements IPortalPattern{
    private final Block key;
    private final Block frame;
    private final Block portal;

    public UPortalPattern(Block key, Block frame, Block portal) {
        this.key = key;
        this.frame = frame;
        this.portal = portal;
    }

    @Override
    public boolean isKey(IBlockState state) {
        return state.getBlock() == this.key;
    }

    @Override
    public boolean isFrame(IBlockState state) {
        return state.getBlock() == this.frame;
    }

    @Override
    public boolean isPortal(IBlockState state) {
        return state.getBlock() == this.portal;
    }

    @Override
    public Block getKey() {
        return this.key;
    }

    @Override
    public IBlockState getFrame() {
        return this.frame.getDefaultState();
    }

    @Override
    public IBlockState getPortal(EnumFacing.Axis axis) {
        IBlockState state = this.portal.getDefaultState();
        if(state.getPropertyKeys().contains(BlockPortal.AXIS)) {
            state = state.withProperty(BlockPortal.AXIS, axis);
        }
        return state;
    }
}
