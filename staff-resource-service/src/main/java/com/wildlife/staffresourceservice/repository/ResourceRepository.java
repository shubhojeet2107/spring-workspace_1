
package com.wildlife.staffresourceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildlife.staffresourceservice.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
