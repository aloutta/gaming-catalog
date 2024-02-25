package com.github.aloutta.gaming.catalog.controller;

import com.github.aloutta.gaming.catalog.api.*;
import com.github.aloutta.gaming.catalog.data.*;
import com.github.aloutta.gaming.catalog.mapping.*;
import com.github.aloutta.gaming.catalog.model.*;
import com.github.aloutta.gaming.catalog.model.Developer;
import io.micronaut.http.annotation.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;
import reactor.core.publisher.*;

@Controller
public class DeveloperController implements DeveloperApi {

  private final DeveloperRepository developerRepository;

  public DeveloperController(DeveloperRepository developerRepository) {
    this.developerRepository = developerRepository;
  }

  @Override
  public Mono<@NotNull List<@Valid Developer>> developersGet() {
    return null;
  }

  @Override
  public Mono<Void> developersIdDelete(Long id) {
    return developerRepository.deleteById(id).then();
  }

  @Override
  public Mono<@Valid Developer> developersIdGet(Long id) {
    return developerRepository.findById(id).map(DeveloperMapper::map);
  }

  @Override
  public Mono<Void> developersIdPatch(Long id, Developer developer) {
    return null;
  }

  @Override
  public Mono<@Valid Developer> developersPost(Developer developer) {
    return developerRepository.save(DeveloperMapper.map(developer)).map(DeveloperMapper::map);
  }
}
