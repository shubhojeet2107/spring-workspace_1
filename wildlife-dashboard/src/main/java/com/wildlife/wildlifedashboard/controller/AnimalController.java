
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
public class AnimalController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "http://localhost:8081/api/animals";

    // Display all animals
    @GetMapping("/animals")
    public String showAnimals(Model model) {

        try {
            List<Map<String, Object>> animals = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            ).getBody();

            if (animals == null) {
                animals = new ArrayList<>();
            }

            model.addAttribute("animals", animals);

        } catch (Exception e) {
            model.addAttribute("animals", new ArrayList<>());
            model.addAttribute(
                "error",
                "Could not load animal records. Please check Wildlife Service."
            );
        }

        return "animals";
    }

    // Display Add Animal form
    @GetMapping("/animals/add")
    public String showAddAnimalForm() {
        return "add-animal";
    }

    // Save a new animal
    @PostMapping("/animals/add")
    public String addAnimal(
            @ModelAttribute AnimalForm animal,
            RedirectAttributes redirectAttributes) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AnimalForm> request =
                new HttpEntity<>(animal, headers);

            restTemplate.postForEntity(
                baseUrl, request, String.class
            );

            redirectAttributes.addFlashAttribute(
                "success", "Animal added successfully!"
            );

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not add animal. Please check Wildlife Service."
            );
        }

        return "redirect:/animals";
    }

    // Display Edit Animal form
    @GetMapping("/animals/edit/{id}")
    public String showEditAnimalForm(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            Map<String, Object> animal = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            ).getBody();

            if (animal == null) {
                redirectAttributes.addFlashAttribute(
                    "error", "Animal record not found."
                );
                return "redirect:/animals";
            }

            model.addAttribute("animal", animal);
            return "edit-animal";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not retrieve animal for editing."
            );
            return "redirect:/animals";
        }
    }

    // Update an existing animal
    @PostMapping("/animals/edit/{id}")
    public String updateAnimal(
            @PathVariable Long id,
            @ModelAttribute AnimalForm animal,
            RedirectAttributes redirectAttributes) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AnimalForm> request =
                new HttpEntity<>(animal, headers);

            restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.PUT,
                request,
                String.class
            );

            redirectAttributes.addFlashAttribute(
                "success", "Animal updated successfully!"
            );

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not update animal. Please check Wildlife Service."
            );
        }

        return "redirect:/animals";
    }

    // Delete an animal
    @PostMapping("/animals/delete/{id}")
    public String deleteAnimal(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {
            restTemplate.delete(baseUrl + "/" + id);

            redirectAttributes.addFlashAttribute(
                "success", "Animal deleted successfully!"
            );

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                "error", "Could not delete animal. Please check Wildlife Service."
            );
        }

        return "redirect:/animals";
    }

    // Fields received from the Add and Edit forms
    public static class AnimalForm {

        private String speciesName;
        private String identificationCode;
        private Integer age;
        private String healthStatus;
        private String location;

        public AnimalForm() {
        }

        public String getSpeciesName() {
            return speciesName;
        }

        public void setSpeciesName(String speciesName) {
            this.speciesName = speciesName;
        }

        public String getIdentificationCode() {
            return identificationCode;
        }

        public void setIdentificationCode(String identificationCode) {
            this.identificationCode = identificationCode;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public void setHealthStatus(String healthStatus) {
            this.healthStatus = healthStatus;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
