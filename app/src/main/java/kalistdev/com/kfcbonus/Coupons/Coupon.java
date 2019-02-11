package kalistdev.com.kfcbonus.Coupons;

public class Coupon {
    private int _imageId;
    private int _price;
    private int _saved;
    private int _couponNumber;
    private String _uri;
    private String _name;
    private String _info;

    public Coupon(int _imageId, int _price, int _saved, int _couponNumber, String _uri, String _name, String _info) {
        this._imageId = _imageId;
        this._price = _price;
        this._saved = _saved;
        this._couponNumber = _couponNumber;
        this._uri = _uri;
        this._name = _name;
        this._info = _info;
    }

    public int get_imageId() {
        return _imageId;
    }

    public void set_imageId(int _imageId) {
        this._imageId = _imageId;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }

    public int get_saved() {
        return _saved;
    }

    public void set_saved(int _saved) {
        this._saved = _saved;
    }

    public int get_couponNumber() {
        return _couponNumber;
    }

    public void set_couponNumber(int _couponNumber) {
        this._couponNumber = _couponNumber;
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

    public String get_info() {
        return _info;
    }

    public void set_info(String _info) {
        this._info = _info;
    }
}
