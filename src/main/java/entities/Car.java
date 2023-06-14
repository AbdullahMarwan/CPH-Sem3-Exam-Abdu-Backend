package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
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

    public Car(String registrationNumber, String brand, String make, String year) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }
}