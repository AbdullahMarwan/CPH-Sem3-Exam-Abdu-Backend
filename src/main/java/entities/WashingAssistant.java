package entities;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "washing_assistant")
public class WashingAssistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="primary_language")
    private String primaryLanguage;
    @Column(name="years_of_experience")
    private String yearsOfExperience;
    @Column(name="price_pr_hour")
    private String pricePrHour;

    @JoinTable(name = "washing_assistant_booking", joinColumns = {
            @JoinColumn(name = "washing_assistant_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "booking_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Booking> bookingList = new ArrayList<>();

    public WashingAssistant() {
    }

    public WashingAssistant(String name, String primaryLanguage, String yearsOfExperience, String pricePrHour) {
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.yearsOfExperience = yearsOfExperience;
        this.pricePrHour = pricePrHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getPricePrHour() {
        return pricePrHour;
    }

    public void setPricePrHour(String pricePrHour) {
        this.pricePrHour = pricePrHour;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public void addBooking(Booking booking){
        bookingList.add(booking);
    }
}