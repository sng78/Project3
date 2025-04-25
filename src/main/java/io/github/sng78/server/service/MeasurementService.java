package io.github.sng78.server.service;

import io.github.sng78.server.model.Measurement;
import io.github.sng78.server.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository repository, SensorService sensorService) {
        this.measurementRepository = repository;
        this.sensorService = sensorService;
    }

    @Transactional
    public Measurement save(Measurement measurement) {
        measurement.setDateTime(LocalDateTime.now());
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).orElse(null));
        measurementRepository.save(measurement);
        return measurement;
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public Long getRainyDays() {
        return measurementRepository.findAll().stream().filter(Measurement::isRaining).count();
    }
}
