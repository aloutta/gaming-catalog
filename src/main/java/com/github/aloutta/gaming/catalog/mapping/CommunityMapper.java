package com.github.aloutta.gaming.catalog.mapping;

public final class CommunityMapper {

  private CommunityMapper() {}

  public static com.github.aloutta.gaming.catalog.data.Community map(
      com.github.aloutta.gaming.catalog.model.Community model) {
    return new com.github.aloutta.gaming.catalog.data.Community(model.getId(), model.getName());
  }

  public static com.github.aloutta.gaming.catalog.model.Community map(
      com.github.aloutta.gaming.catalog.data.Community data) {
    var model = new com.github.aloutta.gaming.catalog.model.Community(data.name());
    model.setId(data.id());
    return model;
  }
}
