package eyeq.util.block.portal;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// BlockPortal.Size
public class UPortalSize {
    public final World world;
    public final BlockPos bottomLeft;
    public final Axis axis;
    public final EnumFacing rightDir;
    public final EnumFacing leftDir;
    public final IPortalPattern portalPattern;
    public final int width;
    public final int height;
    public final int portalBlockCount;
    private int count;

    public UPortalSize(World world, BlockPos pos, Axis axis, IPortalPattern portalPattern) {
        this.world = world;
        this.axis = axis;
        if(axis == EnumFacing.Axis.X) {
            this.leftDir = EnumFacing.EAST;
            this.rightDir = EnumFacing.WEST;
        } else {
            this.leftDir = EnumFacing.NORTH;
            this.rightDir = EnumFacing.SOUTH;
        }
        this.portalPattern = portalPattern;
        for(BlockPos pos1 = pos; pos.getY() > pos1.getY() - 21 && pos.getY() > 0 && isEmptyBlock(world.getBlockState(pos.down())); pos = pos.down())
            ;
        int i  = getPortalWidthBottom(pos, this.leftDir) - 1;
        if(i < 0) {
            this.bottomLeft = null;
            this.width = 0;
            this.height = 0;
            this.portalBlockCount = 0;
            return;
        }
        this.bottomLeft = pos.offset(this.leftDir, i);
        int w = getPortalWidthBottom(this.bottomLeft, this.rightDir);
        int h;
        if(w < 2 || w > 21) {
            w = 0;
            h = 0;
        } else {
            h = getPortalHeight(w);
            if(h < 2 || h > 21 || !isPortalFrameTop(w, h)) {
                w = 0;
                h = 0;
            }
        }
        this.width = w;
        this.height = h;
        this.portalBlockCount = this.count;
    }

    protected boolean isKey(IBlockState state) {
        return portalPattern.isKey(state);
    }

    protected boolean isFrame(IBlockState state) {
        return portalPattern.isFrame(state);
    }

    protected boolean isPortal(IBlockState state) {
        return portalPattern.isPortal(state);
    }

    protected IBlockState getPortal(EnumFacing.Axis axis) {
        return portalPattern.getPortal(axis);
    }

    protected boolean isEmptyBlock(IBlockState state) {
        return state.getMaterial() == Material.AIR || isKey(state)|| isPortal(state);
    }

    protected int getPortalWidthBottom(BlockPos pos, EnumFacing facing) {
        int i;
        for(i = 0; i < 22; i++, pos = pos.offset(facing)) {
            if(!isEmptyBlock(this.world.getBlockState(pos)) || !isFrame(this.world.getBlockState(pos.down()))) {
                break;
            }
        }
        return isFrame(this.world.getBlockState(pos)) ? i : 0;
    }

    protected int getPortalHeight(int width) {
        this.count = 0;
        for(int y = 0; y < 21; y++) {
            BlockPos pos = this.bottomLeft.up(y);
            if(!isFrame(this.world.getBlockState(pos.offset(this.leftDir)))) {
                return y;
            }
            for(int x = 0; x < width; x++, pos = pos.offset(this.rightDir)) {
                IBlockState state = this.world.getBlockState(pos);
                if(!this.isEmptyBlock(state)) {
                    return y;
                }
                if(isPortal(state)) {
                    this.count++;
                }
            }
            if(!isFrame(this.world.getBlockState(pos))) {
                return y;
            }
        }
        return 21;
    }

    protected boolean isPortalFrameTop(int width, int height) {
        BlockPos pos = this.bottomLeft.up(height);
        for(int i = 0; i < width; ++i, pos = pos.offset(this.rightDir)) {
            if(!isFrame(world.getBlockState(pos))) {
                return false;
            }
        }
        return true;
    }

    public void placePortalBlocks() {
        for(int x = 0; x < this.width; x++) {
            BlockPos pos = this.bottomLeft.offset(this.rightDir, x);
            for(int y = 0; y < this.height; y++, pos = pos.up()) {
                this.world.setBlockState(pos, getPortal(this.axis), 2);
            }
        }
    }

    public boolean isValid() {
        return this.width != 0 && this.height != 0;
    }

    public boolean isFull() {
        return portalBlockCount == width * height;
    }
}
