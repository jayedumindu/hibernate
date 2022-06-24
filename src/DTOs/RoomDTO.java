package DTOs;

import entity.Reservation;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomDTO {

    @NonNull
    private String roomTypeId;
    @NonNull
    private String type;
    @NonNull
    private double keyMoney;
    @NonNull
    private int quantity;

    private List<Reservation> reservations = new ArrayList<>();


}
