package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	public static Properties prop;

	public ConfigDataProvider() {
		File src = new File(System.getProperty("user.dir") + "/src/test/resources/Configuration/"
				+ "config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
			System.out.println("property is loaded");
		} catch (Exception e) {
			System.out.println("failed to load properties");
			System.out.println("print src path" +src);
		}
	}

	public String geturl() {
		return prop.getProperty("URL");
	}

	public long getPageLoadTimeout() {
		String pageLoadTimeout = prop.getProperty("pageLoadTimeout");
		return Long.parseLong(pageLoadTimeout);
	}

	public long getImplicitWait() {
		String implicitwait = prop.getProperty("implicitWait");
		return Long.parseLong(implicitwait);
	}

}
