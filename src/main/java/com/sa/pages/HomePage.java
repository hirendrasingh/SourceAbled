package com.sa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sa.testbase.BaseClass;

public class HomePage extends BaseClass{
	
	
		/*public static void ClickByXpath(String element){
		driver.findElement(By.xpath(element)).click();*/
		
		/*public static void click(WebElement element){
			element.click();
		}*/
		//return ClickByXpath();
		
		/*public static WebElement waitElement(String element) {
			String s = element;
			// only work with ids....
			By t1 = By.id(s);
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(t1));
			if (ele.isDisplayed())
				return ele;
			else
				log.info("webElement not found");
			return ele;
		}*/
		
		/*public static WebElement jsUser()
		{
			return waitElement(JSlogin.getProperty("JS_username"));
		}*/
		
		//public static WebElement jsUser = driver.findElement(By.id(JSlogin.getProperty("JS_username")));

		public static void waitForVisitibilty(WebElement element) {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 60);
			// wait.until(ExpectedConditions.visibilityOf(element));
			if (!element.isDisplayed()) {
				wait.until(ExpectedConditions.visibilityOf(element));
				wait.pollingEvery(5, TimeUnit.SECONDS);
				wait.ignoring(NoSuchElementException.class);
			} else {
				log.info("Element not present");
			}
		}
		
		public static WebElement jsUser()
		{
			WebElement element = driver.findElement(By.id(JSlogin.getProperty("JS_username")));
			return element;
		}
		

}
