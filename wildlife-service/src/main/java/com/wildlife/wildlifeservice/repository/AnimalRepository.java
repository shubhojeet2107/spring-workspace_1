
package com.wildlife.wildlifeservice.repository;

import com.wildlife.wildlifeservice.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
