package JSLoginTestCases;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sa.pages.HomePage;
import com.sa.testbase.BaseClass;
import com.sa.testbase.CommonMethods;
import com.sa.utility.ExcelReader;

public class VerifyInvalidJSLogin extends BaseClass{
	
	CommonMethods CM = new CommonMethods();
	
	/*// Created Method to give excel path using ExcelReader constructer......	
	public Object[][] getData(String ExcelName, String sheetName) {
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
	
	/*@DataProvider
	public Object[][] loginData(){
		Object[][] data = getData("Logins.xlsx", "InvalidJsLogins");
		return data;*/
	
	@DataProvider(name="logins")
	public Object[][] loginData(){
		Object[][] data = ExcelReader.getData("Logins.xlsx", "InvalidJsLogins");
		return data;
	
	}
	
	@Test(dataProvider = "logins")
	public void InvalidLoginsJS(String TestCaseName, String Email, String Password,String runMode) throws InterruptedException{
		Reporter.log("This test is running throungh DataProvider....", true);
        //log.info("I am from data provider gest");
		if(runMode.equalsIgnoreCase("N")){
			throw new SkipException("Skipping the test");
		}
		else{
			Reporter.log("****Starting TestCase*****", true);
			CommonMethods.ClickById(JSlogin.getProperty("Job_Seekers"), "");
			sendKeys(HomePage.jsUser(), Email);
			
			//sendKeys(HomePage.jsUser, Email);
			
			//CM.SendKeysById(JSlogin.getProperty("JS_username"), Email);
			CM.SendKeysByXpath(JSlogin.getProperty("JS_password"), Password);
			CommonMethods.ClickByCSS(JSlogin.getProperty("Submit_button"), "");
			Thread.sleep(5000);
			CM.ClickByXpath(JSlogin.getProperty("JS_Logout"), "");
			Thread.sleep(3000);
			
		}
				
	}
	
}
