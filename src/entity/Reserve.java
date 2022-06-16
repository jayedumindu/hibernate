package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Reserve {

    @Id
    private String orderId;

    @CreationTimestamp
    private Date date;
    private double price;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Reserve() {
    }

    public Reserve(String orderId, Date date, double price, Customer customer) {
        this.orderId = orderId;
        this.date = date;
        this.price = price;
        this.customer = customer;
    }

    public Reserve(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public Reserve(String orderId, Date date, double price, Customer customer, List<OrderDetails> orderDetails) {
        this.orderId = orderId;
        this.date = date;
        this.price = price;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", customer=" + customer +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
