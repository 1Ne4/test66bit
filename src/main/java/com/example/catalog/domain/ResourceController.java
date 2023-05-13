package com.example.catalog.domain;

import com.example.catalog.repositories.GenderRepository;
import com.example.catalog.services.PlayerService;
import com.example.catalog.enteties.Player;
import com.example.catalog.enteties.PlayerDto;
import com.example.catalog.repositories.CountryRepository;
import com.example.catalog.repositories.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResourceController {

    TeamRepository teamRepository;
    PlayerService playerService;
    CountryRepository countryRepository;
    GenderRepository genderRepository;

    ResourceController(GenderRepository genderRepository, TeamRepository teamRepository, PlayerService playerService,
                       CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        this.teamRepository = teamRepository;
        this.playerService = playerService;
        this.genderRepository = genderRepository;
    }

    @GetMapping("/")
    public String getPlayers(Model model) {
        var players = playerService.getAllPlayers();
        model.addAttribute("players", players.isEmpty() ? null : players);
        return "players";
    }

    @GetMapping("/add-player")
    public String getData(PlayerDto player, Model model) {
        addAttributes(model, player);
        return "/save-player";
    }

    @PostMapping("/save-player")
    public String savePlayer(@Valid PlayerDto player, BindingResult result, Model model) {
        addAttributes(model, player);
        if (result.hasErrors()) {
            return "/save-player";
        }
        playerService.savePlayer(player);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        var player = new PlayerDto(playerService.findPlayerById(id));
        addAttributes(model, player);
        return "update-player";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid PlayerDto player,
                             BindingResult result, Model model) {
        addAttributes(model, player);
        player.setId(id);
        if (result.hasErrors()) {
            return "update-player";
        }
        playerService.savePlayer(player);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Player player = playerService.findPlayerById(id);
        playerService.deletePlayer(player);
        return "redirect:/";
    }

    void addAttributes(Model model, PlayerDto player) {
        model.addAttribute("player", player);
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("genders", genderRepository.findAll());
    }
}
