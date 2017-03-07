package eyeq.util.item;

import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class UItemJuice extends UItemFood {
    public UItemJuice(int amount, boolean isWolfFood) {
        super(amount, isWolfFood);
    }

    public UItemJuice(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.DRINK;
    }
}
