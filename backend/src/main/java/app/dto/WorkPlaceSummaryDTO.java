package app.dto;

import lombok.Data;

@Data
public class WorkPlaceSummaryDTO {

    private int yellowAlerts;
    private int redAlerts;
    private int sensors;
    private int readings;

    public WorkPlaceSummaryDTO(int yellowAlerts, int redAlerts, int sensors, int readings) {
        this.yellowAlerts = yellowAlerts;
        this.redAlerts = redAlerts;
        this.sensors = sensors;
        this.readings = readings;
    }
}
