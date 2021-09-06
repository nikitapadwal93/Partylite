package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class FindYourSponsorPage_PartyLite extends Class_initEcomPrac{
	FindYourSponsorPage_PartyLite(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(id="consultan_firstname")
	WebElement web_oe_cons_fname;
	public FindYourSponsorPage_PartyLite enterConsFname(String consfname) {
		type(web_oe_cons_fname, consfname);
		return this;
	}
	
	@FindBy(id="consultan_lastname")
	WebElement web_oe_cons_lname;
	public FindYourSponsorPage_PartyLite enterConsLname(String conslname) {
		type(web_oe_cons_lname, conslname);
		return this;
	}
	
	@FindBy(css="form#search-consultan .search")
	WebElement web_oe_cons_search;
	public FindYourSponsorPage_PartyLite clickConsSearch() {
		click(web_oe_cons_search);
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
	
	@FindBy(css="button.choose")
	WebElement web_oe_cons_choose;
	public FindYourSponsorPage_PartyLite clickMySponsor() {
		//click(web_oe_cons_choose);
		driver.get().executeScript("arguments[0].click()", web_oe_cons_choose);
		/*Actions actions = new Actions(driver.get());
		actions.moveToElement(web_oe_cons_choose).click().build().perform();*/
		System.out.println("Cons sponsor selection process going"+driver.get().getCurrentUrl());
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
	
	@FindBy(xpath="//label[@for='no_consultant']")
	WebElement web_oe_cons_nochoose;
	public FindYourSponsorPage_PartyLite clickIDontHaveCons() {
		/*Actions actions = new Actions(driver.get());
		actions.moveToElement(web_oe_cons_nochoose).click().build().perform();*/
		System.out.println("I don't have cons option before select"+driver.get().getCurrentUrl());
		driver.get().executeScript("arguments[0].click()", web_oe_cons_nochoose);
		System.out.println("I don't have cons option selected"+driver.get().getCurrentUrl());
		return this;
	}
	
	@FindBy(xpath="//div[@class='consultant-description']")
	WebElement web_oe_sponsor;
	public FindYourSponsorPage_PartyLite verifySponsor() {
		try {
			web_oe_sponsor.isDisplayed();
		}
		catch(Exception e) {
			System.out.println("failed to view description");
		}
		return this;
	}
	
	@FindBy(css="button.choose")
	WebElement web_oe_spon_conf;
	public KitsAndAddOnsPage_PartyLite clickMySponsorConfirm() {
		/*Actions actions = new Actions(driver.get());
		actions.moveToElement(web_oe_spon_conf).click().build().perform();*/
		driver.get().executeScript("arguments[0].click()", web_oe_spon_conf);
		System.out.println("confirmed sponsor selected"+driver.get().getCurrentUrl());
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			return new KitsAndAddOnsPage_PartyLite(driver, Test);
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
