// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none
/**
 * This interface defines getter methods for each food's data attributes
 * and is implemented by classes that represent a food and its associated
 * data.
 */
public interface IFood extends Comparable {

   /**
    * Returns name of the food item
    * @return name of the food item
    */
    String getName();
    
    /**
     * Returns category (ie, vegetable, fruit, dairy, bread) of the food item
     * @return
     */
    String getCategory();
    
    /**
     * Returns price of the food item
     * @return price of the food item
     */
    double getPrice();
    
    /**
     * Returns quantity of the food item
     * @return quantity of the food item
     */
    int getQuantity();
    
    /**
     * Set the food item's quantity to its current quantity + quantity to add
     * @return the food item's new quantity (old quantity + quantity to add)
     */
    int setQuantity(int quantityToAdd);
}
