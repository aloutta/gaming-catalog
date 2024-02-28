package com.github.aloutta.gaming.catalog.drm;

public final class DRMMapper {

  private DRMMapper() {}

  public static DRM map(com.github.aloutta.gaming.catalog.model.DRM model) {
    DRM drm = new DRM();
    drm.setId(model.getId());
    drm.setName(model.getName());
    return drm;
  }

  public static com.github.aloutta.gaming.catalog.model.DRM map(DRM data) {
    var model = new com.github.aloutta.gaming.catalog.model.DRM(data.getName());
    model.setId(data.getId());
    return model;
  }
}
