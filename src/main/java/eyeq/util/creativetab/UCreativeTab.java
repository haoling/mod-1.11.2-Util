package eyeq.util.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UCreativeTab extends CreativeTabs {
    @FunctionalInterface
    public interface ITabIconItem {
        @SideOnly(Side.CLIENT)
        ItemStack getTabIconItem();
    }

    public final ITabIconItem getTabIconItem;

    public UCreativeTab(String label, ITabIconItem getTabIconItem) {
        super(label);
        this.getTabIconItem = getTabIconItem;
    }

    public UCreativeTab(int index, String label, ITabIconItem getTabIconItem) {
        super(index, label);
        this.getTabIconItem = getTabIconItem;
    }

    @Override
    public ItemStack getTabIconItem() {
        return getTabIconItem.getTabIconItem();
    }
}
