package TestCases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.CommonFunctions;
import PageObjects.Home_Page_Objects;

public class excelWrite extends CommonFunctions
{
	static Method method;
	
	@Test(description = "This Test Case will write the Junior Engineer names From Team Page in excel")
	public static void write() throws IOException
	{
		try 
		{
		PageFactory.initElements(driver, Home_Page_Objects.class);
		
		test.log(LogStatus.INFO, "Writing Data into Excel File");
		
		Home_Page_Objects.teamPage.click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
				
		FileInputStream inputStream = new FileInputStream("GrootanTechnologies/ExcelFile/TestData.xls");
		Workbook wb=new HSSFWorkbook(inputStream);
		Sheet sheet=wb.createSheet("Junior Engineer");     

		int k=0;
		String beforePath = "//body/div[@id='root']/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[";
		String afterPath = "]";
		//String afterCol ="//div";
		String afterColCount = "]//div";
		int rowCountsize = Home_Page_Objects.rowCount.size();	

		for(int i=1;i<rowCountsize;i++) 
		{
			String completeRowPath = beforePath+i+afterPath;
			WebElement rowXpath=driver.findElement(By.xpath(completeRowPath));
			String ColCount = beforePath+i+afterColCount;
			List<WebElement> colCountXpath =driver.findElements(By.xpath(ColCount));
			
			if(colCountXpath.size()==4)
			{
				for(int j=1;j<=colCountXpath.size();j++)
				{			
					String beforePosition = "//body/div[@id='root']/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[";
					String afterPosition = "]//h5";
					String beforePosition1="]//div[";
					String completePosition = beforePosition+i+beforePosition1+j+afterPosition;				
					WebElement colXpath = driver.findElement(By.xpath(completePosition));

					StringBuffer sb = new StringBuffer(colXpath.toString());
					sb.deleteCharAt(sb.length()-1);				
					if(colXpath.getText().equalsIgnoreCase("Junior Engineer")) 
					{
						String beforeName = "/html/body/div[1]/div/section[2]/div/div/div/div/div/div[";
						String midName="]/div[";
						String afterName= "]/h5/preceding-sibling::h3";

						String completeName=beforeName+i+midName+j+afterName;
						WebElement completeNameXpath = driver.findElement(By.xpath(completeName));					

						Row row = sheet.createRow(k);
						Cell cell =row.createCell(0);
						cell.setCellValue(completeNameXpath.getText());
						k++;
					}			  
				}
			}
			FileOutputStream outputStream = new FileOutputStream("GrootanTechnologies/ExcelFile/TestData.xls");
			wb.write(outputStream);
			outputStream.close();        
		}		
		System.out.println("File Written Successfully!!!...");
		Assert.assertTrue(true);
		TestNGResults.put("3", new Object[] { 2d, "Excel Write","Junior Engineers name in Excel","Pass" });		
	}			
	catch(Exception e)
	{
		TestNGResults.put("3", new Object[] { 2d, "Excel Write","Junior Engineers name craetion in Excel","Fail" });
	}
  }	
}
