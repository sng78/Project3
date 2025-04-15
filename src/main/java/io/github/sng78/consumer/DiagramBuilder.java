package io.github.sng78.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.sng78.server.to.MeasurementTo;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DiagramBuilder {
    public static void main(String[] args) throws JsonProcessingException {
        List<MeasurementTo> measurements = getMeasurements();
        drawDiagram(measurements);
    }

    private static List<MeasurementTo> getMeasurements() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject("http://localhost:8080/measurements/", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(string, new TypeReference<>() {
        });
    }

    private static void drawDiagram(List<MeasurementTo> measurements) {
        int size = measurements.size();
        double[] xData = new double[size];
        double[] yData = new double[size];
        for (int i = 0; i < size; i++) {
            xData[i] = i;
            yData[i] = measurements.get(i).getValue();
        }

        XYChart chart = QuickChart.getChart(
                "Temperature chart", "Measurement", "Temperature", "y(x)", xData, yData);
        SwingWrapper<XYChart> swingWrapper = new SwingWrapper<>(chart);
        swingWrapper.displayChart();
    }
}
