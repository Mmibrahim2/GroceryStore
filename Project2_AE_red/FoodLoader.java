// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none

import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Methods of this class can be used to load food items data from a XML file.
 * @author suyogrithesh
 *
 */
public class FoodLoader implements IFoodLoader{

  /**
   * This method loads the list of food items data from a XML file.
   * 
   * @param filepathToXML path to the XML file relative to the executable
   * @return list - list of food items from the file
   * @throws SAXException
   * @throws ParserConfigurationException - when file cannot be parsed
   * @throws IOException - when file is not found
   */
  @Override
  public List<IFood> loadFoodItems(String filepathToXML) throws SAXException, 
  ParserConfigurationException, IOException{
    
    List<IFood> list = new ArrayList<IFood>();

    try {
      
      // get the document builder
      DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = fact.newDocumentBuilder();
      
      // loading XML document
      Document doc = builder.parse(new File(filepathToXML));
      NodeList nodeList = doc.getDocumentElement().getChildNodes();
      
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element elem = (Element) node;
          
          Food foodItem = new Food(elem.getElementsByTagName("ItemName").item(0).getTextContent(),
              elem.getElementsByTagName("Category").item(0).getTextContent(),
              Double.parseDouble(elem.getElementsByTagName("Price").item(0).getTextContent()),
              Integer.parseInt(elem.getElementsByTagName("Quantity").item(0).getTextContent()));
          list.add(foodItem);
        }
      }
            
    } catch (ParserConfigurationException e) {
      System.out.println(e.getMessage());
      throw new ParserConfigurationException();
    } catch (SAXException e) {
      System.out.println(e.getMessage());
      throw new SAXException();
    } catch (IOException e) {
      throw new IOException("File not found.");

    }
    
    return list;
    
  }

}
