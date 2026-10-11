
package com.wildlife.rescueservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wildlife.rescueservice.entity.Rescue;
import com.wildlife.rescueservice.service.RescueService;

@RestController
@RequestMapping("/api/rescues")
public class RescueController {

    private final RescueService rescueService;

    public RescueController(RescueService rescueService) {
        this.rescueService = rescueService;
    }

    @GetMapping
    public List<Rescue> getAllRescues() {
        return rescueService.getAllRescues();
    }

    @GetMapping("/{id}")
    public Rescue getRescueById(@PathVariable Long id) {
        return rescueService.getRescueById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rescue addRescue(@RequestBody Rescue rescue) {
        return rescueService.addRescue(rescue);
    }

    @PutMapping("/{id}")
    public Rescue updateRescue(
            @PathVariable Long id,
            @RequestBody Rescue rescue) {
        return rescueService.updateRescue(id, rescue);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRescue(@PathVariable Long id) {
        rescueService.deleteRescue(id);
    }
}
