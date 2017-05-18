package eyeq.util.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ShapedAmountRecipes extends ShapedRecipes {
    public ShapedAmountRecipes(int width, int height, ItemStack[] inputs, ItemStack output) {
        super(width, height, inputs, output);
    }

    @Override
    public boolean matches(InventoryCrafting inputs, World world) {
        for(int i = 0; i <= 3 - this.recipeWidth; i++) {
            for(int j = 0; j <= 3 - this.recipeHeight; j++) {
                if(this.checkMatch(inputs, i, j, true) || this.checkMatch(inputs, i, j, false)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean checkMatch(InventoryCrafting inputs, int left, int up, boolean isMirror) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                ItemStack input = inputs.getStackInRowAndColumn(i, j);

                ItemStack recipe = ItemStack.EMPTY;
                int x = i - left;
                int y = j - up;
                if(x >= 0 && y >= 0 && x < this.recipeWidth && y < this.recipeHeight) {
                    recipe = this.recipeItems[(isMirror ? this.recipeWidth - x - 1 : x) + y * this.recipeWidth];
                }

                if(recipe.getItem() != input.getItem()) {
                    return false;
                }
                if(recipe.getCount() != input.getCount()) {
                    return false;
                }
                if(recipe.getMetadata() != OreDictionary.WILDCARD_VALUE && recipe.getMetadata() != input.getMetadata()) {
                    return false;
                }
            }
        }
        return true;
    }
}
