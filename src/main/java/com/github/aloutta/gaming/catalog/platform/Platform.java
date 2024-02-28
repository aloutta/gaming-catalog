package com.github.aloutta.gaming.catalog.platform;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Entity
public class Platform {
  @Id @GeneratedValue Long id;

  @NotBlank
  @Size(max = 255)
  String name;
}
