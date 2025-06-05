package testCases;

import browserFactory.BrowserFactory;
import browserFactory.RetryClass;
import dataProvider.DataProviderClass;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SearchPage;
import utility.Util;

public class AddProduct extends BrowserFactory{

    public AddProduct()
    {
        super();
    }
    HomePage homePage;
    SearchPage searchPage;
    public static Logger logger = Logger.getLogger(AddProduct.class);
    SoftAssert soft = new SoftAssert();

    @BeforeMethod
    public void setup()
    {
        getBrowser("chrome");
        homePage= new HomePage();
        searchPage= new SearchPage();
    }

    /**
     * This method is to verify title and landing page URL
     */
    @Test (priority = 1)
    public void verifyTitle() {
        //Verify Title
        String title= homePage.verifyTitleOfPage();
        soft.assertEquals(homePage.verifyLandingPageURL(), Util.getStringName("current_url"));
        soft.assertEquals(title, Util.getStringName("title"));
        soft.assertAll();
    }

    /**
     * This method is to search an item, assert productName in product description, add to cart and increace the quantity and then assert expected price with computed price
     * @param productName
     * @param quantity
     */
    @Test(dataProvider="testdata", dataProviderClass = DataProviderClass.class, priority=2, retryAnalyzer = RetryClass.class)
    public void verifyAddToCartWorkflow(String productName, int quantity)
    {
        System.out.println(productName);
        System.out.println(quantity);
        homePage.searchProduct(productName);

        boolean matches=searchPage.verifyDescription(productName);
        soft.assertEquals(matches,true);  // Assertion will fail due to app design as all the productName description do not contain productName in this website
        searchPage.AddItemToCart();

        soft.assertEquals(searchPage.getTotalPrice(),searchPage.calculatedPrice(quantity)); //Assertion will fail due to poorly managed locator in the website, tried multiple locators to fetch the total price value from site but it is retrieving the unitPrice.
        soft.assertAll();
    }

    /**
     * This method is to tear down the driver instance
     */
    @AfterMethod
    public void tearDown()
    {
        close();
    }
}
