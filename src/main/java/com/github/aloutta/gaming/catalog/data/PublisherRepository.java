package com.github.aloutta.gaming.catalog.data;

import io.micronaut.data.annotation.*;
import io.micronaut.data.repository.reactive.*;

@Repository
public interface PublisherRepository extends ReactorPageableRepository<Publisher, Long> {}
