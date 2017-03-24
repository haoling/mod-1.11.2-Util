package eyeq.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class UBlockDrops extends Block {
    private final ItemStack drops;

    public UBlockDrops(Material material, MapColor mapColor, ItemStack drops) {
        super(material, mapColor);
        this.drops = drops;
    }

    public UBlockDrops(Material material, ItemStack drops) {
        super(material);
        this.drops = drops;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return drops.getItem();
    }

    @Override
    public int quantityDropped(Random random) {
        return drops.getCount();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return drops.getMetadata();
    }
}
