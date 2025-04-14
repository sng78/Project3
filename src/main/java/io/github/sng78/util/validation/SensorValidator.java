package io.github.sng78.util.validation;

import io.github.sng78.model.Sensor;
import io.github.sng78.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        Optional<Sensor> serviceFindByName = sensorService.findByName(sensor.getName());

        if (serviceFindByName.isPresent()) {
            errors.rejectValue("name", "", "Sensor already exists");
        }
    }
}
