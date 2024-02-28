package com.github.aloutta.gaming.catalog.publisher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.text.IsEmptyString.*;
import static org.junit.jupiter.api.Assertions.*;

import com.github.aloutta.gaming.catalog.model.Publisher;
import io.micronaut.http.*;
import io.micronaut.test.extensions.junit5.annotation.*;
import io.restassured.http.*;
import io.restassured.specification.*;
import jakarta.inject.*;
import org.junit.jupiter.api.*;

@MicronautTest
class PublisherControllerTest {

  @Inject private PublisherRepository publisherRepository;

  private RequestSpecification requestSpecification;

  @BeforeEach
  void setUp(RequestSpecification requestSpecification) {
    this.requestSpecification = requestSpecification;
    requestSpecification.accept(ContentType.JSON).contentType(ContentType.JSON);
  }

  @Test
  void createAndGetPublisher() {
    var id =
        requestSpecification
            .body(new Publisher("test"))
            .when()
            .post("/publishers")
            .then()
            .statusCode(is(HttpStatus.OK.getCode()))
            .extract()
            .body()
            .as(Publisher.class)
            .getId();

    requestSpecification
        .when()
        .get("/publishers/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }

  @Test
  void createPublisherInvalidRequest() {
    requestSpecification
        .when()
        .body(1)
        .when()
        .post("/publishers")
        .then()
        .statusCode(is(HttpStatus.BAD_REQUEST.getCode()));
  }

  @Test
  void publisherNotFound() {
    requestSpecification
        .when()
        .get("/publishers/{id}", 0)
        .then()
        .statusCode(is(HttpStatus.NOT_FOUND.getCode()));
  }

  @Test
  void patchPublisher() {
    var id =
        requestSpecification
            .body(new Publisher("test"))
            .when()
            .post("/publishers")
            .then()
            .extract()
            .body()
            .as(Publisher.class)
            .getId();
    var before = publisherRepository.findById(id).block();

    requestSpecification
        .body(new Publisher("other"))
        .when()
        .patch("/publishers/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    var after = publisherRepository.findById(id).block();

    assertEquals(before.getId(), after.getId());
    assertEquals("other", after.getName());
  }

  @Test
  void patchNotExistingPublisher() {
    var id = 0L;
    assertNull(publisherRepository.findById(id).block());

    requestSpecification
        .body(new Publisher("other"))
        .when()
        .patch("/publishers/{id}", id)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()))
        .contentType(emptyOrNullString());

    assertNull(publisherRepository.findById(id).block());
  }

  @Test
  void deletePublisher() {
    requestSpecification
        .when()
        .delete("/publishers/{id}", 1)
        .then()
        .statusCode(is(HttpStatus.OK.getCode()));
  }
}
