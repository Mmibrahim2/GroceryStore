// --== CS400 Project One File Header ==--
// Name: Nikita Agarwal
// CSL Username: nikitaa
// Email: nagarwal29@wisc.edu
// Lecture #: 001 @11:00am
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testers for checking the implementation of remove method for Red-Black tree
 */
public class AlgorithmEngineerTests {
    @Test
    public void AlgorithmEngineerTest1(){
        RedBlackTree<Integer> rbtTestOne = new RedBlackTree<Integer>();
        //inserts nodes into the tree
        rbtTestOne.insert(6);
        rbtTestOne.insert(1);
        rbtTestOne.insert(7);
        rbtTestOne.insert(2);
        rbtTestOne.insert(9);
        assertEquals("[ 6, 1, 7, 2, 9 ]", rbtTestOne.toLevelOrderString());
        assertEquals(1, rbtTestOne.root.leftChild.blackHeight);
        assertEquals(0, rbtTestOne.root.rightChild.rightChild.blackHeight);
        rbtTestOne.insert(3);
        assertEquals(0, rbtTestOne.root.leftChild.rightChild.blackHeight);
        //removes a black node with one red child
        rbtTestOne.remove(7);
        assertEquals(1, rbtTestOne.root.leftChild.blackHeight);
        assertEquals("[ 6, 2, 9, 1, 3 ]", rbtTestOne.toLevelOrderString());
        //removes a black node with two red children
        rbtTestOne.remove(2);
        assertEquals(1, rbtTestOne.root.leftChild.blackHeight);
        assertEquals("[ 6, 1, 9, 3 ]", rbtTestOne.toLevelOrderString());
    }

    @Test
    public void AlgorithmEngineerTest2(){
        RedBlackTree<Integer> rbtTestTwo = new RedBlackTree<Integer>();
        //inserts nodes into the tree
        rbtTestTwo.insert(14);
        rbtTestTwo.insert(7);
        rbtTestTwo.insert(19);
        rbtTestTwo.insert(5);
        rbtTestTwo.insert(11);
        rbtTestTwo.insert(18);
        rbtTestTwo.insert(29);
        rbtTestTwo.insert(1);
        rbtTestTwo.insert(6);
        rbtTestTwo.insert(9);
        rbtTestTwo.insert(30);
        assertEquals("[ 14, 7, 19, 5, 11, 18, 29, 1, 6, 9, 30 ]", rbtTestTwo.toLevelOrderString());
        assertEquals(0, rbtTestTwo.root.leftChild.blackHeight);
        assertEquals(1, rbtTestTwo.root.rightChild.rightChild.blackHeight);
        //removes a red leaf node
        rbtTestTwo.remove(30);
        assertEquals(1, rbtTestTwo.root.rightChild.rightChild.blackHeight);
        assertEquals(1, rbtTestTwo.root.rightChild.leftChild.blackHeight);
        assertEquals("[ 14, 7, 19, 5, 11, 18, 29, 1, 6, 9 ]", rbtTestTwo.toLevelOrderString());
        //removes a black leaf node
        rbtTestTwo.remove(29);
        assertEquals(0, rbtTestTwo.root.rightChild.leftChild.blackHeight);
        assertEquals("[ 14, 7, 19, 5, 11, 18, 1, 6, 9 ]", rbtTestTwo.toLevelOrderString());

    }

    @Test
    public void AlgorithmEngineerTest3(){
        RedBlackTree<Integer> rbtTestThree = new RedBlackTree<Integer>();
        //inserts nodes into the tree
        rbtTestThree.insert(30);
        rbtTestThree.insert(40);
        rbtTestThree.insert(20);
        rbtTestThree.insert(50);
        rbtTestThree.insert(35);
        assertEquals("[ 30, 20, 40, 35, 50 ]", rbtTestThree.toLevelOrderString());
        assertEquals(0, rbtTestThree.root.rightChild.leftChild.blackHeight);
        assertEquals(1, rbtTestThree.root.rightChild.blackHeight);
        //creates a double black node while removing 20
        rbtTestThree.remove(20);
        assertEquals("[ 40, 30, 50, 35 ]", rbtTestThree.toLevelOrderString());
        assertEquals(1, rbtTestThree.root.rightChild.blackHeight);
        assertEquals(0, rbtTestThree.root.leftChild.rightChild.blackHeight);
    }

    @Test
    public void AlgorithmEngineerTest4(){
        RedBlackTree<Integer> rbtTestFour = new RedBlackTree<Integer>();
        //inserts nodes into the tree
        rbtTestFour.insert(7);
        rbtTestFour.insert(5);
        rbtTestFour.insert(14);
        rbtTestFour.insert(1);
        rbtTestFour.insert(6);
        rbtTestFour.insert(11);
        rbtTestFour.insert(19);
        rbtTestFour.insert(9);
        rbtTestFour.insert(18);
        assertEquals("[ 7, 5, 14, 1, 6, 11, 19, 9, 18 ]", rbtTestFour.toLevelOrderString());
        assertEquals(0, rbtTestFour.root.rightChild.blackHeight);
        assertEquals(1, rbtTestFour.root.rightChild.leftChild.blackHeight);
        //removes the root node
        rbtTestFour.remove(7);
        assertEquals("[ 6, 5, 14, 1, 11, 19, 9, 18 ]", rbtTestFour.toLevelOrderString());
        assertEquals(0, rbtTestFour.root.rightChild.blackHeight);
        assertEquals(0, rbtTestFour.root.rightChild.leftChild.leftChild.blackHeight);
    }

    @Test
    public void AlgorithmEngineerTest5(){
        RedBlackTree<Integer> rbtTestFive = new RedBlackTree<Integer>();
        //inserts nodes into the tree
        rbtTestFive.insert(7);
        rbtTestFive.insert(5);
        rbtTestFive.insert(14);
        rbtTestFive.insert(1);
        rbtTestFive.insert(6);
        rbtTestFive.insert(11);
        rbtTestFive.insert(19);
        rbtTestFive.insert(9);
        rbtTestFive.insert(18);
        rbtTestFive.remove(7);
        //removes a red node with two black children
        rbtTestFive.remove(14);
        assertEquals("[ 6, 5, 11, 1, 9, 19, 18 ]", rbtTestFive.toLevelOrderString());
        assertEquals(0, rbtTestFive.root.rightChild.blackHeight);
        assertEquals(1, rbtTestFive.root.rightChild.rightChild.blackHeight);
    }

    /**
     * Inserts food items by their names into a red black tree according to the category type from IFood(Data Wrangler's
     * Class). Checks the level order string and color of the updated red black tree after the removal of food items by
     * name.
     */
    @Test
    public void IntegrationTest1AE(){
        FoodLoader loader = new FoodLoader();
        //creates a red black tree
        RedBlackTree<String> groceryTestOne = new RedBlackTree<String>();
        //inserts dairy items into red black tree
        IFood dairyOne = new Food("Eggs", "Dairy", 1.8, 1);
        IFood dairyTwo = new Food("Butter", "Dairy", 6.5, 1);
        IFood dairyThree = new Food("Cheese", "Dairy", 5.9, 1);
        groceryTestOne.insert(dairyOne.getName());
        groceryTestOne.insert(dairyTwo.getName());
        groceryTestOne.insert(dairyThree.getName());
        //checks the Level order
        assertEquals("[ Cheese, Butter, Eggs ]", groceryTestOne.toLevelOrderString());
        //removes the root node with 2 red children
        groceryTestOne.remove("Cheese");
        assertEquals("[ Butter, Eggs ]", groceryTestOne.toLevelOrderString());
        assertEquals(0, groceryTestOne.root.rightChild.blackHeight);
        //removes the root node with 1 red children
        groceryTestOne.remove("Butter");
        assertEquals("[ Eggs ]", groceryTestOne.toLevelOrderString());
        assertEquals(1, groceryTestOne.root.blackHeight);
    }

    /**
     * Inserts food items by their names into a red black tree according to the category type from IFood(Data Wrangler's
     * class). Checks the level order string and color of the updated red black tree after the removal of food items by
     * name.
     */
    @Test
    public void IntegrationTest2AE(){
        FoodLoader loader = new FoodLoader();
        //creates a red black tree
        RedBlackTree<String> groceryTestTwo = new RedBlackTree<String>();
        //inserts Vegetable items into red black tree
        IFood VegetableOne = new Food("Potatoes", "Vegetable", 0.69, 1);
        IFood VegetableTwo = new Food("Onions", "Vegetable", 0.89, 1);
        IFood VegetableThree = new Food("Tomatoes", "Vegetable", 2, 1);
        IFood VegetableFour = new Food("Mushroom", "Vegetable", 3, 1);
        IFood VegetableFive = new Food("Ginger", "Vegetable", 3.99, 1);

        groceryTestTwo.insert(VegetableOne.getName());
        groceryTestTwo.insert(VegetableTwo.getName());
        groceryTestTwo.insert(VegetableThree.getName());
        groceryTestTwo.insert(VegetableFour.getName());
        groceryTestTwo.insert(VegetableFive.getName());
        //checks the Level order
        assertEquals("[ Potatoes, Mushroom, Tomatoes, Ginger, Onions ]", groceryTestTwo.toLevelOrderString());
        //removes black parent with two red children
        groceryTestTwo.remove("Mushroom");
        assertEquals("[ Potatoes, Ginger, Tomatoes, Onions ]", groceryTestTwo.toLevelOrderString());
        assertEquals(0, groceryTestTwo.root.leftChild.rightChild.blackHeight);
        assertEquals(1, groceryTestTwo.root.leftChild.blackHeight);
        //removes the root node
        groceryTestTwo.remove("Potatoes");
        assertEquals("[ Onions, Ginger, Tomatoes ]", groceryTestTwo.toLevelOrderString());
        assertEquals(1, groceryTestTwo.root.rightChild.blackHeight);
    }

    /**
     * This method is testing the Food class. It makes sure that the getter methods
     * return the right values and are doing their functions.
     */
    @Test
    public void CodeReviewOfDataWrangler1() {
        final double delta = 1e-15;
        try {
            //checks if Food method getters are working
            //initializing Food items
            Food fooditem1 = new Food("Grapes", "Fruit", 1.5, 18);
            Food fooditem2= new Food("Cheese", "Dairy", 8.5, 10);

            //checking for fooditem1
            assertEquals(fooditem1.getName(), "Grapes");
            assertEquals(fooditem1.getCategory(), "Fruit");
            assertEquals(fooditem1.getPrice(), 1.5, delta);
            assertEquals(fooditem1.getQuantity(), 18);

            //checking for fooditem2
            assertEquals(fooditem2.getName(), "Cheese");
            assertEquals(fooditem2.getCategory(), "Dairy");
            assertEquals(fooditem2.getPrice(), 8.5, delta);
            assertEquals(fooditem2.getQuantity(), 10);

        }catch(Exception e) {
            fail("Exception thrown");
        }

    }

    /**
     * This method tests the functionality of loadFoodItems() in FoodLoader.java
     */
    @Test
    public void CodeReviewOfDataWrangler2() {
        final double delta = 1e-15;
        try {
            // initializing objects and variables
            FoodLoader foodLoad = new FoodLoader();
            String filePath = "fooditems.xml";
            List<IFood> foodList = new ArrayList<IFood>();

            //adding all items in the file to array
            foodList = foodLoad.loadFoodItems(filePath);
            //checking if the 13th food item(lemons) in the file is in the same
            //position in the array and its contents are not changed.
            assertEquals(foodList.get(12).getName(),"Lemons");
            assertEquals(foodList.get(12).getPrice(),0.69, delta);
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }
}
