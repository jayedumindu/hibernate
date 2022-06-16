package entity;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int dId;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Reserve order;

    @ManyToOne
    @JoinColumn(name="item_code")
    private Item item;

    private int orderQty;

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public OrderDetails() {
    }

    public OrderDetails(Reserve order, Item item, int orderQty) {
        this.order = order;
        this.item = item;
        this.orderQty = orderQty;
    }

    public Reserve getOrder() {
        return order;
    }

    public void setOrder(Reserve order) {
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
