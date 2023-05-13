package com.example.catalog.enteties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotNull
    @ManyToOne
    Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthDate;
    @NotNull
    @ManyToOne
    Team team;
    @NotNull
    @ManyToOne
    Country country;

    public Player(PlayerDto playerDto, Gender gender, Team team, Country country) {
        this.id = playerDto.id;
        this.firstName = playerDto.firstName;
        this.lastName = playerDto.lastName;
        this.gender = gender;
        this.birthDate = playerDto.birthDate;
        this.team = team;
        this.country = country;
    }
}
