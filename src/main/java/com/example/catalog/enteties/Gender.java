package com.example.catalog.enteties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gender {
    @Id
    Long id;
    String name;
}
