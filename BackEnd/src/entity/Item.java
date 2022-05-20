package entity;

public class Item {
    private String code;
    private String item;
    private int qtyOnHand;
    private double price;

    public Item() {
    }

    public Item(String code, String item, int qtyOnHand, double price) {
        this.code = code;
        this.item = item;
        this.qtyOnHand = qtyOnHand;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qty) {
        this.qtyOnHand = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
