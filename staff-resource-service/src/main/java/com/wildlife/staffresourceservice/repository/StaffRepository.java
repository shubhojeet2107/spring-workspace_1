
package com.wildlife.staffresourceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildlife.staffresourceservice.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
