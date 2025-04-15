package io.github.sng78.server.controller;

import io.github.sng78.server.model.Measurement;
import io.github.sng78.server.service.MeasurementService;
import io.github.sng78.server.to.MeasurementTo;
import io.github.sng78.server.util.exception.ErrorResponse;
import io.github.sng78.server.util.exception.exceptionWithOverrideMessage;
import io.github.sng78.server.util.validation.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.sng78.server.util.exception.ErrorMessage.returnErrorMessage;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    private final ModelMapper modelMapper;

    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid MeasurementTo measurementTo,
                                           BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementTo);
        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorMessage(bindingResult);
        }

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

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(exceptionWithOverrideMessage exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
