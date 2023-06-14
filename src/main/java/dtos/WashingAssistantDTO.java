package dtos;

import entities.WashingAssistant;
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

public class WashingAssistantDTO {
    private Long id;
    private String name;
    private String primaryLanguage;
    private String yearsOfExperience;
    private String pricePrHour;

    public WashingAssistantDTO(WashingAssistant washingAssistant) {
        this.id = washingAssistant.getId();
        this.name = washingAssistant.getName();
        this.primaryLanguage = washingAssistant.getPrimaryLanguage();
        this.yearsOfExperience = washingAssistant.getYearsOfExperience();
        this.pricePrHour = washingAssistant.getPricePrHour();
    }

    public WashingAssistantDTO(Long id, String name, String primaryLanguage, String yearsOfExperience, String pricePrHour) {
        this.id = id;
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.yearsOfExperience = yearsOfExperience;
        this.pricePrHour = pricePrHour;
    }

    public static List<WashingAssistantDTO> getDTOs(List<WashingAssistant> washingAssistantList) {
        List<WashingAssistantDTO> washingAssistantDTOs = new ArrayList<>();
        washingAssistantList.forEach(washingAssistant -> washingAssistantDTOs.add(new WashingAssistantDTO(washingAssistant)));
        return washingAssistantDTOs;
    }

}