package eyeq.util.world.gen;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenOre implements IWorldGenerator {
    private final int count;
    private final WorldGenerator generator;
    private final int minHeight;
    private final int maxHeight;

    public WorldGenOre(int count, WorldGenerator generator, int minHeight, int maxHeight) {
        this.count = count;
        this.generator = generator;

        if(maxHeight < minHeight) {
            int temp = minHeight;
            minHeight = maxHeight;
            maxHeight = temp;
        } else if(maxHeight == minHeight) {
            if(minHeight >= 255) {
                minHeight--;
            } else {
                maxHeight++;
            }
        }
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    // BiomeDecorator ## protected void genStandardOre1
    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        BlockPos pos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
        for(int i = 0; i < count; i++) {
            int x = rand.nextInt(16);
            int z = rand.nextInt(16);
            int y = rand.nextInt(maxHeight - minHeight) + minHeight;
            generator.generate(world, rand, pos.add(x, y, z));
        }
    }
}
