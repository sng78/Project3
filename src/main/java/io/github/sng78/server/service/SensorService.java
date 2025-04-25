package io.github.sng78.server.service;

import io.github.sng78.server.model.Sensor;
import io.github.sng78.server.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor save(Sensor sensor) {
        sensorRepository.save(sensor);
        return sensor;
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }
}
