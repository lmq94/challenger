package app.dto;

import app.domain.WorkPlace;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WorkPlaceDTO {

    @NotNull(message = "El pais no puede ser nulo" )
    private String country;

    @NotNull(message = "El nombre no puede ser nulo" )
    private String name;

    private int yellowAlerts;

    private int redAlerts;

    private int readings;

    private Long id;

    public WorkPlaceDTO(WorkPlace workPlace) {
        this.country = workPlace.getCountry();
        this.name = workPlace.getName();
        this.yellowAlerts = workPlace.getYellowAlerts();
        this.redAlerts = workPlace.getRedAlerts();
        this.readings = workPlace.getReadings();
        this.id = workPlace.getId();
    }
}
