package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Reservation {

    @Id
    @Column(name = "Reservation_Id")
    @NonNull
    private String resId;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date date;
    @Column(name = "Status")
    @NonNull
    private String status;
    @Column(name = "PAID")
    @NonNull
    private boolean paid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "Student")
    private Student student;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "Room_Type")
    private Room room;

}
