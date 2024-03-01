package com.github.aloutta.gaming.catalog.developer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.aloutta.gaming.catalog.model.Developer;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
class DeveloperControllerTest {

  @Inject private DeveloperRepository developerRepository;

  private RequestSpecification requestSpecification;

  @BeforeEach
  void setUp(RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
    requestSpecification.accept(ContentType.JSON).contentType(ContentType.JSON);
  }

  @Test
  void createAndGetDeveloper() {
    var id =
        requestSpecification
            .body(new Developer("test"))
            .when()
            .post("/developers")
            .then()
            .statusCode(is(HttpStatus.OK.getCode()))
            .extract()
            .body()
            .as(Developer.class)
            .getId();

    requestSpecification
        .when()
        .get("/developers/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }

  @Test
  void createDeveloperInvalidRequest() {
    requestSpecification
        .when()
        .body(1)
        .when()
        .post("/developers")
        .then()
        .statusCode(is(HttpStatus.BAD_REQUEST.getCode()));
  }

  @Test
  void developerNotFound() {
    requestSpecification
        .when()
        .get("/developers/{id}", 0)
        .then()
        .statusCode(is(HttpStatus.NOT_FOUND.getCode()));
  }

  @Test
  void patchDeveloper() {
    var id =
        requestSpecification
            .body(new Developer("test"))
            .when()
            .post("/developers")
            .then()
            .extract()
            .body()
            .as(Developer.class)
            .getId();
    var before = developerRepository.findById(id).block();

    requestSpecification
        .body(new Developer("other"))
        .when()
        .patch("/developers/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    var after = developerRepository.findById(id).block();

    assertEquals(before.getId(), after.getId());
    assertEquals("other", after.getName());
  }

  @Test
  void patchNotExistingDeveloper() {
    var id = 0L;
    assertNull(developerRepository.findById(id).block());

    requestSpecification
        .body(new Developer("other"))
        .when()
        .patch("/developers/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    assertNull(developerRepository.findById(id).block());
  }

  @Test
  void deleteDeveloper() {
    requestSpecification
        .when()
        .delete("/developers/{id}", 1)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }
}
