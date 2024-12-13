package com.sam.business.controller;

import com.sam.business.model.Incident;
import com.sam.business.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public Incident createIncident(@RequestBody Incident incident) {
        return incidentService.createIncident(incident);
    }

    @PutMapping("/{id}")
    public Incident updateIncident(@RequestBody Incident incident) {
        return incidentService.updateIncident(incident);
    }

    @DeleteMapping("/{id}")
    public void deleteIncident(@PathVariable String id) {
        incidentService.deleteIncident(id);
    }

    @GetMapping
    public List<Incident> getAllIncidents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return incidentService.getAllIncidents(page, size);
    }
}
