package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class LandingPage_Mailinator extends Class_initEcomPrac {
	
	
		public LandingPage_Mailinator(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}

	
	@FindBy(id="addOverlay")
	WebElement inbox_email;
	public LandingPage_Mailinator openGuestEmail() {
/*		//System.out.prinln(Guest_Email);
		int index=Guest_Email.indexOf("@");
		String guest_email_name=Guest_Email.substring(0, index);
		
		//hardcode value guest email name
		String url="https://www.mailinator.com/v3/index.jsp?zone=public&query=anand#/#inboxpane";
		//System.out.prinln(url);
		driver.navigate().to(url);*/
		type(inbox_email,Guest_Email);
		return this;
	}
	
	@FindBy(id="addOverlay")
	WebElement inbox_new_guest_email;
	public LandingPage_Mailinator openNewGuestEmail() {
		type(inbox_new_guest_email,New_Guest_Email);
		return this;
	}
	
	@FindBy(id="addOverlay")
	WebElement inbox_virtual_guest_email;
	public LandingPage_Mailinator openVirtualGuestEmail() {
		type(inbox_virtual_guest_email,Virtual_New_Guest_Email);
		return this;
	}
	
	@FindBy(id="inboxfield")
	WebElement inbox_contact_email;
	public LandingPage_Mailinator openContactEmail(String host_contact) {
/*		//System.out.prinln(Guest_Email);
		int index=Guest_Email.indexOf("@");
		String guest_email_name=Guest_Email.substring(0, index);
		
		//hardcode value guest email name
		String url="https://www.mailinator.com/v3/index.jsp?zone=public&query=anand#/#inboxpane";
		//System.out.prinln(url);
		driver.navigate().to(url);*/
		type(inbox_contact_email,host_contact);
		return this;
	}
	
	@FindBy(id="go-to-public")
	WebElement go_button;
	public MailinatorLandingPage clickGoButton() {
		click(go_button);
		try {
			return new MailinatorLandingPage(driver,Test);
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

