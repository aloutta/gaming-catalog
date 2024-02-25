package com.github.aloutta.gaming.catalog.controller;

import com.github.aloutta.gaming.catalog.api.*;
import com.github.aloutta.gaming.catalog.data.*;
import com.github.aloutta.gaming.catalog.mapping.*;
import com.github.aloutta.gaming.catalog.model.Community;
import io.micronaut.http.annotation.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;
import reactor.core.publisher.*;

@Controller
public class CommunityController implements CommunityApi {

  private final CommunityRepository communityRepository;

  public CommunityController(CommunityRepository communityRepository) {
    this.communityRepository = communityRepository;
  }

  @Override
  public Mono<@NotNull List<@Valid Community>> communitiesGet() {
    return null;
  }

  @Override
  public Mono<Void> communitiesIdDelete(Long id) {
    return communityRepository.deleteById(id).then();
  }

  @Override
  public Mono<@Valid Community> communitiesIdGet(Long id) {
    return communityRepository.findById(id).map(CommunityMapper::map);
  }

  @Override
  public Mono<Void> communitiesIdPatch(Long id, Community community) {
    return null;
  }

  @Override
  public Mono<@Valid Community> communitiesPost(Community community) {
    return communityRepository.save(CommunityMapper.map(community)).map(CommunityMapper::map);
  }
}
