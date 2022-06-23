package DTOs;

import entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int quantity;

    private List<Reservation> reservations = new ArrayList<>();

}
