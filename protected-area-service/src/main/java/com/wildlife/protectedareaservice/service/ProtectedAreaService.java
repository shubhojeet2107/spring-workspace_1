
package com.wildlife.protectedareaservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.protectedareaservice.entity.ProtectedArea;
import com.wildlife.protectedareaservice.repository.ProtectedAreaRepository;

@Service
public class ProtectedAreaService {

    private final ProtectedAreaRepository areaRepository;

    public ProtectedAreaService(ProtectedAreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    // Get all protected areas
    public List<ProtectedArea> getAllAreas() {
        return areaRepository.findAll();
    }

    // Add a protected area
    public ProtectedArea addArea(ProtectedArea area) {
        area.setAreaId(null);
        return areaRepository.save(area);
    }

    // Get a protected area by ID
    public ProtectedArea getAreaById(Long id) {
        List<ProtectedArea> areas = areaRepository.findAll();

        for (ProtectedArea area : areas) {
            if (area.getAreaId().equals(id)) {
                return area;
            }
        }

        return null;
    }

    // Update a protected area
    public ProtectedArea updateArea(Long id, ProtectedArea updatedArea) {
        List<ProtectedArea> areas = areaRepository.findAll();

        for (ProtectedArea area : areas) {
            if (area.getAreaId().equals(id)) {
                area.setAreaName(updatedArea.getAreaName());
                area.setAreaType(updatedArea.getAreaType());
                area.setLocation(updatedArea.getLocation());
                area.setTotalArea(updatedArea.getTotalArea());
                area.setStatus(updatedArea.getStatus());

                return areaRepository.save(area);
            }
        }

        return null;
    }

    // Delete a protected area
    public boolean deleteArea(Long id) {
        List<ProtectedArea> areas = areaRepository.findAll();

        for (ProtectedArea area : areas) {
            if (area.getAreaId().equals(id)) {
                areaRepository.delete(area);
                return true;
            }
        }

        return false;
    }
}
