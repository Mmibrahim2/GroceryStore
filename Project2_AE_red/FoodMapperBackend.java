// --== CS400 Fall 2022 File Header Information ==--
// Name: Sean McHugh
// Email: spmchugh@wisc.edu
// Team: AE
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import java.util.List;

public class FoodMapperBackend extends RedBlackTree implements IFoodMapperBackend {
  RedBlackTree foodTree = new RedBlackTree();
  // List of categories that new and existing items/food can have and used for searching
  private String[] categories =
      new String[] {"Meat", "Dairy", "Fruit", "Vegetable", "Pantry", "Bakery"};


  /**
   * Adds an item/food to the RBT
   * 
   * @param food the food to be removed from the tree
   */
  @SuppressWarnings("unchecked")
  @Override
  public void addFood(IFood food) {
    foodTree.insert(food);
  }

  /**
   * Removes a food of given name from the RBT if it exists
   * 
   * @param foodName the name of the food to be removed from the tree
   */
  @Override
  public void removeFood(String foodName) {
    if (getByItemName(foodName) != null) {
      foodTree.remove(getByItemName(foodName));
    } else {
      System.out.println("Food to be removed doesn't exist");
    }
  }

  /**
   * Returns the number of items in the tree
   * 
   * @return number of items in the tree
   */
  @Override
  public int getNumberOfItems() {
    return foodTree.size;
  }

  /**
   * Adds a given quantity to the food with the specified name
   * 
   * @param itemName the name of the item to add quantity to
   * @param quantity the quantity to be added
   * @return the new quantity of the item
   */
  @Override
  public int addQuantity(String itemName, int quantity) {
    if (getByItemName(itemName) == null) {
      return -1;
    }
    IFood food = getByItemName(itemName);
    food.setQuantity(quantity + food.getQuantity());
    return food.getQuantity();
  }

  @Override
  /**
   * Creates an ArrayList with foods of the passed category
   * 
   * @param category the category of foods to be added to the list
   * @return an ArrayList containing IFood items of the given category
   */
  public List<IFood> searchByCategory(String category) {
    boolean categoryExists = false;
    for (int i = 0; i < categories.length; i++) {
      if (categories[i].equals(category)) {
        categoryExists = true;
      }
    }
    if (!categoryExists) {
      return null;
    }
    ArrayList<IFood> foodList = new ArrayList<IFood>();
    searchByCategoryHelper(category, foodTree.root, foodList);

    return foodList;
  }

  /**
   * Helper method for searchByCategory
   * 
   * @param category Category of foods to add to list
   * @param node     Current node in the RBT to check category of
   * @param foodList The list of foods of given category to be returned
   */
  private void searchByCategoryHelper(String category, Node<IFood> node,
      ArrayList<IFood> foodList) {
    if (node == null) {
      return;
    }
    if (node.data != null && node.data.getCategory().equals(category)) {
      foodList.add(node.data);
    }
    if (node.leftChild != null) {
      searchByCategoryHelper(category, node.leftChild, foodList);
    }
    if (node.rightChild != null) {
      searchByCategoryHelper(category, node.rightChild, foodList);
    }
  }

  /**
   * Method to search for item given its name
   * 
   * @param itemName is the name of the item to be searched for
   * @return IFood with the same name as passed name, or null if no item with that name exists
   */
  @Override
  public IFood getByItemName(String itemName) {
    IFood food = null;
    food = getByItemNameHelper(itemName, foodTree.root);
    return food;
  }

  /**
   * Helper method to search for food given its name
   * 
   * @param itemName Name of item to be searched for
   * @param node     Current node to check name of in tree
   * @return Returns node with IFood data if the name matches passed name
   */
  private IFood getByItemNameHelper(String itemName, Node<IFood> node) {
    if (node.data.getName().equals(itemName)) {
      return node.data;
    }
    if (node.data.getName().compareTo(itemName) > 0) {
      if (node.leftChild != null) {
        return getByItemNameHelper(itemName, node.leftChild);
      }
      else {
        return null;
      }
    }
    if (node.data.getName().compareTo(itemName) < 0) {
      if (node.rightChild != null) {
        return getByItemNameHelper(itemName, node.rightChild);
      }
      else {
        return null;
      }
    }
    return null;
  }

    @Override
    public void setItemType(String filterBy) { }

    /**
     * Returns the string used as the item filter, null if no item
     * filter is currently set.
     * @return the string used as the item filter, or null if none is set
     */
    @Override
    public String getItemFilter() { return null; }

    /**
     * Resets the author filter to null (no filter).
     */
    @Override
    public void resetItemFilter() { }
}
