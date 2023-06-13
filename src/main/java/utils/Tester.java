package utils;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

    public static void main(String[] args) {

        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        User user1 = new User("NewUser", "test");
        User user2 = new User("NewAdmin", "test");

        Role userRole = new Role("user");
        Role adminRole = new Role("admin");

        //TODO Replace the EntityType1 and EntityType2 with the ones from the domain model
        EntityType1 entityType11 = new EntityType1("Param1", "Param2");
        EntityType1 entityType12 = new EntityType1("Param1", "Param2");

        EntityType2 entityType21 = new EntityType2("Param1", "Param2");
        EntityType2 entityType22 = new EntityType2("Param1", "Param2");

        user1.addRole(userRole);
        user2.addRole(adminRole);

        em.getTransaction().begin();

        em.persist(user1);
        em.persist(user2);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(entityType11);
        em.persist(entityType12);
        em.persist(entityType21);
        em.persist(entityType22);

        em.getTransaction().commit();

        System.out.println("");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println("");
        System.out.println(entityType11);
        System.out.println(entityType12);
        System.out.println("");
        System.out.println(entityType21);
        System.out.println(entityType22);
        System.out.println("");

        em.close();

         */
    }

}
