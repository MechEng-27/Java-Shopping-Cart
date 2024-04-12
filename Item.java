// import required library
import java.math.BigDecimal;

// define item class with item name, qty and price
public class Item {
  private String itemName;
  private BigDecimal itemQty;
  private BigDecimal itemPrice;

  public Item(String itemName, BigDecimal itemQty, BigDecimal itemPrice) {
    this.itemName = itemName;
    this.itemQty = itemQty;
    this.itemPrice = itemPrice;
  }

  // methods to set item qty, and to get name, qty and price
  public BigDecimal setItemQty(BigDecimal itemQty) {
    return itemQty;
  }

  public String getItemName() {
    return itemName;
  }
  
  public BigDecimal getItemQty() {
    return itemQty;
  }

  public BigDecimal getItemPrice() {
    return itemPrice;
  }
}
