package eyeq.util.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IItemSpawnEntity {
    public Entity spawnEntity(ItemStack itemStack, World world, double x, double y, double z);
}
