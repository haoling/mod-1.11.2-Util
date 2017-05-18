package eyeq.util.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class UWorldProvider extends WorldProvider {
    public abstract WorldType getTerrainType();

    @Override
    protected void init() {
        this.hasSkyLight = true;
        this.biomeProvider = getTerrainType().getBiomeProvider(this.world);
    }

    protected String getGeneretorOptions() {
        return this.world.getWorldInfo().getGeneratorOptions();
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return getTerrainType().getChunkGenerator(this.world, getGeneretorOptions());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return getTerrainType().getCloudHeight();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getVoidFogYFactor() {
        return getTerrainType().voidFadeMagnitude();
    }

    @Override
    public int getAverageGroundLevel() {
        return getTerrainType().getMinimumSpawnHeight(this.world);
    }

    @Override
    public String getWelcomeMessage() {
        return "Entering the " + getDimensionType().getName();
    }

    @Override
    public String getDepartMessage() {
        return "Leaving the " + getDimensionType().getName();
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }
}
