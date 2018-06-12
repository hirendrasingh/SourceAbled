package com.sa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class CommonMethods extends BaseClass{
	
	public static WebDriverWait wait=null;
	
	public static WebElement seleniumelement;
	
	/*public static Properties JSelements=null;
	public static File JSloginProp=null;
	public static FileInputStream JSpath=null;*/
	
	/*@BeforeTest
	public static void LoadProperties(){
		
		JSloginProp = new File(System.getProperty("user.dir")+"\\ElementLocators\\JSLoginLocators.properties");
	try {
		JSpath = new FileInputStream(JSloginProp);
		JSelements = new Properties();
		JSelements.load(JSpath);
		System.out.println("JSLoginLocators File Loaded Successfully");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Unable to Load JSLoginLocators File --> "+e.getMessage());
	}
	}*/
	
	public static WebElement FluentWait(final WebElement driverelement) throws InterruptedException
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(60, TimeUnit.SECONDS) 			
				.pollingEvery(5, TimeUnit.SECONDS) 			
				.ignoring(InvalidElementStateException.class,ElementNotVisibleException.class);
		seleniumelement = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver ) {
				return driverelement;
			}
		});
		return seleniumelement;
	}
		
	// In this class(commonmethods) we had added all the methods to call in testcases....
	public static void ClickByXpath(String locator, String data) throws InterruptedException{
		//driver.findElement(By.xpath(element)).click();
		FluentWait(driver.findElement(By.xpath(locator)));
		seleniumelement.click();
	}
	// Change this to static if want to access directly without creating Object in Test method.....
	public static void ClickById(String locator, String data){
		driver.findElement(By.id(locator)).click();
	}
	
	public static void ClickByLinkText(String locator, String data){
		driver.findElement(By.linkText(locator)).click();
	}
	
	public static void ClickByCSS(String locator, String data) throws InterruptedException{
		//driver.findElement(By.cssSelector(element)).click();
		FluentWait(driver.findElement(By.cssSelector(locator)));
		seleniumelement.click();
	}
	
	public static void SendKeysByXpath(String locator, String data){
		driver.findElement(By.xpath(locator)).sendKeys(data);
			/*FluentWait(driver.findElement(By.xpath(element)));
			seleniumelement.clear();
			seleniumelement.sendKeys(data);*/
				
	}
	
	public static void SendKeysById(String locator, String data){
		driver.findElement(By.id(locator)).sendKeys(data);
		
			/*FluentWait(driver.findElement(By.xpath(element)));
			seleniumelement.clear();
			seleniumelement.sendKeys(data);*/
				
	}
	
	public static void ClearByXpath(String locator, String data) throws InterruptedException{
		//driver.findElement(By.xpath(element)).click();
		FluentWait(driver.findElement(By.xpath(locator)));
		seleniumelement.clear();
	}
	
	public static void ClearById(String locator, String data) throws InterruptedException{
		//driver.findElement(By.xpath(element)).click();
		FluentWait(driver.findElement(By.id(locator)));
		seleniumelement.clear();
	}
	
	public static void SingleScrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
	}
	
	public static void DoubleScrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 500);");
	}
	
	public static void TripleScrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 1500);");
	}
	
	// Wait for element to visible...
	public static void WaitForVisitibilty(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(element))));
	}
	
	// Wait for page to load.......
	public static void WaitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                 public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
              }
           };
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(pageLoadCondition);
	}
	
	/*public static void FindElement(String Object){
		driver.findElement(By.xpath(Object));
	}*/
	
	/*public static void sendKeys(WebElement element, String keyword) {
		WaitForVisitibilty(element);
		element.clear();
		element.sendKeys(keyword);
	}*/

}
