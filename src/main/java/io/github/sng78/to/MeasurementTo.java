package io.github.sng78.to;

import io.github.sng78.model.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementTo {

    @NotNull(message = "Cannot be NULL")
    @Min(value = -100, message = "Minimum temperature -100")
    @Max(value = 100, message = "Maximum temperature 100")
    private double value;

    @NotNull(message = "Cannot be NULL")
    private boolean isRaining;

    @NotNull(message = "Cannot be NULL")
    private Sensor sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
