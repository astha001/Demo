package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {

	ContactsPage contactsPage;
	HomePage homepage;
	LoginPage loginpage;
	TestUtil testUtil;
	public String sheetName = "Contacts";

	public ContactsPageTest()
	{
		super();
	}

	@BeforeMethod
	public void setup()
	{
		initialize();
		loginpage  = new LoginPage();
		testUtil = new TestUtil();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homepage.clickContacts();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyContactsPageLabelTest()
	{
		Assert.assertTrue(contactsPage.verifyContactsPage(),"Contacts label is missing");
	}

	@Test(priority=2)
	public void selectContactsTest()
	{
		contactsPage.selectContacts("test test");
	}
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		contactsPage.selectContacts("test test");
		contactsPage.selectContacts("ui uiii");
	}
	
	@DataProvider
	public Object[][] getCRMTestData()
	{
		Object data[][] = TestUtil.getTestDta(sheetName);
		return data;
	}
	
	@Test(priority = 4 , dataProvider = "getCRMTestData")
	public void validateCreateNewContactTest( String title , String fname, String lname , String comp)
	{
		homepage.createNewContactLink();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, fname, lname, comp);
	}
}