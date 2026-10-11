
package com.wildlife.rescueservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.rescueservice.entity.Rescue;
import com.wildlife.rescueservice.repository.RescueRepository;

@Service
public class RescueService {

    private final RescueRepository rescueRepository;

    public RescueService(RescueRepository rescueRepository) {
        this.rescueRepository = rescueRepository;
    }

    public List<Rescue> getAllRescues() {
        return rescueRepository.findAll();
    }

    public Rescue addRescue(Rescue rescue) {
        rescue.setRescueId(null);
        return rescueRepository.save(rescue);
    }

    public Rescue getRescueById(Long id) {
        List<Rescue> rescues = rescueRepository.findAll();

        for (Rescue rescue : rescues) {
            if (rescue.getRescueId().equals(id)) {
                return rescue;
            }
        }

        return null;
    }

    public Rescue updateRescue(Long id, Rescue updatedRescue) {
        List<Rescue> rescues = rescueRepository.findAll();

        for (Rescue rescue : rescues) {
            if (rescue.getRescueId().equals(id)) {
                rescue.setAnimalName(updatedRescue.getAnimalName());
                rescue.setRescueDate(updatedRescue.getRescueDate());
                rescue.setLocation(updatedRescue.getLocation());
                rescue.setAssignedStaff(updatedRescue.getAssignedStaff());
                rescue.setDescription(updatedRescue.getDescription());
                rescue.setStatus(updatedRescue.getStatus());

                return rescueRepository.save(rescue);
            }
        }

        return null;
    }

    public boolean deleteRescue(Long id) {
        List<Rescue> rescues = rescueRepository.findAll();

        for (Rescue rescue : rescues) {
            if (rescue.getRescueId().equals(id)) {
                rescueRepository.delete(rescue);
                return true;
            }
        }

        return false;
    }
}
