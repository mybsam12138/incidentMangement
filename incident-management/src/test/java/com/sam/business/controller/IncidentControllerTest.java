package com.sam.business.controller;


import com.sam.business.model.Incident;
import com.sam.business.service.IncidentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IncidentController.class)
public class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentService incidentService;

    @Test
    public void testCreateIncident() throws Exception {
        Incident incident = new Incident("1", "Test Incident", "Description", "Open");

        Mockito.when(incidentService.createIncident(Mockito.any(Incident.class))).thenReturn(incident);

        mockMvc.perform(post("/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"title\":\"Test Incident\",\"description\":\"Description\",\"status\":\"Open\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Test Incident"));
    }

    @Test
    public void testGetAllIncidents() throws Exception {
        mockMvc.perform(get("/incidents"))
                .andExpect(status().isOk());
    }
}