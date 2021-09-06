package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

//Share Party Sep 6 whole page

public class NanoSiteUserFormPage extends Class_initEcomPrac {

	String genEmail= null;
	
	NanoSiteUserFormPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(xpath="//input[@id='pass'][@title]")
	WebElement txtboxPassword;
	public NanoSiteUserFormPage enterPassword(String ExistingUserPassword) {
		type(txtboxPassword, ExistingUserPassword);
		return this;
	}
	
	
	@FindBy(className="fieldset")
	WebElement userform;
	public NanoSiteUserFormPage userForm() {
		try {
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.visibilityOf(userform));
		}
		catch(Exception e) {
			driver.get().findElementById("headerActionYes").click();
		}
		
		return this;
	}
	
	@FindBy(id="contact_firstname")
	WebElement txtFirstName;
	public NanoSiteUserFormPage typeFirstName(String ContactsFN) {
		try {
			type(txtFirstName, ContactsFN);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	@FindBy(id="last_name")
	WebElement txtLastName;
	public NanoSiteUserFormPage typeLastName(String ContactsLN) {
		type(txtLastName, ContactsLN);
		return this;
	}
	
	@FindBy(id="contact_email")
	WebElement txtEmail;
	public NanoSiteUserFormPage typeEmail(String ContactsEmail) {
		long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
		Guest_Email= ContactsEmail+randomNum+"@mailinator.com";
		
		////System.out.prinln(genEmail);
		type(txtEmail, Guest_Email);
		return this;
	}
	
	@FindBy(id="contact_phone")
	WebElement txtPhone;

	public NanoSiteUserFormPage typePhone(String Country,String ContactsPhone) {
		type(txtPhone, markets.get(Country).getProperty("phoneNumber", ContactsPhone));

		return this;
	}
	
	@FindBy(xpath = "//label[@for='assign_to_me']/span")
	WebElement cboxTAC;

	public NanoSiteUserFormPage clickAgreementToAddToParty() {
	driver.get().executeScript("arguments[0].click();", cboxTAC);
		//click(cboxTAC);
		return this;
	}
	
	@CacheLookup
	@FindBy(id="send")
	WebElement btnSaveContact;
	public NanoSiteLandingPage clickSaveContact() {
		click(btnSaveContact);
		try {
		new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(btnSaveContact));
		new WebDriverWait(driver.get(),waitTimeout)
		.until(ExpectedConditions.refreshed
				(ExpectedConditions.urlContains("/party/public/")));
		} catch(TimeoutException e) {
			reportStep("Save contacts action took longer than usual.","info");
			
		}
		
				
		try {
			return new NanoSiteLandingPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@FindBy(id="email_address_confirmation")
	WebElement txtboxNanoCustomerConfEmail;
	public NanoSiteUserFormPage enterNanoCustomerConfEmail() {
		type(txtboxNanoCustomerConfEmail, New_Guest_Email);
		return this;
	}
	
	@FindBy(id="email_address_confirmation")
	WebElement txtboxVirtualNanoCustomerConfEmail;
	public NanoSiteUserFormPage enterVirtualNanoCustomerConfEmail() {
		type(txtboxNanoCustomerConfEmail, Virtual_New_Guest_Email);
		return this;
	}
	
	
	@FindBy(id="firstname")
	WebElement txtboxfirstname;
	public NanoSiteUserFormPage enterFirstname(String firstname) {
		type(txtboxfirstname, firstname);
		return this;
	}
	
	@FindBy(id="lastname")
	WebElement txtAccountLastName;
	public NanoSiteUserFormPage typeAccountLastName(String ContactsLN) {
		type(txtAccountLastName, ContactsLN);
		return this;
	}
	
	@FindBy(id="password")
	WebElement txtPassword;
	public NanoSiteUserFormPage enterNewPassword(String Password) {
		type(txtPassword, Password);
		return this;
	}
	
	@FindBy(id="password-confirmation")
	WebElement txtPasswordConfirmation;
	public NanoSiteUserFormPage enterPasswordConf(String PasswordConf) {
		type(txtPasswordConfirmation, PasswordConf);
		return this;
	}
	
}
