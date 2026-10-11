
package com.wildlife.patrolservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.patrolservice.entity.Patrol;
import com.wildlife.patrolservice.repository.PatrolRepository;

@Service
public class PatrolService {

    private final PatrolRepository patrolRepository;

    public PatrolService(PatrolRepository patrolRepository) {
        this.patrolRepository = patrolRepository;
    }

    public List<Patrol> getAllPatrols() {
        return patrolRepository.findAll();
    }

    public Patrol addPatrol(Patrol patrol) {
        patrol.setPatrolId(null);
        return patrolRepository.save(patrol);
    }

    public Patrol getPatrolById(Long id) {
        List<Patrol> patrols = patrolRepository.findAll();

        for (Patrol patrol : patrols) {
            if (patrol.getPatrolId().equals(id)) {
                return patrol;
            }
        }

        return null;
    }

    public Patrol updatePatrol(Long id, Patrol updatedPatrol) {
        List<Patrol> patrols = patrolRepository.findAll();

        for (Patrol patrol : patrols) {
            if (patrol.getPatrolId().equals(id)) {
                patrol.setPatrolDate(updatedPatrol.getPatrolDate());
                patrol.setStaffName(updatedPatrol.getStaffName());
                patrol.setProtectedArea(updatedPatrol.getProtectedArea());
                patrol.setRoute(updatedPatrol.getRoute());
                patrol.setStatus(updatedPatrol.getStatus());

                return patrolRepository.save(patrol);
            }
        }

        return null;
    }

    public boolean deletePatrol(Long id) {
        List<Patrol> patrols = patrolRepository.findAll();

        for (Patrol patrol : patrols) {
            if (patrol.getPatrolId().equals(id)) {
                patrolRepository.delete(patrol);
                return true;
            }
        }

        return false;
    }
}
