
package com.wildlife.patrolservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wildlife.patrolservice.entity.Patrol;
import com.wildlife.patrolservice.service.PatrolService;

@RestController
@RequestMapping("/api/patrols")
public class PatrolController {

    private final PatrolService patrolService;

    public PatrolController(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

    @GetMapping
    public List<Patrol> getAllPatrols() {
        return patrolService.getAllPatrols();
    }

    @GetMapping("/{id}")
    public Patrol getPatrolById(@PathVariable Long id) {
        return patrolService.getPatrolById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patrol addPatrol(@RequestBody Patrol patrol) {
        return patrolService.addPatrol(patrol);
    }

    @PutMapping("/{id}")
    public Patrol updatePatrol(
            @PathVariable Long id,
            @RequestBody Patrol patrol) {
        return patrolService.updatePatrol(id, patrol);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatrol(@PathVariable Long id) {
        patrolService.deletePatrol(id);
    }
}
