import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
/**
 * Instances of this interface can be used to load food items data from a XML file.
 */
public interface IFoodLoader {

  /**
   * This method loads the list of food items data from a XML file.
   * 
   * @param filepathToXML path to the XML file relative to the executable
   * @return list - list of food items from the file
   * @throws SAXException
   * @throws ParserConfigurationException - when file cannot be parsed
   * @throws IOException - when file is not found
   */
    List<IFood> loadFoodItems(String filepathToXML) throws SAXException, 
    ParserConfigurationException, IOException;

}
