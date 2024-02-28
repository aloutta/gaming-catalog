package com.github.aloutta.gaming.catalog.game;

import com.github.aloutta.gaming.catalog.api.*;
import com.github.aloutta.gaming.catalog.model.Game;
import io.micronaut.http.annotation.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;
import reactor.core.publisher.*;

@Controller
public class GameController implements GameApi {

  private final GameRepository gameRepository;

  public GameController(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public Mono<@NotNull List<@Valid Game>> gamesGet() {
    return null;
  }

  @Override
  public Mono<Void> gamesIdDelete(Long id) {
    return gameRepository.deleteById(id).then();
  }

  @Override
  public Mono<@Valid Game> gamesIdGet(Long id) {
    return gameRepository.findById(id).map(GameMapper::map);
  }

  @Override
  public Mono<Void> gamesIdPatch(Long id, Game game) {
    return null;
  }

  @Override
  public Mono<@Valid Game> gamesPost(Game game) {
    return gameRepository.save(GameMapper.map(game)).map(GameMapper::map);
  }
}
