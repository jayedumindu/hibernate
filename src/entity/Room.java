package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Room {

    @Id
    @Column(name = "Room_Code")
    @NonNull
    private String roomTypeId;
    @Column(name = "Type")
    @NonNull
    private String type;
    @Column(name = "Key_Money")
    @NonNull
    private double keyMoney;
    @Column(name = "Quantity", columnDefinition = "INT(3) UNSIGNED")
    @NonNull
    private int quantity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

}
