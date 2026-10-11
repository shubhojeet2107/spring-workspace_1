
package com.wildlife.wildlifedashboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProtectedAreaController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "http://localhost:8082/api/protected-areas";

    // Display all protected areas
    @GetMapping("/protected-areas")
    public String showAreas(Model model) {
        try {
            List<Map<String, Object>> areas = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            ).getBody();

            if (areas == null) {
                areas = new ArrayList<>();
            }

            model.addAttribute("areas", areas);

        } catch (Exception e) {
            model.addAttribute("areas", new ArrayList<>());
            model.addAttribute("error",
                "Could not load protected areas. Check whether the service is running.");
        }

        return "protected-areas";
    }

    // Display Add form
    @GetMapping("/protected-areas/add")
    public String showAddForm() {
        return "add-protected-area";
    }

    // Add a protected area
    @PostMapping("/protected-areas/add")
    public String addArea(
            @ModelAttribute AreaForm area,
            RedirectAttributes redirectAttributes) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AreaForm> request = new HttpEntity<>(area, headers);

            restTemplate.postForEntity(baseUrl, request, String.class);

            redirectAttributes.addFlashAttribute(
                "success", "Protected area added successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not add protected area. Check the service.");
        }

        return "redirect:/protected-areas";
    }

    // Display Edit form
    @GetMapping("/protected-areas/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Map<String, Object> area = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            ).getBody();

            if (area == null) {
                redirectAttributes.addFlashAttribute(
                    "error", "Protected area not found.");
                return "redirect:/protected-areas";
            }

            model.addAttribute("area", area);
            return "edit-protected-area";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not retrieve protected area.");
            return "redirect:/protected-areas";
        }
    }

    // Update protected area
    @PostMapping("/protected-areas/edit/{id}")
    public String updateArea(
            @PathVariable Long id,
            @ModelAttribute AreaForm area,
            RedirectAttributes redirectAttributes) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AreaForm> request = new HttpEntity<>(area, headers);

            restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.PUT,
                request,
                String.class
            );

            redirectAttributes.addFlashAttribute(
                "success", "Protected area updated successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not update protected area. Check the service.");
        }

        return "redirect:/protected-areas";
    }

    // Delete protected area
    @PostMapping("/protected-areas/delete/{id}")
    public String deleteArea(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            restTemplate.delete(baseUrl + "/" + id);

            redirectAttributes.addFlashAttribute(
                "success", "Protected area deleted successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not delete protected area. Check the service.");
        }

        return "redirect:/protected-areas";
    }

    // Form fields
    public static class AreaForm {
        private String areaName;
        private String areaType;
        private String location;
        private Double totalArea;
        private String status;

        public AreaForm() {
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAreaType() {
            return areaType;
        }

        public void setAreaType(String areaType) {
            this.areaType = areaType;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Double getTotalArea() {
            return totalArea;
        }

        public void setTotalArea(Double totalArea) {
            this.totalArea = totalArea;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
