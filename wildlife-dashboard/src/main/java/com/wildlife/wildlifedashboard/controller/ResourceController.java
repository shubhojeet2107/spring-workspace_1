
package com.wildlife.wildlifedashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ResourceController {

    RestTemplate rest = new RestTemplate();

    String url = "http://localhost:8086/api/resources";

    @GetMapping("/resources")
    public String viewResources(Model model) {
        List<Map<String, Object>> resources =
                rest.getForObject(url, List.class);

        model.addAttribute("resources", resources);
        return "resources";
    }

    @GetMapping("/resources/add")
    public String addResource(Model model) {
        model.addAttribute("resource", new Resource());
        return "add-resource";
    }

    @PostMapping("/resources/save")
    public String saveResource(
            @ModelAttribute("resource") Resource resource) {
        rest.postForObject(url, resource, Map.class);
        return "redirect:/resources";
    }

    @GetMapping("/resources/edit/{id}")
    public String editResource(@PathVariable Long id, Model model) {
        Map<String, Object> data =
                rest.getForObject(url + "/" + id, Map.class);

        Resource resource = new Resource();

        resource.setResourceName((String) data.get("resourceName"));
        resource.setResourceType((String) data.get("resourceType"));
        resource.setQuantity(((Number) data.get("quantity")).intValue());
        resource.setAssignedArea((String) data.get("assignedArea"));
        resource.setStatus((String) data.get("status"));

        model.addAttribute("id", id);
        model.addAttribute("resource", resource);

        return "edit-resource";
    }

    @PostMapping("/resources/update/{id}")
    public String updateResource(
            @PathVariable Long id,
            @ModelAttribute("resource") Resource resource) {
        rest.put(url + "/" + id, resource);
        return "redirect:/resources";
    }

    @GetMapping("/resources/delete/{id}")
    public String deleteResource(@PathVariable Long id) {
        rest.delete(url + "/" + id);
        return "redirect:/resources";
    }

    public static class Resource {

        private String resourceName;
        private String resourceType;
        private int quantity;
        private String assignedArea;
        private String status;

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getAssignedArea() {
            return assignedArea;
        }

        public void setAssignedArea(String assignedArea) {
            this.assignedArea = assignedArea;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}