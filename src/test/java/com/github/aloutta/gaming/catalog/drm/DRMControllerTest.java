package com.github.aloutta.gaming.catalog.drm;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.aloutta.gaming.catalog.model.DRM;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
class DRMControllerTest {

  @Inject private DRMRepository drmRepository;

  private RequestSpecification requestSpecification;

  @BeforeEach
  void setUp(RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
    requestSpecification.accept(ContentType.JSON).contentType(ContentType.JSON);
  }

  @Test
  void createAndGetDRM() {
    var id =
        requestSpecification
            .body(new DRM("test"))
            .when()
            .post("/drms")
            .then()
            .statusCode(is(HttpStatus.OK.getCode()))
            .extract()
            .body()
            .as(DRM.class)
            .getId();

    requestSpecification
        .when()
        .get("/drms/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }

  @Test
  void createDRMInvalidRequest() {
    requestSpecification
        .when()
        .body(1)
        .when()
        .post("/drms")
        .then()
        .statusCode(is(HttpStatus.BAD_REQUEST.getCode()));
  }

  @Test
  void drmNotFound() {
    requestSpecification
        .when()
        .get("/drms/{id}", 0)
        .then()
        .statusCode(is(HttpStatus.NOT_FOUND.getCode()));
  }

  @Test
  void patchDRM() {
    var id =
        requestSpecification
            .body(new DRM("test"))
            .when()
            .post("/drms")
            .then()
            .extract()
            .body()
            .as(DRM.class)
            .getId();
    var before = drmRepository.findById(id).block();

    requestSpecification
        .body(new DRM("other"))
        .when()
        .patch("/drms/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    var after = drmRepository.findById(id).block();

    assertEquals(before.getId(), after.getId());
    assertEquals("other", after.getName());
  }

  @Test
  void patchNotExistingDRM() {
    var id = 0L;
    assertNull(drmRepository.findById(id).block());

    requestSpecification
        .body(new DRM("other"))
        .when()
        .patch("/drms/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    assertNull(drmRepository.findById(id).block());
  }

  @Test
  void deleteDRM() {
    requestSpecification
        .when()
        .delete("/drms/{id}", 1)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }
}
