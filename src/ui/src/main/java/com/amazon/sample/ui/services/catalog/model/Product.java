

package com.amazon.sample.ui.services.catalog.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor        // ✅ REQUIRED for Jackson
public class Product {

  private String id;

  private String name;

  private String description;

  private int price;

  private String image;   // ✅ REQUIRED for UI images

  private List<ProductTag> tags;

  public boolean hasTag(String tag) {
    return tags.stream().anyMatch(t -> t.getName().equals(tag));
  }
}

