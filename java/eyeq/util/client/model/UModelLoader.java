package eyeq.util.client.model;

import eyeq.util.client.renderer.ResourceLocationFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class UModelLoader {
    public static void setCustomModelResourceLocation(Item item) {
        setCustomModelResourceLocation(item, 0);
    }

    public static void setCustomModelResourceLocation(Item item, int metadata) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, ResourceLocationFactory.createModelResourceLocation(item));
    }

    public static void setCustomModelResourceLocation(Block block) {
        setCustomModelResourceLocation(block, 0);
    }

    public static void setCustomModelResourceLocation(Block block, int metadata) {
        setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata);
    }
}
