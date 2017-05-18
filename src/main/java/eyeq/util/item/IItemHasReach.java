package eyeq.util.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IItemHasReach {
    float getReach();
    float getReach(World world, EntityLivingBase entity, ItemStack itemStack);
}
