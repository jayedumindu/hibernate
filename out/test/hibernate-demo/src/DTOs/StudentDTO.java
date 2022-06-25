package DTOs;

import entity.Reservation;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentDTO {

    @NonNull
    private String sId;
    @NonNull
    private String address;
    @NonNull
    private String contact;
    @NonNull
    private Date DOB;
    @NonNull
    private String gender;

    private List<Reservation> reservations = new ArrayList<>();

}
