package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class LandingPage_FakeUSAddress extends Class_initEcomPrac {
	
	
		public LandingPage_FakeUSAddress(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}

	
	@FindBy(xpath="//input[@class='no-style']")
	WebElement street;
	public LandingPage_FakeUSAddress getStreet() {
		verifyDisplayed(street);
		us_random_street=street.getAttribute("value");
		System.out.println(us_random_street);
		return this;
	}
	
	@FindBy(xpath="(//input[@class='no-style'])[2]")
	WebElement city;
	public LandingPage_FakeUSAddress getCity() {
		verifyDisplayed(city);
		us_random_city=city.getAttribute("value");
		System.out.println(us_random_city);
		return this;
	}
	
	@FindBy(xpath="(//input[@class='no-style'])[6]")
	WebElement contnum;
	public LandingPage_FakeUSAddress getPhoneNum() {
		verifyDisplayed(contnum);
		us_random_phonenum=contnum.getAttribute("value");
		System.out.println(us_random_phonenum);
		return this;
	}
	
	@FindBy(xpath="(//input[@class='no-style'])[4]")
	WebElement state;
	public LandingPage_FakeUSAddress getState() {
		verifyDisplayed(state);
		us_random_state=state.getAttribute("value");
		System.out.println(us_random_state);
		return this;
	}
	
	@FindBy(xpath="(//input[@class='no-style'])[5]")
	WebElement zipcode;
	public LandingPage_FakeUSAddress getZipcode() {
		verifyDisplayed(zipcode);
		us_random_zipcode=zipcode.getAttribute("value");
		System.out.println(us_random_zipcode);
		return this;
	}
	
	@FindBy(xpath="(//input[@class='no-style'])[7]")
	WebElement mobnum;
	public LandingPage_FakeUSAddress getMobNum() {
		verifyDisplayed(mobnum);
		us_random_mobnum=mobnum.getAttribute("value");
		System.out.println(us_random_mobnum);
		return this;
	}
	
	}

