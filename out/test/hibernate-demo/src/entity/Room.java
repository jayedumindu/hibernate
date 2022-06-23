package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @Column(name = "Room_Code")
    private String roomTypeId;
    @Column(name = "Type")
    private String type;
    @Column(name = "Key_Money")
    private double keyMoney;
    @Column(name = "Quantity", columnDefinition = "UNSIGNED INT")
    private int quantity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

}
