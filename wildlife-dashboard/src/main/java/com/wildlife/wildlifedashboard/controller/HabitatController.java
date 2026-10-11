
package com.wildlife.wildlifedashboard.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class HabitatController {

    private final String url = "http://localhost:8089/api/habitats";

    private final RestTemplate rest = new RestTemplate();

    @GetMapping("/habitats")
    public String listHabitats(Model model) {
        try {
            Habitat[] habitats = rest.getForObject(url, Habitat[].class);

            model.addAttribute("habitats",
                    habitats == null ? Arrays.asList() : Arrays.asList(habitats));

        } catch (Exception e) {
            model.addAttribute("habitats", List.of());
            model.addAttribute("error",
                    "Unable to load habitats. Check whether Habitat Service is running.");
        }

        return "habitats";
    }

    @GetMapping("/habitats/add")
    public String addForm(Model model) {
        model.addAttribute("habitat", new Habitat());
        return "add-habitat";
    }

    @PostMapping("/habitats/save")
    public String saveHabitat(@ModelAttribute Habitat habitat) {
        rest.postForObject(url, habitat, Habitat.class);
        return "redirect:/habitats";
    }

    @GetMapping("/habitats/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Habitat habitat = rest.getForObject(url + "/" + id, Habitat.class);

        model.addAttribute("habitat", habitat);
        model.addAttribute("id", id);

        return "edit-habitat";
    }

    @PostMapping("/habitats/update/{id}")
    public String updateHabitat(
            @PathVariable Long id,
            @ModelAttribute Habitat habitat) {

        rest.put(url + "/" + id, habitat);
        return "redirect:/habitats";
    }

    @GetMapping("/habitats/delete/{id}")
    public String deleteHabitat(@PathVariable Long id) {
        rest.delete(url + "/" + id);
        return "redirect:/habitats";
    }

    public static class Habitat {

        private Long habitatId;
        private String protectedArea;
        private String zoneName;
        private String habitatType;
        private String waterResource;
        private String geographicLocation;
        private double areaSize;
        private String conservationStatus;

        public Long getHabitatId() { return habitatId; }
        public void setHabitatId(Long habitatId) { this.habitatId = habitatId; }

        public String getProtectedArea() { return protectedArea; }
        public void setProtectedArea(String protectedArea) { this.protectedArea = protectedArea; }

        public String getZoneName() { return zoneName; }
        public void setZoneName(String zoneName) { this.zoneName = zoneName; }

        public String getHabitatType() { return habitatType; }
        public void setHabitatType(String habitatType) { this.habitatType = habitatType; }

        public String getWaterResource() { return waterResource; }
        public void setWaterResource(String waterResource) { this.waterResource = waterResource; }

        public String getGeographicLocation() { return geographicLocation; }
        public void setGeographicLocation(String geographicLocation) { this.geographicLocation = geographicLocation; }

        public double getAreaSize() { return areaSize; }
        public void setAreaSize(double areaSize) { this.areaSize = areaSize; }

        public String getConservationStatus() { return conservationStatus; }
        public void setConservationStatus(String conservationStatus) { this.conservationStatus = conservationStatus; }
    }
}
