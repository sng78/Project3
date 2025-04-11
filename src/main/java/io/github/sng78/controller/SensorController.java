package io.github.sng78.controller;

import io.github.sng78.model.Sensor;
import io.github.sng78.service.SensorService;
import io.github.sng78.to.SensorTo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid SensorTo sensorTo) {
        sensorService.save(covertToSensor(sensorTo));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor covertToSensor(SensorTo sensorTo) {
        return modelMapper.map(sensorTo, Sensor.class);
    }
}
