package io.github.sng78.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotNull(message = "Cannot be NULL")
    @Min(value = -100, message = "Minimum temperature -100")
    @Max(value = 100, message = "Maximum temperature 100")
    private Double value;

    @Column(name = "is_raining")
    @NotNull(message = "Cannot be NULL")
    private Boolean isRaining;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    @NotNull(message = "Cannot be NULL")
    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(Double value, Boolean isRaining) {
        this.value = value;
        this.isRaining = isRaining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
