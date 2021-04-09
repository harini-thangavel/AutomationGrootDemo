package TestCases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.CommonFunctions;
import PageObjects.Home_Page_Objects;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class imageComparison extends CommonFunctions 
{
	static Method method;
	
	@Test(description = "This Test Case will compare the images of CTO and HR Manager in Team Page")
	public static void imageCompare() throws IOException
	{
		try
		{
		PageFactory.initElements(driver, Home_Page_Objects.class);
		
		Home_Page_Objects.teamPage.click();
		WebElement ctoImgXpath = Home_Page_Objects.ctoXpath;
	    WebElement hrImgXpath = Home_Page_Objects.hrXpath;
	    
	    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
	    
	    test.log(LogStatus.INFO, "Image Comparison of CTO and HR Manager");
	    
	    Screenshot screenshotCTO = new AShot().takeScreenshot(driver,ctoImgXpath);
	    ImageIO.write(screenshotCTO.getImage(),"PNG",new File("GrootanTechnologies/resources/cto.jpg"));
	    
	    Screenshot screenshotHR = new AShot().takeScreenshot(driver,hrImgXpath);
	    ImageIO.write(screenshotHR.getImage(),"PNG",new File("GrootanTechnologies/resources/hr.jpg"));
	    
	    ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(screenshotCTO, screenshotHR);
        Assert.assertEquals(diff.hasDiff(),true);	
        
        test.log(LogStatus.FAIL,test.addScreenCapture("GrootanTechnologies/resources/cto.jpg"));
        test.log(LogStatus.FAIL,test.addScreenCapture("GrootanTechnologies/resources/hr.jpg"));
        TestNGResults.put("4", new Object[] { 3d, "Image comparison","Comparing images of CTO and HR","Fail" });
	}
		catch(Exception e)
		{
			TestNGResults.put("4", new Object[] { 3d, "Image comparison","Comparing images of CTO and HR","Pass" });
		}		
}
	}
