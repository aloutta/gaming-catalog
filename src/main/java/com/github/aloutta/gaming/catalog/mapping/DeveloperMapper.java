package com.github.aloutta.gaming.catalog.mapping;

public final class DeveloperMapper {

  private DeveloperMapper() {}

  public static com.github.aloutta.gaming.catalog.data.Developer map(
      com.github.aloutta.gaming.catalog.model.Developer model) {
    return new com.github.aloutta.gaming.catalog.data.Developer(model.getId(), model.getName());
  }

  public static com.github.aloutta.gaming.catalog.model.Developer map(
      com.github.aloutta.gaming.catalog.data.Developer data) {
    var model = new com.github.aloutta.gaming.catalog.model.Developer(data.name());
    model.setId(data.id());
    return model;
  }
}
