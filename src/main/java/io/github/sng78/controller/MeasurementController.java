package io.github.sng78.controller;

import io.github.sng78.model.Measurement;
import io.github.sng78.service.MeasurementService;
import io.github.sng78.to.MeasurementTo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody MeasurementTo measurementTo) {
        measurementService.save(convertToMeasurement(measurementTo));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementTo> getAll() {
        return measurementService.getAll().stream()
                .map(this::convertToMeasurementTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.getRainyDays();
    }

    private Measurement convertToMeasurement(MeasurementTo measurementTo) {
        return modelMapper.map(measurementTo, Measurement.class);
    }

    private MeasurementTo convertToMeasurementTo(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementTo.class);
    }
}
