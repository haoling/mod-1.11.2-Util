package eyeq.util.world;

import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldUtils {
    public static void playSound(World world, Entity entity, SoundEvent sound, Random rand) {
        playSound(world, entity, sound, entity.getSoundCategory(), rand);
    }

    public static void playSound(World world, Entity entity, SoundEvent sound, SoundCategory category, Random rand) {
        world.playSound(null, entity.posX, entity.posY, entity.posZ, sound, category, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
    }

    public static void playSoundBlocks(World world, EntityPlayer player, BlockPos pos, SoundType type) {
        world.playSound(player, pos, type.getPlaceSound(), SoundCategory.BLOCKS, (type.getVolume() + 1.0F) / 2.0F, type.getPitch() * 0.8F);
    }
}
