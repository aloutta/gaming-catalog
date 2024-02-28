package com.github.aloutta.gaming.catalog.developer;

public final class DeveloperMapper {

  private DeveloperMapper() {}

  public static Developer map(com.github.aloutta.gaming.catalog.model.Developer model) {
    Developer developer = new Developer();
    developer.setId(model.getId());
    developer.setName(model.getName());
    return developer;
  }

  public static com.github.aloutta.gaming.catalog.model.Developer map(Developer data) {
    var model = new com.github.aloutta.gaming.catalog.model.Developer(data.getName());
    model.setId(data.getId());
    return model;
  }
}
