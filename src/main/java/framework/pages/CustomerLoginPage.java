package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class CustomerLoginPage extends Class_initEcomPrac  {

		CustomerLoginPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}
		
	@FindBy(xpath="//input[@id='email']")
	WebElement txtboxEmailID;
	public CustomerLoginPage enterEmailName(String ExistingUserEmail) {
		type(txtboxEmailID, ExistingUserEmail);
		return this;
	}
	
	@FindBy(xpath="//input[@id='email']")
	WebElement txtboxNanoGuestEmailID;
	public CustomerLoginPage enterNanoGuestEmailName() {
		type(txtboxEmailID, Guest_Email);
		return this;
	}
	
	@CacheLookup
	@FindBy(xpath="//div[@class='newsletter-subscribe-modal']")
	//"//(//button[@class='action-close'])[2]")
WebElement SignUpToNewsLetterText;
public CustomerLoginPage closeDialogSignUpNewsLetter() {
try {
	
	driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
try {
WebElement closeSignUpDialog = driver.get().findElementByXPath("(//button[@class='action-close'])[1]"); 
closeSignUpDialog.click();
reportStep("Newsletter modal dialog closed.","info");
return this;
} catch(org.openqa.selenium.ElementNotInteractableException e1)
{
	try {
		WebElement closeSignUpDialog = driver.get().findElementByXPath("(//button[@class='action-close'])[2]");
		closeSignUpDialog.click();
		reportStep("Newsletter modal dialog closed.","info");
		return this;
		
	}catch(org.openqa.selenium.ElementNotInteractableException e2)
	{
		try {
			WebElement closeSignUpDialog = driver.get().findElementByXPath("(//button[@class='action-close'])[3]");
			closeSignUpDialog.click();
			reportStep("Newsletter modal dialog closed.","info");
			return this;
	} catch(org.openqa.selenium.NoSuchElementException e3) {
		reportStep("Newsletter could not be recognized and closed", "warning");
	}
		
	}
}


return null;		
		
		

} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
	reportStep("Newsletter subscription modal 1st timeout occurred or missing","info");
	try {
	new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
	WebElement closeSignUpDialog = driver.get().findElementByXPath("(//button[@class='action-close'])[2]");
	click(closeSignUpDialog);
	reportStep("Newsletter modal dialog has been closed.","info");
	return this;
	} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e2) {
		reportStep("Newsletter subscription modal timedout again.","info");
		return this;
	}
}


}
	

	@FindBy(xpath="//input[@id='pass'][@title]")
	WebElement txtboxPassword;
	public CustomerLoginPage enterPassword(String ExistingUserPassword) {
		type(txtboxPassword, ExistingUserPassword);
		return this;
	}
	
	
	@FindBy(xpath="//a[@class='create']")

	WebElement linkTxtCreateAnAccount;
	public createNewCustomerAcct clickCreateAnAccount() {
		click(linkTxtCreateAnAccount);
		try {
			return new createNewCustomerAcct(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(xpath="//button[@class='action login primary']")
	WebElement btnSignInNanositeGuest;
	public  CartSummaryPage btnClickSigninNanositeGuest() {
		try {
		btnSignInNanositeGuest.click();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("NanositeGuest signin click event failed");
			reportStep("Nanosite guest signin failed", "warning");
		}
		try {
			return new CartSummaryPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(xpath="//button[@class='action login primary']")
	WebElement btnSignIn;
	public  LandingPage_PartyLite btnClickSignin() {
		/*try {
			WebElement SignUpToNewsLetterText = driver.get().findElementByXPath("//div[@class='newsletter-subscribe-modal']");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
			WebElement closeSignUpDialog = driver.get().findElementByXPath("//button[@data-role='action']");
			driver.get().executeScript("arguments[0].click()", closeSignUpDialog);
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.invisibilityOf(SignUpToNewsLetterText));
			System.out.println("newsletter closed in sign in page");
			reportStep("newsletter closed in sign in page", "info");
		}
		catch(Exception e) {
			System.out.println("No newsletter in sign in page");
			reportStep("No newsletter in sign in page", "info");
		}
		try
		{
	WebElement modalCurtain = driver.get().findElementByXPath("//aside");
	new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalCurtain));
	reportStep("model curtain displayed", "info");
		}catch (Exception e3) {
			reportStep("no model curtain displayed", "info");
		}*/
		click(btnSignIn);
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

	
	@FindBy(xpath="//button[@class='action login primary']")
	WebElement btnConSignIn;
	public  Party_Dashboard ConClickSignin() {
		/*try {
			WebElement SignUpToNewsLetterText = driver.get().findElementByXPath("//div[@class='newsletter-subscribe-modal']");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
			WebElement closeSignUpDialog = driver.get().findElementByXPath("//button[@data-role='action']");
			driver.get().executeScript("arguments[0].click()", closeSignUpDialog);
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.invisibilityOf(SignUpToNewsLetterText));
			System.out.println("newsletter closed in sign in page");
			reportStep("newsletter closed in sign in page", "info");
		}
		catch(Exception e) {
			System.out.println("No newsletter in sign in page");
			reportStep("No newsletter in sign in page", "info");
		}
		try
		{
	WebElement modalCurtain = driver.get().findElementByXPath("//aside");
	new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalCurtain));
	reportStep("model curtain displayed", "info");
		}catch (Exception e3) {
			reportStep("no model curtain displayed", "info");
		}*/
		click(btnConSignIn);
		try {
			return new Party_Dashboard(driver, Test);
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
	
