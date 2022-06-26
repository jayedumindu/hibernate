package DTOs;

import entity.Reservation;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate DOB;
    @NonNull
    private String gender;

    private List<Reservation> reservations = new ArrayList<>();

}
