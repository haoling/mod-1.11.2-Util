package eyeq.util.world.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public abstract class WorldGenDecorations implements IWorldGenerator {
    private final float probability;

    public WorldGenDecorations(float probability) {
        this.probability = probability;
    }

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(rand.nextFloat() < probability) {
            BlockPos pos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y = world.getHeight(pos.add(x, 0, z)).getY() * 2;
            if(y > 0) {
                y = rand.nextInt(y);
                generate(world, rand, pos.add(x, y, z));
            }
        }
    }

    public abstract boolean generate(World world, Random rand, BlockPos pos);
}
