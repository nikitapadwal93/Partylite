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

public class PartyOrderCompletionPage extends Class_initEcomPrac {

	Boolean isPageReady;
	PartyOrderCompletionPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;

	}

	@FindBy(className="success-text")
	WebElement paymentSuccessful;
	public PartyOrderCompletionPage partyconfirmOrderIsSuccess() {
		try {
			verifyEnabled(paymentSuccessful);
			return this;
		}
		catch(NoSuchElementException e) {
			reportStep("Order Failed", "warning");
			return null;
		}

	}

	@FindBy(xpath="//button[@class='action-close']")
	WebElement closePopup;
	public PartyOrderCompletionPage closePopup() {

		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			click(closePopup);
		}
		catch(Exception e) {
			e.printStackTrace();
			reportStep("popup not closed ","info");
		}

		/*try
		{
			WebElement modalCurtain = driver.get().findElementByXPath("//aside");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalCurtain));
			System.out.println("modal curtain success");
		}
		catch (Exception e) {
			System.out.println("No modal curtain");
		}*/ //Modal curtain - catched exception
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("loader after modal curtain success");
		} catch (Exception e) {
			System.out.println("no loader after modal curtain success");
		}
		return this;
	}

	@FindBy(xpath="(//a[@class='level-top'])[5]")
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
}
