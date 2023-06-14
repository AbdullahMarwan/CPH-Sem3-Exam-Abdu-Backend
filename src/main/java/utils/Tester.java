package utils;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        User user1 = new User("NewUser", "test123");
        User user2 = new User("NewAdmin", "test123");

//        Role userRole = new Role("user");
//        Role adminRole = new Role("admin");
//
//        user1.addRole(userRole);
//        user2.addRole(adminRole);

        WashingAssistant washingAssistant1 = new WashingAssistant("Tom","Danish","10 Years", "50DKK Hour");
        WashingAssistant washingAssistant2 = new WashingAssistant("Ole","English","2 Years", "30DKK Hour");

        Car car1 = new Car("2020", "Mercedes", "10", "2000");
        Car car2 = new Car("3020", "Bugatti", "100", "3000");

        Booking booking1 = new Booking("10th June - 10:00", "10Min");
        Booking booking2 = new Booking("23th February - 20:00", "1Hour");

        em.getTransaction().begin();

        em.persist(user1);
        em.persist(user2);
//        em.persist(userRole);
//        em.persist(adminRole);
        em.persist(washingAssistant1);
        em.persist(washingAssistant2);
        em.persist(car1);
        em.persist(car2);
        em.persist(booking1);
        em.persist(booking2);

        em.getTransaction().commit();

        System.out.println("");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println("");
        System.out.println(washingAssistant1);
        System.out.println(washingAssistant2);
        System.out.println("");
        System.out.println(car1);
        System.out.println(car2);
        System.out.println("");
        System.out.println(booking1);
        System.out.println(booking2);
        System.out.println("");

        em.close();
    }

}
