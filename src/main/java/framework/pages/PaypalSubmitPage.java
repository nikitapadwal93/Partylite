package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
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

public class PaypalSubmitPage extends Class_initEcomPrac {
	
	PaypalSubmitPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(xpath="//input[@name='process']")
	WebElement buttonPaypalConfirm;
	public OrderCompletionPage clickPaypalSubmit() {
		try {
			click(buttonPaypalConfirm);
		}
		catch(Exception e) {
			System.out.println("Reload button shows instead of paypal submit");
			driver.get().navigate().refresh();
			click(buttonPaypalConfirm);
		}
		
		try {
			return new OrderCompletionPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@FindBy(xpath="//*[@id=\"contentMain\"]/form/input[5]")
	WebElement worldlineAccept;
	public PaypalSubmitPage clickWorldlineAccept() {
		driver.get().navigate().refresh();
		click(worldlineAccept);
		
		return null;
	
	}
	
	@FindBy(xpath="//*[@id='newsletter']")
	WebElement newsletterFooter;
	public PaypalSubmitPage enterEmailtoSubscribeFooter(String email) {
		type(newsletterFooter, email);
		return this;

	}
	
	@FindBy(xpath="//*[@id='newsletter-validate-detail']/div/div/div[1]/button")
	WebElement clickSubscribefromFooter;
	public PaypalSubmitPage clickFooterSubscribeButton() {
		click(clickSubscribefromFooter);
		return this;

	}
	
	@FindBy(xpath="(//button[@data-role='action']/span)[2]")
	WebElement btnContinueShopping;
	public PaypalSubmitPage clickContinueShopping() {
		click(btnContinueShopping);
		return this;
	}
	
	@FindBy(xpath="//div[@id='price']")  // multi-lingual support added
	WebElement OrderNumber;
	public PaypalSubmitPage getOrderNumber() {
		System.out.println(OrderNumber.getText());
		reportStep("Order Number" + OrderNumber.getText() +" retrieved successfully","pass");
		System.out.println("In WL "+driver.get().getCurrentUrl());
		reportStep("In WL"+driver.get().getCurrentUrl(),"info");
		return null;
	}
	
	@FindBy(id="submitCC")
	WebElement buttonConfirmCardDetails;
	public OrderCompletionPage clickConfirm() {
		click(buttonConfirmCardDetails);
		try {
			return new OrderCompletionPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(xpath="//div[@class='success-text']/h2")
	WebElement confirmOrderSuccess;
	public PaypalSubmitPage confirmOrderIsSuccess() {
		String successMessage=confirmOrderSuccess.getText(); 
		try {
			System.out.println("After submit card details  "+driver.get().getCurrentUrl());
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//section[@class='details']"))));
			if(successMessage.contains("Thanks for shopping!")
					|| driver.get().getCurrentUrl().contains("onepage/success")) {
				reportStep(driver.get().getCurrentUrl()+"The order is successfully placed with text - " + successMessage, "pass");
			return this;
		}
			else {
				reportStep("The order failed to complete"+driver.get().getCurrentUrl(), "pass");
				return this;
			}
		}
		catch(TimeoutException | NullPointerException | NoSuchElementException e) {
			
			reportStep(driver.get().getCurrentUrl()+"Order Failed", "pass");
			return this;
		}
		
	}
}
