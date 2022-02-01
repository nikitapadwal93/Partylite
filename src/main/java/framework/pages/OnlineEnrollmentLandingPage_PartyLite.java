package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

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
//import framework.utils.NameOfTestListener;


//@Listeners(framework.utils.NameOfTestListener.class)

public class OnlineEnrollmentLandingPage_PartyLite extends Class_initEcomPrac {

	int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int a=1;
	public OnlineEnrollmentLandingPage_PartyLite(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;

	}

	@CacheLookup
	//@FindBy(xpath="//div[@class='newsletter-subscribe-modal']//h2[@class='title']") ** to revert 
	@FindBy(xpath="//div[@class='newsletter-subscribe-modal']")
	//"//(//button[@class='action-close'])[2]")
	WebElement SignUpToNewsLetterText;
	public OnlineEnrollmentLandingPage_PartyLite closeDialogSignUpNewsLetter() {

try {
			
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
			WebElement closeSignUpDialog = driver.get().findElementByXPath("//button[@data-role='action']");
			driver.get().executeScript("arguments[0].click()", closeSignUpDialog);
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.invisibilityOf(SignUpToNewsLetterText));
			System.out.println("Newsletter closed in "+a+"attempt");
			reportStep("Newsletter closed in "+a+"attempt","info");
			
		} catch(Exception e) {
			System.out.println("Newsletter subscription missing" +a+"time");
			reportStep("Newsletter subscription missing" +a+"time","info");
			driver.get().navigate().refresh();
			a++;
			try {
				System.out.println("Body container modal check");
				WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
						refreshed(ExpectedConditions.attributeContains(bodyContainer, "class", "_has-modal")));
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.stalenessOf(SignUpToNewsLetterText));
			}
			catch(Exception e1) {
				System.out.println("Body container modal check failed");
			}
			closeDialogSignUpNewsLetter();
			/*if(!(driver.get().getCurrentUrl().contains("pexdev")))
			closeDialogSignUpNewsLetter();*/ //commenting for pexdev newsletter popup enabled
		}

		return this;
	}


	@CacheLookup
	@FindBy(id="btn-cookie-allow")
	WebElement btnContinue;
	public OnlineEnrollmentLandingPage_PartyLite clickToAllowCookie() {

		//new code to close newsletter popup - added on Nov 5
		/*driver.get().navigate().refresh();

		try {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
			WebElement closeSignUpDialog = driver.get().findElementByXPath("(//button[@class='action-close'])[2]");
			driver.get().executeScript("arguments[0].click()", closeSignUpDialog);
			System.out.println("Newsletter closed in third attempt after second refresh");
			reportStep("Newsletter closed in third attempt after second refresh","info");
			return this;
		} catch(Exception e) {
			System.out.println("Newsletter closed before second refresh");
		}*/

		try
		{
			WebElement modalCurtain = driver.get().findElementByXPath("//aside");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalCurtain));
		}catch (Exception e3) {

			try {
				WebElement btnAcceptCookie = driver.get().findElementByXPath("//button[@id='btn-cookie-allow']");
				reportStep("About to click on the continue button","info");
				try {
					WebElement modalOverlay = driver.get().findElementByXPath("//div[@class='modals-overlay']");
					new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalOverlay));
				}catch (Exception e) {

					driver.get().executeScript("arguments[0].click()", btnAcceptCookie);
					return this;
				}
			} catch (org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e) {
				reportStep("Button to allow cookie usage timed out or missing ","info");
				return this;
			}
		}
		try {
			WebElement btnAcceptCookie = driver.get().findElementByXPath("//button[@id='btn-cookie-allow']");
			reportStep("To close the continue button","info");
			try {
				WebElement modalOverlay = driver.get().findElementByXPath("//div[@class='modals-overlay']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalOverlay));
				driver.get().executeScript("arguments[0].click()", btnAcceptCookie);
				return this;
			}catch (Exception e) {
				System.out.println("Cookie status?");
				try {
					new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.elementToBeClickable(btnAcceptCookie));
					driver.get().executeScript("arguments[0].click()", btnAcceptCookie);
					return this;
				}
				catch(Exception e1) {
					return this;
				}
			}

		} catch (org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e) {
			reportStep("Button to allow cookie usage timed out or missing ","info");
			return this;
		}

	}
	
	public OnlineEnrollmentLandingPage_PartyLite bodyContainer() {
		try {
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
					refreshed(ExpectedConditions.attributeContains(bodyContainer, "aria-busy", "false")));
			reportStep("Body container reached false", "info");
		}
		catch(Exception e) {
			System.out.println("Body container wait failed");
		}
		return this;
	}

	@FindBy(css="form#about-you input")
	WebElement web_oe_email;
	public OnlineEnrollmentLandingPage_PartyLite enterEmail() {
		long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
		random_oe_email = "codemoe"+randomNum+"@mailinator.com";
		type(web_oe_email, random_oe_email);
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(css="form#about-you button#send span")
	WebElement web_email_submit;
	public OnlineEnrollmentLandingPage_PartyLite clickSubmit() {
		click(web_email_submit);
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
	
	@FindBy(id="re-enter-email")
	WebElement web_oe_reenter_email;
	public OnlineEnrollmentLandingPage_PartyLite re_enterEmail() {
		System.out.println("Entered email"+web_oe_email.getAttribute("value"));
		type(web_oe_reenter_email, web_oe_email.getAttribute("value"));
		return this;
	}
	
	@FindBy(id="password")
	WebElement web_oe_password;
	public OnlineEnrollmentLandingPage_PartyLite enterPassword(String password) {
		type(web_oe_password, password);
		return this;
	}
	
	@FindBy(id="re-enter-password")
	WebElement web_oe_re_password;
	public OnlineEnrollmentLandingPage_PartyLite re_enterPassword(String password) {
		type(web_oe_re_password, password);
		return this;
	}
	
	@FindBy(className=".select_options")
	WebElement web_oe_gender;
	public OnlineEnrollmentLandingPage_PartyLite clickGenderDropdown() {
		click(web_oe_gender);
		return this;
	}
	
	@FindBy(xpath="//label[@for='user-gender-1']")
	WebElement web_oe_gender_male;
	public OnlineEnrollmentLandingPage_PartyLite clickGenderMale() {
		click(web_oe_gender_male);
		return this;
	}
	
	@FindBy(xpath="//label[@for='user-gender-3']")
	WebElement web_oe_gender_notspecified;
	public OnlineEnrollmentLandingPage_PartyLite clickGenderNotSpecified() {
		click(web_oe_gender_notspecified);
		return this;
	}
	
	@FindBy(id="user_firstname")
	WebElement web_oe_user_fname;
	public OnlineEnrollmentLandingPage_PartyLite enterFname() {
		
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    random_oe_fname = buffer.toString();
	 
	    System.out.println("First name"+random_oe_fname);
		type(web_oe_user_fname, random_oe_fname);
		return this;
	}
	
	@FindBy(id="middle_name")
	WebElement web_oe_user_mname;
	public OnlineEnrollmentLandingPage_PartyLite enterMname() {
		
		int targetStringLength = 1;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    random_oe_mname = buffer.toString();
	 
	    System.out.println("Middle name"+random_oe_mname);
		type(web_oe_user_mname, random_oe_mname);
		return this;
	}
	
	@FindBy(id="user_lastname")
	WebElement web_oe_user_lname;
	public OnlineEnrollmentLandingPage_PartyLite enterLname() {
		int targetStringLength = 5;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    random_oe_lname = buffer.toString();
	 
	    System.out.println("last name"+random_oe_lname);
		type(web_oe_user_lname, random_oe_lname);
		return this;
	}
	
	@FindBy(id="pref_firstname")
	WebElement web_oe_user_pfname;
	public OnlineEnrollmentLandingPage_PartyLite enterPFname() {
		
		int targetStringLength = 8;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    random_oe_pfname = buffer.toString();
	 
	    System.out.println("Preferred name"+random_oe_pfname);
		type(web_oe_user_pfname, random_oe_pfname);
		return this;
	}
	
	@FindBy(id="date-birth")
	WebElement web_oe_user_dob;
	public OnlineEnrollmentLandingPage_PartyLite enterUserDOB() {
		clear(web_oe_user_dob);
		type(web_oe_user_dob, "11011199");
		return this;
	}
	
	@FindBy(id="ssn")
	WebElement web_oe_user_ssn;
	public OnlineEnrollmentLandingPage_PartyLite enterUserSSN() {
		Random random = new Random();
		long randomNum = (long)100000000 + random.nextInt(900000000);
		String convert_str=Long.toString(randomNum);
		System.out.println(convert_str+"converted string ssn");
		StringBuilder buffer = new StringBuilder(convert_str);
		buffer.insert(3, '-');
		buffer.insert(6, '-');
		System.out.println(buffer+"after buffer -");
		random_oe_ssn=buffer.toString();
		System.out.println(random_oe_ssn+"Original ssn");
		type(web_oe_user_ssn, random_oe_ssn);
		return this;
	}
	
	@FindBy(id="re-enter-ssn")
	WebElement web_oe_user_ressn;
	public OnlineEnrollmentLandingPage_PartyLite enterUserReSSN() {
		System.out.println("SSN to reneter"+web_oe_user_ssn.getAttribute("value"));
		type(web_oe_user_ressn, web_oe_user_ssn.getAttribute("value"));
		return this;
	}
	
	@FindBy(css="button.next")
	WebElement web_oe_next;
	public OnlineEnrollmentLandingPage_PartyLite clickNext() {
		click(web_oe_next);
		
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			
			System.out.println("Loader finished loading");
		}
		
		
		return this;
	}
	
	
	@FindBy(id="contact_phone")
	WebElement web_oe_user_cphone;
	public OnlineEnrollmentLandingPage_PartyLite enterUserContactPhone(String cphone) {
		try {
			WebElement body = driver.get().findElementByXPath("//body");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(body, "aria-busy", "false")));
			System.out.println("Aria busy false is reached");
		} catch (Exception e) {
			
			System.out.println("Aria busy false is not reached");
		}
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			
			System.out.println("Loader finished loading");
		}
		verifyDisplayed(web_oe_user_cphone);
		if(driver.get().getCurrentUrl().contains("partylite.com"))
			//type(web_oe_user_cphone, us_random_phonenum);
			type(web_oe_user_cphone, cphone);
		else if(driver.get().getCurrentUrl().contains("partylite.ca"))
			//type(web_oe_user_cphone, ca_random_phonenum);
			type(web_oe_user_cphone, cphone);
		return this;
	}
	
	@FindBy(id="mobile_phone")
	WebElement web_oe_user_mphone;
	public OnlineEnrollmentLandingPage_PartyLite enterUserMobilePhone(String mphone) {
		verifyDisplayed(web_oe_user_mphone);
		if(driver.get().getCurrentUrl().contains("partylite.com"))
			//type(web_oe_user_mphone, us_random_mobnum);
			type(web_oe_user_mphone, mphone);
		else if(driver.get().getCurrentUrl().contains("partylite.ca"))
			//type(web_oe_user_mphone, ca_random_mobnum);
			type(web_oe_user_mphone, mphone);
		else if(driver.get().getCurrentUrl().contains("partylite.co.uk"))
			//type(web_oe_user_mphone, ca_random_mobnum);
			type(web_oe_user_mphone, mphone);
		return this;
	}
	
	@FindBy(xpath = "//*[@id='pl_aff_sorting_code_number']")
	WebElement web_oe_sortcode;

	public OnlineEnrollmentLandingPage_PartyLite enterSortingCode() {
		verifyDisplayed(web_oe_sortcode);
		type(web_oe_sortcode, "12-34-56");
		return this;
	}
	
	@FindBy(xpath = "//*[@id='pl_aff_account_number']")
	WebElement web_oe_accnum;

	public OnlineEnrollmentLandingPage_PartyLite enterAcctNum() {
		type(web_oe_accnum, "14785296");
		return this;
	}
	
	@FindBy(xpath = "//*[@id='pl_aff_bank_name']")
	WebElement web_oe_bankname;

	public OnlineEnrollmentLandingPage_PartyLite enterBankname() {
		type(web_oe_bankname, "Santander UK");
		return this;
	}
	
	@FindBy(id="shipping_line_1")
	WebElement web_oe_user_ship_ad1;
	public OnlineEnrollmentLandingPage_PartyLite enterUserShipAddressLine1(String shad1) {
		verifyDisplayed(web_oe_user_ship_ad1);
		if(driver.get().getCurrentUrl().contains("partylite.com"))
			type(web_oe_user_ship_ad1, shad1);
		else if(driver.get().getCurrentUrl().contains("partylite.ca"))
			type(web_oe_user_ship_ad1, shad1);
		else if(driver.get().getCurrentUrl().contains("partylite.co.uk"))
			//type(web_oe_user_mphone, ca_random_mobnum);
			type(web_oe_user_ship_ad1, shad1);
		return this;
		
	}
	
	@FindBy(id="shipping_city")
	WebElement web_oe_user_shcity;
	public OnlineEnrollmentLandingPage_PartyLite enterUserShipCity(String shcity) {
		verifyDisplayed(web_oe_user_shcity);
		if(driver.get().getCurrentUrl().contains("partylite.com"))
			type(web_oe_user_shcity, shcity);
		else if(driver.get().getCurrentUrl().contains("partylite.ca"))
			type(web_oe_user_shcity, shcity);
		else if(driver.get().getCurrentUrl().contains("partylite.co.uk"))
			type(web_oe_user_shcity, shcity);
		return this;
	}
	
	@FindBy(id="shipping_postcode")
	WebElement web_oe_user_shpostcode;
	public OnlineEnrollmentLandingPage_PartyLite enterUserShipPostcode(String shpostcode) {
		verifyDisplayed(web_oe_user_shpostcode);
		if(driver.get().getCurrentUrl().contains("partylite.com"))
			type(web_oe_user_shpostcode, shpostcode);
		else if(driver.get().getCurrentUrl().contains("partylite.ca"))
			type(web_oe_user_shpostcode, shpostcode);
		else if(driver.get().getCurrentUrl().contains("partylite.co.uk"))
			type(web_oe_user_shpostcode, shpostcode);
		return this;
	}
	
	@FindBy(xpath="//label[@for='shipping_regions-opener']")
	WebElement web_oe_user_statedropdown;
	public OnlineEnrollmentLandingPage_PartyLite clickStateDropdown(String state) {
		WebElement drop_state;
		verifyDisplayed(web_oe_user_statedropdown);
		click(web_oe_user_statedropdown);
		try {
			if(driver.get().getCurrentUrl().contains("partylite.com"))
				drop_state =driver.get().findElementByXPath("//span[contains(text(),'"+state+"')]");
			else if(driver.get().getCurrentUrl().contains("partylite.ca"))
				drop_state =driver.get().findElementByXPath("//span[contains(text(),'"+state+"')]");
			else
				drop_state=null;
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.elementToBeClickable(drop_state));
			driver.get().executeScript("arguments[0].click()", drop_state);
			/*Actions actions = new Actions(driver.get());
			actions.moveToElement(drop_state).click().build().perform();*/
			System.out.println("State selected using javascript executor");
			//driver.get().executeScript("arguments[0].click()", drop_state);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(xpath = "//*[@id='about-you']/fieldset[3]/div[1]/label")
	WebElement web_oe_user_billingcheckbox;

	public OnlineEnrollmentLandingPage_PartyLite clickBillingCheckbox() {
		verifyDisplayed(web_oe_user_billingcheckbox);
		click(web_oe_user_billingcheckbox);
		return this;
	}
	
	@FindBy(id = "billing_line_1")
	WebElement web_oe_user_bill_ad1;

	public OnlineEnrollmentLandingPage_PartyLite enterUserBillAddressLine1(String billaddress1) {
		verifyDisplayed(web_oe_user_bill_ad1);
		if (driver.get().getCurrentUrl().contains("partylite.com"))
			type(web_oe_user_bill_ad1, billaddress1);
		else if (driver.get().getCurrentUrl().contains("partylite.ca"))
			type(web_oe_user_bill_ad1, billaddress1);
		else if (driver.get().getCurrentUrl().contains("partylite.co.uk"))
			// type(web_oe_user_mphone, ca_random_mobnum);
			type(web_oe_user_bill_ad1, billaddress1);
		return this;

	}
	
	@FindBy(id = "billing_city")
	WebElement web_oe_user_bicity;

	public OnlineEnrollmentLandingPage_PartyLite enterUserBillCity(String billcity) {
		verifyDisplayed(web_oe_user_bicity);
		if (driver.get().getCurrentUrl().contains("partylite.com"))
			type(web_oe_user_bicity, billcity);
		else if (driver.get().getCurrentUrl().contains("partylite.ca"))
			type(web_oe_user_bicity, billcity);
		else if (driver.get().getCurrentUrl().contains("partylite.co.uk"))
			type(web_oe_user_bicity, billcity);
		return this;
	}
	
	@FindBy(xpath = "//label[@for='billing_regions-opener']")
	WebElement web_oe_user_statedropdown1;
	public OnlineEnrollmentLandingPage_PartyLite clickStateDropdown1(String state1) {
		WebElement drop_state1;
		verifyDisplayed(web_oe_user_statedropdown1);
		click(web_oe_user_statedropdown1);
		try {
			if (driver.get().getCurrentUrl().contains("partylite.com.au"))
				//drop_state1 = driver.get().findElementByXPath("//span[contains(text(),'" + state1 + "')]");
				drop_state1 = driver.get().findElementByXPath("//div[contains(@class, 'field billing_regions')]//span[contains(text(),'" + state1 + "')]");
			else
				drop_state1 = null;
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.elementToBeClickable(drop_state1));
			driver.get().executeScript("arguments[0].click()", drop_state1);
			/*
			 * Actions actions = new Actions(driver.get());
			 * actions.moveToElement(drop_state).click().build().perform();
			 */
			System.out.println("Billing State selected using javascript executor");
			// driver.get().executeScript("arguments[0].click()", drop_state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(id = "billing_postcode")
	WebElement web_oe_user_bipostcode;

	public OnlineEnrollmentLandingPage_PartyLite enterUserBillPostcode(String billzipcode) {
		verifyDisplayed(web_oe_user_bipostcode);
		if (driver.get().getCurrentUrl().contains("partylite.com"))
			type(web_oe_user_bipostcode, billzipcode);
		else if (driver.get().getCurrentUrl().contains("partylite.ca"))
			type(web_oe_user_bipostcode, billzipcode);
		else if (driver.get().getCurrentUrl().contains("partylite.co.uk"))
			type(web_oe_user_bipostcode, billzipcode);
		return this;
	}
	
	@FindBy(xpath="//label[@for='marketing-opener']")
	WebElement web_oe_user_marketing;
	public OnlineEnrollmentLandingPage_PartyLite clickMarketingDropdown(String state) {
		click(web_oe_user_marketing);
		try {
			driver.get().findElementByXPath("//label[@for='marketing-227']").click();;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(id="marketing-details")
	WebElement web_oe_user_mktdetails;
	public OnlineEnrollmentLandingPage_PartyLite enterMarketingDetails(String mkdetails) {
		type(web_oe_user_mktdetails, mkdetails);
		return this;
	}
	
	@FindBy(css=".search-format-list span")
	WebElement web_oe_user_expresult;
	public KitsAndAddOnsPage_PartyLite clickExpResult() {
		
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
			Actions actions = new Actions(driver.get());
			actions.moveToElement(web_oe_user_expresult).click().build().perform();
			System.out.println("Experian suggestion selected"+driver.get().getCurrentUrl());
		}
		catch(Exception e) {
			System.out.println("No address suggestion");
			WebElement web_oe_user_manual=driver.get().findElementByCssSelector("button.with-address");
			Actions actions = new Actions(driver.get());
			actions.moveToElement(web_oe_user_manual).click().build().perform();
		}
		
		//driver.get().executeScript("arguments[0].click()", web_oe_user_expresult);
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
	
	@FindBy(xpath = "//*[@id='about-you']/fieldset[2]/div[5]/div[3]/div/ul/li")
	WebElement web_oe_user_shipresult;
	public OnlineEnrollmentLandingPage_PartyLite clickShipExpResult() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			// System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout)
					.until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains(loader, "style", "none")));

			// System.out.prinln("Page rendering is complete to click on delivery type
			// selection dropdown.");
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			Actions actions = new Actions(driver.get());
			actions.moveToElement(web_oe_user_shipresult).click().build().perform();
			System.out.println("Experian suggestion selected" + driver.get().getCurrentUrl());
		} catch (Exception e) {
			System.out.println("No address suggestion");
			WebElement web_oe_user_manual1 = driver.get().findElementByCssSelector("button.with-address");
			Actions actions = new Actions(driver.get());
			actions.moveToElement(web_oe_user_manual1).click().build().perform();
		}

		// driver.get().executeScript("arguments[0].click()", web_oe_user_expresult);
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			// System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout)
					.until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains(loader, "style", "none")));

			// System.out.prinln("Page rendering is complete to click on delivery type
			// selection dropdown.");
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			return new OnlineEnrollmentLandingPage_PartyLite(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	@FindBy(xpath = "//*[@id='about-you']/fieldset[3]/div[6]/div[3]/div/ul/li[1]")
	WebElement web_oe_user_billresult;
	public KitsAndAddOnsPage_PartyLite clickBillExpResult() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			// System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout)
					.until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains(loader, "style", "none")));

			// System.out.prinln("Page rendering is complete to click on delivery type
			// selection dropdown.");
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			Actions actions = new Actions(driver.get());
			actions.moveToElement(web_oe_user_billresult).click().build().perform();
			System.out.println("Experian suggestion selected" + driver.get().getCurrentUrl());
		} catch (Exception e) {
			System.out.println("No address suggestion");
			WebElement web_oe_user_manual2 = driver.get().findElementByCssSelector("button.with-address");
			Actions actions = new Actions(driver.get());
			actions.moveToElement(web_oe_user_manual2).click().build().perform();
		}

		// driver.get().executeScript("arguments[0].click()", web_oe_user_expresult);
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			// System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout)
					.until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains(loader, "style", "none")));

			// System.out.prinln("Page rendering is complete to click on delivery type
			// selection dropdown.");
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




