package eyeq.util.oredict;

public class CategoryTypes {
    // tree- and wood-related things
    public static final CategoryType LOG_WOOD = new CategoryType(UOreDictionary.OREDICT_LOG);
    public static final CategoryType PLANK_WOOD = new CategoryType(UOreDictionary.OREDICT_PLANKS);
    public static final CategoryType SLAB_WOOD = new CategoryType(UOreDictionary.OREDICT_WOODEN_SLAB);
    public static final CategoryType STAIR_WOOD = new CategoryType(UOreDictionary.OREDICT_WOODEN_STAIRS);
    public static final CategoryType STICK_WOOD = new CategoryType(UOreDictionary.OREDICT_STICK);
    public static final CategoryType TREE_SAPLING = new CategoryType(UOreDictionary.OREDICT_SAPLING);
    public static final CategoryType TREE_LEAVES = new CategoryType(UOreDictionary.OREDICT_LEAVES);
    public static final CategoryType VINE = new CategoryType(UOreDictionary.OREDICT_VINE);
    // misc materials
    public static final CategoryType DYE = new CategoryType(UOreDictionary.OREDICT_DYE);
    public static final CategoryType PAPER = new CategoryType(UOreDictionary.OREDICT_PAPER);
    // mob drops
    public static final CategoryType SLIMEBALL = new CategoryType(UOreDictionary.OREDICT_SLIME_BALL);
    public static final CategoryType ENDERPEARL = new CategoryType(UOreDictionary.OREDICT_ENDER_PEARL);
    public static final CategoryType BONE = new CategoryType(UOreDictionary.OREDICT_BONE);
    public static final CategoryType GUNPOWDER = new CategoryType(UOreDictionary.OREDICT_GUNPOWDER);
    public static final CategoryType STRING = new CategoryType(UOreDictionary.OREDICT_STRING);
    public static final CategoryType NETHER_STAR = new CategoryType(UOreDictionary.OREDICT_NETHER_STAR);
    public static final CategoryType LEATHER = new CategoryType(UOreDictionary.OREDICT_LEATHER);
    public static final CategoryType FEATHER = new CategoryType(UOreDictionary.OREDICT_FEATHER);
    public static final CategoryType EGG = new CategoryType(UOreDictionary.OREDICT_EGG);
    public static final CategoryType RECORD = new CategoryType(UOreDictionary.OREDICT_RECORD);
    // blocks
    public static final CategoryType DIRT = new CategoryType(UOreDictionary.OREDICT_DIRT);
    public static final CategoryType GRASS = new CategoryType(UOreDictionary.OREDICT_GRASS);
    public static final CategoryType STONE = new CategoryType(UOreDictionary.OREDICT_STONE);
    public static final CategoryType COBBLESTONE = new CategoryType(UOreDictionary.OREDICT_COBBLESTONE);
    public static final CategoryType GRAVEL = new CategoryType(UOreDictionary.OREDICT_GRAVEL);
    public static final CategoryType SAND = new CategoryType(UOreDictionary.OREDICT_SAND);
    public static final CategoryType SANDSTONE = new CategoryType(UOreDictionary.OREDICT_SANDSTONE);
    public static final CategoryType NETHERRACK = new CategoryType(UOreDictionary.OREDICT_NETHERRACK);
    public static final CategoryType OBSIDIAN = new CategoryType(UOreDictionary.OREDICT_OBSIDIAN);
    public static final CategoryType GLOWSTONE = new CategoryType(UOreDictionary.OREDICT_GLOWSTONE);
    public static final CategoryType ENDSTONE = new CategoryType(UOreDictionary.OREDICT_END_STONE);
    public static final CategoryType TORCH = new CategoryType(UOreDictionary.OREDICT_TORCH);
    public static final CategoryType WORKBENCH = new CategoryType(UOreDictionary.OREDICT_CRAFTING_TABLE);
    public static final CategoryType CHEST = new CategoryType(UOreDictionary.OREDICT_CHEST);
    public static final CategoryType BLOCK_GRASS = new CategoryType(UOreDictionary.OREDICT_GLASS);
    public static final CategoryType BLOCK_GRASS_COLORLESS = new CategoryType(BLOCK_GRASS, UOreDictionary.OREDICT_COLORLESS_GLASS);
    public static final CategoryType PANE_GRASS = new CategoryType(UOreDictionary.OREDICT_GLASS_PANE);
    public static final CategoryType PANE_GRASS_COLORLESS = new CategoryType(PANE_GRASS, UOreDictionary.OREDICT_COLORLESS_GLASS_PANE);
    // prefixes
    public static final CategoryType PREFIX_ORE = new PreFixCategoryType("ore");
    public static final CategoryType PREFIX_INGOT = new PreFixCategoryType("ingot");
    public static final CategoryType PREFIX_NUGGET = new PreFixCategoryType("nugget");
    public static final CategoryType PREFIX_GEM = new PreFixCategoryType("gem");
    public static final CategoryType PREFIX_BLOCK = new PreFixCategoryType("block");
    public static final CategoryType PREFIX_CROP = new PreFixCategoryType("crop");
    public static final CategoryType PREFIX_DUST = new PreFixCategoryType("dust");
    public static final CategoryType PREFIX_PANE = new PreFixCategoryType("pane");
    public static final CategoryType PREFIX_STONE = new PreFixCategoryType(STONE);
    public static final CategoryType PREFIX_CHEST = new PreFixCategoryType(CHEST);
    // colored
    public static final CategoryType PREFIX_DYE = new PreFixCategoryType(DYE);
    public static final CategoryType PREFIX_BLOCK_GRASS = new PreFixCategoryType(BLOCK_GRASS);
    public static final CategoryType PREFIX_PANE_GRASS = new PreFixCategoryType(PANE_GRASS);
    // not forge
    public static final CategoryType MILK = new CategoryType(UOreDictionary.OREDICT_MILK);
    public static final CategoryType FOOD_GOLD = new CategoryType(UOreDictionary.OREDICT_GOLDEN_FOOD);
    public static final CategoryType COOKED = new CategoryType(UOreDictionary.OREDICT_COOKED);
    public static final CategoryType COOKED_MEAT = new CategoryType(COOKED, UOreDictionary.OREDICT_COOKED_MEAT);
    public static final CategoryType COOKED_FISH = new CategoryType(COOKED, UOreDictionary.OREDICT_COOKED_FISH);
    public static final CategoryType SWEET = new CategoryType(UOreDictionary.OREDICT_SWEET);
    public static final CategoryType FISH = new CategoryType(UOreDictionary.OREDICT_FISH);
    public static final CategoryType MEAT = new CategoryType(UOreDictionary.OREDICT_MEAT);
    public static final CategoryType GRAIN = new CategoryType(UOreDictionary.OREDICT_GRAIN);
    public static final CategoryType FRUIT = new CategoryType(UOreDictionary.OREDICT_FRUIT);
    public static final CategoryType VEGETABLE = new CategoryType(UOreDictionary.OREDICT_VEGETABLE);
    public static final CategoryType MUSHROOM = new CategoryType(UOreDictionary.OREDICT_MUSHROOM);
    public static final CategoryType FLOWER = new CategoryType(UOreDictionary.OREDICT_FLOWER);
    public static final CategoryType STONE_IGNEOUS = new CategoryType(UOreDictionary.OREDICT_STONE_IGNEOUS);
    public static final CategoryType STONE_IGNEOUS_POLISHED = new CategoryType(UOreDictionary.OREDICT_STONE_IGNEOUS_POLISHED);
    // prefixes
    public static final CategoryType PREFIX_FOOD_GOLD = new PreFixCategoryType(FOOD_GOLD);
    public static final CategoryType PREFIX_MEAT = new PreFixCategoryType(MEAT);
    public static final CategoryType PREFIX_GRAIN = new PreFixCategoryType(GRAIN);
    public static final CategoryType PREFIX_FRUIT = new PreFixCategoryType(FRUIT);
    public static final CategoryType PREFIX_VEGETABLE = new PreFixCategoryType(VEGETABLE);
    // colored
    public static final CategoryType PREFIX_MUSHROOM = new PreFixCategoryType(MUSHROOM);
    public static final CategoryType PREFIX_FLOWER = new PreFixCategoryType(FLOWER);
}
