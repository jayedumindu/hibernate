package entity;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Entity
public class Customer {

    @Id
    private String cus_id;
    private String cus_name;
    private String cus_address;
    private String cus_contact;

    @Transient
    private Map<Item,Integer> cart = new HashMap<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reserve> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String cus_id, String cus_name, String cus_address, String cus_contact) {
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.cus_address = cus_address;
        this.cus_contact = cus_contact;
    }

    public Customer(String cus_id, String cus_name, String cus_address, String cus_contact, List<Reserve> orders) {
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.cus_address = cus_address;
        this.cus_contact = cus_contact;
        this.orders = orders;
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    public String getCus_contact() {
        return cus_contact;
    }

    public void setCus_contact(String cus_contact) {
        this.cus_contact = cus_contact;
    }

    public List<Reserve> getOrders() {
        return orders;
    }

    public void setOrders(List<Reserve> orders) {
        this.orders = orders;
    }

    public void addToCart(Item item, int amount){
        if(cart.containsKey(item)){
            cart.put(item,cart.get(item)+amount);
        }
        cart.put(item,amount);
    }

    public void placeOrder(Session session){
        Reserve order1 = new Reserve("OD01",this);
        AtomicReference<Double> total = new AtomicReference<>(0.0);
        cart.forEach((key,value)->{
            // update item
            //session.update(new Item(key.getItem_code(), key.getItem_description(), key.getItem_price(), key.getQtyOnHand()-value));
            key.setQtyOnHand(key.getQtyOnHand()-value);
            OrderDetails orderDetails = new OrderDetails(order1,key,value);
            order1.getOrderDetails().add(orderDetails);
            key.getOrderDetails().add(orderDetails);
            // details
            session.save(orderDetails);
            total.updateAndGet(v -> v + key.getItem_price() * value);
        });
        order1.setPrice(total.get());
        session.save(order1);
        this.getOrders().add(order1);

    }

    @Override
    public String toString() {
        return "Customer{" +
                "cus_id='" + cus_id + '\'' +
                ", cus_name='" + cus_name + '\'' +
                ", cus_address='" + cus_address + '\'' +
                ", cus_contact='" + cus_contact + '\'' +
                ", orders=" + orders +
                '}';
    }
}
