// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none

/**
 * This class implements the unimplemented methods in the IFood interface that defines getter
 * methods for each food item's data attributes.
 * 
 * @author SuyogRithesh
 *
 */
public class Food implements IFood{
  
  private String name; // name field
  private String category; // category field
  private double price; // price field
  private int quantity; // quantity field
  
  /**
   * This constructor creates an object of type Book and initializes the title, author, and ISBN13
   * number
   * 
   * @param name - the name of the food item
   * @param category - the category of the food item
   * @param price - the price of the food item
   * @param quantity - the quantity of the food item
   */
  public Food(String name, String category, double price, int quantity) {
    this.name = name; // initializes name
    this.category = category; // initializes category
    this.price = price; // initializes price
    this.quantity = quantity; // initializes quantity
  }

  /**
   * Returns name of the food item
   * 
   * @return name - the name of the food item
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns category (i.e., vegetable, fruit, dairy, bread) of the food item
   * 
   * @return category - the category of the food item
   */
  @Override
  public String getCategory() {
    return this.category;

  }

  /**
   * Returns price of the food item
   * 
   * @return price - the price of the food item
   */
  @Override
  public double getPrice() {
    return this.price;

  }

  /**
   * Returns quantity of the food item
   * 
   * @return quantity - the quantity of the food item
   */
  @Override
  public int getQuantity() {
    return this.quantity;

  }

  /**
   * Set the food item's quantity to its current quantity + quantity to add
   * 
   * @return val - the food item's new quantity (old quantity + quantity to add)
   */
  @Override
  public int setQuantity(int quantityToAdd) {
    int val = this.quantity + quantityToAdd;
    this.quantity = val;
    return val;
  }

  @Override
  public String toString() {
       return "<" + name + ", " + category + ", $" + price + ", x" + quantity + ">";
  }

  public int compareTo(IFood otherString) {
    return this.getName().compareTo(otherString.getName());
    
  }

  @Override
  public int compareTo(Object o) {
    IFood food = (IFood) o;
    return this.getName().compareTo(food.getName());
  }
}
