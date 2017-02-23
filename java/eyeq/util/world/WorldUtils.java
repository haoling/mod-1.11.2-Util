package eyeq.util.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.Random;

public class WorldUtils {
    public static void playSound(World world, Entity entity, SoundEvent sound, SoundCategory category, Random rand) {
        world.playSound(null, entity.posX, entity.posY, entity.posZ, sound, category, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
    }
}
