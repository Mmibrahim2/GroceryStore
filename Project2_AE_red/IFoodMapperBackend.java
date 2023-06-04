import java.util.List;

public interface IFoodMapperBackend {
    /**
     * Adds a new food item to the backend's database and is stored in
     * a red-black tree.
     * @param food the food item to add
     */
    public void addFood(IFood food);
    
    /**
     * Removes given food from tree if food is in the tree
     * @param foodName the name of food to be removed
     */
    public void removeFood(String foodName);

    /**
     * Method that takes a food name and if it exists adds the quantity to be added to it
     * @param itemName name of the item for quantity to be added
     * @param quantity amount of the item to be added to current amount
     */
    public int addQuantity(String itemName, int quantity);
    
    /**
     * Returns the number of food items stored in the backend's database.
     * @return the number of food items
     */
    public int getNumberOfItems();

    /**
     * Search through all the books in the food tree and return foods whose
     * category matches the category word (and that satisfies the author filter,
     * if an author filter is set).
     * @param category of the food to be searched for
     * @return list of foods found
     *
     */
    public List<IFood> searchByCategory(String category);
    /**
     * Return the item uniquely identified by the name, or null if name is not
     * present in the dataset.
     * @param name the items name
     * @return the item identified by the name, or null if name not in database
     */
    public IFood getByItemName(String itemName);

    public void setItemType(String filterBy);

    /**
     * Returns the string used as the item filter, null if no item
     * filter is currently set.
     * @return the string used as the item filter, or null if none is set
     */
    String getItemFilter();

    /**
     * Resets the author filter to null (no filter).
     */
    void resetItemFilter();
    
}
