package TestCases;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.CommonFunctions;
import PageObjects.Home_Page_Objects;

public class clickSections extends CommonFunctions implements ITestListener{
	static Logger logger = Logger.getLogger(clickSections.class);
	static String actualXpath;
	static Method method;
	static ITestResult result;
	
	@Test(description = "This Test Case will click all the sections in page and take screenshots")
	public static void clickOperation() throws IOException, InterruptedException
	{
		try 
		{
		PageFactory.initElements(driver, Home_Page_Objects.class);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		
		logger.info("Navigating to Home Page");
		Home_Page_Objects.homePage.click(); 
		test.log(LogStatus.INFO,"Grootan Technologies Home Page");	  
		screenshot(Home_Page_Objects.homePage.getText());			  

		logger.info("Navigating to Service Page");
		Home_Page_Objects.servicePage.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Grootan Technologies Service Page");
		screenshot(Home_Page_Objects.servicePage.getText());			  

		logger.info("Navigating to Open Source Page");
		Home_Page_Objects.openSourcePage.click(); 
		test.log(LogStatus.INFO,"Grootan Technologies Open Source Page"); 
		screenshot(Home_Page_Objects.openSourcePage.getText());	

		logger.info("Navigating to Careers Page");
		Home_Page_Objects.careersPage.click(); 
		test.log(LogStatus.INFO,"Grootan Technologies Careers Page");
		screenshot(Home_Page_Objects.careersPage.getText());

		logger.info("Navigating to Contact Page");
		Home_Page_Objects.contactPage.click();
		test.log(LogStatus.INFO,"Grootan Technologies Contact Page"); 
		screenshot(Home_Page_Objects.contactPage.getText());
		
		logger.info("Navigating to Team Page");
		Home_Page_Objects.teamPage.click();
		test.log(LogStatus.INFO, "Grootan Technologies Team Page");
		screenshot(Home_Page_Objects.teamPage.getText());	
				
		logger.info("Navigating to Blog Page"); 
		Home_Page_Objects.blogPage.click();
		String currentWin=driver.getWindowHandle(); 
		Set<String> allHandle=driver.getWindowHandles();

		for (String string : allHandle) 
		{ 
			if(!string.equalsIgnoreCase(currentWin)) 
			{
				driver.switchTo().window(string);
			}
		}
		test.log(LogStatus.INFO,"Grootan Technologies Blog Page"); 
		screenshot(Home_Page_Objects.blogPage.getText());
		driver.switchTo().defaultContent();
		
		Assert.assertTrue(true);	
		
		TestNGResults.put("2", new Object[] { 1d, "Click Sections and screenshot capture","Screenshot of all sections","Pass" });
		}
		catch(Exception e)
		{
		TestNGResults.put("2", new Object[] { 1d, "Click Sections and screenshot capture","Screenshot of all sections","Fail" });
		}
	} 		
	public static void screenshot(String filename) throws IOException, InterruptedException 
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("GrootanTechnologies/resources/Folder1/"+ filename+".png");
		FileUtils.copyFile(src, dest);
		test.log(LogStatus.PASS,test.addScreenCapture(dest.getAbsolutePath()));			
	}	
}	

