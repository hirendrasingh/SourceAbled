package JSLoginTestCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sa.testbase.BaseClass;
import com.sa.testbase.CommonMethods;

//public class VerifyValidJSLogin extends CommonMethods
public class VerifyValidJSLogin extends BaseClass 
{
	CommonMethods CM = new CommonMethods();
	
	@Test
	/*public void ValidLoginJS(){
		CommonMethods.ClickById("jsDD");
		CommonMethods.SendKeysById(JSelements.getProperty("JS_username"), "hirendrarajawat@rangam.com");
		System.out.println(jslogin.getProperty("Submit_button"));
		CommonMethods.ClickByCSS(jslogin.getProperty("Submit_button"));*/
	
	public void ValidLoginJS() throws InterruptedException{
		
		CM.ClickById("jsDD", "");
		//CommonMethods.ClickById("jsDD", "");
		CommonMethods.SendKeysById(JSlogin.getProperty("JS_username"), "hirendrarajawat@rangam.com");
		System.out.println("CellContent: "+ cellint);
		CommonMethods.SendKeysByXpath(JSlogin.getProperty("JS_password"), cellint);
		Reporter.log(JSlogin.getProperty("Submit_button"), true);
		CommonMethods.ClickByCSS(JSlogin.getProperty("Submit_button"), "");
		
	}
			
	/*@Test
	public void ValidLoginJS(){
		ClickById("jsDD");
		ClickById("EmailAddress");
		//ClickByXpath(".//*[@id='Password']");
		//ClickByXpath(JSelements.getProperty("JS_password"));
		SendKeysByXpath(JSelements.getProperty("JS_password"), "123456");
		ClickByCSS("#btnSubmit");
	}*/
	
	/*//String element=".//*[@id='jsDD']/a";
	@Test
	public void login(){
		//ClickByXpath(".//*[@id='jsDD']/a");
		//driver.findElement(By.id("jsDD")).click();
		click(driver.findElement(By.id("jsDD")));
		driver.findElement(By.id("EmailAddress")).sendKeys("hirendrarajawat@rangam.com");
		driver.findElement(By.cssSelector("#btnSubmit")).click();		
	}*/

}
