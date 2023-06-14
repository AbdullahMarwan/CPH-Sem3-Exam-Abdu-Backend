package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@NamedQuery(name = "WashingAssistant.deleteAllRows", query = "DELETE from WashingAssistant")
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

    public WashingAssistant(String name, String primaryLanguage, String yearsOfExperience, String pricePrHour) {
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.yearsOfExperience = yearsOfExperience;
        this.pricePrHour = pricePrHour;
    }

    public void addBooking(Booking booking){
        bookingList.add(booking);
    }
}