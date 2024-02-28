package com.github.aloutta.gaming.catalog.game;

public final class GameMapper {

  private GameMapper() {}

  public static Game map(com.github.aloutta.gaming.catalog.model.Game model) {
    Game game = new Game();
    game.setId(model.getId());
    game.setName(model.getName());
    return game;
  }

  public static com.github.aloutta.gaming.catalog.model.Game map(Game data) {
    var model = new com.github.aloutta.gaming.catalog.model.Game(data.getName());
    model.setId(data.getId());
    return model;
  }
}
