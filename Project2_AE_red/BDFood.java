// --== CS400 Fall 2022 File Header Information ==--
// Name: Sean McHugh
// Email: spmchugh@wisc.edu
// Team: AE
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
public class BDFood implements IFood {
  private String name = null;
  private String category = null;
  private double price = -1.0;
  private int quantity = -1;
  
  public BDFood(String name, String category, double price, int quantity) {
    this.name = name;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public int getQuantity() {
    return quantity;
  }

  @Override
  public int setQuantity(int quantityToAdd) {
    return quantity + quantityToAdd;
  }

  public int compareTo(IFood otherString) {
    return this.getName().compareTo(otherString.getName());
    
  }

  public int compareTo(Object o) {
    IFood food = (IFood) o;
    return this.getName().compareTo(food.getName());
  }
  
  @Override
  public String toString() {
    return "[" + getName() + ", " + getCategory() + ", " + getPrice() + ", " + getQuantity() + "]";
  }
}
