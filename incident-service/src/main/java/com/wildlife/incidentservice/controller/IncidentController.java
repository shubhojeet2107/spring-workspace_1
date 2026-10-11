
package com.wildlife.incidentservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wildlife.incidentservice.entity.Incident;
import com.wildlife.incidentservice.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // Get all incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    // Get an incident by ID
    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentService.getIncidentById(id);
    }

    // Add an incident
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Incident addIncident(@RequestBody Incident incident) {
        return incidentService.addIncident(incident);
    }

    // Update an incident
    @PutMapping("/{id}")
    public Incident updateIncident(
            @PathVariable Long id,
            @RequestBody Incident incident) {
        return incidentService.updateIncident(id, incident);
    }

    // Delete an incident
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
    }
}
