package com.project.remotemotormonitoring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RelayController {

    private final String esp32Url = "http://192.168.137.183"; // Replace with your ESP32's IP address

    @GetMapping("/relay")
    public String controlRelay(@RequestParam String action) {
        if (action.equalsIgnoreCase("start")) {
            sendHttpRequest(esp32Url + "/start");
            return "Relay turned ON";
        } else if (action.equalsIgnoreCase("stop")) {
            sendHttpRequest(esp32Url + "/stop");
            return "Relay turned OFF";
        } else {
            return "Invalid action";
        }
    }

    private void sendHttpRequest(String urlString) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(urlString, String.class);
    }
}
