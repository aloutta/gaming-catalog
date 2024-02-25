package com.github.aloutta.gaming.catalog.data;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public record Developer(@Id @GeneratedValue Long id, @NotBlank @Size(max = 255) String name) {}