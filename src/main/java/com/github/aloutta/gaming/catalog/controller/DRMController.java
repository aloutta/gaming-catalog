package com.github.aloutta.gaming.catalog.controller;

import com.github.aloutta.gaming.catalog.api.*;
import com.github.aloutta.gaming.catalog.data.*;
import com.github.aloutta.gaming.catalog.mapping.*;
import com.github.aloutta.gaming.catalog.model.DRM;
import io.micronaut.http.annotation.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;
import reactor.core.publisher.*;

@Controller
public class DRMController implements DrmApi {

  private final DRMRepository drmRepository;

  public DRMController(DRMRepository drmRepository) {
    this.drmRepository = drmRepository;
  }

  @Override
  public Mono<@NotNull List<@Valid DRM>> drmsGet() {
    return null;
  }

  @Override
  public Mono<Void> drmsIdDelete(Long id) {
    return drmRepository.deleteById(id).then();
  }

  @Override
  public Mono<@Valid DRM> drmsIdGet(Long id) {
    return drmRepository.findById(id).map(DRMMapper::map);
  }

  @Override
  public Mono<Void> drmsIdPatch(Long id, DRM game) {
    return null;
  }

  @Override
  public Mono<@Valid DRM> drmsPost(DRM game) {
    return drmRepository.save(DRMMapper.map(game)).map(DRMMapper::map);
  }
}