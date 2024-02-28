package com.github.aloutta.gaming.catalog.developer;

import io.micronaut.data.annotation.*;
import io.micronaut.data.repository.reactive.*;

@Repository
public interface DeveloperRepository extends ReactorPageableRepository<Developer, Long> {}
