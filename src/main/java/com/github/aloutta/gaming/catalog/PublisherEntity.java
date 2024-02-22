package com.github.aloutta.gaming.catalog;

import jakarta.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity(name = "publisher")
public record PublisherEntity(@Nullable @Id @GeneratedValue Long id, @NotBlank String name) {}
