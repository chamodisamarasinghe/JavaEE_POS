package entity;

public class Order {
    String orderId;
    String name;
    double total;
    String date;

    public Order() {
    }

    public Order(String orderId, String name, double total, String date) {
        this.orderId = orderId;
        this.name = name;
        this.total = total;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
