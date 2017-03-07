package eyeq.util.item.crafting;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionType;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ShapelessPotionRecipe extends ShapelessRecipes {
    public final PotionType inputType;

    public ShapelessPotionRecipe(ItemStack output, PotionType inputType, Object... inputs) {
        super(output, new ArrayList<>());
        this.inputType = inputType;

        for(Object input : inputs) {
            ItemStack itemStack = null;
            if(input instanceof ItemStack) {
                itemStack = (ItemStack) input;
            } else if(input instanceof Item) {
                itemStack = new ItemStack((Item) input);
            } else if(input instanceof Block) {
                itemStack = new ItemStack((Block) input);
            }
            if(itemStack == null) {
                String ret = "Invalid shapeless ore recipe: ";
                for(Object tmp : inputs) {
                    ret += tmp + ", ";
                }
                ret += output;
                throw new RuntimeException(ret);
            } else {
                this.recipeItems.add(itemStack);
            }
        }
    }

    public boolean matches(InventoryCrafting inputs, World world) {
        List<ItemStack> list = Lists.newArrayList(this.recipeItems);
        boolean isPotion = false;
        String type = PotionType.REGISTRY.getNameForObject(inputType).toString();
        for(int i = 0; i < inputs.getHeight(); i++) {
            for(int j = 0; j < inputs.getWidth(); j++) {
                ItemStack input = inputs.getStackInRowAndColumn(j, i);
                if(input.isEmpty()) {
                    continue;
                }
                Item item = input.getItem();
                if(!isPotion && item == Items.POTIONITEM) {
                    NBTTagCompound nbt = input.serializeNBT();
                    if(type.equals(nbt.getString("Potion"))) {
                        continue;
                    }
                }
                boolean flag = false;
                int meta = input.getMetadata();
                for(ItemStack recipes : list) {
                    if(item == recipes.getItem() && (meta == OreDictionary.WILDCARD_VALUE || meta == recipes.getMetadata())) {
                        flag = true;
                        list.remove(recipes);
                        break;
                    }
                }
                if(!flag) {
                    return false;
                }
            }
        }
        return list.isEmpty();
    }
}
