package entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reservation {

    @Id
    @Column(name = "Reservation_Id")
    @NonNull
    private String resId;
    @Column(name = "Date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "Status")
    @NonNull
    private String status;
    @Column(name = "PAID")
    @NonNull
    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "Student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "Room_Type")
    private Room room;

}
