package browserFactory;

import dataProvider.ConfigDataProvider;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class DataProviderFactory {

	public XSSFWorkbook wb;
	public XSSFSheet sh;
	public XSSFRow row;
	FileInputStream fis;
	public static ConfigDataProvider getconfig() {

		ConfigDataProvider config = new ConfigDataProvider();
		return config;
	}
}
