
package com.wildlife.habitatservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wildlife.habitatservice.entity.Habitat;
import com.wildlife.habitatservice.service.HabitatService;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {

    private final HabitatService service;

    public HabitatController(HabitatService service) {
        this.service = service;
    }

    @GetMapping
    public List<Habitat> getAllHabitats() {
        return service.getAllHabitats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitat> getHabitatById(
            @PathVariable Long id) {

        Habitat habitat = service.getHabitatById(id);

        if (habitat == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(habitat);
    }

    @PostMapping
    public ResponseEntity<Habitat> addHabitat(
            @RequestBody Habitat habitat) {

        Habitat saved = service.addHabitat(habitat);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitat> updateHabitat(
            @PathVariable Long id,
            @RequestBody Habitat habitat) {

        Habitat updated = service.updateHabitat(id, habitat);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitat(
            @PathVariable Long id) {

        boolean deleted = service.deleteHabitat(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
