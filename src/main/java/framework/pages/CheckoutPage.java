package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class CheckoutPage extends Class_initEcomPrac {
	Boolean isPageReady;

	CheckoutPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;

	}

	@FindBy(id="customer-email")
	WebElement txtBoxCustomerEmail;
	public CheckoutPage enterCustomerEmail(String email) {

		try {
			driver.get().findElementById("btn-cookie-allow").click();
			System.out.println("Cookie is shown in checkout page");
			reportStep("Cookie is shown in checkout page", "info");
		}
		catch(Exception e) {
			System.out.println("Cookie button is not shown");
			//e.printStackTrace();
		}
		bodyContainer();
		//WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
	//	wait.until(ExpectedConditions.visibilityOf(txtBoxCustomerEmail));
	//	long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
		//String guest_email = email+randomNum+"@mailinator.com";
		type(txtBoxCustomerEmail,email);
		return this;
	}

	@FindBy(name="firstname")
	WebElement txtBoxFirstName;
	public CheckoutPage enterCustomerFirstName(String firstname) {
		type(txtBoxFirstName,firstname);
		return this;
	}

	@FindBy(name="lastname")
	WebElement txtBoxLastName;
	public CheckoutPage enterCustomerLastName(String lastname) {
		type(txtBoxLastName,lastname);
		return this;
	}

	@FindBy(name="street[0]")
	WebElement txtBoxStreetAddress1;
	public CheckoutPage enterCustomerAddress1(String address1) {
		type(txtBoxStreetAddress1,address1);
			return this;
	}

	@FindBy(name="street[1]")
	WebElement txtBoxStreetAddress2;
	public CheckoutPage enterCustomerAddress2(String address2) {
		type(txtBoxStreetAddress2,address2);
		return this;
	}
	
	@FindBy(xpath="//button[@class='action action-show-popup']")
	WebElement clickAddNewAddress;
	public CheckoutPage clickAddNewAddress() {
		click(clickAddNewAddress);
		return this;
	}
	

	@FindBy(xpath="(//button[@class='action action-select-shipping-item'])[2]")
	WebElement clickAddressAgain;
	public CheckoutPage clickAddress() {
		click(clickAddressAgain);
		return this;
	}
	
	@FindBy(xpath="(//button[@class='action action-select-shipping-item'])[1]")
	WebElement clickSavedAddress;
	public CheckoutPage clickOnSavedAddress() {
		click(clickSavedAddress);
		return this;
	}
	
	@FindBy(xpath="//button[@class='action primary action-save-address']")
	WebElement clickSaveAddress;
	public CheckoutPage clickSaveNewAddress() {
		click(clickSaveAddress);
		return this;
	}

	@FindBy(name="street[2]") // multi-lingual support added
	WebElement txtBoxStreetAddress3;
	public CheckoutPage enterCustomerAddress3(String address3) {
		String current_url=driver.get().getCurrentUrl();
		System.out.println(current_url+" current url");
		if(current_url.contains("partylite.co.uk")
				|| current_url.contains("partylite.ca"))
		{
			try {
				type(txtBoxStreetAddress3,address3);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				reportStep("Textbox field for street address 3 not found.","info");
				return this;
			}	
		}
		return this;
	}

	@FindBy(xpath="//label[contains(@for,'dpdclassic')]")
	WebElement selectHomeDeliveryMethod;
	public CheckoutPage chooseHomeDeliveryMethod() {
		try {

			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			//System.out.prinln("Page rendering is complete to choose delivery type.");

		} catch (Exception e ) {

		}
		click(selectHomeDeliveryMethod);
		try {

			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			//System.out.prinln("Page rendering is complete to choose delivery type.");

		} catch (Exception e ) {

		}
		return this;

	}


	@FindBy(xpath="//label[@for='shipping-party']")
	WebElement shipToParty;
	public CheckoutPage clickShipToParty() {
		try {
			WebElement checkout_loader = driver.get().findElementById("checkout-loader");
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.invisibilityOf(checkout_loader)));
			System.out.println("Ship to party invisibility loader success");
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("Ship to party loader none success");
		} catch (Exception e) {

			// TODO Auto-generated catch block
			System.out.println("Ship to party catch");
		}
		bodyContainer();
		click(shipToParty);

		return this;
	}

	@FindBy(xpath="//label[@for='shipping-consultant']")
	WebElement shipToConsultant;
	public CheckoutPage clickShipToConsultant() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("Ship to Consultant loader success");
		} catch (Exception e) {
			System.out.println("Ship to Consultant catch");
			return this;
		}
		bodyContainer();
		click(shipToConsultant);
		return this;
	}


	@FindBy(xpath="//label[@for='shipping-host']")
	WebElement shipToHost;
	public CheckoutPage clickShipToHost() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("Ship to host loader success");
		} catch (Exception e) {
			System.out.println("Ship to host catch");
			return this;
		}
		bodyContainer();
		click(shipToHost);
		return this;
	}

	@FindBy(name="postcode")
	WebElement txtBoxZipCode;
	public CheckoutPage enterCustomerZipCode(String zipcode) {
		System.out.println("Retrieved postcode value is " + zipcode);
		type(txtBoxZipCode,zipcode);
		return this;
	}

	@FindBy(name="postcode")
	WebElement txtBoxZipCodeRemove;
	
	
	@FindBy(name="city")
	WebElement txtBoxCity;
	public CheckoutPage enterCustomerCity(String city) {
		type(txtBoxCity,city);
		return this;
	}

	@FindBy(name="telephone")
	WebElement txtBoxTelephone;
	public CheckoutPage enterCustomerTelephone(String Country, String telephone) {
		telephone=markets.get(Country).getProperty("phoneNumber");
		System.out.println("Retrieved phone number value is " + telephone);
		type(txtBoxTelephone,telephone);
		return this;
	}

	@FindBy(name="region_id")
	WebElement state_selection;
	public CheckoutPage selectState(String state) {
		//state=markets.get(Country).getProperty("state");
		System.out.println("Retrieved state value is " + state);
		String current_url=driver.get().getCurrentUrl();
		System.out.println(current_url+" current url");
		if(current_url.contains("partylite.com")
				|| current_url.contains("partylite.ca")) 
		{
			try {
				selectDropDownUsingText(state_selection, state);
			} catch(Exception e) {  //multi-lingual support added
				reportStep("State selection option not available in the screen. Ignoring this step.","info");
			}
		}
		return this;
	}


	@FindBy(css="div.select-shipping-methods")
	WebElement DeliveryType;
	public CheckoutPage clickDeliveryTypeBox() {
		/*try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("loader before Delivery type");
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			System.out.println("Delivery type catch");
			// TODO Auto-generated catch block
		}*/
		try {
			driver.get().findElementById("btn-cookie-allow").click();
			System.out.println("Cookie is shown in create party page");
			reportStep("Cookie is shown in create party page", "info");
		}
		catch(Exception e) {
			System.out.println("Cookie button is not shown");
			//e.printStackTrace();
		}


		click(DeliveryType);

		return this;


	}

	
	
	@FindBy(css="div.option-shipping-method")
	WebElement selectDeliveryType;
	public CheckoutPage chooseDeliveryType() {
		String current_url=driver.get().getCurrentUrl();
		System.out.println(current_url+" current url");
		if(current_url.contains("/party/checkout"))
		{
			try {

				WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering.");
				isPageReady = new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));

			} catch (Exception e) {
				System.out.println("shipping method catch");
				// TODO Auto-generated catch block

			}
		}
		System.out.println("shipping method selection");
		click(selectDeliveryType);
		return this;

	}

		
	public CheckoutPage loaderCheck() {
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

	public CheckoutPage bodyContainer() {
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

	public CheckoutPage bodyContainerTrue() {
		try {
			WebElement body = driver.get().findElementByXPath("//body");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(body, "aria-busy", "true")));
			System.out.println("body container true pass");
		} catch (Exception e ) {
			System.out.println("body container true catch");
		}
		return this;
	}


	@FindBy(xpath="//label[@for='s_method_dpdparcelshops_dpdparcelshops']")
	WebElement selectPickUpPointDeliveryMethod;
	public CheckoutPage choosePickUpPointDeliveryMethod() {

		click(selectPickUpPointDeliveryMethod);
		return this;		
	}
	
	@FindBy(xpath="//button[@class='showparcels secondary-btn']")
	WebElement selectParcelShop;
	public CheckoutPage chooseParcelShop() {

		click(selectPickUpPointDeliveryMethod);
		return this;		
	}

	@FindBy(xpath="//label[@for='s_method_expedited_expedited']")
	WebElement selectExpeditedMethod;
	public CheckoutPage chooseExpeditedMethod() {

		click(selectExpeditedMethod);
		String shipping_price=driver.get().findElementByXPath("//label[@for='s_method_expedited_expedited']/span[@class='price']").getText();
		System.out.println("Expedited Shipping Price"+shipping_price);
		return this;		
	}

	@FindBy(xpath="//label[@for='s_method_relaiscolis_relaiscolis']")
	WebElement selectRCDeliveryMethod;
	public CheckoutPage chooseRCDeliveryMethod() {
		
		click(selectRCDeliveryMethod);
		return this;

	}
	
	@FindBy(xpath="//*[@id=\"form_address\"]")
	WebElement txtBoxRCAddress;
	public CheckoutPage enterRCAddress(String addressRC) {
		loaderCheck();
		bodyContainer();
		type(txtBoxRCAddress,addressRC);
			return this;
	}
	
	@FindBy(name="form_CP")
	WebElement txtBoxRCZipCode;
	public CheckoutPage enterRCZipCode(String zipRC) {
		System.out.println("Retrieved postcode value is " + zipRC);
		type(txtBoxRCZipCode,zipRC);
		return this;
	}

	@FindBy(name="form_city")
	WebElement txtBoxRCCity;
	public CheckoutPage enterRCCity(String cityRC) {
		type(txtBoxRCCity,cityRC);
		return this;
	}

	@FindBy(css="#relaiscolisRecherche button")
	WebElement clickRCbutton;
	public CheckoutPage clickRCbutton() {
		click(clickRCbutton);
		loaderCheck();
		bodyContainer();
		return this;

	}

	@FindBy(xpath="(//span[@class='relais-link'])[1]")
	WebElement clickRClink;
	public CheckoutPage clickRClink() {
		try {
			loaderCheck();
			bodyContainer();
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			if(isPageReady) {

				click(clickRClink);

			}
			//System.out.prinln("Page rendering is complete to choose delivery type.");
			return this;

		} catch (NoSuchElementException | TimeoutException e ) {
			/*if(!(selectDeliveryType.getText().startsWith("Free")))
				click(selectDelsiveryType);*/
			return this;
			// TODO Auto-generated catch block

		}

	}

	@FindBy(xpath="//button[@data-bind='click: showparcelshop, visible: isParcelShopVisible()']")
	WebElement clickParcelShop;
	public CheckoutPage clickParcelShop() {
		try {

			click(clickParcelShop);
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			//System.out.prinln("Page rendering is complete to choose delivery type.");

		} catch (Exception e ) {

			System.out.println("Time out exception");
		}
		try {
			WebElement body = driver.get().findElementByXPath("//body");

			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(body, "aria-busy", "false")));
		}
		catch(Exception e) {
			System.out.println("Aria busy true");
		}
		try {
			WebElement isMapShown = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(driver.get().findElementById("map")));
			if(isMapShown.isDisplayed()) {
				return this;
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@FindBy(xpath="//button[@title='Zoom out']")
	WebElement clickZoomOut;
	public CheckoutPage clickZoomOut() {
		try {


			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			//System.out.prinln("Page rendering is complete to choose delivery type.");

			click(clickZoomOut);
			if(driver.get().getCurrentUrl().contains(".uk")) {
				click(clickZoomOut);
			}

			return this;

		} catch (NoSuchElementException | TimeoutException e ) {
			/*if(!(selectDeliveryType.getText().startsWith("Free")))
				click(selectDelsiveryType);*/
			return null;
			// TODO Auto-generated catch block

		}

	}

	//@CacheLookup
	//@FindBy(xpath="((//div[contains(@style, 'z-index: 4')])/img)[1]")
	//+ "//*[@id='map']/div/div/div[1]/div[3]/div/div[3]/div[6]/img")
	//+ "//*[@id='map']/div/div/div[1]/div[3]/div/div[3]/div[1]/img")
	WebElement storeImg;
	public CheckoutPage clickStoreImg() {
		//driver.get().findElementByXPath("//button[@title='Toggle fullscreen view']").click();
		List<WebElement> storeImgs = driver.get().findElementsByXPath("//div[@id='map']//div[@title]");
		//(//div[@id='map']//div[@title])
		//driver.get().findElementsByXPath("((//div[contains(@style, 'z-index: 4')])/img)[1]");
		System.out.println("The number of store images available - " + storeImgs.size());
		System.out.println("The title of the third store image is - " + storeImgs.get(2).getAttribute("title"));
		int count =0;
		for (WebElement w : storeImgs) {

			System.out.println("Store image index selected is - " + count);
			count++;
			if(w.isEnabled()) {
				try
				{
					driver.get().findElementByXPath("((//div[@id='map']//div[@title])[1]//img)[1]").click();
				} catch(org.openqa.selenium.ElementClickInterceptedException e) {
					driver.get().findElementByXPath("((//div[@id='map']//div[@title])[2]//img)[1]").click();
				}
				return this;
			}





			//		try {
			//			click(storeImg);
			//			//new Actions(driver).pause(8000).moveToElement(storeImg).click().build();
			//			//driver.executeScript("arguments[0].click()", storeImg);
			//			driver.get().findElementByXPath("//button[@title='Toggle fullscreen view']").click();
			//			
			//		}
			//		catch(Exception e) {
			//			e.printStackTrace();
			//		}



		}
		return null;
	}

	@FindBy(xpath="//*[@id='shipping-method-buttons-container']/div/button")
	WebElement nextButtonWoPUP;
	public CheckoutPage clickNextButtonWoPUP() {

		/*try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));

			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		click(nextButtonWoPUP);
		return this;

	}

	@FindBy(className="checkout-cart-validationmessages-message-error")
	WebElement error_msg;
	public CheckoutPage pupErrorMsg() {
		verifyDisplayed(error_msg);
		return this;

	}

	@FindBy(xpath="//div[@class='dpd_tooltip']//div[@class='parcelshoplink']")
	WebElement selectStoreImg;
	public CheckoutPage selectStoreImg() {

		try {
			new Actions(driver.get()).pause(5000).build();
			selectStoreImg.click();
			WebElement loader = driver.get().findElementByXPath("//body[@data-container='body']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "aria-busy", "false")));
			//System.out.prinln("Page rendering is complete to choose delivery type.");
			try {
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(driver.get().findElementById("parcelshopselected")));
			} catch(TimeoutException e123) {
				System.out.println("The select button click did not work as expected, re-initiating the selection.");
				selectStoreImg();
			}

			return this;

		} catch (NoSuchElementException | TimeoutException | org.openqa.selenium.ElementClickInterceptedException ee ) {
			try
			{
				driver.get().findElementByXPath("//div[@class='dpd_tooltip']//div[@class='parcelshoplink']/span").click();
				return this;
			} catch(org.openqa.selenium.ElementNotInteractableException e) {
				reportStep("Could not click the selected store to populate relay info","warning");
				return null;
			}
			// TODO Auto-generated catch block

		}


	}


	@CacheLookup
	@FindBy(xpath="//label[@for='address_format_0']")
	WebElement selectAddress_Format;
	public CheckoutPage selectAddressFormat() {
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

	public CheckoutPage waitAddressFormat() {
		try {
			new WebDriverWait(driver.get(), waitTimeout).until(
					ExpectedConditions.refreshed(
							ExpectedConditions.invisibilityOf(selectAddress_Format)));
			System.out.println("Address format not shown after invisibility check");
		}
		catch(Exception e) {
			System.out.println("Address format shown after invisibility check");
			selectAddressFormat();
		}
		return this;
	}

	@FindBy(xpath="//*[@id='shipping-method-buttons-container']/div/button")
	WebElement nextButtonGuest;
	public CheckoutPage clickNextButtonGuest() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page before next button guest click.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));

			//System.out.prinln("Page rendering is complete for clicking on the next button guest.");
		} catch (NoSuchElementException  | TimeoutException e) {
			// TODO Auto-generated catch block
			click(nextButtonGuest);
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering after clicking on next button.");
			new WebDriverWait(driver.get(), waitTimeout).until
			(ExpectedConditions.attributeContains(loader, "style", "none"));
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false"));
			try {
				WebElement addressManualValidate = driver.get().findElementByXPath("//span[contains(@data-bind, 'manual')]/parent::button");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.elementToBeClickable(addressManualValidate));
				addressManualValidate.click();
				loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering after clicking on next button.");
				new WebDriverWait(driver.get(), waitTimeout).until
				(ExpectedConditions.attributeContains(loader, "style", "none"));
				click(nextButtonGuest);
				loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering after clicking on next button.");
				new WebDriverWait(driver.get(), waitTimeout).until
				(ExpectedConditions.attributeContains(loader, "style", "none"));
				bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false"));
			} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e1) {

				return this;
			}
		}

		if(isPageReady) {
			click(nextButtonGuest);
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering after clicking on next button.");
			new WebDriverWait(driver.get(), waitTimeout).until
			(ExpectedConditions.attributeContains(loader, "style", "none"));
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false"));
			try {
				WebElement addressManualValidate = driver.get().findElementByXPath("//label[@for='address_format_0']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.elementToBeClickable(addressManualValidate));
				addressManualValidate.click();

				loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering after clicking on next button.");
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				click(nextButtonGuest);
				loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering after clicking on next button.");
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false"));
			} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e1) {

				return this;
			}
		}

		return this;

	}

	@FindBy(xpath="//button[@data-role='opc-continue']")
	WebElement next_button;
	public CheckoutPage clickGuestNextButton() {
		loaderCheck();
		try {
			click(next_button);
			System.out.println("Next button guest clicked");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loaderCheck();
		bodyContainer();
		return this;
	}

	@FindBy(xpath="//*[@id=\"shipping-new-address-form\"]/div[4]/div/button[2]")
	WebElement confirmAddress;
	
	public PaymentMethodsPage clickConfirmAddress(){ 
		loaderCheck();
		try {
			click(confirmAddress);
			System.out.println("Confirm address is clicked");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loaderCheck();
		bodyContainer();
		return null;	
		
	}
	
	public PaymentMethodsPage clickNextProceedToPayment() {
		loaderCheck();
		bodyContainer();
		try {
			click(next_button);
			System.out.println("Next button clicked to go to billing");
			loaderCheck();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loaderCheck();
		bodyContainer();
		try {
			return new PaymentMethodsPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@FindBy(css="#shipping-method-buttons-container button")
	WebElement next_button_oe;
	public TestPaymentPage clickNextProceedToPaymentOE() {
		loaderCheck();
		bodyContainer();
		try {
			click(next_button_oe);
			System.out.println("Next button clicked to go to billing");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loaderCheck();
		//bodyContainer();
		try {
			return new TestPaymentPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	@FindBy(xpath="//*[@id='shipping-method-buttons-container']/div/button")
	WebElement nextButton;
	public PaymentMethodsPage clickNextButton(String Country) {
		WebElement loader = null;
		try {
			loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering before clicking on next button.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));

			//System.out.prinln("Page rendering is complete to click on next button.");
		} catch (NoSuchElementException  | TimeoutException e) {
			// TODO Auto-generated catch block
			click(nextButton);  
			loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false"));
			if(markets.get(Country).getProperty("propURL").contains("fi")) {
				loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				WebElement bodyContainer2 = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer2, "aria-busy", "false"));

				//selectAddressFormat();
				try {
					new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//label[contains(@data-bind,'address_format')])[1]")));
				} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e1) {
					reportStep("Address suggestion for Finland is missing on the screen.", "info");
					try {
						return new PaymentMethodsPage(driver, Test);
					} catch (FileNotFoundException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					} catch (IOException e22) {
						// TODO Auto-generated catch block
						e22.printStackTrace();
					}
				}
				WebElement suggestedAddress = driver.get().findElementByXPath("//span[contains(@data-bind,'searchFormatLabel')]/ancestor::div[1]//label[@for='address_format_0']");
				driver.get().executeScript("arguments[0].click()", suggestedAddress);
				loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				bodyContainer2 = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer2, "aria-busy", "false"));
				nextButton.click();
			}

			try {
				return new PaymentMethodsPage(driver, Test);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}



		if(isPageReady) {
			try {	
				nextButton.click(); 
				if(markets.get(Country).getProperty("propURL").contains("fi")) {
					loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
					new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
							(ExpectedConditions.attributeContains(loader, "style", "none")));
					WebElement bodyContainer2 = driver.get().findElementByXPath("//body[@data-container='body']");
					new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer2, "aria-busy", "false"));

					//selectAddressFormat();
					try {
						new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//label[contains(@data-bind,'address_format')])[1]")));
					} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
						reportStep("Address suggestion for Finland is missing on the screen.", "info");
						try {
							return new PaymentMethodsPage(driver, Test);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					WebElement suggestedAddress = driver.get().findElementByXPath("//span[contains(@data-bind,'searchFormatLabel')]/ancestor::div[1]//label[@for='address_format_0']");
					driver.get().executeScript("arguments[0].click()", suggestedAddress);
					loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
					new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
							(ExpectedConditions.attributeContains(loader, "style", "none")));
					bodyContainer2 = driver.get().findElementByXPath("//body[@data-container='body']");
					new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer2, "aria-busy", "false"));
					nextButton.click();

				}//multi-lingual support
			} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException e) {
				try {
					return new PaymentMethodsPage(driver, Test);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
			loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering after clicking on next button.");
			new WebDriverWait(driver.get(), waitTimeout).until
			(ExpectedConditions.attributeContains(loader, "style", "none"));
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false"));
			//System.out.prinln("Page is now will navigate to payments method");

			try {
				return new PaymentMethodsPage(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		return null;
	}	


	@FindBy(xpath="//label[@for='agree-consultant']")
	WebElement web_oe_agree;
	public CheckoutPage clickAgree() {
		/*
		 * try { WebElement loader =
		 * driver.get().findElementByXPath("//div[@class='loading-mask']");
		 * //System.out.prinln("Waiting for loader to finish page rendering."); new
		 * WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
		 * (ExpectedConditions.attributeContains(loader, "style", "none")));
		 * 
		 * //System.out.
		 * prinln("Page rendering is complete to click on delivery type selection dropdown."
		 * ); } catch (Exception e) { //e.printStackTrace();
		 * System.out.println("No loader"); }
		 */
		try {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.elementToBeClickable(web_oe_agree));
			/*Actions actions = new Actions(driver.get());
			actions.moveToElement(web_oe_agree).click().build().perform();*/
			System.out.println("Before Agree"+driver.get().getCurrentUrl());
			driver.get().executeScript("arguments[0].click()", web_oe_agree);
			System.out.println("After Agree"+driver.get().getCurrentUrl());
			//web_oe_agree.click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return this;		
	}

	@FindBy(xpath="//button[@data-role='opc-continue']")
	WebElement web_oe_agree_next;
	public CheckoutPage clickAgreeNext() {
		loaderCheck();
		bodyContainer();
		loaderCheck();
		try {
			web_oe_agree_next.click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loaderCheck();
		/*try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));

			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {

			e.printStackTrace();
		}*/
		return this;		
	}

	@FindBy(xpath="//label[contains(@for,'homedelivery')]")
	WebElement selectUSHomeMethod;
	public CheckoutPage chooseHomeDeliveryUSMethod() {

		click(selectUSHomeMethod);
		String shipping_price=driver.get().findElementByXPath("//label[contains(@for,'homedelivery')]/span[@class='price']").getText();
		System.out.println("Expedited Shipping Price"+shipping_price);
		return this;		
	}


	public CheckoutPage removeExtraDots(String zipcode) {
		txtBoxZipCode.sendKeys(Keys.BACK_SPACE);
		txtBoxZipCode.sendKeys(Keys.BACK_SPACE);
		return this;
		
	}
	
	@FindBy(xpath = "//*[@id='opc-sidebar']/div[1]/table/tbody/tr[2]/td/span")
	WebElement ShippingAmt;

	public CheckoutPage verifyshipping() {
		verifyEnabled(ShippingAmt);
		System.out.println("Shipping & Handling Fee= " + ShippingAmt.getText());
		reportStep("S&H fee= " + ShippingAmt.getText(), "pass");
		return this;
	}


}