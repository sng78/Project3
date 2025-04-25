package io.github.sng78.server.service;

import io.github.sng78.server.model.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SensorServiceTest {

    private final String sensorName = "TEST SENSOR";

    private final Sensor sensor = new Sensor(sensorName);

    @Autowired
    private SensorService sensorService;

    @Test
    void save() {
        Sensor savedSensor = sensorService.save(sensor);
        verifySensor(savedSensor);
    }

    @Test
    void findByName() {
        sensorService.save(sensor);
        Sensor foundSensor = sensorService.findByName(sensorName).orElse(null);
        verifySensor(foundSensor);
    }

    private void verifySensor(Sensor sensor) {
        assertNotNull(sensor);
        assertEquals(sensorName, sensor.getName());
    }
}
