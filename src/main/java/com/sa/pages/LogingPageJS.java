package com.sa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.sa.testbase.BaseClass;

public class LogingPageJS extends BaseClass{
	
	@FindBy(id="EmailAddress") 
	static WebElement username;
	
	//This is the standard approch used mostly...
	@FindBy(how=How.XPATH, using=".//*[@id='Password']")
	WebElement password;
	
	@FindBy(how=How.CSS, using="#btnSubmit")
	WebElement submit;
	
	@FindBy(how=How.LINK_TEXT,using="Join")
	WebElement JSregister;
	
	@FindBy(how=How.LINK_TEXT,using="Forgot Password?")
	WebElement JSForgotPassword;
	
	public static WebElement getusername(){
		return username;
	}

}
