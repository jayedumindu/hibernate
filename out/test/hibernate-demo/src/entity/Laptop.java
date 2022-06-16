package entity;

import javax.persistence.*;

@Entity
public class Laptop {

    @Id
    private String id;
    private String model;

    @ManyToOne
    @JoinColumn(name = "Customer_Id")
    private Customer owner;

    public Laptop() {
    }

    public Laptop(String id, String model, Customer owner) {
        this.id = id;
        this.model = model;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
