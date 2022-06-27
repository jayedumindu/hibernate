package DTOs;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ReservationDTO {

    @NonNull
    private String resId;

    private Date date;
    @NonNull
    private String status;

    private boolean paid;

    private StudentDTO student;

    private RoomDTO room;
}
