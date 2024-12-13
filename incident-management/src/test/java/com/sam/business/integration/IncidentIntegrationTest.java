package com.sam.business.integration;

import com.sam.business.model.Incident;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncidentIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testIncidentLifecycle() {
        String baseUrl = "http://localhost:" + port + "/incidents";

        // Create Incident
        Incident newIncident = new Incident("1", "Test Incident", "Integration Test Description", "Open");
        ResponseEntity<Incident> createResponse = restTemplate.postForEntity(baseUrl, newIncident, Incident.class);

        assertEquals(200, createResponse.getStatusCodeValue());
        assertNotNull(Objects.requireNonNull(createResponse.getBody()).getId());

        // Get All Incidents
        ResponseEntity<Incident[]> getAllResponse = restTemplate.getForEntity(baseUrl, Incident[].class);

        assertEquals(200, getAllResponse.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(getAllResponse.getBody()).length);

        // Delete Incident
        restTemplate.delete(baseUrl + "/1");

        // Verify Deletion
        ResponseEntity<Incident[]> afterDeleteResponse = restTemplate.getForEntity(baseUrl, Incident[].class);
        assertEquals(0, Objects.requireNonNull(afterDeleteResponse.getBody()).length);
    }
}