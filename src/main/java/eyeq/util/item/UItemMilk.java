package eyeq.util.item;

import net.minecraft.creativetab.CreativeTabs;

public class UItemMilk extends UItemJuice {
    public UItemMilk() {
        super(0, 0.0F, false);
        this.setAlwaysEdible();
        this.setCreativeTab(CreativeTabs.MISC);
        this.setClearPotionType(ClearPotionType.CLEAR_ALL);
    }
}
