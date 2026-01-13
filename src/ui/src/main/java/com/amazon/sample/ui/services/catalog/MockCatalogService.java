

package com.amazon.sample.ui.services.catalog;

import com.amazon.sample.ui.services.catalog.model.Product;
import com.amazon.sample.ui.services.catalog.model.ProductPage;
import com.amazon.sample.ui.services.catalog.model.ProductTag;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MockCatalogService implements CatalogService {

  private Map<String, Product> products;
  private Map<String, ProductTag> tags;

  public MockCatalogService() {
    tags = loadTagsFromJson();
    products = loadProductsFromJson();
  }

  private Map<String, ProductTag> loadTagsFromJson() {
    Map<String, ProductTag> tagMap = new HashMap<>();

    try (InputStream inputStream =
      getClass().getResourceAsStream("/data/tags.json")) {

      ObjectMapper mapper = new ObjectMapper();
      List<TagData> tagList = mapper.readValue(
        inputStream,
        mapper.getTypeFactory()
          .constructCollectionType(List.class, TagData.class)
      );

      for (TagData tag : tagList) {
        tagMap.put(
          tag.getName(),
          new ProductTag(tag.getName(), tag.getDisplayName())
        );
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to load tags.json", e);
    }

    return tagMap;
  }

  private Map<String, Product> loadProductsFromJson() {
    Map<String, Product> productMap = new HashMap<>();

    try (InputStream inputStream =
      getClass().getResourceAsStream("/data/products.json")) {

      ObjectMapper mapper = new ObjectMapper();
      List<ProductData> productList = mapper.readValue(
        inputStream,
        mapper.getTypeFactory()
          .constructCollectionType(List.class, ProductData.class)
      );

      for (ProductData pd : productList) {
        List<ProductTag> productTags = pd.getTags()
          .stream()
          .map(tags::get)
          .filter(Objects::nonNull)
          .collect(Collectors.toList());

        Product product = new Product(
          pd.getId(),
          pd.getName(),
          pd.getDescription(),
          pd.getPrice(),
          pd.getImage(),        // ✅ image wired
          productTags
        );

        productMap.put(product.getId(), product);
      }

    } catch (IOException e) {
      throw new RuntimeException("Failed to load products.json", e);
    }

    return productMap;
  }

  // ===================== INNER DTOs =====================

  @Data
  private static class TagData {
    private String name;
    private String displayName;
  }

  @Data
  private static class ProductData {
    private String id;
    private String name;
    private String description;
    private int price;
    private String image;
    private List<String> tags;
  }

  // ===================== SERVICE METHODS =====================

  @Override
  public Mono<ProductPage> getProducts(
    String tag,
    String order,
    int page,
    int size
  ) {
    List<Product> productList = products.values()
      .stream()
      .sorted(Comparator.comparing(Product::getName))
      .filter(p -> tag == null || tag.isBlank() || p.hasTag(tag))
      .collect(Collectors.toList());

    int start = (page - 1) * size;
    int end = Math.min(start + size, productList.size());

    return Mono.just(
      new ProductPage(
        page,
        size,
        productList.size(),
        productList.subList(start, end)
      )
    );
  }

  @Override
  public Mono<Product> getProduct(String productId) {
    return Mono.just(products.get(productId));
  }

  @Override
  public Flux<ProductTag> getTags() {
    return Flux.fromIterable(tags.values());
  }
}

