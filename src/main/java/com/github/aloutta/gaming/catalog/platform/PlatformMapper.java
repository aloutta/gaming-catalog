package com.github.aloutta.gaming.catalog.platform;

public final class PlatformMapper {

  private PlatformMapper() {}

  public static Platform map(com.github.aloutta.gaming.catalog.model.Platform model) {
    Platform platform = new Platform();
    platform.setId(model.getId());
    platform.setName(model.getName());
    return platform;
  }

  public static com.github.aloutta.gaming.catalog.model.Platform map(Platform data) {
    var model = new com.github.aloutta.gaming.catalog.model.Platform(data.getName());
    model.setId(data.getId());
    return model;
  }
}
