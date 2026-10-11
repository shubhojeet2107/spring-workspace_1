
package com.wildlife.conservationservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wildlife.conservationservice.entity.ConservationProgramme;
import com.wildlife.conservationservice.service.ConservationProgrammeService;

@RestController
@RequestMapping("/api/conservation-programmes")
public class ConservationProgrammeController {

    private final ConservationProgrammeService service;

    public ConservationProgrammeController(
            ConservationProgrammeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConservationProgramme> getAllProgrammes() {
        return service.getAllProgrammes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConservationProgramme> getProgrammeById(
            @PathVariable Long id) {

        ConservationProgramme programme = service.getProgrammeById(id);

        if (programme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(programme);
    }

    @PostMapping
    public ResponseEntity<ConservationProgramme> addProgramme(
            @RequestBody ConservationProgramme programme) {

        ConservationProgramme saved = service.saveProgramme(programme);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConservationProgramme> updateProgramme(
            @PathVariable Long id,
            @RequestBody ConservationProgramme programme) {

        ConservationProgramme updated =
                service.updateProgramme(id, programme);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramme(
            @PathVariable Long id) {

        boolean deleted = service.deleteProgramme(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
