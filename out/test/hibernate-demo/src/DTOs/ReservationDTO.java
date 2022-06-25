package DTOs;

import entity.Room;
import entity.Student;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ReservationDTO {

    @NonNull
    private String resId;
    @NonNull
    private Date date;
    @NonNull
    private String status;

    private boolean paid;

    private Student student;

    private Room room;
}
