package kalistdev.com.kfcbonus.Actions;

public class Action {
    private int _id;
    private String uri;
    private String name;
    private String info;

    public Action(String uri, String name, String info) {
        this.uri = uri;
        this.name = name;
        this.info = info;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUri() {
        return uri;
    }

    public String getInfo() {
        return info;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }
}
