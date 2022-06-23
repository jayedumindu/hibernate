package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "Student_ID")
    private String sId;
    @Column(name = "Address")
    private String address;
    @Column(name = "Contact_NO")
    private String contact;
    @Temporal(TemporalType.TIMESTAMP)
    private Date DOB;
    @Column(name = "Gender")
    private String gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

}
