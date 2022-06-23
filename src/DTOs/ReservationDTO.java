package DTOs;

import entity.Room;
import entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private String resId;
    private Date date;
    private String status;

    private Student student;

    private Room room;
}
