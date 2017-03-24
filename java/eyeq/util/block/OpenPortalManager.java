package eyeq.util.block;

import eyeq.util.block.portal.IPortalPattern;
import eyeq.util.block.portal.UPortalSize;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPortalManager {
    public Map<Block, List<IPortalPattern>> patternMap = new HashMap<>();

    public void register(IPortalPattern pattern) {
        Block block = pattern.getKey();
        List<IPortalPattern> list = patternMap.get(block);
        if(list == null) {
            list = new ArrayList<>();
            patternMap.put(block, list);
        }
        list.add(pattern);
    }

    public boolean tryOpen(World world, BlockPos pos, IBlockState state) {
        List<IPortalPattern> list = patternMap.get(state.getBlock());
        if(list == null) {
            return false;
        }
        for(IPortalPattern pattern : list) {
            if(!pattern.isKey(state)) {
                continue;
            }
            UPortalSize size = new UPortalSize(world, pos, EnumFacing.Axis.X, pattern);
            if(size.isValid() && size.portalBlockCount == 0) {
                size.placePortalBlocks();
                return true;
            }
            size = new UPortalSize(world, pos, EnumFacing.Axis.Z, pattern);
            if(size.isValid() && size.portalBlockCount == 0) {
                size.placePortalBlocks();
                return true;
            }
        }
        return false;
    }
}
