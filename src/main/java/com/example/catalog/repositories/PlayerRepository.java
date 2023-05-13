package com.example.catalog.repositories;

import com.example.catalog.enteties.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}