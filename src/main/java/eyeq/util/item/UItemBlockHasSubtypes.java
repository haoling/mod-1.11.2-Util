package eyeq.util.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class UItemBlockHasSubtypes extends ItemBlock {
    public UItemBlockHasSubtypes(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
