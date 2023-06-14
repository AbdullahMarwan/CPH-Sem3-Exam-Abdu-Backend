package facades;

import dtos.CarDTO;
import entities.Booking;
import entities.Car;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CarFacade {
    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
    }

    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    //Create car
    public CarDTO createCar(CarDTO carDTO) {
        EntityManager em = emf.createEntityManager();
        Car car = new Car(carDTO.getRegistrationNumber(), carDTO.getBrand(), carDTO.getMake(), carDTO.getYear());
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CarDTO(car);
    }

    //Return list of all cars
    public List<CarDTO> getCars() {
        EntityManager em = emf.createEntityManager();
        List<Car> cars;
        try {
            cars = em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        } finally {
            em.close();
        }
        return CarDTO.getDTOs(cars);
    }

    //Remove car by id
    public CarDTO removeCar(Long id) {
        EntityManager em = emf.createEntityManager();
        Car car = em.find(Car.class, id);
        try {
            em.getTransaction().begin();
            em.remove(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CarDTO(car);
    }

    //Edit car
    public CarDTO editCar(CarDTO carDTO) {
        EntityManager em = emf.createEntityManager();
        Car car = em.find(Car.class, carDTO.getId());
        try {
            em.getTransaction().begin();
            car.setRegistrationNumber(carDTO.getRegistrationNumber());
            car.setBrand(carDTO.getBrand());
            car.setMake(carDTO.getMake());
            car.setYear(carDTO.getYear());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CarDTO(car);
    }

    //Method Tester
    public static void main(String[] args) {
        CarFacade carFacade = getCarFacade(EMF_Creator.createEntityManagerFactory());
        carFacade.createCar(new CarDTO(5L, "1010", "Toyota", "20", "11"));
    }
}
