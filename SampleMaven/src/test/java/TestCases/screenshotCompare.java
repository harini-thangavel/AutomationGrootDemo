package TestCases;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.CommonFunctions;
import PageObjects.Home_Page_Objects;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
public class screenshotCompare extends CommonFunctions 
{
	static Method method;
	public static void compare(String fileName) throws IOException
	{
		File file = new File("GrootanTechnologies/resources/Folder2/"+fileName+".png");
		BufferedImage actualImg = ImageIO.read(file);

		File file1 = new File("GrootanTechnologies/resources/Folder1/"+fileName+".png");
		BufferedImage expectedImg = ImageIO.read(file1);

		ImageDiffer imgDiff = new ImageDiffer(); 
		ImageDiff diff=imgDiff.makeDiff(actualImg,expectedImg);
		//Assert.assertTrue(diff.hasDiff());
		if(diff.hasDiff())
		{
			test.log(LogStatus.WARNING, fileName,"Images are different");
		}
		else
		{
			test.log(LogStatus.WARNING, fileName,"Images are same");
		}
		
	}
	@Test(description = "This Test Case will compare the images of screenshots from previous run records")
	public static void compareScreenshot() throws IOException
	{
		try
		{
			PageFactory.initElements(driver,Home_Page_Objects.class);
			test.log(LogStatus.INFO, "Screenshot Comparison of Home Page");
			compare(Home_Page_Objects.homePage.getText());

			test.log(LogStatus.INFO, "Screenshot Comparison of Service Page");
			compare(Home_Page_Objects.servicePage.getText());

			test.log(LogStatus.INFO, "Screenshot Comparison of Open Source Page");
			compare(Home_Page_Objects.openSourcePage.getText());

			test.log(LogStatus.INFO, "Screenshot Comparison of Careers Page");
			compare(Home_Page_Objects.careersPage.getText());

			test.log(LogStatus.INFO, "Screenshot Comparison of Contact Page");
			compare(Home_Page_Objects.contactPage.getText());

			test.log(LogStatus.INFO, "Screenshot Comparison of Team Page");
			compare(Home_Page_Objects.teamPage.getText());

			test.log(LogStatus.INFO, "Screenshot Comparison of Blog Page");
			compare(Home_Page_Objects.blogPage.getText());
			TestNGResults.put("5", new Object[] { 4d, "Screenshot Comparison","Screenshot Comparison","Pass" });
		}	
		catch(Exception e)
		{
			TestNGResults.put("5", new Object[] { 4d, "Screenshot Comparison","Screenshot Comparison","Fail" });
		}
	}
	public static String getDesc()
	{
		Test test = method.getAnnotation(Test.class);
		return test.description();
	}
}
