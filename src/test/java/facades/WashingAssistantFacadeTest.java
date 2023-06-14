package facades;

import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.Car;
import entities.WashingAssistant;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WashingAssistantFacadeTest {
    private static EntityManagerFactory emf;
    private static WashingAssistantFacade facade;

    WashingAssistant w1, w2;
    Car c1, c2;
    Booking b1, b2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = WashingAssistantFacade.getWashingAssistantFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
        // Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        //Drop-and-Create used on pu-test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("WashingAssistant.deleteAllRows").executeUpdate();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.createNamedQuery("Booking.deleteAllRows").executeUpdate();
            w1 = new WashingAssistant("Tom", "Danish", "10 Years", "50DKK Hour");
            w2 = new WashingAssistant("Ole", "English", "2 Years", "30DKK Hour");
            c1 = new Car("2020", "Mercedes", "10", "2000");
            c2 = new Car("3020", "Bugatti", "100", "3000");
            b1 = new Booking("10th June - 10:00", "10Min");
            b2 = new Booking("23th February - 20:00", "1Hour");
            em.persist(w1);
            em.persist(w2);
            em.persist(c1);
            em.persist(c2);
            em.persist(b1);
            em.persist(b2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /*
    @Test
    void create() {
        System.out.println("Testing create(WashingAssistant w)");
        WashingAssistant w = new WashingAssistant("TestWashingAssistant", "Danish", "10 Years", "50DKK Hour");
        WashingAssistant expected = w;
        WashingAssistant actual = facade.createWashingAssistant(w);
        assertEquals(expected, actual);
    }

     */

    /*
    @Test
    void create() {
        System.out.println("Testing create(WashingAssistant w)");
        WashingAssistantDTO w = new WashingAssistantDTO(3L, "TestWashingAssistant", "Danish", "10 Years", "50DKK Hour");
        WashingAssistantDTO expected = w;
        WashingAssistantDTO actual = facade.createWashingAssistant(w);
        assertEquals(expected, actual);
    }

     */

    @Test
    void getById() throws EntityNotFoundException {
        System.out.println("Testing getbyid(id)");
        WashingAssistant expected = w1;
        WashingAssistantDTO actual = facade.getById(w1.getId());
        assertEquals(expected, actual);
    }

} 
