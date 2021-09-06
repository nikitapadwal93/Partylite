package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class partyGuestPage extends Class_initEcomPrac {

	partyGuestPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}

	
	
	@FindBy(xpath="(//p[@class='guest-name']/a)[2]")
	WebElement hostOfParty;
	public partyHostPage clickPartyHost() {
		//WebElement linkOfHost = null;
		try {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.elementToBeClickable(hostOfParty));
			hostOfParty.click();
		} catch (ElementNotInteractableException |TimeoutException e) 
			{
			e.getMessage();
			reportStep("The host of the party link displayed is not clickable","warning");
			throw new RuntimeException("he host of the party link displayed is not clickable");
			}
			/*List<WebElement> hostLinks = driver.findElements(By.xpath("//p[@class='guest-name']/a"));
			 //linkOfHost =hostLinks.get(new Random().nextInt(hostLinks.size()));
			linkOfHost= hostLinks.get(1);//to force selection to be always the second host name
			linkOfHost.click();
			}catch(ElementNotInteractableException enie) {	
			new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(linkOfHost));
			try {
				linkOfHost.click();
			} catch(org.openqa.selenium.ElementClickInterceptedException |TimeoutException e1)
			{
				reportStep("Host link found is not clickable", "warning");
				throw new RuntimeException("Host link displayed could not be clicked");
			}
			*/
		
		try {
			return new partyHostPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@FindBy(xpath = "((//button[@class='level-top'])[2]/span")
	WebElement btnAddGuest;

	public partyGuestPage clickAddGuestLink() {
		try {
			new WebDriverWait(driver.get(),waitTimeout).until(
					ExpectedConditions.attributeContains(By.xpath("//div[@class='loading-mask']"), "style", "none"));
			//new WebDriverWait(driver,waitTimeout).until(ExpectedConditions.elementToBeClickable(btnAddGuest));	
			
			WebElement refreshedAddGuests = (new WebDriverWait(driver.get(),30)).until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath("(//button[@class='level-top'])[2]/.."));
					
				}
				
				
			});
			refreshedAddGuests.click();
			return this;
		} catch (ElementNotInteractableException | TimeoutException e1) {
			//System.out.prinln(e1.getMessage());
			btnAddGuest.click();
			reportStep("AddGuest link could not be clicked, not interactable or wait got timedout","warning");
			return null;
		}
		
	}
	
	@FindBy(xpath="//span[@class='sent-invite']")
	 WebElement send_invite;
	 public SendInvitePage clickSendInvite(){
		 try {
			 new WebDriverWait(driver.get(),waitTimeout).until(
						ExpectedConditions.attributeContains(By.xpath("//div[@class='loading-mask']"), "style", "none"));
		 }
		 catch(Exception e)
		 {
			 
		 }
		 click(send_invite);
		 try {
				return new SendInvitePage(driver,Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return null;
	 }
	 
	//Body Container Aria-Busy attribute Wait Check
		public partyGuestPage waitBodyContainer() {
			try {
				WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),reducedTimeout).until(ExpectedConditions.
						refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
				reportStep("Body container reached false", "info");
			}
			catch(Exception e) {
				System.out.println("Body container wait failed");
			}
			return this;
		}

	@FindBy(id = "contact_firstname")
	WebElement tboxFN;
	public partyGuestPage typeGuestFN(String guestFN) {
		try {
		type(tboxFN, guestFN);
		return this;
		} catch(NoSuchElementException | TimeoutException e) {
			type(tboxFN, guestFN);
			return this;
		}
		
	}

	@FindBy(id = "last_name")
	WebElement tboxLN;

	public partyGuestPage typeGuestLN(String guestLN) {
		type(tboxLN, guestLN);
		return this;
	}

	@FindBy(id = "contact_email")
	WebElement tboxEmail;

	public partyGuestPage typeGuestEmail(String guestEmail) {
		long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
		guestEmail= guestEmail+randomNum+"@mailinator.com";
		type(tboxEmail, guestEmail);
		return this;
	}

	@FindBy(id = "contact_phone")
	WebElement tboxPhone;


	public partyGuestPage typeGuestPhone(String Country,String guestPhone) {
		type(tboxPhone, markets.get(Country).getProperty("phoneNumber", guestPhone));

		return this;
	}

	@FindBy(xpath = "//label[@for='assign_to_me']")
	WebElement cboxTAC;

	public partyGuestPage clickAgreementToAddToParty() {
		
		click(cboxTAC);
		return this;
	}

	@FindBy(xpath="//button[@name]")
	WebElement btnSubmitGuestDetails;

	public partyGuestPage clickAddGuestButton() {
		click(btnSubmitGuestDetails);
		/*try {
		new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.invisibilityOf(btnSubmitGuestDetails));
		System.out.println("Add guest wait success");
		} catch(Exception e) {
			System.out.println("Catch for add guest pop up wait");
			return this;
			
		}*/ //Check after adding guest 
		return this;
	}
	
	public partyGuestPage validateGuestNameAdded(String guestFN, String guestLN) {
		String fullName = guestFN.toLowerCase()+" "+guestLN.toLowerCase();
		try {
		locateElement("xpath", "//a[text()[contains(.,"+ fullName +")]]");
		return this;
		} catch(org.openqa.selenium.NoSuchElementException e) {
			reportStep("Added guest could not be validated on the screen.","info");
			return this;
		}
		
	}
	
	//@FindBy(xpath="(//a[@class='name-underline'])[3]")
	@FindBy(css=".guest-list .no_rightmargin a")
	WebElement partyguest;
	public partyHostPage clickPartyGuest() {
		//WebElement linkOfHost = null;
		try {
			//new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.elementToBeClickable(partyguest));
			click(partyguest);
			System.out.println("Party guest link selected");
		} catch (Exception e) 
			{
			e.getMessage();
			reportStep("The host of the party link displayed is not clickable","warning");
			throw new RuntimeException("he host of the party link displayed is not clickable");
			}
			/*List<WebElement> hostLinks = driver.findElements(By.xpath("//p[@class='guest-name']/a"));
			 //linkOfHost =hostLinks.get(new Random().nextInt(hostLinks.size()));
			linkOfHost= hostLinks.get(1);//to force selection to be always the second host name
			linkOfHost.click();
			}catch(ElementNotInteractableException enie) {	
			new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(linkOfHost));
			try {
				linkOfHost.click();
			} catch(org.openqa.selenium.ElementClickInterceptedException |TimeoutException e1)
			{
				reportStep("Host link found is not clickable", "warning");
				throw new RuntimeException("Host link displayed could not be clicked");
			}
			*/
		
		try {
			return new partyHostPage(driver, Test);
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
