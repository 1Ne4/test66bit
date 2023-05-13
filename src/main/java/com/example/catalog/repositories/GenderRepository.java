package com.example.catalog.repositories;

import com.example.catalog.enteties.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    Gender findByName(String name);
}