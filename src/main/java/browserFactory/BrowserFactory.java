package browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import static browserFactory.DataProviderFactory.getconfig;

public class BrowserFactory {

	public static WebDriver driver;

	/**
	 * To initiate the browser instance and launch URL
	 * @param browserName
	 */
	public static void getBrowser(String browserName)
	{
		if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.bin","/Applications/Firefox.app/Contents/MacOS/firefox");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			//String driverPath=System.getProperty("user.dir") + "//src//main//resources//Drivers//chromedriver.exe"; // Enable this to execute on Windows platform
			String driverPath=System.getProperty("user.dir") + "//src//main//resources//Drivers//chromedriver";		// Enable this to execute on macbook
			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			//WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(getconfig().geturl());
		getconfig().getPageLoadTimeout();
	}

	/**
	 * To close the driver instance
	 */
	public void close() { //Tear Down
		driver.quit();
	}

}
