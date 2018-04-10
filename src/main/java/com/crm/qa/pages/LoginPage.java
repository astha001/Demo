package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(name="username")
	WebElement username;

	@FindBy(name="password")
	WebElement password;

	@FindBy(xpath="//input[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;

	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement signUpButton;

	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}

	public String validateLoginPageTitle(){

		return driver.getTitle();
	}

	public boolean validateCRMImage(){

		return crmLogo.isDisplayed();

	}

	public HomePage login(String user, String pwd){

		username.sendKeys(user);
		password.sendKeys(pwd);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", loginButton);
		return new HomePage();
	}

}
