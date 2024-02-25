package com.github.aloutta.gaming.catalog.mapping;

public final class GameMapper {

  private GameMapper() {}

  public static com.github.aloutta.gaming.catalog.data.Game map(
      com.github.aloutta.gaming.catalog.model.Game model) {
    return new com.github.aloutta.gaming.catalog.data.Game(model.getId(), model.getName());
  }

  public static com.github.aloutta.gaming.catalog.model.Game map(
      com.github.aloutta.gaming.catalog.data.Game data) {
    var model = new com.github.aloutta.gaming.catalog.model.Game(data.name());
    model.setId(data.id());
    return model;
  }
}
