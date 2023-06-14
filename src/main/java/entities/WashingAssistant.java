package entities;

import javax.persistence.*;

@Entity
@Table(name = "washing_assistant")
public class WashingAssistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String primaryLanguage;
    private String yearsOfExperience;
    private String pricePrHour;

    public WashingAssistant() {
    }

    public WashingAssistant(String name, String primaryLanguage, String yearsOfExperience, String pricePrHour) {
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.yearsOfExperience = yearsOfExperience;
        this.pricePrHour = pricePrHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}