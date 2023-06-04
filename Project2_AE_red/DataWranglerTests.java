// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * This class contains various tester methods that test all the methods in FoodLoader.java and
 * Book.java
 * 
 * @author SuyogRithesh
 *
 */
public class DataWranglerTests {

	/**
	 * This method is testing the Food class. It makes sure that the methods getName() and 
	 * getCategory() return the right values and are doing their functions.
	 */
	@Test
	public void DataWranglerTest1() {
		try {
			//checks if Food method getters are working
			Food fooditem1 = new Food("Grapes", "Fruit", 1.5, 18);
			Food fooditem2= new Food("Cheese", "Dairy", 8.5, 10);

			assertEquals(fooditem1.getName(), "Grapes");
			assertEquals(fooditem1.getCategory(), "Fruit");

			assertEquals(fooditem2.getName(), "Cheese");
			assertEquals(fooditem2.getCategory(), "Dairy");

		}catch(Exception e) {
			fail("Exception thrown");
		}
	}

	/**
	 * This method tests the functionality of loadFoodItems() in FoodLoader.java
	 */
	@Test
	public void DataWranglerTest2() {

		final double delta = 1e-15;
		try {
			FoodLoader fl = new FoodLoader();
			String filePath = "fooditems.xml";
			List<IFood> list = new ArrayList<IFood>();

			list = fl.loadFoodItems(filePath);
			assertEquals(list.get(3).getName(),"Eggs");
			assertEquals(list.get(3).getPrice(),1.8, delta);
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	/**
	 * This method tests the functionality of loadFoodItems() in FoodLoader.java when file is not
	 * found. 
	 */
	@Test
	public void DataWranglerTest3() {
		//final double delta = 1e-15;
		List<IFood> list = new ArrayList<IFood>();

		try {
			FoodLoader fl = new FoodLoader();
			String filePath = "unknown.xml";
			list = fl.loadFoodItems(filePath);

			fail("DataWranglerTest3 failed");
			//assertEquals(list.get(3).getName(),"Eggs");
			//assertEquals(list.get(3).getPrice(),1.8, delta);
		} catch (Exception e) {     
			assertEquals(list.size(), 0);
		}
	}

	/**
	 * This method is testing the Food class. It makes sure the toString method returns the right 
	 * values and are doing their functions.
	 */
	@Test
	public void DataWranglerTest4() {
		final double delta = 1e-15;
		try {
			//checks if Food method getters are working
			Food fooditem = new Food("Butter", "Dairy", 6.5, 10);
			assertEquals(fooditem.getName(), "Butter");
			assertEquals(fooditem.getCategory(), "Dairy");
			assertEquals(fooditem.getPrice(), 6.5, delta);
			assertEquals(fooditem.getQuantity(), 10);

			//checking the toString() method
			assertEquals(fooditem.toString(), "<Butter, Dairy, $6.5, x10>");

		}catch(Exception e) {
			fail("Exception thrown");
		}
	}

	/**
	 * This method is testing the Food class. It makes sure that the methods getPrice(), 
	 * setQuantity() and getQuantity() return the right values and are doing their functions.
	 */
	@Test
	public void DataWranglerTest5() {
		final double delta = 1e-15;
		try {
			//checks if Food method getters are working
			Food fooditem1 = new Food("Grapes", "Fruit", 1.5, 18);
			Food fooditem2= new Food("Cheese", "Dairy", 8.5, 10);

			assertEquals(fooditem1.getPrice(), 1.5, delta);
			assertEquals(fooditem1.getQuantity(), 18);

			assertEquals(fooditem2.getPrice(), 8.5, delta);
			assertEquals(fooditem2.getQuantity(), 10);

			//checking is the setters(mutator) setQuantity() is working
			assertEquals(fooditem1.setQuantity(12), 30);
			assertEquals(fooditem1.getQuantity(),30);

			assertEquals(fooditem2.setQuantity(15), 25);
			assertEquals(fooditem2.getQuantity(),25);

		}catch(Exception e) {
			fail("Exception thrown");
		}
	}

	/**
	 * This is an Integration test that confirms that the data wrangler's code 
	 * has integrated with backends code.
	 * 
	 */
	@Test
	public void IntegrationTest1() {
		FoodLoader foodLoader = new FoodLoader();
		FoodMapperBackend foods = new FoodMapperBackend();
		try {
			List<IFood> foodList1 = foodLoader.loadFoodItems("fooditems.xml");   
			//Food fooditem1 = new Food("Strawberries", "Fruit", 2.3, 10);
                        //Food fooditem2 = new Food("Shrimp", "Meat", 9.5, 12);
			//foods.addFood(fooditem1);
			//foods.addFood(fooditem2);

			for(IFood fooditem: foodList1) {
				foods.addFood(fooditem);
			}

			//check number of items
			assertEquals(foods.getNumberOfItems(), 30);
		}catch (Exception e) {
			fail("Exception thrown");
		}

	}

	/**
	 * This is an Integration test that confirms that the data wrangler's code 
	 * has integrated with algorithm engineers code.
	 * 
	 */
	@Test
	public void IntegrationTest2() {
		//FoodLoader loader = new FoodLoader();
		//creates an RBT
		RedBlackTree<String> grocery = new RedBlackTree<String>();
		IFood VegetableOne = new Food("Ginger", "Vegetable", 3.99, 1);
		IFood VegetableTwo = new Food("Onions", "Vegetable", 0.89, 1);

		//inserts vegetables into RBT
		grocery.insert(VegetableOne.getName());
		grocery.insert(VegetableTwo.getName());

		//removing items and checking size to see if insert and remove are 
		//working with String type.
		assertEquals(grocery.size, 2);
		grocery.remove("Ginger");
		assertEquals(grocery.size, 1);		
		grocery.remove("Onions");
		assertEquals(grocery.size, 0);


	}


	/**
	 * Inserts food items into a red black tree according to the category time. 
	 * Checks the level order and color of the red black tree after the removal of any food item
	 * This method checks items with the "Vegetable" category
	 */
	@Test
	public void CodeReviewOfAlgorithmEngineer1() {
		//FoodLoader loader = new FoodLoader();
		//creates an RBT
		RedBlackTree<String> grocery1 = new RedBlackTree<String>();
		IFood VegetableOne = new Food("Ginger", "Vegetable", 3.99, 1);
		IFood VegetableTwo = new Food("Onions", "Vegetable", 0.89, 1);
		IFood VegetableThree = new Food("Mushroom", "Vegetable", 3, 1);
		IFood VegetableFour = new Food("Tomatoes", "Vegetable", 2, 1);
		IFood VegetableFive = new Food("Carrots", "Vegetable", 0.65, 1);

		//inserts vegetables into RBT
		grocery1.insert(VegetableOne.getName());
		grocery1.insert(VegetableTwo.getName());
		grocery1.insert(VegetableThree.getName());
		grocery1.insert(VegetableFour.getName());
		grocery1.insert(VegetableFive.getName());

		assertEquals("[ Mushroom, Ginger, Onions, Carrots, Tomatoes ]", 
				grocery1.toLevelOrderString());
		//removes the root node with two black children
		grocery1.remove("Mushroom");
		//tests the level order and color of the node in the updated rbt
		assertEquals("[ Ginger, Carrots, Onions, Tomatoes ]", grocery1.toLevelOrderString());
		assertEquals(0, grocery1.root.rightChild.rightChild.blackHeight);
		//removes the red child leaf
		grocery1.remove("Tomatoes");
		assertEquals("[ Ginger, Carrots, Onions ]", grocery1.toLevelOrderString());
		assertEquals(1, grocery1.root.rightChild.blackHeight);
	}

	/**
	 * Inserts food items into a red black tree according to the category time. 
	 * Checks the level order and color of the red black tree after the removal of any food item
	 * This method checks items with the "Fruit" category
	 */
	@Test
	public void CodeReviewOfAlgorithmEngineer2() {
		//FoodLoader loader = new FoodLoader();
		//creates an RBT
		RedBlackTree<String> grocery2 = new RedBlackTree<String>();
		IFood FruitOne = new Food("Pears", "Fruit", 1, 1);
		IFood FruitTwo = new Food("Strawberries", "Fruit", 1.99, 1);
		IFood FruitThree = new Food("Bananas", "Fruit", 0.39, 1);
		IFood FruitFour = new Food("Grapes", "Fruit", 1.5, 1);

		//inserts vegetables into RBT
		grocery2.insert(FruitOne.getName());
		grocery2.insert(FruitTwo.getName());
		grocery2.insert(FruitThree.getName());
		grocery2.insert(FruitFour.getName());

		assertEquals("[ Pears, Bananas, Strawberries, Grapes ]", grocery2.toLevelOrderString());
		//removes the root node with red child
		grocery2.remove("Bananas");
		//tests the level order and color of the node in the updated rbt
		assertEquals("[ Pears, Grapes, Strawberries ]", grocery2.toLevelOrderString());
		assertEquals(1, grocery2.root.rightChild.blackHeight);
		//removes the root node
		grocery2.remove("Pears");
		assertEquals("[ Grapes, Strawberries ]", grocery2.toLevelOrderString());
		assertEquals(0, grocery2.root.rightChild.blackHeight);
	}

}

