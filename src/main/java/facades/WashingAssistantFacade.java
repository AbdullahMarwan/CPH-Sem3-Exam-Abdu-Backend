package facades;

import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.WashingAssistant;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public class WashingAssistantFacade {
    private static WashingAssistantFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private WashingAssistantFacade() {
    }

    public static WashingAssistantFacade getWashingAssistantFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WashingAssistantFacade();
        }
        return instance;
    }

    //Create washingAssistant
    public WashingAssistantDTO createWashingAssistant(WashingAssistantDTO washingAssistantDTO) {
        EntityManager em = emf.createEntityManager();
        WashingAssistant washingAssistant = new WashingAssistant(washingAssistantDTO.getName(), washingAssistantDTO.getPrimaryLanguage(), washingAssistantDTO.getYearsOfExperience(), washingAssistantDTO.getPricePrHour());
        try {
            em.getTransaction().begin();
            em.persist(washingAssistant);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new WashingAssistantDTO(washingAssistant);
    }

    //Return list of all washingAssistants
    public List<WashingAssistantDTO> getWashingAssistants() {
        EntityManager em = emf.createEntityManager();
        List<WashingAssistant> washingAssistants;
        try {
            washingAssistants = em.createQuery("SELECT w FROM WashingAssistant w", WashingAssistant.class).getResultList();
        } finally {
            em.close();
        }
        return WashingAssistantDTO.getDTOs(washingAssistants);
    }

    //Remove washingAssistant by id
    public WashingAssistantDTO removeWashingAssistant(Long id) {
        EntityManager em = emf.createEntityManager();
        WashingAssistant washingAssistant = em.find(WashingAssistant.class, id);
        try {
            em.getTransaction().begin();
            em.remove(washingAssistant);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new WashingAssistantDTO(washingAssistant);
    }

    //Add washingAssistant to a booking
    public WashingAssistantDTO addWashingAssistantToBooking(Long id, Long bookingId) {
        EntityManager em = emf.createEntityManager();
        WashingAssistant washingAssistant = em.find(WashingAssistant.class, id);
        try {
            em.getTransaction().begin();
            washingAssistant.addBooking(em.find(Booking.class, bookingId));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new WashingAssistantDTO(washingAssistant);
    }

    //Get washingAssistant by id
    public WashingAssistantDTO getById(Long id) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        WashingAssistant w = em.find(WashingAssistant.class, id);
        if (w == null)
            throw new EntityNotFoundException("The WashingAssistant entity with ID: " + id + " Was not found");

        return new WashingAssistantDTO(w);
    }

    //Edit washingAssistant
    public WashingAssistantDTO editWashingAssistant(WashingAssistantDTO washingAssistantDTO) {
        EntityManager em = emf.createEntityManager();
        WashingAssistant washingAssistant = em.find(WashingAssistant.class, washingAssistantDTO.getId());
        try {
            em.getTransaction().begin();
            washingAssistant.setName(washingAssistantDTO.getName());
            washingAssistant.setPrimaryLanguage(washingAssistantDTO.getPrimaryLanguage());
            washingAssistant.setYearsOfExperience(washingAssistantDTO.getYearsOfExperience());
            washingAssistant.setPricePrHour(washingAssistantDTO.getPricePrHour());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new WashingAssistantDTO(washingAssistant);
    }

    //Method Tester
    public static void main(String[] args) {
        WashingAssistantFacade washingAssistantFacade = getWashingAssistantFacade(EMF_Creator.createEntityManagerFactory());
        //Edit method works (need to input correct id of the WashingAssistant I want to change)
        washingAssistantFacade.editWashingAssistant(new WashingAssistantDTO(1L, "Bobby", "English", "2", "10"));

        //Create method works
        washingAssistantFacade.createWashingAssistant(new WashingAssistantDTO(5L, "Bob", "English", "2", "10"));
    }

}
