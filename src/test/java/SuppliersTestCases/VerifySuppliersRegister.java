package SuppliersTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sa.testbase.BaseClass;
import com.sa.testbase.CommonMethods;
import com.sa.utility.ExcelReader;

public class VerifySuppliersRegister extends BaseClass{
	
	@DataProvider(name="SuppliersRegister")
	public Object[][] SuppliersRegData(){
		Object[][] supplierRegData = ExcelReader.getData("Register.xlsx", "SuppliersRegister");
				return supplierRegData;
	}
	
	@Test(dataProvider="SuppliersRegister")
	public static void RegisterSupplier(String FirstName,String MiddleName, String LastName, String BusinessEmail,
			String OrganizationName, String Website, String	AboutOrg, String Address, String ZipCode, String Phone, 
			String AuthorizedOfficer, String DailyContact, String LinkedIN, String PromoCode, String Runmode) throws InterruptedException{
		Reporter.log("This test is running throungh DataProvider....", true);
		if(Runmode.equalsIgnoreCase("n")){
			throw new SkipException("Skipping Suppliers Registartion test-> "+ FirstName );
		}
		else {
			Reporter.log("****Starting Suppliers Registartion TestCase*****", true);
			
				CommonMethods.ClickById(Homepage.getProperty("Suppliers_link"), "");
				CommonMethods.ClickByLinkText(Suppliers.getProperty("register_link"), "");
				CommonMethods.DoubleScrollDown();
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("firstname_txtbx"), FirstName);
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("lastname_txtbx"), LastName);
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("businessEmail_textbx"), BusinessEmail);
				CommonMethods.SingleScrollDown();
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("organization_txtbx"), OrganizationName);
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("website_txtbx"), Website);
				CommonMethods.SingleScrollDown();
				CommonMethods.ClickByXpath(Suppliers.getProperty("career_chkbx"), "");
				CommonMethods.ClickByXpath(Suppliers.getProperty("transportation_chkbx"), "");
				CommonMethods.ClickByXpath(Suppliers.getProperty("university_chkbx"), "");
				CommonMethods.SingleScrollDown();
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("address_txtbx"), Address);
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("zipcode_txtbx"), ZipCode);
				CommonMethods.SingleScrollDown();
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("phone_txtbx"), Phone);
				Thread.sleep(2000);
				CommonMethods.ClickByXpath(Suppliers.getProperty("emailsubscribe_chkbx"), "");
				//CommonMethods.ClickById(Suppliers.getProperty("emailsubscribe_chkbx"), "");
				CommonMethods.SingleScrollDown();
				Thread.sleep(2000);
				CommonMethods.ClearByXpath(Suppliers.getProperty("promocode_txtbx"), "");
				CommonMethods.SendKeysByXpath(Suppliers.getProperty("promocode_txtbx"), PromoCode);
				CommonMethods.ClickByXpath(Suppliers.getProperty("apply_btn"), "");
				CommonMethods.SingleScrollDown();
				//Thread.sleep(2000);
				//CommonMethods.WaitForVisitibilty(driver.findElement(By.xpath(Suppliers.getProperty("zipcode_val"))));
				/*String Val=driver.findElement(By.xpath("//span[text()='Please enter a valid U.S. ZIP Code']")).getText();
				System.out.println("ZipVal:"+Val);
				Thread.sleep(3000);*/
				CommonMethods.TripleScrollDown();
				/*Thread.sleep(2000);
				driver.switchTo().frame(0);
				Thread.sleep(2000);
				CommonMethods.ClickByXpath(".//*[@id='recaptcha-anchor']/div[5]", "");*/
				Thread.sleep(3000);
				CommonMethods.ClickByXpath(Suppliers.getProperty("register_btn"), "");
				/*CommonMethods.WaitForVisitibilty(driver.findElement(By.xpath(Suppliers.getProperty("captch_chkbx"))));
				//CommonMethods.ClickByCSS(Suppliers.getProperty("captch_chkbx"), "");
				CommonMethods.ClickByXpath(Suppliers.getProperty("captch_chkbx"), "");
				//CommonMethods.ClickById(Suppliers.getProperty("captch_chkbx"), "");
*/						
		}
	}

}
