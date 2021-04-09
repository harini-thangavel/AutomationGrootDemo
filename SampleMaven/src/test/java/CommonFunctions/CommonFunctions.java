package CommonFunctions;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions {

	public static WebDriver driver;
	Properties properties=null;
	public static Logger logger=Logger.getLogger(CommonFunctions.class);
	public static ExtentTest test;
	public static ExtentReports report;
	public static Map<String, Object[]> TestNGResults;
	Workbook wb1;

	
	public Properties loadPropertyFile() throws IOException
	{
		FileInputStream fs = new FileInputStream("config.properties");
		properties = new Properties();
		properties.load(fs);
		return properties;
	}		
	@BeforeSuite
	public void launchBrowser() throws IOException
	{
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Navigating to Grootan Technologies Page");
		loadPropertyFile();
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		
		TestNGResults = new LinkedHashMap<String, Object[]>();	
		
		report = new ExtentReports("D:\\Reports\\ExtentReport.html");
		test = report.startTest("GrootanTechnologies...This report is about Grooton Automation");		
		
		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			test.log(LogStatus.INFO, "Opening in Chrome browser");
		}
		else if (browser.equalsIgnoreCase("Firefox"))
		{
			logger.info("Launching Firefox Browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			test.log(LogStatus.INFO, "Opening in Firefox browser");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Workbook wb = new HSSFWorkbook();
		FileOutputStream fileOut = new FileOutputStream("GrootanTechnologies/ExcelFile/TestData.xls");
		wb.createSheet("TSR");		
		wb.write(fileOut);
		fileOut.close();
	}	
	
					
	@AfterClass
	public void resultWriteExcel() throws IOException
	{		
		Set<String> keyset = TestNGResults.keySet();
		int rownum = 0;
		for (String key : keyset) 
		{
			FileInputStream fileIn =new FileInputStream("GrootanTechnologies/ExcelFile/TestData.xls");
			wb1 = new HSSFWorkbook(fileIn);
			Sheet sheet=wb1.getSheet("TSR");				
			Row row = sheet.createRow(rownum++);
			Object[] objArr = TestNGResults.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}	
		}
		FileOutputStream out = new FileOutputStream("GrootanTechnologies/ExcelFile/TestData.xls");
		wb1.write(out);
		out.close();
	}
	
	@AfterSuite
	public void tearDown()
	{
		logger.info("Grootan Technologies automation done successfully!!!...");
		driver.quit();
		report.endTest(test);
		report.flush();
	}	
}