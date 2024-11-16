package app.dto;

import app.domain.WorkPlace;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WorkPlaceDTO {

    private String country;

    private String name;

    private int yellowAlerts;

    private int redAlerts;

    private int readings;

    public WorkPlaceDTO(WorkPlace workPlace) {
        this.country = workPlace.getCountry();
        this.name = workPlace.getName();
        this.yellowAlerts = workPlace.getYellowAlerts();
        this.redAlerts = workPlace.getRedAlerts();
        this.readings = workPlace.getReadings();
    }
}
