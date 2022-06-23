package DTOs;

import entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String sId;
    private String address;
    private String contact;
    private Date DOB;
    private String gender;

    private List<Reservation> reservations = new ArrayList<>();

}
