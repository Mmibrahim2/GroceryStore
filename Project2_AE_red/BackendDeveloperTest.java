// --== CS400 Fall 2022 File Header Information ==--
// Name: Sean McHugh
// Email: spmchugh@wisc.edu
// Team: AE
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.fail;
import java.util.List;
import java.util.Scanner;

public class BackendDeveloperTest {

  FoodMapperBackend testBackend;
  IFood food1;
  IFood food2;
  IFood food3;
  IFood food4;
  IFood food5;
  IFood food6;
  IFood food7;
  IFood food8;
  IFood food9;
  IFood food10;

  /**
  * Creates RBT and food data to be used in the testers
  */
  @BeforeEach
  public void createRBT() {
    testBackend = new FoodMapperBackend();
    food1 = new BDFood("Banana", "Fruit", 2.78, 5);
    food2 = new BDFood("Apple", "Fruit", 3.12, 7);
    food3 = new BDFood("Crackers", "Pantry", 4.00, 20);
    food4 = new BDFood("Pork", "Meat", 10.25, 3);
    food5 = new BDFood("Strawberry", "Fruit", 2.84, 2);
    food6 = new BDFood("Chips", "Pantry", 1.29, 2);
    food7 = new BDFood("Cookies", "Pantry", 3.89, 11);
    food8 = new BDFood("Bread", "Pantry", 4.55, 3);

  }


  /**
   * Checks that adding foods to the RBT increment the size of the RBT
   */
  @Test
  public void backendTestOne() {
    assertEquals(testBackend.getNumberOfItems(), 0);
    testBackend.addFood(food1);
    assertEquals(testBackend.getNumberOfItems(), 1);
    testBackend.addFood(food2);
    assertEquals(testBackend.getNumberOfItems(), 2);
    testBackend.addFood(food3);
    assertEquals(testBackend.getNumberOfItems(), 3);

  }

  /**
   * Tests the adding quantity of item, for items that exist and don't exist in the RBT
   */
  @Test
  public void backendTestTwo() {
    testBackend.addFood(food1);
    testBackend.addFood(food2);
    testBackend.addFood(food3);
    assertEquals(testBackend.addQuantity("Banana", 10), 5);
    assertEquals(testBackend.addQuantity("Apple", 3), 7);
    assertEquals(testBackend.addQuantity("Crackers", 0), 20);
    assertEquals(testBackend.addQuantity("Water", 1), -1);
  }

  /**
   * Tests to make sure correct food item is returned when searching by item name
   */
  @Test
  public void backendTestThree() {
    // Adds IFoods to RBT to be used for searching by name
    testBackend.addFood(food1);
    testBackend.addFood(food2);
    testBackend.addFood(food3);
    // Checks that getByItemName returns correct IFood
    assertEquals(testBackend.getByItemName("Banana"), food1);
    assertEquals(testBackend.getByItemName("Apple"), food2);
    assertEquals(testBackend.getByItemName("Crackers"), food3);
  }

  /**
   * Tests that searching for food by category returns expected list
   */
  @Test
  public void backendTestFour() {
    // Sets up array to be tested below
    ArrayList<IFood> testList = new ArrayList<IFood>();
    // Adds IFood objects to RBT to be used for testing
    testBackend.addFood(food1);
    testBackend.addFood(food2);
    testBackend.addFood(food3);
    testBackend.addFood(food5);
    // Searches for foods by category and adds them to testList
    testList = (ArrayList<IFood>) testBackend.searchByCategory("Fruit");
    // Checks that each index in list has expected food
    assertEquals(testList.get(0), food1);
    assertEquals(testList.get(1), food2);
    assertEquals(testList.get(2), food5);
  }

  /**
   * Checks category search on a system with more items
   */
  @Test
  public void backendTestFive() {
    // Sets up array that will be tested below
    ArrayList<IFood> testList = new ArrayList<IFood>();
    // Adds IFood objects to RBT to be used for testing
    testBackend.addFood(food1);
    testBackend.addFood(food2);
    testBackend.addFood(food3);
    testBackend.addFood(food4);
    testBackend.addFood(food5);
    testBackend.addFood(food6);
    testBackend.addFood(food7);
    testBackend.addFood(food8);
    // Searches for foods by category
    testList = (ArrayList<IFood>) testBackend.searchByCategory("Pantry");
    // Tests that each of the indexes in the array have the expected food item
    assertEquals(testList.get(0), food7);
    assertEquals(testList.get(1), food6);
    assertEquals(testList.get(2), food8);
    assertEquals(testList.get(3), food3);
  }

  @Test
  public void IntegrationTest1() {
    // Sets up the test application by reading setting up RBT and reading info from XML file
    FoodLoader foodLoader = new FoodLoader();
    try {
      List<IFood> foodList = foodLoader.loadFoodItems("fooditems.xml");
      FoodMapperBackend backend = new FoodMapperBackend();
      for (IFood food : foodList) {
        backend.addFood(food);
      }
      ArrayList<IFood> testList = new ArrayList<IFood>();
      testList = (ArrayList<IFood>) backend.searchByCategory("Fruit");
      
      // Checks the size of the list returned
      assertEquals(testList.size(), 7);
      
      // Checks the first item in the list
      assertEquals(testList.get(0).getName(), "Bananas");
      assertEquals(testList.get(0).getCategory(), "Fruit");
      assertEquals(testList.get(0).getPrice(), 0.39);
      assertEquals(testList.get(0).getQuantity(), 0);
      
      // Checks the last item in the list
      assertEquals(testList.get(6).getName(), "Strawberries");
      assertEquals(testList.get(6).getCategory(), "Fruit");
      assertEquals(testList.get(6).getPrice(), 1.99);
      assertEquals(testList.get(6).getQuantity(), 0);

    } catch (Exception e) {
      fail("Exception Thrown in IntegrationTest1 " + e.getMessage());
    }
  }

  /**
   * Checks getByItemName and addQuantity for foods in the XML file and RBT
   */
  @Test
  public void IntegrationTest2() {
    // Sets up the test application by reading setting up RBT and reading info from XML file
    FoodLoader foodLoader = new FoodLoader();
    try {
      List<IFood> foodList = foodLoader.loadFoodItems("fooditems.xml");
      FoodMapperBackend backend = new FoodMapperBackend();
      for (IFood food : foodList) {
        backend.addFood(food);
      }
      
      // Tests getByItemName() for Milk which is loaded into by DataWrangler and in the RBT
      // created by Algorithm Engineer
      IFood food = backend.getByItemName("Milk");
      assertEquals(food.getName(), "Milk");
      assertEquals(food.getCategory(), "Dairy");
      assertEquals(food.getPrice(), 4.59);
      assertEquals(food.getQuantity(), 0);
      
      // Tries adding quantity to food above and checking it increased
      backend.addQuantity("Milk", 5);
      assertEquals(food.getQuantity(), 5);
    } catch (Exception e) {
      fail("Exception Thrown in IntegrationTest1 " + e.getMessage());
    }
  }

  /**
   * Method to test the search by item name function output by FrontendDeveloper
   * and the print out menu
   */
  @Test
  public void CodeReviewOfFrontendDeveloper1() {
    // Sets up the program to be checked by if statements below
    FoodLoader foodLoader = new FoodLoader();
    try {
      List<IFood> foodList = foodLoader.loadFoodItems("fooditems.xml");
      FoodMapperBackend backend = new FoodMapperBackend();
      for (IFood food : foodList) {
        backend.addFood(food);
      }
      TextUITester tester = new TextUITester("4\nChicken\n6\n");
      Scanner scnr = new Scanner(System.in);
      FoodMapperFrontend frontend = new FoodMapperFrontend(scnr, backend);
      frontend.runCommandLoop();
      String output = tester.checkOutput();
      
      // Checks welcome question
      if (!output.contains("What would you like to do")) {
        fail("Didn't contain \"What would you like to do\" string");
      }   
      // Checks that the menu presented options
      if (!output.contains("1) Add Food")) {
        fail("Option 1) Wasn't Presented");
      }
      if (!output.contains("2) Remove Food")) {
        fail("Option 2) Wasn't Presented");
      }
      if (!output.contains("3) Add Quantity")) {
        fail("Otion 3) Wasn't Presented");
      }
      if (!output.contains("4) Print information given food name")) {
        fail("Option 4) Wasn't Presented");
      }
      if (!output.contains("5) Print list of item names of given type")) {
        fail("Option 5) Wasn't Presented");
      }
      if (!output.contains("6) Quit")) {
        fail("Option 6) Wasn't Presented");
      }
      
      // Check that Chicken's attributes are correctly printed
      if (!output.contains("Food: Chicken") || !output.contains("Category: Meat") || !output.contains("Price($): 2.99") || !output.contains("Quantity: 0")) {
        fail("Chicken information wasn't printed");
      }
      
      // Checks that goodbye message is correctly printed
      if (!output.contains("Thank you! Have A Nice Day!")) {
        fail("Goodbye message not printed");
      }
    } catch (Exception e) {
      fail("Exceptionm thrown in CodeReviewOfFrontendDeveloper " + e.getMessage());
    }
  }

  /**
   * Method to frontend outputs correct IFood list and attributes when searching by
   * Fruit category
   */
  @Test
  public void CodeReviewOfFrontendDeveloper2() {
    // Sets up the program to be checked by if statements below
    FoodLoader foodLoader = new FoodLoader();
    try {
      List<IFood> foodList = foodLoader.loadFoodItems("fooditems.xml");
      FoodMapperBackend backend = new FoodMapperBackend();
      for (IFood food : foodList) {
        backend.addFood(food);
      }
      TextUITester tester = new TextUITester("5\nFruit\n6\n");
      Scanner scnr = new Scanner(System.in);
      FoodMapperFrontend frontend = new FoodMapperFrontend(scnr, backend);
      frontend.runCommandLoop();
      String output = tester.checkOutput();
      if (!output.contains("<Bananas, Fruit, $0.39, x0>")) {
        fail("Bananas not shown when searching for Fruit");
      }
      if (!output.contains("<Apples, Fruit, $0.99, x0>")) {
        fail("Apples not shown when searching for Fruit");
      }
      if (!output.contains("<Grapes, Fruit, $1.5, x0>")) {
        fail("Grapes not shown when searching for Fruit");
      }
      if (!output.contains("<Lemons, Fruit, $0.69, x0>")) {
        fail("Lemons not shown when searching for Fruit");
      }
      if (!output.contains("<Pears, Fruit, $1.0, x0>")) {
        fail("Pears not shown when searching for Fruit");
      }
      if (!output.contains("<Oranges, Fruit, $0.5, x0>")) {
        fail("Oranges not shown when searching for Fruit");
      }
      if (!output.contains("<Strawberries, Fruit, $1.99, x0>")) {
        fail("Strawberries not shown when searching for Fruit");
      }
    } catch (Exception e) {
      fail("Exceptionm thrown in CodeReviewOfFrontendDeveloper " + e.getMessage());
    }
  }
}
