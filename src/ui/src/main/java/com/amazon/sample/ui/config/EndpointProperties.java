package com.amazon.sample.ui.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("retail.ui.endpoints")
public class EndpointProperties {

  private String catalog;
  private String carts;
  private String checkout;
  private String orders;

  public String getCatalog() {
    return catalog;
  }

  public void setCatalog(String catalog) {
    this.catalog = catalog;
  }

  public String getCarts() {
    return carts;
  }

  public void setCarts(String carts) {
    this.carts = carts;
  }

  public String getCheckout() {
    return checkout;
  }

  public void setCheckout(String checkout) {
    this.checkout = checkout;
  }

  public String getOrders() {
    return orders;
  }

  public void setOrders(String orders) {
    this.orders = orders;
  }
}

