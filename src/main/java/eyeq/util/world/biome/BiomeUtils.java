package eyeq.util.world.biome;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class BiomeUtils {
    public static boolean isSpawn(Biome biome, Class<? extends EntityLiving> entityClass, EnumCreatureType creatureType) {
        return biome.getSpawnableList(creatureType).contains(entityClass);
    }

    public static List<Biome> getSpawnBiomes(Class<? extends EntityLiving> entityClass, EnumCreatureType creatureType) {
        List<Biome> list = new ArrayList<>();
        for(Biome biome : Biome.REGISTRY) {
            if(biome == null) {
                continue;
            }
            if(isSpawn(biome, entityClass, creatureType)) {
                list.add(biome);
            }
        }
        return list;
    }

    public static List<Biome> getBiomes() {
        List<Biome> list = new ArrayList<>();
        for(Biome biome : Biome.REGISTRY) {
            if(biome == null) {
                continue;
            }
            list.add(biome);
        }
        return list;
    }
}
