
package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class MyAccount_Dashboard extends Class_initEcomPrac  {

	MyAccount_Dashboard(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
		
	@FindBy(xpath="//span[@class='customer-name'][@tabindex='0']")
	WebElement linkMyAccount;
	public MyAccount_Dashboard verifyUserLogin(String ContentToVerify) {
		verifyExactText(linkMyAccount, ContentToVerify);
		return this;
	}
	
	@FindBy(css=".account-header-info h4")
	WebElement my_email;
	public MyAccount_Dashboard verifyCustomerEmail(String email) {
		if(my_email.getText().equalsIgnoreCase(email))
			reportStep("Email verified: email from browser  "+my_email.getText()+"\n email from excel  "+email, "PASS");
		else
			reportStep("Email verification failed: email from browser"+my_email.getText()+"\n email from excel"+email, "fail");
		return this;
	}
	
	@FindBy(className="blue-text")
	WebElement my_email_blue;
	public MyAccount_Dashboard verifyCustomerEmailBlue(String email) {
		if(my_email_blue.getText().equalsIgnoreCase(email))
			reportStep("Email verified: email from browser  "+my_email_blue.getText()+"\n email from excel  "+email, "PASS");
		else
			reportStep("Email verification failed: email from browser"+my_email_blue.getText()+"\n email from excel"+email, "fail");
		return this;
	}
	

}
