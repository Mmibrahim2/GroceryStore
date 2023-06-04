/**
 * This class tests the implemented methods of FoodMapperFrontend
 *
 * @author Mohammud Ibrahim
 *
 */
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class uses JUnit to test FD methods
 */
public class FrontDeveloperTests {


    /**
     * main used to call methods
     * @return all true if all test pass
     */

    /**
     * testing quit options and basic format
     * @return true if all test pass, false other wise
     */
    @Test
    public void FrontendDeveloperTest1() {
        try {
            TextUITester tester = new TextUITester("6\n");
            Scanner scnr = new Scanner(System.in);
            FDFoodMapperBackend backend = new FDFoodMapperBackend();
            FoodMapperFrontend FD = new FoodMapperFrontend(scnr, backend);
            FD.runCommandLoop();
            String output = tester.checkOutput();
            String expected = output.trim().replaceAll("\n","");
            Boolean s = output.contains("Welcome to the Grocery Store Inventory Application!");
            assertEquals(true,s);
            Boolean d = output.contains("          1) Add Food\r\n" + "          2) Remove Food\r\n"
                    + "          3) Add Quantity\r\n" + "          4) Print information given food name\r\n"+ "          5) Print list of item names of given type\r\n"+ "          6) Quit\r\n");
            assertEquals(true,d);

            // ensures
            Boolean f = output.contains("Thank you! Have A Nice Day!");
            assertEquals(true,f);

        } catch(Exception e) {
            System.out.println("Error Exception");
        }

    }

    /**
     * Testing option 3
     * @return true if all test pass, false otherwise
     */
    //
    @Test
    public void FrontendDeveloperTest2() {
        try {
            int quantity = 4;
            TextUITester tester = new TextUITester("3\nDonuts\n" + quantity);
            Scanner scnr = new Scanner(System.in);
            FDFoodMapperBackend backend = new FDFoodMapperBackend();
            FoodMapperFrontend FD = new FoodMapperFrontend(scnr, backend);
            FD.runCommandLoop();
            String output = tester.checkOutput();
            Boolean s = output.contains("You are in the Add Quantity Menu:");
            assertEquals(true,s);
            assertEquals(5,backend.getNumberOfItems());
            Boolean f = output.contains("          Enter the name of the food you would like to add quantity to:");
            assertEquals(true,f);
            assertEquals(4,backend.addQuantity("Donuts",quantity));
            Boolean l = output.contains("          " + "Donuts" + " New Quantity: " + 4);
            assertEquals(true,l);


        } catch(Exception e) {
            System.out.println("Error");
        }

    }


    /**
     * This method test food info  option
     * @return true if test pass, false otherwise
     */
    // food info
    @Test
    public void FrontendDeveloperTest3() {
            try {
                TextUITester tester = new TextUITester("4\nLemons\n" + 4);
                Scanner scnr = new Scanner(System.in);
                FDFoodMapperBackend backend = new FDFoodMapperBackend();
                FoodMapperFrontend FD = new FoodMapperFrontend(scnr, backend);
                FD.runCommandLoop();
                String output = tester.checkOutput();
                Boolean b = output.contains("Enter the name of the food you would like information about:");
                assertEquals(true,b);
                Boolean f = output.contains("          Food: " + "Lemons" + "\n          Category: " + "Fruit" + "\n" + "          Price($): " + 1.49 + "\n" + "          Quantity: " + 4);
                assertEquals(true,f);
            } catch(Exception e) {
                System.out.println("Error Exception");

            }
        }


    /**
     * This method test food list format
     * @return true if test pass otherwise false
     */
    // food list
    @Test
    public void FrontendDeveloperTest4() {
        try {
            TextUITester tester = new TextUITester("5\nBakery");
            Scanner scnr = new Scanner(System.in);
            FDFoodMapperBackend backend = new FDFoodMapperBackend();
            FoodMapperFrontend FD = new FoodMapperFrontend(scnr, backend);
            FD.runCommandLoop();
            String output = tester.checkOutput();
            Boolean s = output.contains("          " + "Bakery" + ": " + "Donuts");
            assertEquals(true,s);
        } catch(Exception e) {
            System.out.println("Error Exception");
        }
    }

    //quantity

    /**
     * This test the error message when an invalid food item is added
     * @return true if all test pass, false otherwise
     */
    @Test
    public void FrontendDeveloperTest5() {
        try {
            TextUITester tester = new TextUITester("3\ncheese\n" + 4);
            Scanner scnr = new Scanner(System.in);
            IFoodMapperBackend backend = new FDFoodMapperBackend();
            IFoodMapperFrontend FD = new FoodMapperFrontend(scnr, backend);
            FD.runCommandLoop();
            String output = tester.checkOutput();
            Boolean b = output.contains("          Food Item not found, please try again: ");
            assertEquals(true,b);
        } catch(Exception e) {
            System.out.println("Error Exception");
        }
    }


    /**
     * This test backends implementations of adding food and getitems number
     */
    // partner adding food
    @Test
    public void CodeReviewOfBackendDeveloperTest1() {
        try {
            IFoodMapperBackend backend = new FoodMapperBackend();
            assertEquals(0,backend.getNumberOfItems());
            IFood food = new Food("Protein shake","Dairy",2.29,4);
            backend.addFood(food);
            assertEquals(food,backend.getByItemName("Protein shake"));
            assertEquals(1,backend.getNumberOfItems());
        } catch (Exception e) {
            System.out.println("Error: Exception thrown");
        }


    }


    /**
     * This test backend implementation of adding quantity and remove
     */
    // partner
    @Test
    public void CodeReviewOfBackendDeveloperTest2() {
        try {
            IFoodMapperBackend backend = new FoodMapperBackend();
            IFood food = new Food("Butter","Dairy",2.30,0);
            backend.addFood(food);
            backend.addQuantity("Butter", 3);
            IFood food1 = backend.getByItemName("Butter");
            assertEquals(3, food.getQuantity());
            assertEquals("Dairy", food.getCategory());
            backend.removeFood("Butter");
            //assertNull(backend.getByItemName("Butter"));
        } catch (Exception e) {
            System.out.println("ERROR: Exception thrown");
        }
    
    }


    /**
     * This test integration of Print info given Item
     */
    @Test
    public void IntegrationTest1() {
        TextUITester tester = new TextUITester("6\n");
        Scanner scnr = new Scanner(System.in);
        IFoodMapperBackend backend = new FoodMapperBackend();
        IFoodMapperFrontend FD = new FoodMapperFrontend(scnr, backend);
        FD.runCommandLoop();
        String output = tester.checkOutput();
        Boolean b = output.contains("Welcome to the Grocery Store Inventory Application!");
        assertEquals(true,b);
        Boolean d = output.contains("          1) Add Food\r\n" + "          2) Remove Food\r\n"
                + "          3) Add Quantity\r\n" + "          4) Print information given food name\r\n"+ "          5) Print list of item names of given type\r\n"+ "          6) Quit\r\n");
        assertEquals(true,d);
        Boolean v = output.contains("Thank you! Have A Nice Day!");
        assertEquals(true,v);

    }

    /**
     * This test implementation of Print list
     */

    @Test
    public void IntegrationTest2() {
	    TextUITester tester = new TextUITester("5\nFruit");
        Scanner scnr = new Scanner(System.in);
        IFoodMapperBackend backend = new FoodMapperBackend();
        IFoodMapperFrontend FD = new FoodMapperFrontend(scnr, backend);
        IFood food = new Food("DragonFruit","Fruit",3.99,4);
	backend.addFood(food);
        String output = tester.checkOutput();
	assertEquals(3.99,backend.getByItemName("DragonFruit").getPrice(),0.0f);
        assertEquals(4,backend.getByItemName("DragonFruit").getQuantity());
	assertEquals("Fruit",backend.getByItemName("DragonFruit").getCategory());
    }
}
