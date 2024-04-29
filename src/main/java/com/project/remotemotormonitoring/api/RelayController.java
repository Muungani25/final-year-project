package com.project.remotemotormonitoring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RelayController {

    private final String esp32Url = "http://192.168.137.81"; // Replace with your ESP32's IP address

    @GetMapping("/relay")
    public String controlRelay(@RequestParam String action) {
        if (action.equalsIgnoreCase("on")) {
            sendHttpRequest(esp32Url + "/on");
            return "Relay turned ON";
        } else if (action.equalsIgnoreCase("off")) {
            sendHttpRequest(esp32Url + "/off");
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
