package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="date_and_time")
    private String dateAndTime;
    @Column(name="duration")
    private String duration;

    @ManyToMany(mappedBy = "bookingList")
    private List<WashingAssistant> washingAssistantList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Booking(String dateAndTime, String duration) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;
    }

    public void addWashingAssistant(WashingAssistant washingAssistant){
        this.washingAssistantList.add(washingAssistant);
        washingAssistant.getBookingList().add(this);
    }
}