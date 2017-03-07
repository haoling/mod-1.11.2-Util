package eyeq.util.entity;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.Iterator;

public class EntityUtils {
    public static ItemStack getArmor(Entity entity, EntityEquipmentSlot slot) {
        int index = slot.getIndex();
        Iterator<ItemStack> iterator = entity.getArmorInventoryList().iterator();
        while(iterator.hasNext()) {
            ItemStack itemStack = iterator.next();
            if(index <= 0) {
                return itemStack;
            }
            index--;
        }
        return ItemStack.EMPTY;
    }
}
