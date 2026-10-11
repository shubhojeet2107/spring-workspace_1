
package com.wildlife.protectedareaservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wildlife.protectedareaservice.entity.ProtectedArea;
import com.wildlife.protectedareaservice.service.ProtectedAreaService;

@RestController
@RequestMapping("/api/protected-area")
public class ProtectedAreaController {

    private final ProtectedAreaService areaService;

    public ProtectedAreaController(ProtectedAreaService areaService) {
        this.areaService = areaService;
    }

    // Get all protected areas
    @GetMapping
    public List<ProtectedArea> getAllAreas() {
        return areaService.getAllAreas();
    }

    // Get a protected area by ID
    @GetMapping("/{id}")
    public ProtectedArea getAreaById(@PathVariable Long id) {
        return areaService.getAreaById(id);
    }

    // Add a protected area
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProtectedArea addArea(@RequestBody ProtectedArea area) {
        return areaService.addArea(area);
    }

    // Update a protected area
    @PutMapping("/{id}")
    public ProtectedArea updateArea(
            @PathVariable Long id,
            @RequestBody ProtectedArea area) {
        return areaService.updateArea(id, area);
    }

    // Delete a protected area
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArea(@PathVariable Long id) {
        areaService.deleteArea(id);
    }
}
