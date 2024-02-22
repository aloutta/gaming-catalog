package com.github.aloutta.gaming.catalog;

import com.github.aloutta.gaming.catalog.api.*;
import com.github.aloutta.gaming.catalog.model.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;
import reactor.core.publisher.*;

public class Controller implements DefaultApi {

  private final PublisherRepository publisherRepository;

  public Controller(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  @Override
  public Mono<Void> createPublisher(Publisher publisher) {
    return null;
  }

  @Override
  public Mono<@NotNull List<@Valid Publisher>> publishersGet(
      String name,
      Integer limit,
      PublishersGetSortPropertyParameter sortProperty,
      PublishersGetSortOrderParameter sortOrder) {
    return null;
  }

  @Override
  public Mono<Void> publishersIdDelete(Long id) {
    return publisherRepository.deleteById(id).then();
  }

  @Override
  public Mono<@Valid Publisher> publishersIdGet(Long id) {
    return null;
  }

  @Override
  public Mono<Void> publishersIdPatch(Long id, Publisher publisher) {
    return null;
  }
}
