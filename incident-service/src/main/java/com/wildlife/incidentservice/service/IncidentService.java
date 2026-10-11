
package com.wildlife.incidentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.incidentservice.entity.Incident;
import com.wildlife.incidentservice.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    // Get all incidents
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Add a new incident
    public Incident addIncident(Incident incident) {
        incident.setIncidentId(null);
        return incidentRepository.save(incident);
    }

    // Get an incident by ID without using .orElse()
    public Incident getIncidentById(Long id) {
        List<Incident> incidents = incidentRepository.findAll();

        for (Incident incident : incidents) {
            if (incident.getIncidentId().equals(id)) {
                return incident;
            }
        }

        return null;
    }

    // Update an existing incident
    public Incident updateIncident(Long id, Incident updatedIncident) {
        List<Incident> incidents = incidentRepository.findAll();

        for (Incident incident : incidents) {
            if (incident.getIncidentId().equals(id)) {
                incident.setIncidentType(updatedIncident.getIncidentType());
                incident.setDescription(updatedIncident.getDescription());
                incident.setLocation(updatedIncident.getLocation());
                incident.setIncidentDate(updatedIncident.getIncidentDate());
                incident.setSeverity(updatedIncident.getSeverity());
                incident.setStatus(updatedIncident.getStatus());

                return incidentRepository.save(incident);
            }
        }

        return null;
    }

    // Delete an incident
    public boolean deleteIncident(Long id) {
        List<Incident> incidents = incidentRepository.findAll();

        for (Incident incident : incidents) {
            if (incident.getIncidentId().equals(id)) {
                incidentRepository.delete(incident);
                return true;
            }
        }

        return false;
    }
}
