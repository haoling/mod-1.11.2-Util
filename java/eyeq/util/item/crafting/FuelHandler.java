package eyeq.util.item.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.oredict.OreDictionary;

public class FuelHandler implements IFuelHandler {
    protected final Item fuel;
    protected int meta;
    protected final int burnTime;

    public FuelHandler(Item fuel, int meta, int burnTime) {
        this.fuel = fuel;
        this.meta = meta;
        this.burnTime = burnTime;
    }

    public FuelHandler(Block fuel, int meta, int burnTime) {
        this(Item.getItemFromBlock(fuel), meta, burnTime);
    }

    public FuelHandler(Item fuel, int burnTime) {
        this(fuel, OreDictionary.WILDCARD_VALUE, burnTime);
    }

    public FuelHandler(Block fuel, int burnTime) {
        this(Item.getItemFromBlock(fuel), burnTime);
    }

    @Override
    public int getBurnTime(ItemStack input) {
        if(input.getItem() == fuel && (meta == OreDictionary.WILDCARD_VALUE || meta == input.getMetadata())) {
            return burnTime;
        }
        return 0;
    }
}
