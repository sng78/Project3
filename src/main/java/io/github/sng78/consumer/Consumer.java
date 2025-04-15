package io.github.sng78.consumer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Consumer {
    public static void main(String[] args) {

        final String name = "Test Sensor";

        final int count = 1000;

        final double min = -100;

        final double max = 100;

        Map<String, Object> json = new HashMap<>();
        json.put("name", name);
        makePostRequest("http://localhost:8080/sensors/registration/", json);

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            json = new HashMap<>();
            double randomValue = min + random.nextDouble() * (max - min);
            json.put("value", Math.round(randomValue * 100.0) / 100.0);
            json.put("raining", Math.random() < 0.5);
            json.put("sensor", Map.of("name", name));
            makePostRequest("http://localhost:8080/measurements/add", json);
        }
    }

    private static void makePostRequest(String url, Map<String, Object> json) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(json, headers);

        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
