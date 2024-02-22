package com.github.aloutta.gaming.catalog;

import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.http.*;
import io.micronaut.http.client.*;
import io.micronaut.http.client.annotation.*;
import io.micronaut.test.annotation.*;
import io.micronaut.test.extensions.junit5.annotation.*;
import jakarta.inject.*;
import org.junit.jupiter.api.*;

@MicronautTest
@Sql(scripts = {"classpath:sql/init-db.sql", "classpath:sql/seed-data.sql"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  void test() {

    HttpRequest<?> request = HttpRequest.GET("/1");
    HttpResponse<Object> response = httpClient.toBlocking().exchange(request);

    assertEquals(HttpStatus.OK, response.getStatus());
  }
}
