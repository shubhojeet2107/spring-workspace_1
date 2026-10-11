
package com.wildlife.patrolservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildlife.patrolservice.entity.Patrol;

public interface PatrolRepository extends JpaRepository<Patrol, Long> {
}
