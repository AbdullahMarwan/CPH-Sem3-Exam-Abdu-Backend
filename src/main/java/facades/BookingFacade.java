package facades;

import dtos.BookingDTO;
import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.Booking;
import entities.Car;
import entities.WashingAssistant;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookingFacade {
    private static BookingFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BookingFacade() {
    }

    public static BookingFacade getBookingFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookingFacade();
        }
        return instance;
    }

    //Create booking
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        EntityManager em = emf.createEntityManager();
        Booking booking = new Booking(bookingDTO.getDateAndTime(), bookingDTO.getDuration());
        try {
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookingDTO(booking);
    }

    //Return list of all bookings
    public List<BookingDTO> getBookings() {
        EntityManager em = emf.createEntityManager();
        List<Booking> bookings;
        try {
            bookings = em.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
        } finally {
            em.close();
        }
        return BookingDTO.getDTOs(bookings);
    }

    /*
    //Get bookingList by id
    public List<BookingDTO> getBookingListByUsername(String user_name) {
        EntityManager em = emf.createEntityManager();
        List<Booking> bookings;
        try {
            bookings = em.createQuery("SELECT b FROM Booking b where b.user_name = :user_name", Booking.class).getResultList();
            //bookings = em.createQuery("SELECT b FROM Booking b where b.user_name = :user_name", Booking.class).getResultList();
        } finally {
            em.close();
        }
        return BookingDTO.getDTOs(bookings);
    }

     */

    //Remove booking by id
    public BookingDTO removeBooking(Long id) {
        EntityManager em = emf.createEntityManager();
        Booking booking = em.find(Booking.class, id);
        try {
            em.getTransaction().begin();
            em.remove(booking);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookingDTO(booking);
    }

    //Add booking to a car
    public BookingDTO addBookingToCar(Long id, Long carId) {
        EntityManager em = emf.createEntityManager();
        Booking booking = em.find(Booking.class, id);
        try {
            em.getTransaction().begin();
            booking.setCar(em.find(Car.class, carId));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookingDTO(booking);
    }

    //Edit booking
    public BookingDTO editBooking(BookingDTO bookingDTO) {
        EntityManager em = emf.createEntityManager();
        Booking booking = em.find(Booking.class, bookingDTO.getId());
        try {
            em.getTransaction().begin();
            booking.setDateAndTime(bookingDTO.getDateAndTime());
            booking.setDuration(bookingDTO.getDuration());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookingDTO(booking);
    }

    public static void main(String[] args) {
        BookingFacade bookingFacade = getBookingFacade(EMF_Creator.createEntityManagerFactory());
        bookingFacade.createBooking(new BookingDTO(2L, "10 May", "1 Hour"));
    }

}
