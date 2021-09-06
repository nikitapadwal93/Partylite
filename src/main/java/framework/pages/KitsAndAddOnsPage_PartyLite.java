package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class KitsAndAddOnsPage_PartyLite extends Class_initEcomPrac {
	KitsAndAddOnsPage_PartyLite(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(css="button.kit-selected-button")
	WebElement web_oe_kit;
	public KitsAndAddOnsPage_PartyLite clickKit() {
		/*Actions actions = new Actions(driver.get());
		actions.moveToElement(web_oe_kit).click().build().perform();*/
		System.out.println("Before selecting kit"+driver.get().getCurrentUrl());
		try {
			driver.get().executeScript("arguments[0].click()", web_oe_kit);
		}
		catch(Exception e) {
			System.out.println("No Kit or Addons");
		}
		
		System.out.println("after kit selected"+driver.get().getCurrentUrl());
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(css="button.next")
	WebElement web_oe_next;
	public KitsAndAddOnsPage_PartyLite clickNext() {
		/*Actions actions = new Actions(driver.get());
		actions.moveToElement(web_oe_next).click().build().perform();*/
		System.out.println("Before Next"+driver.get().getCurrentUrl());
		driver.get().executeScript("arguments[0].click()", web_oe_next);
		System.out.println("After Next"+driver.get().getCurrentUrl());
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(id="pws-id")
	WebElement web_oe_cons_pws;
	public KitsAndAddOnsPage_PartyLite enterConsPWS() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    System.out.println(generatedString);
		type(web_oe_cons_pws, generatedString);
		return this;
	}
	
	@FindBy(xpath="//div[@class='show-pws avaliable-pws']")
	WebElement web_oe_pws_verify;
	public KitsAndAddOnsPage_PartyLite verifyPWSAvailable() {
		try {
			web_oe_pws_verify.isDisplayed();
		}
		catch(Exception e) {
			System.out.println("failed to view description");
		}
		return this;
	}
	
	@FindBy(css="button.next")
	WebElement web_oe_next_cart;
	public CartSummaryPage gotoCart() {
		/*Actions actions = new Actions(driver.get());
		actions.moveToElement(web_oe_next_cart).click().build().perform();*/
		System.out.println("Before Cart"+driver.get().getCurrentUrl());
		driver.get().executeScript("arguments[0].click()", web_oe_next_cart);
		System.out.println("After Next"+driver.get().getCurrentUrl());
		/*try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
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
}
