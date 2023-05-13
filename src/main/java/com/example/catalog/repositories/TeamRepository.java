package com.example.catalog.repositories;

import com.example.catalog.enteties.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);

    boolean existsByName(String name);
}