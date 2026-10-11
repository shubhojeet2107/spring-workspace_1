
package com.wildlife.habitatservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.habitatservice.entity.Habitat;
import com.wildlife.habitatservice.repository.HabitatRepository;

@Service
public class HabitatService {

    private final HabitatRepository repository;

    public HabitatService(HabitatRepository repository) {
        this.repository = repository;
    }

    public List<Habitat> getAllHabitats() {
        return repository.findAll();
    }

    public Habitat getHabitatById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Habitat addHabitat(Habitat habitat) {
        return repository.save(habitat);
    }

    public Habitat updateHabitat(Long id, Habitat habitat) {
        Habitat existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setProtectedArea(habitat.getProtectedArea());
        existing.setZoneName(habitat.getZoneName());
        existing.setHabitatType(habitat.getHabitatType());
        existing.setWaterResource(habitat.getWaterResource());
        existing.setGeographicLocation(habitat.getGeographicLocation());
        existing.setAreaSize(habitat.getAreaSize());
        existing.setConservationStatus(habitat.getConservationStatus());

        return repository.save(existing);
    }

    public boolean deleteHabitat(Long id) {
        Habitat existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return false;
        }

        repository.delete(existing);
        return true;
    }
}
