package TDMs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTDM {

    private String resId;

    private String date;

    private String student;

    private String room;

    private String status;

    private boolean paid;

}
