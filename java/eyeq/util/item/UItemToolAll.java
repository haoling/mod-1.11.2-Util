package eyeq.util.item;

import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class UItemToolAll extends UItemTool {
    protected float strVsBlock;

    public UItemToolAll(float damage, float speed, ToolMaterial material, float strVsBlock) {
        super(damage, speed, material, new HashSet<>());
        this.strVsBlock = strVsBlock;
    }

    public UItemToolAll(ToolMaterial material, float strVsBlock) {
        super(material, new HashSet<>());
        this.strVsBlock = strVsBlock;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return true;
    }

    @Override
    public float getStrVsBlock(ItemStack itemStack, IBlockState state) {
        return strVsBlock;
    }

    @Override
    public Set<String> getToolClasses(ItemStack itemStack) {
        return Sets.newHashSet("pickaxe", "axe", "shovel");
    }
}
