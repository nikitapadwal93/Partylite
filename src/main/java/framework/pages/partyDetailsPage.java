package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class partyDetailsPage extends Class_initEcomPrac  {

	partyDetailsPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	public partyDetailsPage verifyPartyDashboardPageTitle() {
		try {
			WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
			wait.until(ExpectedConditions.titleContains("Party #"));
			return this;
		}
		catch(TimeoutException e) {
			reportStep("Failed to navigate to party details page", "warning");
			return null;
		}
		
	}
	
	@FindBy(className="party-name")
	WebElement partyName;
	public partyDetailsPage verifyPartyName(String verifyName) {
			verifyExactText(partyName, verifyName);
		//System.out.prinln(partyName.getText());
		return this;
	}
	
	//Share Party Sep 6
	@FindBy(xpath="//div[@id='share-party']/a/span")
	WebElement sharePartyBtn;
	public partyDetailsPage shareParty() {
			click(sharePartyBtn);
		return this;
	}
	
	//Share Party Sep 6
	@FindBy(id="shareParty")
	WebElement sharePartyText;
	public NanoSiteLandingPage sharePartyGetText() {
		String sharetext=sharePartyText.getAttribute("value");
		try {
			System.out.println(sharetext);
			driver.get().navigate().to(sharetext);
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
	
	public partyDetailsPage copyPartyUrl() {
		Party_Url=driver.get().getCurrentUrl();
		System.out.println(Party_Url);
		reportStep("Party ID" + driver.get().getCurrentUrl() +" retrieved successfully","pass");
	return this;
}

	@FindBy(xpath="(//a[@class='level-top'])[2]")
	//xpath="(//ul[@role='menu']//a)[2]")
	WebElement linkGuests;
	public partyGuestPage clickGuestsLink() {
		click(linkGuests);
		try {
			return new partyGuestPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@FindBy(xpath="(//a[@class='level-top'])[4]")
	WebElement linkHostBenefits;
	public partyHostBenefitsPage clickHostBenefits() {
		click(linkHostBenefits);
		try {
			return new partyHostBenefitsPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@FindBy(linkText="Complete Order")
	WebElement linkCompleteOrder;
	public partyCompleteOrderPage clickCompleteOrder() {
		click(linkCompleteOrder);
		try {
			return new partyCompleteOrderPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(linkText="Send Invitation")
	WebElement linkSendInvitation;
	public SendInvitePage clickSendInvitation() {
		click(linkSendInvitation);
		try {
			return new SendInvitePage(driver, Test);
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
