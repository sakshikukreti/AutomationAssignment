package pages;

import browserFactory.BrowserFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Util;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static browserFactory.BrowserFactory.driver;

public class SearchPage extends BrowserFactory {

    public static Logger logger = Logger.getLogger(SearchPage.class);

    public SearchPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='grid-product__title grid-product__title--heading']")
    List<WebElement> productDescriptions;

    @FindBy(xpath = "//div[@class='grid__image-ratio grid__image-ratio--square lazyloaded']")
    List<WebElement> productImage;

    @FindBy(xpath = "//span[@class='next']/a")
    WebElement nextLink;

    @FindBy(xpath = "//*[@class='btn btn--full add-to-cart']")
    WebElement addToCart;

    @FindBy(xpath = "//*[@class='js-qty__adjust js-qty__adjust--plus']")
    WebElement increaseQuantityByOne;

    @FindBy(xpath = "//span[@class='product__price']")
    WebElement productPrice;

    @FindBy(xpath = "//div[@class='grid__item one-half text-right']//following-sibling::*[name()='p']")
    WebElement totalPrice;

    @FindBy(xpath = "//div[@class='pagination']//span[2]")
    WebElement navigateToFirstPage;

    @FindBy(xpath = "//button[@class='text-close js-modal-close']")
    WebElement notTodayLink;

    /**
     * This method verifies the description matches with product name while navigating to the last page
     * @param productName
     * @return boolean value - isMatched
     * @throws NoSuchElementException
     */
    public boolean verifyDescription(String productName) throws NoSuchElementException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        boolean isMatched = false;
        while (true) {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            for (WebElement desc : productDescriptions) {
               if (desc.getText().trim().contains(productName))
                {
                    isMatched = true;
                } else {
                    isMatched = false;
                    break;
                }
            }
            boolean isExists=true;

               if(Util.exists(nextLink))
                {
                   nextLink.click();
                   System.out.println("link clicked");
                }
                else
                {
                   break;
                }
        }
        if(Util.exists(notTodayLink)){
            notTodayLink.click();
        }
        return isMatched;
    }

    /**
     * Add item to the cart
     */
    public void AddItemToCart( )
    {
        navigateToFirstPage.click();
      /*  if(Util.exists(notTodayLink)){
            notTodayLink.click();
        }*/
        productImage.get(0).click();
        getUnitPrice();
        addToCart.click();
        increaseQuantityByOne.click();
    }

    /**
     * returns the unit price of item
     * @return unitPrice
     */
    public double getUnitPrice()
    {
        String itemPrice=productPrice.getAttribute("innerText").trim();
        String price=itemPrice.substring(1,itemPrice.length()).trim();
        double unitPrice=Double.parseDouble(price);
        return unitPrice;
    }

    /**
     * This method fetches the total price of added items
     * @return actualPrice
     */
    public double getTotalPrice()
    {
        String subTotal=totalPrice.getAttribute("innerHTML").trim();
        String subTotalFromApp=subTotal.substring(1,subTotal.length()).trim();
        double actualPrice=Double.parseDouble(subTotalFromApp);
        logger.info("Actual Price:" + actualPrice);
        return actualPrice;
    }

    /**
     * This method calculates the total price as per item added
     * @param quanity
     * @return caculatedPrice
     */
    public double calculatedPrice( int quanity)
    {
        double caculatedPrice= quanity*getUnitPrice();
        logger.info("Expected Price:" + caculatedPrice);
        return caculatedPrice;
    }

}
