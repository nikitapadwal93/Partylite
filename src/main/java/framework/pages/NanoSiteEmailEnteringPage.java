package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class NanoSiteEmailEnteringPage extends Class_initEcomPrac{

	NanoSiteEmailEnteringPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	@FindBy(xpath="(//input[@type='email'])[1]")
	WebElement entershare_yes_newguestEmail;
	public NanoSiteEmailEnteringPage enterNewGuestEmail(String guestemail) {
		long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
		Shareparty_guest_email_yes= guestemail+randomNum+"@mailinator.com";
		type(entershare_yes_newguestEmail,Shareparty_guest_email_yes);
		return this;
	}
	
	@FindBy(id="headerAction")
	WebElement clickSubmit;
	public NanoSiteUserFormPage clickSubmit() {
		click(clickSubmit);
		try {
			return new NanoSiteUserFormPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
