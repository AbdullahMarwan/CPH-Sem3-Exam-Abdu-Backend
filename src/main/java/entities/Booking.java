package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public Booking() {
    }

    public Booking(String dateAndTime, String duration) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<WashingAssistant> getWashingAssistantList() {
        return washingAssistantList;
    }

    public void setWashingAssistantList(List<WashingAssistant> washingAssistantList) {
        this.washingAssistantList = washingAssistantList;
    }

    //TODO Fix this method
    public void addWashingAssistant(WashingAssistant washingAssistant){
        this.washingAssistantList.add(washingAssistant);
        washingAssistant.getBookingList().add(this);
    }
}