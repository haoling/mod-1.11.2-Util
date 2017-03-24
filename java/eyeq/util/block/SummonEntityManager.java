package eyeq.util.block;

import eyeq.util.block.state.pattern.IBlockPattern;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;
import java.util.function.Function;

public class SummonEntityManager {
    private final Map<Block, List<IBlockPattern>> patternMap = new HashMap<>();
    private final Map<IBlockPattern, Function<World, Entity>> summonMap = new HashMap<>();
    private final Map<IBlockPattern, EnumParticleTypes> particleMap = new HashMap<>();

    public void register(IBlockPattern pattern, Function<World, Entity> summon, EnumParticleTypes particle) {
        Block block = pattern.getKey();
        List<IBlockPattern> list = patternMap.get(block);
        if(list == null) {
            list = new ArrayList<>();
            patternMap.put(block, list);
        }
        list.add(pattern);
        summonMap.put(pattern, summon);
        particleMap.put(pattern, particle);
    }

    public boolean trySummon(World world, BlockPos pos, IBlockState state) {
        List<IBlockPattern> list = patternMap.get(state.getBlock());
        if(list == null) {
            return false;
        }
        for(IBlockPattern pattern : list) {
            if(!pattern.isKey(state)) {
                continue;
            }
            BlockPattern.PatternHelper patternHelper = pattern.match(world, pos);
            if(patternHelper == null) {
                continue;
            }
            summon(world, pattern, patternHelper);
            return true;
        }
        return false;
    }

    private void summon(World world, IBlockPattern pattern, BlockPattern.PatternHelper patternHelper) {
        int xLength = pattern.getPalmLength();
        int yLength = pattern.getThumbLength();
        int zLength = pattern.getFingerLength();
        for(int z = 0; z < zLength; z++) {
            for(int y = 0; y < yLength; y++) {
                for(int x = 0; x <xLength; x++) {
                    BlockPos pos = patternHelper.translateOffset(x, y, z).getPos();
                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                    world.notifyNeighborsRespectDebug(pos, Blocks.AIR, false);
                }
            }
        }
        Entity entity = summonMap.get(pattern).apply(world);
        BlockPos pos = patternHelper.translateOffset(xLength / 2, yLength - 1, zLength / 2).getPos();
        entity.setLocationAndAngles( pos.getX() + 0.5,  pos.getY() + 0.05,  pos.getZ() + 0.5, 0.0F, 0.0F);
        world.spawnEntity(entity);

        EnumParticleTypes particle = particleMap.get(pattern);
        if(particle != null) {
            Random rand = world.rand;
            double height = entity.height + entity.width * 0.6 / 0.7;
            for(int j = 0; j < 120; ++j) {
                world.spawnParticle(particle, pos.getX() + rand.nextDouble(), pos.getY() + rand.nextDouble() * height, pos.getZ() + rand.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }
}
