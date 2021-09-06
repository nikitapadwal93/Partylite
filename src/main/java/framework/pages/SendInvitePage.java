package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class SendInvitePage extends Class_initEcomPrac {
	
	Boolean isPageReady;

	SendInvitePage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(id="customer-search")
	WebElement email_search;
	public SendInvitePage typeEmail(String guest_email) {
		Guest_Email=guest_email;
		//System.out.prinln(Guest_Email);
		type(email_search,guest_email);
		return this;
	}
	
	@FindBy(css=".customer-list span")
	WebElement selectEmail;
	public SendInvitePage selectEmail() {
		try {
			WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
			wait.until(ExpectedConditions.visibilityOf(selectEmail));
			click(selectEmail);
			click(selectEmail);
			return this;
		}
		catch(RuntimeException e) {
			reportStep("The given host email is not present in the consultant contact", "warning");
			return null;
		}
		
	}
	
	@FindBy(css=".add-btn a")
	WebElement addGuest;
	public SendInvitePage clickAddButton() {
			
			 try {
				 click(addGuest);
				 WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
					Boolean isDOMReady = new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
							refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
					
					WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
					System.out.println("Waiting for loader to finish page rendering.");
					isPageReady = new WebDriverWait(driver.get(), 30).until(ExpectedConditions.refreshed
							(ExpectedConditions.attributeContains(loader, "style", "none")));
					
					//System.out.prinln("Page rendering is complete.");
				} catch (NoSuchElementException  | TimeoutException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			return this;
		
	}
	
	@FindBy(css="tr.tableData-row td")
	 WebElement guest_email_verify;
	 public SendInvitePage verifyGuestEmail(String guest_email){
		 try {
			 
			 verifyPartialText(guest_email_verify, guest_email); 
		 }
		 catch(Exception e)
		 {
			 try {
				 clickAddButton();
				 verifyPartialText(guest_email_verify, guest_email);
			 }
			 catch (Exception e1)
			 {
				 e1.printStackTrace();
			 }
			  
			 
		 }
		
		 return this;
	 }
	
	@FindBy(xpath="//label[@for='all-party']")
	 WebElement check_box;
	 public SendInvitePage clickCheckBox(){
		 click(check_box);
		 return this;
	 }
	 
	 @FindBy(xpath="//button[@class='send-btn']")
	 WebElement send_invitations;
	 public SendInvitePage clickSendInvitations(){
		 click(send_invitations);
		 try {
			 WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				Boolean isDOMReady = new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
						refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
			 
				/* new WebDriverWait(driver,waitTimeout).until(
						ExpectedConditions.refreshed(ExpectedConditions.attributeContains(
					
								By.xpath("///body[@aria-busy='false']"), "aria-busy", "false")));
				WebElement loader = driver.findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering.");
				isPageReady = new WebDriverWait(driver, 30).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));*/
				
				//System.out.prinln("Page rendering is complete.");
			} catch (NoSuchElementException  | TimeoutException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		 return this;
	 }

		@FindBy(xpath="(//input[@type='text'])[3]")
		WebElement guest_email;
		public SendInvitePage enterEmail(String guestEmail) {
			Guest_Email=guestEmail;
			//System.out.prinln(Guest_Email);
			type(guest_email,guestEmail);
			return this;
		}
		
		
		@FindBy(xpath="(//input[@type='text'])[3]")
		WebElement random_guest_email;
		public SendInvitePage enterRandomGuestEmail(String guestEmail) {
			
			long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
			guestEmail= guestEmail+randomNum+"@mailinator.com";
			New_Guest_Email=guestEmail;
			type(random_guest_email,guestEmail);
			return this;
		}
		
		@FindBy(xpath="(//input[@type='text'])[3]")
		WebElement virtual_random_guest_email;
		public SendInvitePage enterVirtualRandomGuestEmail(String guestEmail) {
			
			long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
			String virtual_guestEmail= guestEmail+randomNum+"@mailinator.com";
			Virtual_New_Guest_Email=virtual_guestEmail;
			type(virtual_random_guest_email,virtual_guestEmail);
			return this;
		}
		
		@FindBy(xpath="//input[@name='firstname']")
		WebElement first_name;
		public SendInvitePage enterFname(String guest_fname) {
			type(first_name,guest_fname);
			return this;
		}
		
		@FindBy(xpath="//input[@name='lastname']")
		WebElement last_name;
		public SendInvitePage enterLname(String guest_lname) {
			type(last_name,guest_lname);
			return this;
		}
		
		@FindBy(xpath="//input[@name='phone']")
		WebElement phone_num;
		public SendInvitePage enterPhone(String Country,String phone) {
			type(phone_num,markets.get(Country).getProperty("phoneNumber", phone));
			return this;
		}
		
		@FindBy(className="no-dtails")
		WebElement nodetails;
		public SendInvitePage verifyNoGuest() {
			verifyEnabled(nodetails);
			return this;
		}
 
	 
}




