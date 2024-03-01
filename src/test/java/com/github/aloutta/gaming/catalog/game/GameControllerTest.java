package com.github.aloutta.gaming.catalog.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.aloutta.gaming.catalog.model.Game;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
class GameControllerTest {

  @Inject private GameRepository gameRepository;

  private RequestSpecification requestSpecification;

  @BeforeEach
  void setUp(RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
    requestSpecification.accept(ContentType.JSON).contentType(ContentType.JSON);
  }

  @Test
  void createAndGetGame() {
    var id =
        requestSpecification
            .body(new Game("test"))
            .when()
            .post("/games")
            .then()
            .statusCode(is(HttpStatus.OK.getCode()))
            .extract()
            .body()
            .as(Game.class)
            .getId();

    requestSpecification
        .when()
        .get("/games/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }

  @Test
  void createGameInvalidRequest() {
    requestSpecification
        .when()
        .body(1)
        .when()
        .post("/games")
        .then()
        .statusCode(is(HttpStatus.BAD_REQUEST.getCode()));
  }

  @Test
  void gameNotFound() {
    requestSpecification
        .when()
        .get("/games/{id}", 0)
        .then()
        .statusCode(is(HttpStatus.NOT_FOUND.getCode()));
  }

  @Test
  void patchGame() {
    var id =
        requestSpecification
            .body(new Game("test"))
            .when()
            .post("/games")
            .then()
            .extract()
            .body()
            .as(Game.class)
            .getId();
    var before = gameRepository.findById(id).block();

    requestSpecification
        .body(new Game("other"))
        .when()
        .patch("/games/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    var after = gameRepository.findById(id).block();

    assertEquals(before.getId(), after.getId());
    assertEquals("other", after.getName());
  }

  @Test
  void patchNotExistingGame() {
    var id = 0L;
    assertNull(gameRepository.findById(id).block());

    requestSpecification
        .body(new Game("other"))
        .when()
        .patch("/games/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    assertNull(gameRepository.findById(id).block());
  }

  @Test
  void deleteGame() {
    requestSpecification
        .when()
        .delete("/games/{id}", 1)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }
}
