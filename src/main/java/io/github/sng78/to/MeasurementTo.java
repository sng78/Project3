package io.github.sng78.to;

import io.github.sng78.model.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementTo {

    @NotNull(message = "Cannot be NULL")
    @Min(value = -100, message = "Minimum temperature -100")
    @Max(value = 100, message = "Maximum temperature 100")
    private Double value;

    @NotNull(message = "Cannot be NULL")
    private Boolean isRaining;

    @NotNull(message = "Cannot be NULL")
    private Sensor sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return isRaining;
    }

    public void setRaining(Boolean raining) {
        isRaining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
