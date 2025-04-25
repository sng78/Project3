package io.github.sng78.server.service;

import io.github.sng78.server.model.Measurement;
import io.github.sng78.server.model.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MeasurementServiceTest {

    private final Sensor sensor = new Sensor("TEST SENSOR");

    @Autowired
    private SensorService sensorService;

    @Autowired
    private MeasurementService measurementService;

    @BeforeEach
    void setUp() {
        sensorService.save(sensor);
    }

    @Test
    void save() {
        Measurement measurement = new Measurement(24.7, true);
        measurement.setSensor(sensor);
        Measurement savedMeasurement = measurementService.save(measurement);
        assertNotNull(savedMeasurement);
        assertEquals(measurement, savedMeasurement);
    }

    @Test
    void getAll() {
        saveMeasurements();
        assertEquals(3, measurementService.getAll().size());
    }

    @Test
    void getRainyDays() {
        saveMeasurements();
        assertEquals(2, measurementService.getRainyDays());
    }

    private void saveMeasurements() {
        Measurement measurement1 = new Measurement(24.7, true);
        Measurement measurement2 = new Measurement(24.0, true);
        Measurement measurement3 = new Measurement(-24.7, false);
        measurement1.setSensor(sensor);
        measurement2.setSensor(sensor);
        measurement3.setSensor(sensor);
        measurementService.save(measurement1);
        measurementService.save(measurement2);
        measurementService.save(measurement3);
    }
}
