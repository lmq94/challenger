package app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class WorkPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String name;

    private int yellowAlerts;

    private int redAlerts;

    private int readings;

    private int sensors;


    public WorkPlace(String country, String name, int yellowAlerts, int redAlerts, int readings, int sensors) {
        this.country = country;
        this.name = name;
        this.yellowAlerts = yellowAlerts;
        this.redAlerts = redAlerts;
        this.readings = readings;
        this.sensors = sensors;
    }
}
