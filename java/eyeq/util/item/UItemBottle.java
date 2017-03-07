package eyeq.util.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class UItemBottle extends UItemJuice {
    public UItemBottle() {
        super(0, 0.0F, false);
        this.setAlwaysEdible();
        this.setCreativeTab(CreativeTabs.MISC);
        this.setContainerItem(Items.GLASS_BOTTLE);
        this.setRestItem(new ItemStack(Items.GLASS_BOTTLE));
    }
}
