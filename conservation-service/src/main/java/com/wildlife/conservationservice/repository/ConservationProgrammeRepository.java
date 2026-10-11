
package com.wildlife.conservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildlife.conservationservice.entity.ConservationProgramme;

@Repository
public interface ConservationProgrammeRepository
        extends JpaRepository<ConservationProgramme, Long> {

}
