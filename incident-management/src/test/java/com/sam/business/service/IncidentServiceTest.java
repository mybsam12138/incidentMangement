package com.sam.business.service;


import com.sam.business.model.Incident;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IncidentServiceTest {

    @Test
    public void testCreateAndRetrieveIncident() {
        IncidentService service = new IncidentService();

        Incident incident = new Incident("1", "Test", "Description", "Open");
        service.createIncident(incident);

        List<Incident> incidents = service.getAllIncidents(0, 10);
        assertEquals(1, incidents.size());
        assertEquals("Test", incidents.get(0).getTitle());
    }

    @Test
    public void testDeleteIncident() {
        IncidentService service = new IncidentService();

        Incident incident = new Incident("1", "Test", "Description", "Open");
        service.createIncident(incident);
        service.deleteIncident("1");

        assertEquals(0, service.getAllIncidents(0, 10).size());
    }

    @Test
    public void testUpdateIncident() {
        IncidentService service = new IncidentService();

        Incident incident = new Incident("1", "Test", "Description", "Open");
        service.createIncident(incident);

        Incident updated = new Incident("1", "Updated", "Updated Description", "Closed");
        service.updateIncident(updated);

        Incident result = service.getAllIncidents(0, 10).get(0);
        assertEquals("Updated", result.getTitle());
    }
}