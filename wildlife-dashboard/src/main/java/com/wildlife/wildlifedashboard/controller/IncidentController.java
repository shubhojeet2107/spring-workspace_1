
package com.wildlife.wildlifedashboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class IncidentController {

    RestTemplate rest = new RestTemplate();

    String url = "http://localhost:8083/api/incidents";

    // View and filter incidents
    @GetMapping("/incidents")
    public String viewIncidents(
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "") String startDate,
            @RequestParam(defaultValue = "") String endDate,
            @RequestParam(defaultValue = "") String severity,
            @RequestParam(defaultValue = "") String status,
            Model model) {

        List<Map<String, Object>> allIncidents =
                rest.getForObject(url, List.class);

        List<Map<String, Object>> filtered = new ArrayList<>();

        if (allIncidents != null) {
            for (Map<String, Object> incident : allIncidents) {

                String incidentLocation =
                        String.valueOf(incident.get("location"));

                String incidentDate =
                        String.valueOf(incident.get("incidentDate"));

                String incidentSeverity =
                        String.valueOf(incident.get("severity"));

                String incidentStatus =
                        String.valueOf(incident.get("status"));

                boolean match = true;

                if (!location.isEmpty()
                        && !incidentLocation.toLowerCase()
                        .contains(location.toLowerCase())) {
                    match = false;
                }

                if (!startDate.isEmpty()
                        && incidentDate.compareTo(startDate) < 0) {
                    match = false;
                }

                if (!endDate.isEmpty()
                        && incidentDate.compareTo(endDate) > 0) {
                    match = false;
                }

                if (!severity.isEmpty()
                        && !incidentSeverity.equalsIgnoreCase(severity)) {
                    match = false;
                }

                if (!status.isEmpty()
                        && !incidentStatus.equalsIgnoreCase(status)) {
                    match = false;
                }

                if (match) {
                    filtered.add(incident);
                }
            }
        }

        model.addAttribute("incidents", filtered);
        model.addAttribute("location", location);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("severity", severity);
        model.addAttribute("status", status);

        return "incidents";
    }

    // Show add form
    @GetMapping("/incidents/add")
    public String addIncident(Model model) {
        model.addAttribute("incident", new Incident());
        return "add-incident";
    }

    // Save incident
    @PostMapping("/incidents/save")
    public String saveIncident(
            @ModelAttribute("incident") Incident incident) {
        rest.postForObject(url, incident, Map.class);
        return "redirect:/incidents";
    }

    // Show edit form
    @GetMapping("/incidents/edit/{id}")
    public String editIncident(@PathVariable Long id, Model model) {

        Map<String, Object> data =
                rest.getForObject(url + "/" + id, Map.class);

        Incident incident = new Incident();

        incident.setIncidentType((String) data.get("incidentType"));
        incident.setDescription((String) data.get("description"));
        incident.setLocation((String) data.get("location"));
        incident.setIncidentDate(
                String.valueOf(data.get("incidentDate")));
        incident.setSeverity((String) data.get("severity"));
        incident.setStatus((String) data.get("status"));

        model.addAttribute("id", id);
        model.addAttribute("incident", incident);

        return "edit-incident";
    }

    // Update incident
    @PostMapping("/incidents/update/{id}")
    public String updateIncident(
            @PathVariable Long id,
            @ModelAttribute("incident") Incident incident) {

        rest.put(url + "/" + id, incident);

        return "redirect:/incidents";
    }

    // Delete incident
    @GetMapping("/incidents/delete/{id}")
    public String deleteIncident(@PathVariable Long id) {
        rest.delete(url + "/" + id);
        return "redirect:/incidents";
    }

    // Incident form fields
    public static class Incident {

        private String incidentType;
        private String description;
        private String location;
        private String incidentDate;
        private String severity;
        private String status;

        public String getIncidentType() {
            return incidentType;
        }

        public void setIncidentType(String incidentType) {
            this.incidentType = incidentType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getIncidentDate() {
            return incidentDate;
        }

        public void setIncidentDate(String incidentDate) {
            this.incidentDate = incidentDate;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
