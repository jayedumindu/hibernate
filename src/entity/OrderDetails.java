package entity;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="item_code")
    private Item item;

    private int orderQty;


    public OrderDetails() {
    }

    public OrderDetails(Order order, Item item, int orderQty) {
        this.order = order;
        this.item = item;
        this.orderQty = orderQty;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }



    @Override
    public String toString() {
        return "OrderDetails{" +
                "order=" + order +
                ", item=" + item +
                ", orderQty=" + orderQty +
                '}';
    }
}
