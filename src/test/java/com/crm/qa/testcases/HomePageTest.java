package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	HomePage homepage;
	LoginPage loginpage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest(){
		
		super();
	}

	@BeforeMethod
	public void setUp()
	{
		initialize();
		testUtil = new TestUtil();
		loginpage  = new LoginPage();
		contactsPage= new ContactsPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitleTest()
	{
		String actualTitle = homepage.getTitle();
		Assert.assertEquals(actualTitle, "CRMPRO", "HomePage title not matched");
	}
	
	@Test(priority = 3)
	public void clickContactsTest()
	{
		testUtil.switchToFrame();
		contactsPage = homepage.clickContacts();
	}
	
	@Test(priority = 2)
	public void verifyLoginUserTest()
	{
		testUtil.switchToFrame();
		boolean flag = homepage.verifyLoginUser();
		Assert.assertTrue(flag);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
