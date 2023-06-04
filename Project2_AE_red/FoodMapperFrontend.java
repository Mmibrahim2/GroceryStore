/**
 * This class implements IFoodMapperFrontend
 *
 * @author Mohammud Ibrahim
 *
 */
import java.util.List;
import java.util.Scanner;

/**
 * This class implements IFoodMapperFrontend
 */
public class FoodMapperFrontend implements IFoodMapperFrontend {

    Scanner scnr;
    IFoodMapperBackend backend;

    /**
     * The constructor that the implementation this interface
     */
    FoodMapperFrontend(Scanner userInputScanner, IFoodMapperBackend backend) {
        scnr = userInputScanner;
        this.backend = backend;
        System.out.println("Welcome to the Grocery Store Inventory Application!"); // header
        System.out.println("---------------------------------------" + "\n");
    }


    /**
     * This method starts the command loop for the frontend, and will terminate when the user exists
     * the app.
     */
    @Override
    public void runCommandLoop() {
        displayMainMenu();
        String choice = scnr.next();
	scnr.nextLine();
        switch (choice) {
            case ("1"):
                addFood();
                break;
            case ("2"):
                removeFood();
                 break;
            case ("3"):
                addQuantity();
                break;
            case ("4"):
                displayFoodInfo();
                break;
            case ("5"):
                displayFoodList();
                break;
            case ("6"):
                System.out.println("Thank you! Have A Nice Day!");
                return;
	}
	System.out.println();
	runCommandLoop();
    }


    /**
     *
     */
    @Override
    public void displayMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("          1) Add Food\r\n" + "          2) Remove Food\r\n"
                + "          3) Add Quantity\r\n" + "          4) Print information given food name\r\n"+ "          5) Print list of item names of given type\r\n"+ "          6) Quit\r\n");

    }


    /**
     * this method implements users interactions with add food menu
     */
    @Override
    public void addFood() {
        try {
            System.out.println("You are in the Add Food Menu:");
            System.out.println("          Enter the name of the food you would like to add:");
            String foodname = scnr.nextLine();
            // check if foodname exists
            System.out.println("          Enter the food category:");
            String foodCat = scnr.nextLine();
            System.out.println("          Enter food price:");
            Double price = scnr.nextDouble();
            System.out.println("          Enter the food quantity:");
            int foodnum = scnr.nextInt();

            if (backend.getByItemName(foodname) == null) {
                Food item = new Food(foodname, foodCat, price, foodnum);
                backend.addFood(item);
            } else {
                System.out.println("          Food Already exist Error, Please try again:");
                
            }
        } catch (Exception e) {
            System.out.println("Error: Sorry check your inputs again");
           
        }
    }


    /**
     * this implements users interaction with remove food menu
     */
    @Override
    public void removeFood() {
        try {
        System.out.println("You are in the Remove Food Menu:");
        System.out.println("          Enter the name of food item you would like to remove:");
        String foodname = scnr.nextLine();

        if (backend.getByItemName(foodname) != null) {
            backend.removeFood(foodname);
        } else {
            System.out.println("          Food Item not Found. try again");
           
        }
        } catch (Exception e) {
            System.out.println("Error: Sorry check your inputs again");
           
        }
    }

    /**
     * This method implements users interaction with the add quantity option
     */
    public void addQuantity() {
        try {
            System.out.println("You are in the Add Quantity Menu:");
            System.out.println("          Enter the name of the food you would like to add quantity to:");
            String foodname = scnr.nextLine();
            System.out.println("          Enter how much quantity you would like to add (Positive):");
            int num = scnr.nextInt();

            if (backend.getByItemName(foodname) != null) {
                int newQuantity = backend.addQuantity(foodname, num);
                // not sure to add
                System.out.println("          " + foodname + " New Quantity: " + newQuantity);
            } else {
                System.out.println("          Food Item not found, please try again: ");
                
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error: Sorry check your inputs again");
           
        }
    }



    /**
     * This method implements users interactions with print food information
     */
    public void displayFoodInfo() {
        try {
            System.out.println("Print Information Menu:");
            System.out.println("          Enter the name of the food you would like information about:");
            String foodname = scnr.nextLine();
            if (backend.getByItemName(foodname) != null) {
                IFood food = backend.getByItemName(foodname);
                System.out.println("          Food: " + foodname);
                System.out.println("          Category: " + food.getCategory());
                System.out.println("          Price($): " + food.getPrice());
                System.out.println("          Quantity: " + food.getQuantity());
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error: Sorry check your inputs again");
            
        }

    }

    /**
     * This method implements users interactions with the print food list by category options
     */
    @Override
    public void displayFoodList() {
        try {
            System.out.println("Print List of Category Menu:");
            System.out.println("          Enter the name of category you would like to find:");
            String category = scnr.nextLine();
            if (backend.searchByCategory(category) != null) {
		String f = "";
                List<IFood> s = backend.searchByCategory(category);
                System.out.println("         Category: " + category);
		for (int i = 0; i < s.size(); i++) {
                    f = s.get(i).toString();
		    System.out.println("          " + f);
                }
            } else {
                System.out.println("          Category not found, Please try again");
            }
        } catch (Exception e) {
            System.out.println("Error: Sorry check your inputs again");
           
        }
    }
}
