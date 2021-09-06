package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class Party_Dashboard extends Class_initEcomPrac  {
	

	Party_Dashboard(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@CacheLookup
	@FindBy(xpath="//div[@class='pslogin-share']//a[text()='✕']")
	WebElement dialogSocialNewAccountLink; 
	public Party_Dashboard closeSocialNewAcctDialog() {
		try {
			
			new WebDriverWait(driver.get(), waitTimeout-reducedTimeout).until(ExpectedConditions.visibilityOf(dialogSocialNewAccountLink));
			click(dialogSocialNewAccountLink);
			
		} catch(NoSuchElementException | TimeoutException e){
			reportStep("Display of connect new social sites dialog missing","info");
			return this;
		}
		return this;
	}
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement linkLogout;
	public LandingPage_PartyLite clickLogoutConsultant() {
		try {
			click(linkLogout);
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.titleContains("Partylite"));
			return new LandingPage_PartyLite(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Inserted try-catch for URL redirection to party to register NPEx in reports
	@CacheLookup
	@FindBy(xpath="//a[@class='action primary show-party-form']")
	WebElement btnCreateParty;
	public PartyCreationPage clickCreateParty(String country) {
		try {
			driver.get().navigate().to(markets.get(country).getProperty("propURL")+"/party/dashboard"); //to check for markets NPex - 5Sep2019
			click(btnCreateParty);
			try {
				return new PartyCreationPage(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}catch (java.lang.NullPointerException e) {
			reportStep("URL redirection to party has encountered an exception.","warning");
		}
		return null;
	}
	
	
	@FindBy(id="my-parties-table-ongoing")
	WebElement tableOngoingParties;
	public partyDetailsPage selectFirstOngoingPartyListed(String country) {
		
		driver.get().navigate().to(markets.get(country).getProperty("propURL")+"/party/dashboard");
		
		List<WebElement> partyRows = tableOngoingParties.findElements(By.tagName("tr"));
		//System.out.prinln("Ongoing parties - " + partyRows.size());
		List<WebElement> firstPartyData = partyRows.get(new Random().nextInt(partyRows.size())).findElements(By.tagName("td"));
			//System.out.prinln("the selected parties td count - " + firstPartyData.size());
			//System.out.prinln("randomParty selected - " 
		//+ firstPartyData.get(0).findElement(By.xpath(".//span")).getText());
		WebElement linkPartySelected = firstPartyData.get(3);
		Point locationOfLink = firstPartyData.get(3).getLocation();
		//System.out.prinln(locationOfLink);
			//System.out.prinln(linkPartySelected.findElement(By.xpath("./a")).getAttribute("href"));
			try {
			Rectangle rect = linkPartySelected.findElement(By.xpath("./a")).getRect();
			//System.out.prinln("The anchor tag size is " +rect.height +"-" +rect.width);
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement anchorParty = linkPartySelected.findElement(By.xpath("./a"));
			driver.get().executeScript("arguments[0].click();",anchorParty);
			
			//To get Party Number
			Party_Number=driver.get().getTitle();
			//System.out.prinln(Party_Number);
			String[] splitStr = Party_Number.split("\\s+");
			//System.out.prinln(splitStr[2]);
			try {
				return new partyDetailsPage(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 //Rectangle rect1 = linkPartySelected.findElement(By.xpath("./a")).getRect();
				//System.out.prinln("The anchor tag size after javascript modification is " +rect1.height +"-" +rect1.width);
				 //anchorParty.click();
				//linkPartySelected.click();
			} catch(ElementNotInteractableException e) {
				//WebElement partyLink = linkPartySelected.findElement(By.xpath("./a"));
			//Point location = linkPartySelected.findElement(By.xpath("./a")).getLocation();
				new Actions(driver.get()).moveToElement(linkPartySelected, locationOfLink.getX(), locationOfLink.getY()).click().perform();
				}
			try {
				return new partyDetailsPage(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	
	@FindBy(css="#my-parties-table-ongoing a")  //Not working in Chrome for some cases.
	List<WebElement> tableHostOngoingParties;
	public partyDetailsPage selectHostOngoingPartyListed() {
		
		String party_url=tableHostOngoingParties.get(new Random().nextInt(tableHostOngoingParties.size())).getAttribute("href");
		//System.out.prinln(party_url);

		driver.get().navigate().to(party_url);
		
		
		//To get Party Number
		Party_Number=driver.get().getTitle();
		//System.out.prinln(Party_Number);
		String[] splitStr = Party_Number.split("\\s+");
		//System.out.prinln(splitStr[2]);
		
		try {
			return new partyDetailsPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@FindBy(xpath="//a[@title='PartyLite']")
	WebElement partylite_logo;
	public LandingPage_PartyLite gotoHomepage() {
		click(partylite_logo);
		try {
			return new LandingPage_PartyLite(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@FindBy(xpath="//a[text()='My Business']")
	WebElement linkMyBusiness;
	public LandingPage_PartyLite clickMyBusinessLink() {
		
			try {
				new WebDriverWait(driver.get(),waitTimeout-reducedTimeout).until(ExpectedConditions.invisibilityOf(dialogSocialNewAccountLink));
			click(linkMyBusiness);
			} catch(TimeoutException e){
				new Actions(driver.get()).click(linkMyBusiness).perform();
			}
		try {
			return new LandingPage_PartyLite(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public partyDetailsPage openPartyDashboard(String party_id, String country) {
		driver.get().navigate().to(markets.get(country).getProperty("propURL")+"/party/"+party_id);
		try {
			return new partyDetailsPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}
	
	
	@FindBy(xpath="//a[contains(text(),'My Contacts')]")
	WebElement MyContacts;
	public MyParties_ContactsPage clickPartyContacts(String country) {
		System.out.println("Current country for URL retrieved from Prop file is " + markets.get(country).getProperty("propURL"));
		driver.get().navigate().to(markets.get(country).getProperty("propURL")+"/party/dashboard");

		
		MyContacts=driver.get().findElementByXPath("//a[contains(text(),'"+markets.get(country).getProperty("myContacts") + "')]");
		click(MyContacts);
		try {
			return new MyParties_ContactsPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public partyDetailsPage gotoPartyPage() {
		driver.get().navigate().to(Party_Url);
		try {
			driver.get().findElementById("btn-cookie-allow").click();
			System.out.println("Cookie is shown in create party page");
			reportStep("Cookie is shown in create party page", "info");
		}
		catch(Exception e) {
			System.out.println("Cookie button is not shown");
			//e.printStackTrace();
		}
		try {
			return new partyDetailsPage(driver, Test);
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
