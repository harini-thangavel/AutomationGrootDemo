package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class Home_Page_Objects 
{
	@FindBy(xpath="//a[contains(text(),'Home')]")
	public static WebElement homePage;
	
	@FindBy(xpath="//a[contains(text(),'Services')]")
	public static WebElement servicePage;
	
	@FindBy(xpath="//a[contains(text(),'Open Source')]")
	public static WebElement openSourcePage;
	
	@FindBy(xpath="//a[contains(text(),'Team')]")
	public static WebElement teamPage;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/section[2]/div/div/div/div/div/div[1]/div[1]/img")
	public static WebElement ctoXpath;

	@FindBy(xpath="//*[@id=\"root\"]/div/section[2]/div/div/div/div/div/div[1]/div[2]/img")
	public static WebElement hrXpath;
	
	@FindBy(xpath="//a[contains(text(),'Careers')]")
	public static WebElement careersPage;

	@FindBy(xpath="//a[contains(text(),'Contact Us')]")
	public static WebElement contactPage;

	@FindBy(xpath="//a[contains(text(),'Blog')]")
	public static WebElement blogPage;
	
	@FindBys(
	@FindBy(xpath="//body/div[@id='root']/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div"))
	public static List<WebElement> rowCount;
	
	/*
	 * @FindBys(
	 * 
	 * @FindBy(xpath=
	 * "//body/div[@id='root']/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]//div[1]//div"
	 * )) public static List<WebElement> rowColCount;
	 */
	

}
