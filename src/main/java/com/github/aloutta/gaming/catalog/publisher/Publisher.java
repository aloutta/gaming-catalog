package com.github.aloutta.gaming.catalog.publisher;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Entity
public class Publisher {
  @Id @GeneratedValue Long id;

  @NotBlank
  @Size(max = 255)
  String name;
}
