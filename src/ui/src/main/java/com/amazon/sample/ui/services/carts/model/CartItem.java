@Data
@AllArgsConstructor
public class CartItem {

  private String id;

  private int quantity;

  private int price;

  private String name;

  private String image;   // ✅ ADD THIS

  public int getTotalPrice() {
    return this.quantity * this.price;
  }

  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }
}
