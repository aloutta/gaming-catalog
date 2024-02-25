package com.github.aloutta.gaming.catalog.controller;

import com.github.aloutta.gaming.catalog.api.*;
import com.github.aloutta.gaming.catalog.data.*;
import com.github.aloutta.gaming.catalog.mapping.*;
import com.github.aloutta.gaming.catalog.model.*;
import com.github.aloutta.gaming.catalog.model.Publisher;
import io.micronaut.http.annotation.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;
import reactor.core.publisher.*;

@Controller
public class PublisherController implements PublisherApi {

  private final PublisherRepository publisherRepository;

  public PublisherController(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  @Override
  public Mono<@Valid Publisher> createPublisher(Publisher publisher) {
    return publisherRepository.save(PublisherMapper.map(publisher)).map(PublisherMapper::map);
  }

  @Override
  public Mono<@NotNull List<@Valid Publisher>> publishersGet(
      String name,
      PlatformsGetSortPropertyParameter sortProperty,
      Integer limit,
      SortOrder sortOrder) {
    return null;
  }

  @Override
  public Mono<Void> publishersIdDelete(Long id) {
    return publisherRepository.deleteById(id).then();
  }

  @Override
  public Mono<@Valid Publisher> publishersIdGet(Long id) {
    return publisherRepository.findById(id).map(PublisherMapper::map);
  }

  @Override
  public Mono<Void> publishersIdPatch(Long id, Publisher publisher) {
    return null;
  }
}
