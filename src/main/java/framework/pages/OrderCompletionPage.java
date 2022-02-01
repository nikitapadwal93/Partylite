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

public class OrderCompletionPage extends Class_initEcomPrac  {

	OrderCompletionPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(xpath="//div[@class='success-text']/h2")
	WebElement confirmOrderSuccess;
	public OrderCompletionPage confirmOrderIsSuccess() {
		//String successMessage=confirmOrderSuccess.getText(); 
		try {
			System.out.println("After submit card details  "+driver.get().getCurrentUrl());
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//section[@class='details']"))));
		/*	if(successMessage.contains("Thanks for shopping!")
					|| driver.get().getCurrentUrl().contains("onepage/success")) {
				reportStep(driver.get().getCurrentUrl()+"The order is successfully placed with text - " + successMessage, "pass");
			return this; */
			if(driver.get().getCurrentUrl().contains("onepage/success")){
				reportStep(driver.get().getCurrentUrl()+"The order is successfully placed", "pass");
				return this;
		}
			else {
				reportStep("The order failed to complete"+driver.get().getCurrentUrl(), "warning");
				return this;
			}
		}
		catch(TimeoutException | NullPointerException | NoSuchElementException e) {
			
			reportStep(driver.get().getCurrentUrl()+"Order Failed", "warning");
			return this;
		}
		
	}
	
	@FindBy(xpath="//*[@id=\"authform\"]/input[5]")
	WebElement buttonAuthenticate;
	public OrderCompletionPage clickAuthenticate(){
		click(buttonAuthenticate);
		return this;
	}
	
	@FindBy(className="success-text")
	WebElement paymentSuccessful;
	public OrderCompletionPage partyconfirmOrderIsSuccess() {
		try {
			verifyEnabled(paymentSuccessful);
			return this;
		}
		catch(NoSuchElementException e) {
			reportStep("Order Failed", "warning");
			return null;
		}
		
	}
	
	@FindBy(css="div.user-details p.email-details")
	WebElement guest_email;
	public OrderCompletionPage getOrderEmail() {
		order_guest_email=guest_email.getText();
		System.out.println(order_guest_email+"received order conf email");
		return this;
	}
	
	@FindBy(css="div.user-details strong")
	WebElement order_num;
	public OrderCompletionPage getOrderNumber() {
		order_number=order_num.getText();
		System.out.println(order_number+"extracted from success page");
		return this;
	}
	
	@FindBy(id="addOverlay")
	WebElement inbox_email;
	public LandingPage_Mailinator openOrderGuestEmail() {
		driver.get().navigate().to("https://www.mailinator.com");
		type(inbox_email,order_guest_email);
		try {
			return new LandingPage_Mailinator(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(className="kit-success_wrap_border")
	WebElement kit_banner;
	public OrderCompletionPage verifyKitSuccessPage() {
		/*
		 * try { WebElement body=driver.get().findElementByXPath("//body"); new
		 * WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
		 * (ExpectedConditions.attributeContains(body, "aria-busy", "false"))); }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
		verifyDisplayed(kit_banner);
		if(driver.get().getCurrentUrl().contains("success"))
			reportStep("Success page for OE "+ driver.get().getCurrentUrl(), "pass");
		else
			reportStep("failure page for OE "+ driver.get().getCurrentUrl(), "fail");
		return this;
	}
	
	@FindBy(xpath="//*[@id=\"newsletter\"]")
	WebElement newsletterFooter;
	public OrderCompletionPage enterEmailtoSubscribeFooter(String email) {
		type(newsletterFooter, email);
		System.out.println("Email entered in footer is "+ email);
		return this;
	}
	
	@FindBy(xpath="//*[@id=\"newsletter-validate-detail\"]/div/div/div[1]/button")
	WebElement clickSubscribefromFooter;
	public OrderCompletionPage clickFooterSubscribeButton() {
		click(clickSubscribefromFooter);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(xpath="//aside[contains(@class,'_show')]//footer[contains(@class,'modal-footer')]//button[contains(@type,'button')]")
	WebElement clickContinue;
	public OrderCompletionPage clickContinueShopping() {
		try {
		click(clickContinue);
		Thread.sleep(3000);
		System.out.println("Subscription successfull");
		System.out.println(driver.get().findElement(By.xpath("//span[@class='base']")).getText());
		}
		catch(Exception e) {
			System.out.println("Continue button not found");
		}
		return null;
	}
	
}