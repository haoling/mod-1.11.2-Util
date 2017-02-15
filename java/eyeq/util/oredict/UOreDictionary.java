package eyeq.util.oredict;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UOreDictionary {
    static {
        registerOre(CategoryTypes.MILK, "bucket", Items.MILK_BUCKET);
        registerOre(CategoryTypes.PREFIX_CROP, "beetroot", Items.BEETROOT);
        registerOre(CategoryTypes.COOKED, "bread", Items.BREAD);
        registerOre(CategoryTypes.COOKED, "mushroomStew", Items.MUSHROOM_STEW);
        registerOre(CategoryTypes.COOKED, "beetrootSoup", Items.BEETROOT_SOUP);
        registerOre(CategoryTypes.COOKED, "potato", Items.BAKED_POTATO);
        registerOreAll(CategoryTypes.COOKED_FISH, "fish", Items.COOKED_FISH);
        registerOre(CategoryTypes.COOKED_MEAT, "cow", Items.COOKED_BEEF);
        registerOre(CategoryTypes.COOKED_MEAT, "pig", Items.COOKED_PORKCHOP);
        registerOre(CategoryTypes.COOKED_MEAT, "chicken", Items.COOKED_CHICKEN);
        registerOre(CategoryTypes.COOKED_MEAT, "sheep", Items.COOKED_MUTTON);
        registerOre(CategoryTypes.COOKED_MEAT, "rabbit", Items.COOKED_RABBIT);
        registerOre(CategoryTypes.SWEET, "cookie", Items.COOKIE);
        registerOre(CategoryTypes.SWEET, "pumpkinPie", Items.PUMPKIN_PIE);
        registerOre(CategoryTypes.SWEET, "cake", Items.CAKE);
        registerOreAll(CategoryTypes.FISH, "fish", Items.FISH);
    	
        registerOre(CategoryTypes.PREFIX_FOOD_GOLD, "apple", new ItemStack(Items.GOLDEN_APPLE, 1, 1));
        registerOre(CategoryTypes.PREFIX_MEAT, "cow", Items.BEEF);
        registerOre(CategoryTypes.PREFIX_MEAT, "pig", Items.PORKCHOP);
        registerOre(CategoryTypes.PREFIX_MEAT, "chicken", Items.CHICKEN);
        registerOre(CategoryTypes.PREFIX_MEAT, "sheep", Items.MUTTON);
        registerOre(CategoryTypes.PREFIX_MEAT, "rabbit", Items.RABBIT);
        registerOre(CategoryTypes.PREFIX_GRAIN, "wheat", Items.WHEAT);
        registerOre(CategoryTypes.PREFIX_GRAIN, "cocoa", new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getMetadata()));
        registerOre(CategoryTypes.PREFIX_FRUIT, "apple", Items.APPLE);
        registerOre(CategoryTypes.PREFIX_FRUIT, "chorus", Items.CHORUS_FRUIT);
        registerOre(CategoryTypes.PREFIX_VEGETABLE, "potato", Items.POTATO);
        registerOre(CategoryTypes.PREFIX_VEGETABLE, "carrot", Items.CARROT);
        registerOre(CategoryTypes.PREFIX_VEGETABLE, "beetroot", Items.BEETROOT);
        registerOre(CategoryTypes.PREFIX_VEGETABLE, "waterMelon", Items.MELON);
        registerOre(CategoryTypes.PREFIX_VEGETABLE, "pumpkin", Blocks.PUMPKIN);
        registerOre(CategoryTypes.PREFIX_MUSHROOM, "red", Blocks.RED_MUSHROOM);
        registerOre(CategoryTypes.PREFIX_MUSHROOM, "brown", Blocks.BROWN_MUSHROOM);
        registerOre(CategoryTypes.PREFIX_FLOWER, "dandelion", Blocks.YELLOW_FLOWER);
        registerOre(CategoryTypes.PREFIX_FLOWER, "lightBlue", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.BLUE_ORCHID.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "magenta", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.ALLIUM.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "silver", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.HOUSTONIA.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "red", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.RED_TULIP.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "orange", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.ORANGE_TULIP.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "silver", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.WHITE_TULIP.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "pink", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.PINK_TULIP.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "silver", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlower.EnumFlowerType.OXEYE_DAISY.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "yellow", new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.SUNFLOWER.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "magenta", new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.SYRINGA.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "red", new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.ROSE.getMeta()));
        registerOre(CategoryTypes.PREFIX_FLOWER, "pink", new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.PAEONIA.getMeta()));
    }

    public static void registerOre(CategoryType category, String name, Item ore) {
        registerOre(category, name, new ItemStack(ore));
    }

    public static void registerOreAll(CategoryType category, String name, Item ore) {
        registerOre(category, name, new ItemStack(ore, 1, OreDictionary.WILDCARD_VALUE));
    }

    public static void registerOre(CategoryType category, String name, Block ore) {
        registerOre(category, name, new ItemStack(ore));
    }

    public static void registerOreAll(CategoryType category, String name, Block ore) {
        registerOre(category, name, new ItemStack(ore, 1, OreDictionary.WILDCARD_VALUE));
    }

    public static void registerOre(CategoryType category, String name, ItemStack ore) {
        OreDictionary.registerOre(category.getDictionaryName(name), ore);
        CategoryType parent = category.getParentCategory();
        if(parent != null) {
            registerOre(parent, name, ore);
        }
    }

    public static List<ItemStack> getOres(CategoryType category, String name) {
        return getOres(category, name, true);
    }

    public static List<ItemStack> getOres(CategoryType category, String name, boolean alwaysCreateEntry) {
        return OreDictionary.getOres(category.getDictionaryName(name), alwaysCreateEntry);
    }
}
