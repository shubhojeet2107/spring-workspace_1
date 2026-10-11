
package com.wildlife.wildlifedashboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class DashboardController {

    RestTemplate rest = new RestTemplate();

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("animals",
                count("http://localhost:8081/api/animals"));

        model.addAttribute("areas",
                count("http://localhost:8082/api/protected-area"));

        model.addAttribute("incidents",
                count("http://localhost:8083/api/incidents"));

        model.addAttribute("patrols",
                count("http://localhost:8084/api/patrols"));

        model.addAttribute("rescues",
                count("http://localhost:8085/api/rescues"));

        model.addAttribute("staff",
                count("http://localhost:8086/api/staff"));

        model.addAttribute("resources",
                count("http://localhost:8086/api/resources"));

  
        model.addAttribute("programmes",
                count("http://localhost:8088/api/conservation-programmes"));
        
        model.addAttribute("habitats",
                count("http://localhost:8089/api/habitats"));

        return "dashboard";
    }

    public int count(String url) {
        try {
            List<?> records = rest.getForObject(url, List.class);

            if (records == null) {
                return 0;
            }

            return records.size();

        } catch (Exception e) {
            return -1;
        }
    }
}
