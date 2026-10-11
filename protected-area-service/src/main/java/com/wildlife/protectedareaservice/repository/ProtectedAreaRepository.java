
package com.wildlife.protectedareaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildlife.protectedareaservice.entity.ProtectedArea;

public interface ProtectedAreaRepository extends JpaRepository<ProtectedArea, Long> {
}