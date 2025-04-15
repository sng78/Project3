package io.github.sng78.server.controller;

import io.github.sng78.server.model.Sensor;
import io.github.sng78.server.service.SensorService;
import io.github.sng78.server.to.SensorTo;
import io.github.sng78.server.util.exception.ErrorResponse;
import io.github.sng78.server.util.exception.exceptionWithOverrideMessage;
import io.github.sng78.server.util.validation.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static io.github.sng78.server.util.exception.ErrorMessage.returnErrorMessage;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid SensorTo sensorTo,
                                           BindingResult bindingResult) {
        Sensor sensor = covertToSensor(sensorTo);
        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorMessage(bindingResult);
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor covertToSensor(SensorTo sensorTo) {
        return modelMapper.map(sensorTo, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(exceptionWithOverrideMessage exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
