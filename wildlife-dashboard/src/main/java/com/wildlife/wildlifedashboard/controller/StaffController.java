package com.wildlife.wildlifedashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class StaffController {

    RestTemplate rest = new RestTemplate();

    String url = "http://localhost:8086/api/staff";

    @GetMapping("/staff")
    public String viewStaff(Model model) {
        List<Map<String, Object>> staff =
                rest.getForObject(url, List.class);

        model.addAttribute("staffList", staff);
        return "staff";
    }

    @GetMapping("/staff/add")
    public String addStaff(Model model) {
        model.addAttribute("staff", new Staff());
        return "add-staff";
    }

    @PostMapping("/staff/save")
    public String saveStaff(@ModelAttribute("staff") Staff staff) {
        rest.postForObject(url, staff, Map.class);
        return "redirect:/staff";
    }

    @GetMapping("/staff/edit/{id}")
    public String editStaff(@PathVariable Long id, Model model) {
        Map<String, Object> data =
                rest.getForObject(url + "/" + id, Map.class);

        Staff staff = new Staff();
        staff.setStaffName((String) data.get("staffName"));
        staff.setRole((String) data.get("role"));
        staff.setPhone((String) data.get("phone"));
        staff.setEmail((String) data.get("email"));
        staff.setAssignedArea((String) data.get("assignedArea"));

        model.addAttribute("id", id);
        model.addAttribute("staff", staff);

        return "edit-staff";
    }

    @PostMapping("/staff/update/{id}")
    public String updateStaff(@PathVariable Long id,
                              @ModelAttribute("staff") Staff staff) {
        rest.put(url + "/" + id, staff);
        return "redirect:/staff";
    }

    @GetMapping("/staff/delete/{id}")
    public String deleteStaff(@PathVariable Long id) {
        rest.delete(url + "/" + id);
        return "redirect:/staff";
    }

    public static class Staff {
        private String staffName;
        private String role;
        private String phone;
        private String email;
        private String assignedArea;

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAssignedArea() {
            return assignedArea;
        }

        public void setAssignedArea(String assignedArea) {
            this.assignedArea = assignedArea;
        }
    }
}