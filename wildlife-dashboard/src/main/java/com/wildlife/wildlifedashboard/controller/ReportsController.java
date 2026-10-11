
package com.wildlife.wildlifedashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ReportsController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String animalsUrl =
            "http://localhost:8081/api/animals";
    private final String areasUrl =
            "http://localhost:8082/api/protected-area";
    private final String incidentsUrl =
            "http://localhost:8083/api/incidents";
    private final String patrolsUrl =
            "http://localhost:8084/api/patrols";
    private final String rescuesUrl =
            "http://localhost:8085/api/rescues";
    private final String staffUrl =
            "http://localhost:8086/api/staff";
    private final String resourcesUrl =
            "http://localhost:8086/api/resources";
    private final String programmesUrl =
            "http://localhost:8088/api/conservation-programmes";
    private final String habitatsUrl =
            "http://localhost:8089/api/habitats";

    private int countRecords(String url) {
        try {
            List<Map<String, Object>> records =
                    restTemplate.getForObject(url, List.class);

            if (records == null) {
                return 0;
            }

            return records.size();

        } catch (Exception e) {
            return -1;
        }
    }

    @GetMapping("/reports")
    public String showReports(Model model) {

        model.addAttribute("animalCount",
                countRecords(animalsUrl));
        model.addAttribute("areaCount",
                countRecords(areasUrl));
        model.addAttribute("incidentCount",
                countRecords(incidentsUrl));
        model.addAttribute("patrolCount",
                countRecords(patrolsUrl));
        model.addAttribute("rescueCount",
                countRecords(rescuesUrl));
        model.addAttribute("staffCount",
                countRecords(staffUrl));
        model.addAttribute("resourceCount",
                countRecords(resourcesUrl));
        model.addAttribute("programmeCount",
                countRecords(programmesUrl));
        model.addAttribute("habitatCount",
                countRecords(habitatsUrl));

        return "reports";
    }
}
