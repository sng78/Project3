package io.github.sng78.model;

import java.time.LocalDateTime;

public class Measurement {

    private int id;

    private double value;

    private boolean isRaining;

    private LocalDateTime dateTime;

    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(double value, boolean isRaining) {
        this.value = value;
        this.isRaining = isRaining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
