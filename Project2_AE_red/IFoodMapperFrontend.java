
import java.util.List;
public interface IFoodMapperFrontend {
    /**
     * Constructor
     */
     //FoodMapper(Scanner userInputScanner, IFoodMapperBackend backend, IFoodValidator validator);

    /**
     * This method sta®t the command loop for the frontends, terminates when user exists application
     */
    public void runCommandLoop(); // Helper Method
    public void displayMainMenu(); //prints command options to System.out
    public void addFood(); // reads food item from System.in, to add to tree
    public void removeFood(); // reads food item from System.in, to remove food item from tree
    public void displayFoodInfo(); // reads food name item from System.in, displays results
    public void displayFoodList(); // read food name item from System.in, displays results

}
