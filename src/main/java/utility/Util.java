package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static browserFactory.BrowserFactory.driver;
import static utility.ReadXml.getXmlString;

public class Util {

    public static Logger logger = Logger.getLogger(Util.class);

    private static final String stringsFile = "strings.xml";

    WebDriverWait wait = new WebDriverWait(driver, 30);

    /**
    *  This will fetch the value from strings.xml file
    * */
    public static String getStringName(String attributeValue){
       return getXmlString(stringsFile, attributeValue);
    }

    /**
     *  This will fetch the value from strings.xml file
     * */
    public static String getStringData(String attributeValue){
        return getXmlString(stringsFile, attributeValue);
    }

    /**
     * This method is used to verify whether element displayed or not.
     * @param element - Web Element
     * @return - returns boolean value.
     */
    public static boolean exists(WebElement element)
    {
        try {
            String temp = element.toString();
            temp = temp.substring(0, temp.length() - 1);

            String locatorValue = temp.split("xpath: ")[1];
            int locatorSize = driver.findElements(By.xpath(locatorValue)).size();
            logger.info(String.format("exists: element status: %s", locatorSize > 0));
            return locatorSize > 0;
        }
        catch (NullPointerException e) {
            return false;
        }
    }


}
