package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
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
		
		public customerAccountPage loaderCheck() {
			try {
				WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering.");
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				System.out.println("loader pass");
			} catch (Exception e ) {
				System.out.println("loader catch");
			}
			return this;
		}

		public customerAccountPage bodyContainer() {
			try {
				WebElement body = driver.get().findElementByXPath("//body");
				//System.out.prinln("Waiting for loader to finish page rendering.");
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(body, "aria-busy", "false")));
				System.out.println("body container pass");
			} catch (Exception e ) {
				System.out.println("body container catch");
			}
			return this;
		}

		@CacheLookup
		@FindBy(xpath="//label[@for='address_format_0']")
		WebElement selectAddress_Format;
		public customerAccountPage selectAddressFormat() {
			loaderCheck();
			bodyContainer();
			try {
				loaderCheck();
				click(selectAddress_Format);
				System.out.println("Address format clicked");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			loaderCheck();
			bodyContainer();
			return this;

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
		
		@FindBy(xpath="/html/body/div[6]/header/div[1]/div/ul/li[2]/div/ul/li[2]/a")
		WebElement clickDashboard;
		public customerAccountPage clickOnDashboard() {
			click(clickDashboard);
			return this;

		}
		
		@FindBy(xpath="//*[@id=\"block-collapsible-nav\"]/ul/li[8]/a")
		WebElement clickAddressBook;
		public customerAccountPage clickAddressBookLink() {
			click(clickAddressBook);
			return this;
		}
		
		@FindBy(xpath="//*[@id=\"block-collapsible-nav\"]/ul/li[10]/a")
		WebElement clickAddressBookCH;
		public customerAccountPage clickAddressBookLinkforCH() {
			click(clickAddressBookCH);
			return this;
		}

		
		@FindBy(xpath="//button[@class='action primary add']")
		WebElement clickAddressNewAddress;
		public customerAccountPage clickNewAddressLink() {
			click(clickAddressNewAddress);
			return this;
		}
		
		@CacheLookup
		@FindBy(xpath="//*[@id=\"search_mini_form\"]/div[1]/div/div[1]/div[3]/div[1]/div[1]/div[1]/ul/li/a/div")
		//@FindBy(xpath="//*[@id=\"mana_ajax_wrapper_search_result\"]/div[3]/div[2]/ol/li/div/a/span/span/img")
		//*[@id="mana_ajax_wrapper_search_result"]/div[3]/div[2]/ol/li/div/a/span/span/img
		WebElement linkProductImage;
		public ProductDescriptionPage clickProductImage() {

			try {
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(linkProductImage)));
				click(linkProductImage);
				try {
					return new ProductDescriptionPage(driver, Test);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (org.openqa.selenium.TimeoutException |org.openqa.selenium.NoSuchElementException e) {
				// TODO Auto-generated catch block
				reportStep("The entered SKU did not result in display of product image ", "info");
			} 
			return null;
		}
		
		
		@CacheLookup
		@FindBy(id="search")
		WebElement txtBoxSearch;
		public customerAccountPage enterSKU(String SKU) {  //due to updated code deployed
			try {
				type(txtBoxSearch,SKU);
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated
						(By.xpath("//*[@id=\\\"search_mini_form\\\"]/div[2]/button")));
				//new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".sku span"), SKU)));
				System.out.println("SKU enter success");
			} catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.ElementNotVisibleException | org.openqa.selenium.TimeoutException e) {
				try {
					WebElement searchProduct = driver.get().findElementById("search");
					searchProduct.clear();
				} catch(org.openqa.selenium.NoSuchCookieException e1) {
					reportStep("Product search was not successful.","warning");
				}
				type(txtBoxSearch,SKU);
				return this;
			}
			return this;
		}
		
		@FindBy(id="street_1")
		WebElement streetAddress1;
		public customerAccountPage enterStreetAddress1(String address1) {
			type(streetAddress1, address1);
			return this;
		}
		
		@FindBy(id="city")
		WebElement addresscity;
		public customerAccountPage entercity(String city) {
			type(addresscity, city);
			return this;
		}
		
		@FindBy(id="postcode")
		WebElement postalcode;
		public customerAccountPage enterPostalCode(String zipcode) {
			type(postalcode, zipcode);
			return this;
		} 	
		
		@FindBy(id="telephone")
		WebElement telephone;
		public customerAccountPage enterTelephone(String phone) {
			type(telephone, phone);
			return this;
		} 	
		
		@FindBy(id = "region_id")
		WebElement selectState;
		public customerAccountPage selectStateFromDropdown(String state) {
		{
			try {
				selectDropDownUsingText(selectState, state);
			} catch (org.openqa.selenium.NoSuchElementException e) { // multi-lingual support added
				reportStep("State selection option not available in the screen. Ignoring this step.", "info");
				return this;
			}
		}
			return this;
		}	
		
		
		@FindBy(xpath="//*[@id=\"form-validate\"]/fieldset[2]/div[8]/button[1]")
		WebElement SearchAndValidate;
		public customerAccountPage clickSearchAndValidate() {
			click(SearchAndValidate);
			return this;
		} 
		
		@FindBy(xpath="//*[@id=\"form-validate\"]/fieldset[2]/div[7]/button[1]")
		WebElement SearchAndValidatech;
		public customerAccountPage clickSearchAndValidateforCH() {
			click(SearchAndValidatech);
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

		@FindBy(xpath="//button[@class='action save primary']")
		WebElement saveAddress;
		public LandingPage_PartyLite clickSaveAddressLink() {
			try {
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(saveAddress)));
				click(saveAddress);
				System.out.println("save address button clicked");
				try {
					return new LandingPage_PartyLite(driver, Test);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (org.openqa.selenium.TimeoutException |org.openqa.selenium.NoSuchElementException e) {
				System.out.println("save address button not found");
			} 
			return null;
		}
		
		
		@CacheLookup
		@FindBy(id="search-action")
		WebElement btnSearch;
		public customerAccountPage clickBtnSearch() {
			click(btnSearch);
			return this;
		}

		

}