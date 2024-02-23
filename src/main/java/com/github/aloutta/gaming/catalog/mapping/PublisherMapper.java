package com.github.aloutta.gaming.catalog.mapping;

public final class PublisherMapper {

  private PublisherMapper() {}

  public static com.github.aloutta.gaming.catalog.data.Publisher map(
      com.github.aloutta.gaming.catalog.model.Publisher model) {
    return new com.github.aloutta.gaming.catalog.data.Publisher(model.getId(), model.getName());
  }

  public static com.github.aloutta.gaming.catalog.model.Publisher map(
      com.github.aloutta.gaming.catalog.data.Publisher data) {
    var model = new com.github.aloutta.gaming.catalog.model.Publisher(data.name());
    model.setId(data.id());
    return model;
  }
}
