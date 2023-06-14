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
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="registration_number")
    private String registrationNumber;
    @Column(name="brand")
    private String brand;
    @Column(name="make")
    private String make;
    @Column(name="year")
    private String year;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookingList = new ArrayList<>();

    @OneToOne(mappedBy = "car")
    private User user;

    public Car(String registrationNumber, String brand, String make, String year) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }
}