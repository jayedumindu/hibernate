package entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {

    @Id
    @Column(name = "Student_ID")
    @NonNull
    private String sId;
    @Column(name = "Address")
    @NonNull
    private String address;
    @Column(name = "Contact_NO")
    @NonNull
    private String contact;
    @NonNull
    private LocalDate DOB;
    @Column(name = "Gender")
    @NonNull
    private String gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

}
