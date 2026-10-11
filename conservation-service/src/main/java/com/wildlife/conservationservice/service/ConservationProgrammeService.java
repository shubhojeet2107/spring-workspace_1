
package com.wildlife.conservationservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.conservationservice.entity.ConservationProgramme;
import com.wildlife.conservationservice.repository.ConservationProgrammeRepository;

@Service
public class ConservationProgrammeService {

    private final ConservationProgrammeRepository repository;

    public ConservationProgrammeService(
            ConservationProgrammeRepository repository) {
        this.repository = repository;
    }

    public List<ConservationProgramme> getAllProgrammes() {
        return repository.findAll();
    }

    public ConservationProgramme getProgrammeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ConservationProgramme saveProgramme(
            ConservationProgramme programme) {
        return repository.save(programme);
    }

    public ConservationProgramme updateProgramme(
            Long id, ConservationProgramme programme) {

        ConservationProgramme existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setProgrammeName(programme.getProgrammeName());
        existing.setDescription(programme.getDescription());
        existing.setStartDate(programme.getStartDate());
        existing.setEndDate(programme.getEndDate());
        existing.setTarget(programme.getTarget());
        existing.setProgressPercentage(programme.getProgressPercentage());
        existing.setAssignedStaff(programme.getAssignedStaff());
        existing.setStatus(programme.getStatus());

        return repository.save(existing);
    }

    public boolean deleteProgramme(Long id) {
        ConservationProgramme existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return false;
        }

        repository.delete(existing);
        return true;
    }
}
