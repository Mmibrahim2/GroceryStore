/**
 * This interface defines getter methods for each food's data attributes
 * and is implemented by classes that represent a food and its associated
 * data.
 */
public interface IFood {

    /**
     * Returns name of the food item
     *
     * @return name of the food item
     */
    public String getName();

    /**
     * Returns category (ie, vegetable, fruit, dairy, bread) of the food item
     *
     * @return
     */
    public String getCategory();

    /**
     * Returns price of the food item
     *
     * @return price of the food item
     */
    public double getPrice();

    /**
     * Returns quantity of the food item
     *
     * @return quantity of the food item
     */
    public int getQuantity();

    /**
     * Set the food item's quantity to its current quantity + quantity to add
     * @return the food item's new quantity (old quantity + quantity to add)
     */
    public int setQuantity(int quantityToAdd);

}
