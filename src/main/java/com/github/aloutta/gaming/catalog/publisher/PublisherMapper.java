package com.github.aloutta.gaming.catalog.publisher;

public final class PublisherMapper {

  private PublisherMapper() {}

  public static Publisher map(com.github.aloutta.gaming.catalog.model.Publisher model) {
    Publisher publisher = new Publisher();
    publisher.setId(model.getId());
    publisher.setName(model.getName());
    return publisher;
  }

  public static com.github.aloutta.gaming.catalog.model.Publisher map(Publisher data) {
    var model = new com.github.aloutta.gaming.catalog.model.Publisher(data.getName());
    model.setId(data.getId());
    return model;
  }
}
