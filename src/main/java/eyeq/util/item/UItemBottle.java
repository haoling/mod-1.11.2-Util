package eyeq.util.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class UItemBottle extends UItemJuice {
    public UItemBottle() {
        this(0, 0.0F, false);
    }

    public UItemBottle(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setAlwaysEdible();
        this.setCreativeTab(CreativeTabs.MISC);
        this.setContainerItem(Items.GLASS_BOTTLE);
        this.setRestItem(new ItemStack(Items.GLASS_BOTTLE));
    }
}
