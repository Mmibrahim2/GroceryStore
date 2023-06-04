/**
 * This class implements IFoodMapperBackend allows me to tests outputs
 * @Author Mohammud Ibrahim
 */

import java.util.ArrayList;
import java.util.List;

public class FDFoodMapperBackend implements IFoodMapperBackend {
    String donuts;

    /**
     * Adds a new food item to the backend's database and is stored in
     * a red-black tree.
     * @param food the food item to add
     */
    @Override
    public void addFood(IFood food) {

    }

    /**
     * Removes given food from tree if food is in the tree
     *
     * @param foodName the name of food to be removed
     */
    @Override
    public void removeFood(String foodName) {
        foodName = foodName;
    }

    /**
     * Returns the number of food items stored in the backend's database.
     * @return the number of food items
     */
    @Override
    public int getNumberOfItems() {
        return 5;
    }

    /**
     * This method can be used to set a filter for the items
     * contained in the search results. A book is only returned as
     * a result for a search by title, it is also contains the string
     * filterBy in the names of its authors.
     * @param filterBy the string that the items must contain
     */
    @Override
    public void setItemType(String filterBy) {
        donuts = filterBy;
    }

    /**
     * Returns the string used as the item filter, null if no item
     * filter is currently set.
     * @return the string used as the item filter, or null if none is set
     */
    @Override
    public String getItemFilter() {
        return "Bakery";
    }

    /**
     * Resets the author filter to null (no filter).
     */
    @Override
    public void resetItemFilter() {
        donuts = null;
    }

    /**
     * Search through all the books in the food tree and return foods whose
     * category matches the category word (and that satisfies the author filter,
     * if an author filter is set).
     * @param category of the food to be searched for
     * @return list of foods found
     *
     */
    @Override
    public List<IFood> searchByCategory(String category) {
        List<IFood> dd = new ArrayList<IFood>();
        if (category.equals("Bakery")) {
            Food f = new Food("Donuts",category,1.49,4);
            dd.add(f);
        } else {
            Food f = new Food("Lemons", "Fruits", 2.99, 9);
            dd.add(f);
        }
        return dd;
    }

    /**
     * Return the item uniquely identified by the name, or null if name is not
     * present in the dataset.
     * @param itemName the items name
     * @return the item identified by the name, or null if name not in database
     */
    public IFood getByItemName(String itemName) {
        if (itemName.equals("cheese")) {
            return null;
        }
        IFood food = new Food(itemName,"Fruit",1.49,4);
        return food;
    }

    /**
     * Checks if the given food exists and if it does, adds the new quantity to
     * the current quantity and then returns the new quantity after both are added
     * @param name name of the food
     * @param quantity the amount of quantity to add to the existing quantity
     * @return the quantity of the given food item, or -1 if it doesn't exist in the system
     */
    public int addQuantity(String itemName, int quantity) {
        return 4;
    }
}

