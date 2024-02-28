package com.github.aloutta.gaming.catalog.drm;

import io.micronaut.data.annotation.*;
import io.micronaut.data.repository.reactive.*;

@Repository
public interface DRMRepository extends ReactorPageableRepository<DRM, Long> {}
