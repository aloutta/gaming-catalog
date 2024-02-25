package com.github.aloutta.gaming.catalog.mapping;

public final class PlatformMapper {

  private PlatformMapper() {}

  public static com.github.aloutta.gaming.catalog.data.Platform map(
      com.github.aloutta.gaming.catalog.model.Platform model) {
    return new com.github.aloutta.gaming.catalog.data.Platform(model.getId(), model.getName());
  }

  public static com.github.aloutta.gaming.catalog.model.Platform map(
      com.github.aloutta.gaming.catalog.data.Platform data) {
    var model = new com.github.aloutta.gaming.catalog.model.Platform(data.name());
    model.setId(data.id());
    return model;
  }
}
