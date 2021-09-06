package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
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

public class NanoSiteLandingPage extends Class_initEcomPrac {
	Boolean isPageReady;
	NanoSiteLandingPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@CacheLookup
	//@FindBy(xpath="//div[@class='newsletter-subscribe-modal']//h2[@class='title']") ** to revert 
	@FindBy(xpath="//div[@class='newsletter-subscribe-modal']")
			//"//(//button[@class='action-close'])[2]")
	WebElement SignUpToNewsLetterText;
	public NanoSiteLandingPage closeDialogSignUpNewsLetter() {
		new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
		WebElement closeSignUpDialog = driver.get().findElementByXPath("(//button[@class='action-close'])[2]");
	click(closeSignUpDialog);
	WebElement modalCurtain = driver.get().findElementByXPath("//aside");
	new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalCurtain));	
	WebElement btnAcceptCookie = driver.get().findElementByXPath("//button[@id='btn-cookie-allow']");
	click(btnAcceptCookie);
	return this;
	}	
	
	@FindBy(id="btn-cookie-allow")
	WebElement btnContinue;
	public NanoSiteLandingPage clickToAllowCookie() {
		WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
		wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
		click(btnContinue);
		return this;
	}
	
	@FindBy(xpath="(//input[@type='email'])[1]")
	WebElement enterguestEmail;
	public NanoSiteLandingPage enterGuestEmail() {
		type(enterguestEmail,Guest_Email);
		return this;
	}
	
	@FindBy(xpath="(//input[@type='email'])[1]")
	WebElement enterNewguestEmail;
	public NanoSiteLandingPage enterNewGuestEmail() {
		type(enterNewguestEmail,New_Guest_Email);
		return this;
	}
	
	@FindBy(xpath="(//input[@type='email'])[1]")
	WebElement enterVirtualEmail;
	public NanoSiteLandingPage enterVirtualGuestEmail() {
		type(enterVirtualEmail,Virtual_New_Guest_Email);
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
	
	@FindBy(id="attendBtn")
	WebElement btnAttendParty;
	public NanoSiteLandingPage clickAttendParty() {
		click(btnAttendParty);
		return this;
	}
	
	//Share Party Sep 6
	@FindBy(id="headerActionYes")
	WebElement btnYesParty;
	public NanoSiteEmailEnteringPage clickYesParty() {
		click(btnYesParty);
		try {
			return new NanoSiteEmailEnteringPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(id="createOrderLink")
	WebElement btnCreateOrder;
	public CartSummaryPage clickBtnCreateOrder() {
		
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (NoSuchElementException  | TimeoutException e) {
			
			// TODO Auto-generated catch block
		}
		
		click(btnCreateOrder);
		try {
			return new CartSummaryPage(driver,Test);
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
