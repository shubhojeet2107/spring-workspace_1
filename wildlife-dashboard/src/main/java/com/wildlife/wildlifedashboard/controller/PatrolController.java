package com.wildlife.wildlifedashboard.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/patrols")
public class PatrolController {

    private final String API_URL = "http://localhost:8084/api/patrols";

    private final RestTemplate restTemplate = new RestTemplate();

    // Display all patrol records
    @GetMapping
    public String listPatrols(Model model) {
        try {
            Patrol[] patrolArray =
                    restTemplate.getForObject(API_URL, Patrol[].class);

            List<Patrol> patrols = patrolArray == null
                    ? List.of()
                    : Arrays.asList(patrolArray);

            model.addAttribute("patrols", patrols);

        } catch (Exception e) {
            model.addAttribute("patrols", List.of());
            model.addAttribute(
                    "error",
                    "Unable to load patrols. Check whether Patrol Service is running on port 8084."
            );
        }

        return "patrols";
    }

    // Open the add patrol form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patrol", new Patrol());
        return "add-patrol";
    }

    // Save a new patrol
    @PostMapping("/save")
    public String savePatrol(@ModelAttribute Patrol patrol) {
        restTemplate.postForObject(API_URL, patrol, Patrol.class);
        return "redirect:/patrols";
    }

    // Open the edit patrol form
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Patrol patrol = restTemplate.getForObject(
                API_URL + "/" + id,
                Patrol.class
        );

        model.addAttribute("patrol", patrol);

        return "edit-patrol";
    }
    // Update an existing patrol
    @PostMapping("/update/{id}")
    public String updatePatrol(
            @PathVariable Long id,
            @ModelAttribute Patrol patrol) {

        patrol.setPatrolId(id);
        restTemplate.put(API_URL + "/" + id, patrol);

        return "redirect:/patrols";
    }

    // Delete a patrol
    @GetMapping("/delete/{id}")
    public String deletePatrol(@PathVariable Long id) {
        restTemplate.delete(API_URL + "/" + id);
        return "redirect:/patrols";
    }

    // Dashboard data class
    public static class Patrol {

        private Long patrolId;
        private String patrolDate;
        private String staffName;
        private String patrolTeam;
        private String protectedArea;
        private String route;
        private String activityDetails;
        private String status;

        public Patrol() {
        }

        public Long getPatrolId() {
            return patrolId;
        }

        public void setPatrolId(Long patrolId) {
            this.patrolId = patrolId;
        }

        public String getPatrolDate() {
            return patrolDate;
        }

        public void setPatrolDate(String patrolDate) {
            this.patrolDate = patrolDate;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getPatrolTeam() {
            return patrolTeam;
        }

        public void setPatrolTeam(String patrolTeam) {
            this.patrolTeam = patrolTeam;
        }

        public String getProtectedArea() {
            return protectedArea;
        }

        public void setProtectedArea(String protectedArea) {
            this.protectedArea = protectedArea;
        }

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
        }

        public String getActivityDetails() {
            return activityDetails;
        }

        public void setActivityDetails(String activityDetails) {
            this.activityDetails = activityDetails;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}