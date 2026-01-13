package com.amazon.sample.ui.services.carts.model;

public class CartItem {

  private String id;
  private int quantity;
  private int price;
  private String name;
  private String image;

  public CartItem() {}

  public CartItem(String id, int quantity, int price, String name, String image) {
    this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.name = name;
    this.image = image;
  }

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }

  public int getPrice() { return price; }
  public void setPrice(int price) { this.price = price; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getImage() { return image; }
  public void setImage(String image) { this.image = image; }

  public int getTotalPrice() {
    return this.quantity * this.price;
  }

  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }
}

