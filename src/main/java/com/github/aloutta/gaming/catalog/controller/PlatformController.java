package com.github.aloutta.gaming.catalog.controller;

import com.github.aloutta.gaming.catalog.api.*;
import com.github.aloutta.gaming.catalog.data.*;
import com.github.aloutta.gaming.catalog.mapping.*;
import com.github.aloutta.gaming.catalog.model.*;
import com.github.aloutta.gaming.catalog.model.Platform;
import io.micronaut.http.annotation.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;
import reactor.core.publisher.*;

@Controller
public class PlatformController implements PlatformApi {

  private final PlatformRepository platformRepository;

  public PlatformController(PlatformRepository platformRepository) {
    this.platformRepository = platformRepository;
  }

  @Override
  public Mono<@Valid Platform> createPlatform(Platform platform) {
    return platformRepository.save(PlatformMapper.map(platform)).map(PlatformMapper::map);
  }

  @Override
  public Mono<@NotNull List<@Valid Platform>> platformsGet(
      String name,
      PlatformsGetSortPropertyParameter sortProperty,
      Integer limit,
      SortOrder sortOrder) {
    return null;
  }

  @Override
  public Mono<Void> platformsIdDelete(Long id) {
    return platformRepository.deleteById(id).then();
  }

  @Override
  public Mono<@Valid Platform> platformsIdGet(Long id) {
    return platformRepository.findById(id).map(PlatformMapper::map);
  }

  @Override
  public Mono<Void> platformsIdPatch(Long id, Platform platform) {
    return null;
  }
}
