package com.github.aloutta.gaming.catalog.game;

import io.micronaut.data.annotation.*;
import io.micronaut.data.repository.reactive.*;

@Repository
public interface GameRepository extends ReactorPageableRepository<Game, Long> {}
