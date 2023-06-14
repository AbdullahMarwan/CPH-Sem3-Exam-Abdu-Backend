package dtos;

import entities.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class CarDTO {
    private Long id;
    private String registrationNumber;
    private String brand;
    private String make;
    private String year;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.registrationNumber = car.getRegistrationNumber();
        this.brand = car.getBrand();
        this.make = car.getMake();
        this.year = car.getYear();
    }

    public CarDTO(Long id, String registrationNumber, String brand, String make, String year) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public static List<CarDTO> getDTOs(List<Car> carList) {
        List<CarDTO> carDTOs = new ArrayList<>();
        carList.forEach(car -> carDTOs.add(new CarDTO(car)));
        return carDTOs;
    }

}