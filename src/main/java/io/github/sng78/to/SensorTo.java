package io.github.sng78.to;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SensorTo {

    @NotBlank(message = "Must be not empty")
    @Size(min = 3, max = 30, message = "Must be 3 - 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
