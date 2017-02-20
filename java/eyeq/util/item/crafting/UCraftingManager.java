package eyeq.util.item.crafting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import eyeq.util.oredict.UOreDictionary;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.List;
import java.util.Map;

public class UCraftingManager {
    static {
        RecipeSorter.register("eyeq_util:shaped_amount", ShapedAmountRecipes.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
    }

    public static ShapedOreRecipe getRecipeHelmet(ItemStack output, Object material) {
        return new ShapedOreRecipe(output, "XXX", "X X",
                'X', material);
    }

    public static ShapedOreRecipe getRecipeChestPlate(ItemStack output, Object material) {
        return new ShapedOreRecipe(output, "X X", "XXX", "XXX",
                'X', material);
    }

    public static ShapedOreRecipe getRecipeLeggings(ItemStack output, Object material) {
        return new ShapedOreRecipe(output, "XXX", "X X", "X X",
                'X', material);
    }

    public static ShapedOreRecipe getRecipeBoots(ItemStack output, Object material) {
        return new ShapedOreRecipe(output, "X X", "X X",
                'X', material);
    }

    public static ShapedOreRecipe getRecipeAxe(ItemStack output, Object material, Object stick) {
        return new ShapedOreRecipe(output, "XX", "XY", " Y",
                'X', material, 'Y', stick);
    }

    public static ShapedOreRecipe getRecipePickaxe(ItemStack output, Object material, Object stick) {
        return new ShapedOreRecipe(output, "XXX", " Y ", " Y ",
                'X', material, 'Y', stick);
    }

    public static ShapedOreRecipe getRecipeSpade(ItemStack output, Object material, Object stick) {
        return new ShapedOreRecipe(output, "X", "Y", "Y",
                'X', material, 'Y', stick);
    }

    public static ShapedOreRecipe getRecipeHoe(ItemStack output, Object material, Object stick) {
        return new ShapedOreRecipe(output, "XX", " Y", " Y",
                'X', material, 'Y', stick);
    }

    public static ShapedOreRecipe getRecipeSword(ItemStack output, Object material, Object stick) {
        return new ShapedOreRecipe(output, "X", "X", "Y",
                'X', material, 'Y', stick);
    }

    public static ShapedOreRecipe getRecipeAxe(ItemStack output, Object material) {
        return getRecipeAxe(output, material, UOreDictionary.OREDICT_STICK);
    }

    public static ShapedOreRecipe getRecipePickaxe(ItemStack output, Object material) {
        return getRecipePickaxe(output, material, UOreDictionary.OREDICT_STICK);
    }

    public static ShapedOreRecipe getRecipeSpade(ItemStack output, Object material) {
        return getRecipeSpade(output, material, UOreDictionary.OREDICT_STICK);
    }

    public static ShapedOreRecipe getRecipeHoe(ItemStack output, Object material) {
        return getRecipeHoe(output, material, UOreDictionary.OREDICT_STICK);
    }

    public static ShapedOreRecipe getRecipeSword(ItemStack output, Object material) {
        return getRecipeSword(output, material, UOreDictionary.OREDICT_STICK);
    }

    public static ShapedRecipes getRecipe(ItemStack stack, Object... recipeComponents) {
        ShapedRecipes recipe = CraftingManager.getInstance().addRecipe(stack, recipeComponents);
        CraftingManager.getInstance().getRecipeList().remove(recipe);
        return recipe;
    }

    public static ShapelessRecipes getShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        List list = Lists.newArrayList();
        for(Object obj : recipeComponents) {
            ItemStack input = null;
            if(obj instanceof ItemStack) {
                input = ((ItemStack) obj).copy();
            } else if(obj instanceof Item) {
                input = new ItemStack((Item) obj);
            } else if(obj instanceof Block) {
                input = new ItemStack((Block) obj);
            }
            if(input == null) {
                throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + obj.getClass().getName() + "!");
            } else {
                list.add(input);
            }
        }
        return new ShapelessRecipes(stack, list);
    }

    public static ShapedAmountRecipes getAmountRecipe(ItemStack output, Object... recipeComponents) {
        String s = "";
        int i = 0;
        int width = 0;
        int height;

        if(recipeComponents[i] instanceof String[]) {
            String[] strs = (String[]) recipeComponents[i++];
            for(String str : strs) {
                width = str.length();
                s += str;
            }
            height = strs.length;
        } else {
            while(recipeComponents[i] instanceof String) {
                String str = (String) recipeComponents[i++];
                width = str.length();
                s += str;
            }
            height = i;
        }

        Map map = Maps.newHashMap();
        for(; i < recipeComponents.length; i++) {
            Character character = (Character) recipeComponents[i++];
            ItemStack input = null;
            if(recipeComponents[i] instanceof Item) {
                input = new ItemStack((Item) recipeComponents[i]);
            } else if(recipeComponents[i] instanceof Block) {
                input = new ItemStack((Block) recipeComponents[i], 1, OreDictionary.WILDCARD_VALUE);
            } else if(recipeComponents[i] instanceof ItemStack) {
                input = (ItemStack) recipeComponents[i];
            }
            map.put(character, input);
        }

        ItemStack[] inputs = new ItemStack[width * height];
        for(int j = 0; j < width * height; j++) {
            char c0 = s.charAt(j);
            if(map.containsKey(c0)) {
                inputs[j] = ((ItemStack) map.get(c0)).copy();
            } else {
                inputs[j] = null;
            }
        }
        return new ShapedAmountRecipes(width, height, inputs, output);
    }

    public static ShapedAmountRecipes addAmountRecipe(ItemStack output, Object... recipeComponents) {
        ShapedAmountRecipes recipes = getAmountRecipe(output, recipeComponents);
        GameRegistry.addRecipe(recipes);
        return recipes;
    }

    public static boolean removeRecipe(ItemStack stack, Object... recipeComponents) {
        ShapedRecipes source = getRecipe(stack, recipeComponents);
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        for(IRecipe obj : recipes) {
            if(!(obj instanceof ShapedRecipes)) {
                continue;
            }
            ShapedRecipes recipe = (ShapedRecipes) obj;
            if(ItemStack.areItemStacksEqual(recipe.getRecipeOutput(), source.getRecipeOutput()) && areItemStacksEquals(recipe.recipeItems, source.recipeItems)) {
                recipes.remove(recipe);
                return true;
            }
        }
        return false;
    }

    public static boolean removeShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        ShapelessRecipes source = getShapelessRecipe(stack, recipeComponents);
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        for(IRecipe obj : recipes) {
            if(!(obj instanceof ShapelessRecipes)) {
                continue;
            }
            ShapelessRecipes recipe = (ShapelessRecipes) obj;
            if(ItemStack.areItemStacksEqual(recipe.getRecipeOutput(), source.getRecipeOutput()) && areItemStacksEquals(recipe.recipeItems.toArray(new ItemStack[recipe.recipeItems.size()]), source.recipeItems.toArray(new ItemStack[source.recipeItems.size()]))) {
                recipes.remove(recipe);
                return true;
            }
        }
        return false;
    }

    public static boolean areItemStacksEquals(ItemStack[] stackA, ItemStack[] stackB) {
        int length = stackA.length;
        if(length != stackB.length) {
            return false;
        }
        for(int i = 0; i < length; i++) {
            if(!ItemStack.areItemStacksEqual(stackA[i], stackB[i])) {
                return false;
            }
        }
        return true;
    }
}
