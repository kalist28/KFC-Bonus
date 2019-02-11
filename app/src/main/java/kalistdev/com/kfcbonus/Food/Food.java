package kalistdev.com.kfcbonus.Food;

public class Food extends Object {
    private int _id;
    private String _type;
    private String _uri;
    private String _name;
    private String _addName;
    private int _price;
    private String _description;
    private String _composition;
    private int _calories;

    public Food(int _id, String _type, String _uri, String _name, String _addName, int _price, String _description, String _composition, int _calories) {
        this._id = _id;
        this._type = _type;
        this._uri = _uri;
        this._name = _name;
        this._addName = _addName;
        this._price = _price;
        this._description = _description;
        this._composition = _composition;
        this._calories = _calories;
        if (_addName == null){
            this._addName = " ";
        }
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_uri() {
        return _uri;
    }

    public void set_uri(String _uri) {
        this._uri = _uri;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_addName() {
        return _addName;
    }

    public void set_addName(String _addName) {
        this._addName = _addName;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_composition() {
        return _composition;
    }

    public void set_composition(String _composition) {
        this._composition = _composition;
    }

    public int get_calories() {
        return _calories;
    }

    public void set_calories(int _calories) {
        this._calories = _calories;
    }
}
