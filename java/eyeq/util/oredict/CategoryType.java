package eyeq.util.oredict;

public class CategoryType {
    private final CategoryType parentCategory;
    private final String categoryName;

    public CategoryType(String categoryName) {
        this(null, categoryName);
    }

    public CategoryType(CategoryType parentCategory, String categoryName) {
        this.parentCategory = parentCategory;
        this.categoryName = categoryName;
    }

    public CategoryType getParentCategory() {
        return parentCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDictionaryName(String name) {
        return getCategoryName();
    }
}
