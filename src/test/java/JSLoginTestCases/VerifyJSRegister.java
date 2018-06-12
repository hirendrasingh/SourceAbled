package JSLoginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sa.testbase.BaseClass;
import com.sa.testbase.CommonMethods;
import com.sa.utility.ExcelReader;

public class VerifyJSRegister extends BaseClass{
	
	/*// Create Object to get all cell data from any Excel using path....
			public static Object[][] getData(String ExcelName, String sheetName) {
				ExcelReader Data = new ExcelReader(System.getProperty("user.dir") + "\\ExcelFiles\\"+ExcelName);
				int rowNum = Data.getRowCount(sheetName);
				//System.out.println(rowNum);
				int colNum = Data.getColumnCount(sheetName);
				
				// Created 2d array of type Object to get cell data....
				Object testData[][] = new Object[rowNum - 1][colNum];
				for (int i = 2; i <=rowNum; i++) {
					for (int j = 0; j < colNum; j++) {
						testData[i - 2][j] = Data.getCellData(sheetName, j, i);
					}
				}
				return testData;
			}*/
	
	
	@DataProvider(name="JSRegistration")
	public Object[][] JSregisterData(){
		Object[][] registerData = ExcelReader.getData("Register.xlsx", "JSregister");
		//Object[][] registerData = getData("Register.xlsx", "JSregister");
		return registerData;
	}
	
	@Test(dataProvider="JSRegistration")
	public void RegisterJS(String FirstName, String MiddleName, String LastName, String Email, String Address, 
			String ZipCode, String LinkedIN, String Phone, String UploadResume, String JobInterest, String runMode ) throws InterruptedException{
		Reporter.log("This test is running throungh DataProvider....", true);
        if(runMode.equalsIgnoreCase("N")){
			throw new SkipException("Skipping JS Registartion the test");
		}
		else{
			Reporter.log("****Starting JS Registartion TestCase*****", true);
			CommonMethods.ClickById(JSlogin.getProperty("Job_Seekers"), "");
			CommonMethods.ClickByLinkText(JSlogin.getProperty("JS_register"), "");
			Thread.sleep(3000);
			/*WebElement firstname = driver.findElement(By.id(JSlogin.getProperty("JS_firstname")));
			firstname.sendKeys(FirstName);*/
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 600);");
			//CommonMethods.ClearByXpath(JSlogin.getProperty("JS_firstname"), "");;
			CommonMethods.SendKeysById(JSlogin.getProperty("JS_firstname"), FirstName);
			//Thread.sleep(1000);
			CommonMethods.SendKeysById(JSlogin.getProperty("JS_middlename"), MiddleName);
			//Thread.sleep(1000);
			CommonMethods.SendKeysById(JSlogin.getProperty("JS_lastname"), LastName);
			//Thread.sleep(1000);
			CommonMethods.ClearByXpath(JSlogin.getProperty("JS_registerEmail"), Email);
			//Thread.sleep(1000);
			CommonMethods.SendKeysByXpath(JSlogin.getProperty("JS_registerEmail"), Email);
			//Thread.sleep(2000);
			//CommonMethods.SendKeysById(JSlogin.getProperty("JS_Address"), Address);
			CommonMethods.ClearByXpath(JSlogin.getProperty("JS_Address"), Address);
			CommonMethods.SendKeysByXpath(JSlogin.getProperty("JS_Address"), Address);
			//Thread.sleep(2000);
			//CommonMethods.SendKeysByXpath(JSlogin.getProperty("JS_ZipCode"), ZipCode);
			CommonMethods.ClearById(JSlogin.getProperty("JS_ZipCode"), ZipCode);
			CommonMethods.SendKeysById(JSlogin.getProperty("JS_ZipCode"), ZipCode);
			//Thread.sleep(1000);
			CommonMethods.SendKeysByXpath(JSlogin.getProperty("JS_LinkedIN"), LinkedIN);
			//Thread.sleep(1000);
			CommonMethods.SendKeysById(JSlogin.getProperty("JS_Phone"), Phone);
			Thread.sleep(1000);
			CommonMethods.SendKeysByXpath(JSlogin.getProperty("JS_JobInterest"), JobInterest);
			//CommonMethods.ClickByXpath(JSlogin.getProperty("JS_reg_btn"), "");
			Thread.sleep(3000);
		}
	}
}
