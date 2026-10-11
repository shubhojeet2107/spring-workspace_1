
package com.wildlife.wildlifedashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class RescueController {

    RestTemplate rest = new RestTemplate();

    String url = "http://localhost:8085/api/rescues";

    @GetMapping("/rescues")
    public String viewRescues(Model model) {
        List<Map<String, Object>> rescues =
                rest.getForObject(url, List.class);

        model.addAttribute("rescues", rescues);
        return "rescues";
    }

    @GetMapping("/rescues/add")
    public String addRescue(Model model) {
        model.addAttribute("rescue", new Rescue());
        return "add-rescue";
    }

    @PostMapping("/rescues/save")
    public String saveRescue(@ModelAttribute("rescue") Rescue rescue) {
        rest.postForObject(url, rescue, Map.class);
        return "redirect:/rescues";
    }

    @GetMapping("/rescues/edit/{id}")
    public String editRescue(@PathVariable Long id, Model model) {
        Map<String, Object> data =
                rest.getForObject(url + "/" + id, Map.class);

        Rescue rescue = new Rescue();

        rescue.setAnimalName((String) data.get("animalName"));
        rescue.setRescueDate(String.valueOf(data.get("rescueDate")));
        rescue.setLocation((String) data.get("location"));
        rescue.setAssignedStaff((String) data.get("assignedStaff"));
        rescue.setDescription((String) data.get("description"));
        rescue.setStatus((String) data.get("status"));

        model.addAttribute("id", id);
        model.addAttribute("rescue", rescue);

        return "edit-rescue";
    }

    @PostMapping("/rescues/update/{id}")
    public String updateRescue(@PathVariable Long id,
                               @ModelAttribute("rescue") Rescue rescue) {
        rest.put(url + "/" + id, rescue);
        return "redirect:/rescues";
    }

    @GetMapping("/rescues/delete/{id}")
    public String deleteRescue(@PathVariable Long id) {
        rest.delete(url + "/" + id);
        return "redirect:/rescues";
    }

    public static class Rescue {

        private String animalName;
        private String rescueDate;
        private String location;
        private String assignedStaff;
        private String description;
        private String status;

        public String getAnimalName() {
            return animalName;
        }

        public void setAnimalName(String animalName) {
            this.animalName = animalName;
        }

        public String getRescueDate() {
            return rescueDate;
        }

        public void setRescueDate(String rescueDate) {
            this.rescueDate = rescueDate;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAssignedStaff() {
            return assignedStaff;
        }

        public void setAssignedStaff(String assignedStaff) {
            this.assignedStaff = assignedStaff;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
