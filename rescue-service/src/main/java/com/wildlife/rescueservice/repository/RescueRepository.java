
package com.wildlife.rescueservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildlife.rescueservice.entity.Rescue;

public interface RescueRepository extends JpaRepository<Rescue, Long> {
}
