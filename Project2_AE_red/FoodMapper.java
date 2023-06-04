import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class with main method to run the food mapper app.
 */
public class FoodMapper {

    public static void main(String[] args) {
	try {
        // load the food from the data file
	      FoodLoader fl = new FoodLoader();
      String filePath = "fooditems.xml";
      List<IFood> list = new ArrayList<IFood>();
      list = fl.loadFoodItems(filePath);
	FoodLoader foodLoader = new FoodLoader();
	String file = "fooditems.xml";
	List<IFood> foods = new ArrayList<IFood>();
	foods = foodLoader.loadFoodItems(file);
        // instantiate the backend
        IFoodMapperBackend backend = new FoodMapperBackend();
        // add all the books to the backend
        for (IFood food : foods) {
            backend.addFood(food);
        }
        // instantiate the scanner for user input (to be used by the front end)
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and food validator to it
        FoodMapperFrontend frontend = new FoodMapperFrontend(userInputScanner, backend);
	// start the input loop of the front end
        frontend.runCommandLoop();
	} catch (Exception e) {
		System.out.println("Error in FoodMapper to start program"); }
    }
}
