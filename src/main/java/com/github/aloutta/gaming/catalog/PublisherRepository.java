package com.github.aloutta.gaming.catalog;

import io.micronaut.core.annotation.*;
import io.micronaut.data.annotation.*;
import io.micronaut.data.repository.reactive.*;
import reactor.core.publisher.*;

@Repository
public interface PublisherRepository extends ReactorPageableRepository<PublisherEntity, Long> {

  @NonNull
  Flux<PublisherEntity> findAllByIdOrNameContains(Long id, String name);
}
