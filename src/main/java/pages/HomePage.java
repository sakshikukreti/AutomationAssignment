package pages;

import browserFactory.BrowserFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BrowserFactory {

    public static Logger logger = Logger.getLogger(HomePage.class);
    public HomePage() {
        PageFactory.initElements( driver, this);
    }

    @FindBy(xpath = "//input[@aria-label='Search our store' and @class='input-group-field']")
    WebElement petStoreSearchBox;

    @FindBy(xpath = "//form[@role='search']/div")
    WebElement petStoreSearchBtn;

   /**
   * This method returns landing page URL
   * @return url
   */
   public String verifyLandingPageURL()
    {
        return driver.getCurrentUrl();
    }

   /**
   * This method returns title
   * @return title
   */
   public String verifyTitleOfPage()
    {
        return driver.getTitle();
    }

   /**
    * This method search the product in petstore website
    * @param productName
    */
   public void searchProduct(String productName)
    {
        petStoreSearchBox.sendKeys(productName);
        petStoreSearchBtn.click();
    }

}
