package eyeq.util.item;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IItemEnchanted {
    IItemEnchanted setEnchantmentData(EnchantmentData enchant);

    IItemEnchanted addEnchantmentData(EnchantmentData enchant);

    static void updateEnchantment(ItemStack itemStack, List<EnchantmentData> enchants) {
        if(itemStack.isItemEnchanted()) {
            return;
        }
        for(EnchantmentData enchant : enchants) {
            itemStack.addEnchantment(enchant.enchantmentobj, enchant.enchantmentLevel);
        }
    }
}
