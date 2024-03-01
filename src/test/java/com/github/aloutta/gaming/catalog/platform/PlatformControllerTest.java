package com.github.aloutta.gaming.catalog.platform;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.aloutta.gaming.catalog.model.Platform;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
class PlatformControllerTest {

  @Inject private PlatformRepository platformRepository;

  private RequestSpecification requestSpecification;

  @BeforeEach
  void setUp(RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
    requestSpecification.accept(ContentType.JSON).contentType(ContentType.JSON);
  }

  @Test
  void createAndGetPlatform() {
    var id =
        requestSpecification
            .body(new Platform("test"))
            .when()
            .post("/platforms")
            .then()
            .statusCode(is(HttpStatus.OK.getCode()))
            .extract()
            .body()
            .as(Platform.class)
            .getId();

    requestSpecification
        .when()
        .get("/platforms/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }

  @Test
  void createPlatformInvalidRequest() {
    requestSpecification
        .when()
        .body(1)
        .when()
        .post("/platforms")
        .then()
        .statusCode(is(HttpStatus.BAD_REQUEST.getCode()));
  }

  @Test
  void platformNotFound() {
    requestSpecification
        .when()
        .get("/platforms/{id}", 0)
        .then()
        .statusCode(is(HttpStatus.NOT_FOUND.getCode()));
  }

  @Test
  void patchPlatform() {
    var id =
        requestSpecification
            .body(new Platform("test"))
            .when()
            .post("/platforms")
            .then()
            .extract()
            .body()
            .as(Platform.class)
            .getId();
    var before = platformRepository.findById(id).block();

    requestSpecification
        .body(new Platform("other"))
        .when()
        .patch("/platforms/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    var after = platformRepository.findById(id).block();

    assertEquals(before.getId(), after.getId());
    assertEquals("other", after.getName());
  }

  @Test
  void patchNotExistingPlatform() {
    var id = 0L;
    assertNull(platformRepository.findById(id).block());

    requestSpecification
        .body(new Platform("other"))
        .when()
        .patch("/platforms/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    assertNull(platformRepository.findById(id).block());
  }

  @Test
  void deletePlatform() {
    requestSpecification
        .when()
        .delete("/platforms/{id}", 1)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }
}
