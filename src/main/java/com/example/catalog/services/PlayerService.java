package com.example.catalog.services;

import com.example.catalog.enteties.Player;
import com.example.catalog.enteties.PlayerDto;
import com.example.catalog.enteties.Team;
import com.example.catalog.repositories.CountryRepository;
import com.example.catalog.repositories.GenderRepository;
import com.example.catalog.repositories.PlayerRepository;
import com.example.catalog.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    PlayerRepository playerRepository;
    TeamRepository teamRepository;
    CountryRepository countryRepository;
    GenderRepository genderRepository;

    PlayerService(GenderRepository genderRepository, PlayerRepository playerRepository, TeamRepository teamRepository, CountryRepository countryRepository) {
        this.playerRepository = playerRepository;
        this.genderRepository = genderRepository;
        this.teamRepository = teamRepository;
        this.countryRepository = countryRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public void savePlayer(PlayerDto playerDto) {
        var team = new Team(playerDto.getTeamName());
        var country = countryRepository.findByName(playerDto.getCountryName());
        var gender = genderRepository.findByName(playerDto.getGender());
        if (teamRepository.existsByName(playerDto.getTeamName()))
            team = teamRepository.findByName(playerDto.getTeamName());
        else teamRepository.save(team);
        var player = new Player(playerDto, gender, team, country);
        playerRepository.save(player);
    }

    public Player findPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));
    }

    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }
}
