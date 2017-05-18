package eyeq.util.oredict;

public class PreFixCategoryType extends CategoryType {
    public PreFixCategoryType(String categoryName) {
        super(null, categoryName);
    }

    public PreFixCategoryType(CategoryType parentCategory) {
        super(parentCategory, parentCategory.getCategoryName());
    }

    @Override
    public String getDictionaryName(String name) {
        return getCategoryName() + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
