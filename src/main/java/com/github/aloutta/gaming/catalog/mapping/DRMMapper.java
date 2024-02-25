package com.github.aloutta.gaming.catalog.mapping;

public final class DRMMapper {

  private DRMMapper() {}

  public static com.github.aloutta.gaming.catalog.data.DRM map(
      com.github.aloutta.gaming.catalog.model.DRM model) {
    return new com.github.aloutta.gaming.catalog.data.DRM(model.getId(), model.getName());
  }

  public static com.github.aloutta.gaming.catalog.model.DRM map(
      com.github.aloutta.gaming.catalog.data.DRM data) {
    var model = new com.github.aloutta.gaming.catalog.model.DRM(data.name());
    model.setId(data.id());
    return model;
  }
}
