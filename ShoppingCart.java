// import required libraries
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner; 

// main program
public class ShoppingCart{
  public static void main(String[] args){

    // needed to initialize program
    int userChoice = 0;
    Boolean exitStore = false;
    ArrayList<Item> shoppingCart = new ArrayList<Item>();
    
    // main store loop runs until user sets exitStore to true
    while (exitStore == false){
      
      // renders the store menu, and prompts user for input
      renderMain();
      Scanner choice = new Scanner(System.in);
      try {userChoice = choice.nextInt();
      }
      catch (Exception E){
      }
      
      // add items to cart menu selection
      if (userChoice == 1){
        // ask user for input attributes, and catch any errors
        try {
          System.out.println("\nEnter the name of the item: ");
          String nameInput = choice.nextLine();
          // consume leftover newline 
          nameInput = choice.nextLine();
          System.out.println("\nEnter the quantity of the item: ");
          BigDecimal qtyInput = choice.nextBigDecimal().abs();
          System.out.println("\nEnter the price of the item: ");
          BigDecimal priceInput = choice.nextBigDecimal().abs();
        
          // with proper inputs, add new item to shopping cart
          if (qtyInput.compareTo(BigDecimal.ZERO) > 0 &&
              priceInput.compareTo(BigDecimal.ZERO) > 0){
            shoppingCart.add(new Item(nameInput, qtyInput, priceInput));
            System.out.println("\nItem added to cart. Returning to main menu.\n");
            nameInput = choice.nextLine();
          }
          // comments to notify user of errors
          else {
            System.out.println("\nQuantity and price must be greater than zero\n");
            System.out.println("\nReturning to main menu.\n");
          }
        }
        catch (Exception E){
          System.out.println("\nInvalid input. Returning to main menu\n");
        }
      }

      // remove items from cart menu selection
      else if (userChoice == 2){
        if (shoppingCart.size() == 0) {
          System.out.println("\nShopping cart is empty. Returning to main menu.\n");
        }
        // if shopping cart has contents, render shopping cart
        else if (shoppingCart.size() > 0) {
          renderForUpdate(shoppingCart);
          System.out.println("\nSelect item to remove: \n");
          // gather user input for which item to remove, notify of errors
          try {
            int removeChoice = choice.nextInt();
            if (removeChoice > shoppingCart.size()){
              System.out.println("\nInvalid input. Returning to main menu.\n");
            }
            else {
              // selects proper index, and removes item from cart
              shoppingCart.remove(removeChoice - 1);
              System.out.println("\nItem removed. Returning to main menu.\n");
            }
          }
          catch (Exception E) {
            System.out.println("\nInvalid input. Returning to main menu.\n");
          }
        }
      }

      // menu selection to update cart contents
      else if (userChoice == 3) {
        if (shoppingCart.size() == 0) {
          System.out.println("\nShopping cart is empty. Returning to main menu.\n");
        }
        else {
          // render shopping cart, and prompt user for updated qty. Catch any errors.
          renderForUpdate(shoppingCart);
          System.out.println("\nEnter the item number to update: \n");
          try {
            int updateChoice = choice.nextInt(); 
            if (updateChoice > shoppingCart.size() || updateChoice <= 0) {
              System.out.println("\nInvalid input. Returning to main menu.\n");
            }
            else {
              // display select item qty, and ask for new qty
              System.out.println("\nCurrent quantity is: " 
              + shoppingCart.get(updateChoice - 1).getItemQty() + "\n");
              System.out.println("\nEnter new quantity: \n");
              BigDecimal newQty = choice.nextBigDecimal().abs();
              // if new qty is greater than zero, update qty
              if (newQty.compareTo(BigDecimal.ZERO) > 0) {
                shoppingCart.get(updateChoice - 1).setItemQty(newQty);
                System.out.println("\nItem quantity updated. Returning to main menu.\n");
              }
              else {
                // notify user of error in item qty input
                System.out.println("\nItem qty must be greater than 0.\n");
                System.out.println("\nPlease use the remove item from cart option.\n");
                System.out.println("\nReturning to main menu\n");
              }
            }
          }
          catch (Exception E) {
            System.out.println("\nInvalid input. Returning to main menu.\n");
          }    
        }
      }
      
      // menu selection for viewing cart contents
      else if (userChoice == 4){
        // check if shopping cart empty
        if (shoppingCart.size() < 1) {
          System.out.println("\nShopping cart is empty. Returning to main menu.\n");
        }
        // if cart not empty, render cart contents
        else if (shoppingCart.size() > 0) {
          renderCart(shoppingCart);
        }
      }
      // menu selection to exit store
      else if (userChoice == 5) {
        System.out.println("\nThank you for shopping with us!");
        // when true this exits the while loop, ending program
        exitStore = true;
        choice.close();
      }
      // if user input invalid integer, go back to top of loop
      else {
        System.out.println("\nInvalid input. Returning to main menu\n");
      }
    }
  }

  // method that renders the main menu when called
  public static void renderMain() {
    System.out.println("\nWelcome to the store!\n");
    System.out.println("Please choose an option:\n");
    System.out.println("1. Add an item to the cart");
    System.out.println("2. Remove an item from the cart");
    System.out.println("3. Update item qty");
    System.out.println("4. View cart");
    System.out.println("5. Checkout\n");
  }

  // method that renders the shopping cart contents when called
  public static void renderCart(ArrayList<Item> contents) {
    System.out.println("\nShopping Cart Contents: \n");
    int count = 0;
    BigDecimal itemCost = new BigDecimal(0);
    // for each item in the cart, display attributes sum total cost
    for (Item item : contents) {
      count +=1;
      System.out.println("\nItem: " + count + "\n");
      System.out.println("Item name: " + item.getItemName());
      System.out.println("Qty: " + item.getItemQty());
      System.out.println("Price: $" + item.getItemPrice());
      itemCost = itemCost.add(item.getItemPrice().multiply(item.getItemQty()));
    }
    // display total cost of items in cart
    System.out.println("\nTotal Cost is: $" + itemCost +"\n");
    System.out.println("\nReturning to main menu\n");
  }
  
  // method that renders shopping cart contents by name only
  public static void renderForUpdate(ArrayList<Item> contents) {
    int indexCount = 0;
    for(Item item : contents){
      indexCount +=1;
      System.out.println(indexCount + ". " + item.getItemName()); 
    }
  }
}