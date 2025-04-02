package com.example.sirh_backend.repositories;

import com.example.sirh_backend.models.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
}
