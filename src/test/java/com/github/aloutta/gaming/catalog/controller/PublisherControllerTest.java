package com.github.aloutta.gaming.catalog.controller;

import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.http.*;
import io.micronaut.http.client.*;
import io.micronaut.http.client.annotation.*;
import io.micronaut.http.client.exceptions.*;
import io.micronaut.test.extensions.junit5.annotation.*;
import jakarta.inject.*;
import java.io.*;
import org.junit.jupiter.api.*;

@MicronautTest
class PublisherControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  void test() {
    HttpRequest<?> request = HttpRequest.GET("/publishers/1");
    HttpClientResponseException responseException =
        assertThrows(HttpClientResponseException.class, () -> exchange(request));

    assertEquals(HttpStatus.NOT_FOUND, responseException.getStatus());
  }

  private HttpResponse<?> exchange(HttpRequest<?> request) throws IOException {
    try (BlockingHttpClient blockingHttpClient = httpClient.toBlocking()) {
      return blockingHttpClient.exchange(request);
    }
  }
}
