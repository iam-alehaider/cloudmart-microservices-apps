package com.amazon.sample.ui.web.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {

  private String id;
  private int quantity;
  private int price;
  private String name;
  private String image;

  public int getTotalPrice() {
    return this.quantity * this.price;
  }
}

