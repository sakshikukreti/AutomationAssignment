**Project Structure**
├── src
│   ├── main
│   │   └── java
│   │       ├── browserFactory
│   │       │   ├── BrowserFactory         # WebDriver setup and management
│   │       │   ├── DataProviderFactory    # Creates object of ConfigDataProvider
│   │       │   └── RetryClass             # Retry logic for flaky tests
│   │       ├── dataProvider
│   │       │   ├── ConfigDataProvider     # Reads from config.properties
│   │       │   └── DataProviderClass      # Provides data to tests
│   │       ├── Pages                      # Page Object Model classes
│   │       └── utility                    # Utility/helper classes
│   └── resources
│       └── Drivers                        # Browser driver executables (e.g., chromedriver.exe)

├── test
│   └── java
│       └── testsCases                     # Test case classes

├── resources
│   ├── Configuration
│   │   └── config.properties             # Environment and browser configurations
│   ├── TestData
│   │   └── string.xml                    # Test data file

├── pom.xml                               # Maven project file
├── testng.xml                            # TestNG suite file
└── README.md                             # Project documentation

**Prerequisites**
* Java 11+
* Maven
* Chrome/Firefox browser
* ChromeDriver/GeckoDriver
* IDE (e.g., IntelliJ, Eclipse)

**Installation**
Clone the repository:
git clone https://github.com/sakshikukreti/AutomationAssignment.git

**Install dependencies:**
mvn clean install or from the install option present in Maven tab of IntelliJ

**Technologies Used**
* Selenium WebDriver – browser automation
* TestNG – test orchestration
* Maven – build tool
* TestNG Reports – test reporting
* Log4j / SLF4J – logging
* Page Object Model – test design pattern

**Run all Tests/Execution steps:**
mvn clean test

**Run a specific test suite:**
mvn test -DsuiteXmlFile=testng.xml

**TestCoverage Summary**
This project includes a comprehensive suite of automated UI tests using Selenium WebDriver. The test cases are designed to ensure business workflow of adding item to cart and compute the price.
1. verifyTitle() -: This testcase verify the title and landing URL of website
2. verifyAddToCartWorkflow(String productName, int quantity) -:
   - This test case search a product and iterate all the pages to assert productName in each product listed. (Note: This test case result status is failed because all the productName description does not contain the productName)
   - Add the available product to the cart (Note: Navigated to first page from last page and added the product because available product was in the 1st page and products are sold out in last page in this website)
   - Update the quantity in cart 
   - Retrieve the item price and compute the expected price and assert both (Note: Test case result status is failed as locator is not fetching the correct value from page, tried multiple locators strategy but locators/attributes are poorly managed in this site )
   
**Reporting**
/target/surefire-reports/index.html
/target/surefire-reports/emailable-report.html

**Configuration**
Set base URL, and other parameters in config.properties:

**Design Pattern**
This framework follows the Page Object Model (POM), which improves readability, maintainability, and reusability of test scripts

**Best Practices**
Created BaseClass-"BrowserFactory" for setup/teardown
Externalize test data using xml
Implement retry logic for flaky tests
Parameterized tests
Logging and reporting
