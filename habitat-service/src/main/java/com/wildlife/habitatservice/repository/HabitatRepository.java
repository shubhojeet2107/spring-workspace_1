
package com.wildlife.habitatservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildlife.habitatservice.entity.Habitat;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat, Long> {

}
