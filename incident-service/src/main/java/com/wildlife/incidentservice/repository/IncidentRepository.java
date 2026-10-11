
package com.wildlife.incidentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildlife.incidentservice.entity.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
