package com.amazon.sample.ui.services.carts;

import com.amazon.sample.ui.services.carts.model.Cart;
import com.amazon.sample.ui.services.carts.model.CartItem;
import com.amazon.sample.ui.services.catalog.CatalogService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import reactor.core.publisher.Mono;

public class MockCartsService implements CartsService {

  private final CatalogService catalogService;
  private final Map<String, Cart> carts;

  public MockCartsService(CatalogService catalogService) {
    this.catalogService = catalogService;
    this.carts = new HashMap<>();
  }

  @Override
  public Mono<Cart> getCart(String sessionId) {
    return Mono.just(getOrCreate(sessionId));
  }

  private Cart getOrCreate(String sessionId) {
    return carts.computeIfAbsent(sessionId, id -> new Cart(new ArrayList<>()));
  }

  @Override
  public Mono<Cart> deleteCart(String sessionId) {
    carts.remove(sessionId);
    return Mono.just(new Cart(new ArrayList<>()));
  }

  @Override
  public Mono<Void> addItem(String sessionId, String productId, int quantity) {
    Cart cart = getOrCreate(sessionId);

    return catalogService.getProduct(productId)
      .map(p -> new CartItem(
        productId,
        quantity,
        p.getPrice(),
        p.getName(),
        p.getImage()          // ✅ FIX — IMAGE PASSED
      ))
      .doOnNext(cart::addItem)
      .then();
  }

  @Override
  public Mono<Void> removeItem(String sessionId, String productId) {
    getOrCreate(sessionId).removeItem(productId);
    return Mono.empty();
  }
}
