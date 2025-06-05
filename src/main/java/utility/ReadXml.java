package utility;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReadXml {

    public static Logger logger = Logger.getLogger(ReadXml.class);


    /**
    *  This will fetch an attribute value from a given xml.
    * */
     static String getXmlString(String filename, String attributeValue){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = builder != null ? builder.parse(new File(System.getProperty("user.dir") + "//src//test//resources//TestData//" + filename)) : null;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        NodeList list = doc != null ? doc.getElementsByTagName("string") : null;

//        logger.info("Total no of Strings : " + list.getLength()); //enable to get count when needed

         String requiredValue = "";
        if (list != null) {
            for (int i = 0; i < list.getLength(); i++) {

                Element node = (Element) list.item(i);

                if ((node.getAttribute("name")).equalsIgnoreCase(attributeValue)) {
                    requiredValue = node.getTextContent();
                }
            }
        }
        return requiredValue;
    }

}
