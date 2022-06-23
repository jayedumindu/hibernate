package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @Column(name = "Reservation_Id")
    private String resId;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "Status")
    private String status;
    @Column(name = "PAID")
    private boolean paid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "Student")
    private Student student;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "Room_Type")
    private Room room;

}
