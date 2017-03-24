package eyeq.util.block.portal;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public interface IPortalPattern {
    boolean isKey(IBlockState state);

    boolean isFrame(IBlockState state);

    boolean isPortal(IBlockState state);

    Block getKey();

    IBlockState getFrame();

    IBlockState getPortal(EnumFacing.Axis axis);
}
