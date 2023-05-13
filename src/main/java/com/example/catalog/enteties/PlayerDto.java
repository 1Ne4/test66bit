package com.example.catalog.enteties;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Date;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto implements Serializable {
    Long id;
    @NotBlank String firstName;
    @NotBlank String lastName;
    @NotBlank String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthDate;
    @NotBlank String teamName;
    @NotBlank String countryName;

    public PlayerDto(Player player) {
        this.id = player.id;
        this.firstName = player.firstName;
        this.lastName = player.lastName;
        this.gender = player.gender.name;
        this.birthDate = player.birthDate;
        this.teamName = player.team.name;
        this.countryName = player.country.name;
    }
}
