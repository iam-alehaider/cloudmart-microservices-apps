package com.amazon.sample.ui.services.catalog.model;

import org.mapstruct.Mapper;

@Mapper
public interface CatalogMapper {

  Product product(
    com.amazon.sample.ui.client.catalog.models.model.Product product
  );

  ProductTag tag(com.amazon.sample.ui.client.catalog.models.model.Tag tag);
}

