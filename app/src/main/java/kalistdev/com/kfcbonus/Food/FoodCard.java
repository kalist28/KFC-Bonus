package kalistdev.com.kfcbonus.Food;

public class FoodCard {
    private String uriImage;
    private String name;
    private String tag;
    private String table;

    public FoodCard(String uriImage, String name, String tag, String table) {
        this.uriImage = uriImage;
        this.name = name;
        this.tag = tag;
        this.table = table;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public String getTable() {
        return table;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
