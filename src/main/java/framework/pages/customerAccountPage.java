package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class customerAccountPage extends Class_initEcomPrac  {

		public customerAccountPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}

		//@FindBy(xpath ="//div[text()[contains(., 'Thank you for registering with Party')]]")
		WebElement confirmRegistrationText;
		
		
		public customerAccountPage confirmRegistrationText(String Country) {
			try {
				new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed(
						ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success"))));
				confirmRegistrationText = driver.get().findElementByXPath("//div[text()[contains(., '"+
						
				markets.get(Country).getProperty("successMsgRegister") + "')]]");
			
				verifyDisplayed(confirmRegistrationText);
			}
			catch(Exception e) {
				System.out.println("Acc reg msg catch null value in success msg from property file"+driver.get().getCurrentUrl());
				reportStep("Acc reg msg catch null value in success msg from property file"+driver.get().getCurrentUrl(), "info");
				verifyEnabled(driver.get().findElementByCssSelector("div.success"));
			}
			return this;
		}
		
		@FindBy(xpath="(//button[@data-action='customer-menu-toggle'])[1]")
		WebElement linkMenuExpansion;
		public customerAccountPage clickMenuExpansionLink() {
			linkMenuExpansion.click();
			return this;
		}
		
		@FindAll({@FindBy(css="div[class='customer-menu'][aria-hidden='false'] a")})
		List<WebElement> my_account_dropmenu_links;
		public customerAccountPage menuLinksDropdown(String Country) {
			List<String> text,links;
			text=new ArrayList<String>();
			links=new ArrayList<String>();
			
			if(my_account_dropmenu_links.size()!=7)
				reportStep("my account menu dropdown links size verification fail", "fail");
			
			for (WebElement webElement : my_account_dropmenu_links) {
				System.out.println(webElement.getText()+": "+webElement.getAttribute("href"));
				text.add(webElement.getText());
				links.add(webElement.getAttribute("href"));
			}
			System.out.println(links);
			/*
			 * String[] url_matches=new String[]
			 * {"/wishlist","/customer/account/","/customer/account/edit",
			 * "/customer/account/logout","/consultant/consultant","/pslogin/account/view/",
			 * "/sales/order/history/"}; for (String string : url_matches) {
			 * 
			 * if(links.contains(markets.get(Country).getProperty("propURL")+string)) {
			 * System.out.println(string+" Link matched");
			 * reportStep(string+" Link matched", "pass"); } else {
			 * System.out.println(string+" Link match fail");
			 * reportStep(string+" Link match fail", "fail"); } }
			 */
			String[] text_matches=new String[] {markets.get(Country).getProperty("wishlist"),markets.get(Country).getProperty("Dashboard"),markets.get(Country).getProperty("account_info"),markets.get(Country).getProperty("orders"),markets.get(Country).getProperty("my_consultant"),markets.get(Country).getProperty("product_reviews"),markets.get(Country).getProperty("sign_out")};
			for (String string : text_matches) {
				if(text.contains(string))
				{
					System.out.println(string+" text matched");
					reportStep(string+" text matched", "pass");
				}
				else
				{
					System.out.println(string+" text match fail");
					reportStep(string+" text match fail", "fail");
				}
			}
			WebElement customer_name=driver.get().findElementByCssSelector("div[class='customer-menu'][aria-hidden='false'] .customer-info-name");
			verifyDisplayed(customer_name);
			WebElement customer_email=driver.get().findElementByCssSelector("div[class='customer-menu'][aria-hidden='false'] .customer-info-email");
			verifyDisplayed(customer_email);
			WebElement my_account_button=driver.get().findElementByCssSelector("div[class='customer-menu'][aria-hidden='false'] .customer-my-account-button");
			verifyDisplayed(my_account_button);
			return this;
		}
		
		@FindBy(xpath="//a[contains(@href, 'forgotpassword')]")
		WebElement errMsgDoubleRegistration;
		public customerAccountPage invalidateDoubleRegistration() {
			verifyDisplayed(errMsgDoubleRegistration);
			//errMsgDoubleRegistration.isDisplayed();
			return this;
		}
		
		public CustomerNewAddressPage gotoCustomerAddress(String Country) {
			System.out.println("Value passed for country from test method is " + Country);
			System.out.println(markets.get(Country).toString());
			System.out.println(markets.get(Country).size());
			System.out.println("Property value read for propURL is " + markets.get(Country).getProperty("propURL"));
			driver.get().navigate().to(markets.get(Country).getProperty("propURL")+"/customer/address/new");
			
			try {
				return new CustomerNewAddressPage(driver,Test);
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