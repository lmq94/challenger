package app.dto;

import app.domain.WorkPlace;
import jakarta.validation.constraints.Min;
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

    @Min(value = 0, message = "El número de alertas amarillas no puede ser menor a 0")
    private int yellowAlerts;

    @Min(value = 0, message = "El número de alertas rojas no puede ser menor a 0")
    private int redAlerts;

    @Min(value = 0, message = "El número de lecturas no puede ser menor a 0")
    private int readings;

    @Min(value = 0, message = "El número de sensores no puede ser menor a 0")
    private int sensors;

    private Long id;

    public WorkPlaceDTO(WorkPlace workPlace) {
        this.country = workPlace.getCountry();
        this.name = workPlace.getName();
        this.yellowAlerts = workPlace.getYellowAlerts();
        this.redAlerts = workPlace.getRedAlerts();
        this.readings = workPlace.getReadings();
        this.sensors = workPlace.getSensors();
        this.id = workPlace.getId();
    }
}
