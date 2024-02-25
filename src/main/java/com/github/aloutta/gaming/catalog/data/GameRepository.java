package com.github.aloutta.gaming.catalog.data;

import io.micronaut.core.annotation.*;
import io.micronaut.data.annotation.*;
import io.micronaut.data.repository.reactive.*;
import reactor.core.publisher.*;

@Repository
public interface GameRepository extends ReactorPageableRepository<Game, Long> {}
