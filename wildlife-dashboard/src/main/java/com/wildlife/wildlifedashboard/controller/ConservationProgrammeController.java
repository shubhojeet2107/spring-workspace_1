
package com.wildlife.wildlifedashboard.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConservationProgrammeController {

    private final String url = "http://localhost:8088/api/conservation-programmes";

    private final RestTemplate rest = new RestTemplate();

    @GetMapping("/conservation-programmes")
    public String listProgrammes(Model model) {
        try {
            ConservationProgramme[] programmes =
                    rest.getForObject(url, ConservationProgramme[].class);

            model.addAttribute("programmes",
                    programmes == null
                            ? Arrays.asList()
                            : Arrays.asList(programmes));

        } catch (Exception e) {
            model.addAttribute("programmes", List.of());
            model.addAttribute("error",
                    "Unable to load programmes. Please check whether the conservation service is running.");
        }

        return "conservation-programmes";
    }

    @GetMapping("/conservation-programmes/add")
    public String addForm(Model model) {
        model.addAttribute("programme", new ConservationProgramme());
        return "add-conservation-programme";
    }

    @PostMapping("/conservation-programmes/save")
    public String saveProgramme(
            @ModelAttribute ConservationProgramme programme) {

        rest.postForObject(url, programme, ConservationProgramme.class);
        return "redirect:/conservation-programmes";
    }

    @GetMapping("/conservation-programmes/edit/{id}")
    public String editForm(
            @PathVariable Long id, Model model) {

        ConservationProgramme programme =
                rest.getForObject(url + "/" + id,
                        ConservationProgramme.class);

        model.addAttribute("programme", programme);
        model.addAttribute("id", id);

        return "edit-conservation-programme";
    }

    @PostMapping("/conservation-programmes/update/{id}")
    public String updateProgramme(
            @PathVariable Long id,
            @ModelAttribute ConservationProgramme programme) {

        rest.put(url + "/" + id, programme);
        return "redirect:/conservation-programmes";
    }

    @GetMapping("/conservation-programmes/delete/{id}")
    public String deleteProgramme(@PathVariable Long id) {
        rest.delete(url + "/" + id);
        return "redirect:/conservation-programmes";
    }

    public static class ConservationProgramme {

        private Long programmeId;
        private String programmeName;
        private String description;
        private String startDate;
        private String endDate;
        private String target;
        private int progressPercentage;
        private String assignedStaff;
        private String status;

        public Long getProgrammeId() {
            return programmeId;
        }

        public void setProgrammeId(Long programmeId) {
            this.programmeId = programmeId;
        }

        public String getProgrammeName() {
            return programmeName;
        }

        public void setProgrammeName(String programmeName) {
            this.programmeName = programmeName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public int getProgressPercentage() {
            return progressPercentage;
        }

        public void setProgressPercentage(int progressPercentage) {
            this.progressPercentage = progressPercentage;
        }

        public String getAssignedStaff() {
            return assignedStaff;
        }

        public void setAssignedStaff(String assignedStaff) {
            this.assignedStaff = assignedStaff;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
