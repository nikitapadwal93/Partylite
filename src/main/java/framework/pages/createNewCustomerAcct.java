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

	public class createNewCustomerAcct extends Class_initEcomPrac  {
		String genEmail= null;

			public createNewCustomerAcct(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
				super();
				PageFactory.initElements(driver.get(), this);
				this.driver = driver;
				this.Test = Test;
					
		}
			
			@FindBy(id="firstname")
			WebElement txtboxfirstname;
			public createNewCustomerAcct enterFirstname(String firstname) {
				try {
					driver.get().findElementById("btn-cookie-allow").click();
					System.out.println("Cookie is shown in create party page");
					reportStep("Cookie is shown in create party page", "info");
				}
				catch(Exception e) {
					System.out.println("Cookie button is not shown");
					//e.printStackTrace();
				}
				
				type(txtboxfirstname, firstname);
				return this;
			}
			
			
			@FindBy(id="lastname")
			WebElement txtboxlastname;
			public createNewCustomerAcct enterLastname(String lastname) {
				type(txtboxlastname, lastname);
				return this;
			}
			
			@FindBy(id="email_address")
			WebElement txtboxEmail;
			public createNewCustomerAcct enterEmail(String Email) {
				long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
				genEmail= Email+randomNum+"@mailinator.com";
				
				////System.out.prinln(genEmail);
				type(txtboxEmail, genEmail);
				return this;
			}
			
			@FindBy(id="email_address")
			WebElement txtboxRealEmail;
			public createNewCustomerAcct enterRealEmail(String Email) {
//				long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
//				genEmail= Email+randomNum+"@mailinator.com";
				
				////System.out.prinln(genEmail);
				type(txtboxRealEmail, Email);
				return this;
			}
			
			@FindBy(id="email_address_confirmation")
			WebElement txtboxRealEmailConf;
			public createNewCustomerAcct enterRealEmailConf(String EmailConf) {
				type(txtboxRealEmailConf, EmailConf);
				return this;
			}
			
			
			
			@FindBy(id="email_address")
			WebElement txtboxNanoCustomerEmail;
			public createNewCustomerAcct enterNanoCustomerEmail(String Email) {
				long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
				Guest_Email= Email+randomNum+"@mailinator.com";
				
				////System.out.prinln(genEmail);
				type(txtboxNanoCustomerEmail, Guest_Email);
				return this;
			}
			
			@FindBy(id="email_address_confirmation")
			WebElement txtboxNanoCustomerConfEmail;
			public createNewCustomerAcct enterNanoCustomerConfEmail(String Email) {
				type(txtboxNanoCustomerConfEmail, Guest_Email);
				return this;
			}
			
			@FindBy(id="email_address_confirmation")
			WebElement txtboxEmailConf;
			public createNewCustomerAcct enterEmailConf(String EmailConf) {
				type(txtboxEmailConf, genEmail);
				return this;
			}
			
			@FindBy(id="email_address")
			WebElement txtboxExistingEmail;
			public createNewCustomerAcct enterExistingEmail(String ExistingEmail) {
				type(txtboxExistingEmail, ExistingEmail);
				return this;
			}
			
			@FindBy(id="email_address_confirmation")
			WebElement txtboxExistingEmailConf;
			public createNewCustomerAcct enterExistingEmailConf(String ExistingEmailConf) {
				type(txtboxExistingEmailConf, ExistingEmailConf);
				return this;
			}
			
			@FindBy(id="password")
			WebElement txtPassword;
			public createNewCustomerAcct enterPassword(String Password) {
				type(txtPassword, Password);
				return this;
			}
			
			@FindBy(id="password-confirmation")
			WebElement txtPasswordConfirmation;
			public createNewCustomerAcct enterPasswordConf(String PasswordConf) {
				type(txtPasswordConfirmation, PasswordConf);
				return this;
			}
			
			@FindBy(id="is_terms_conditions")
			WebElement labelTermsConditions;
			public createNewCustomerAcct clickTermsConditions() {
				try {
					driver.get().executeScript("arguments[0].click()", labelTermsConditions);
					System.out.println("Checkbox clicked");
					//labelTermsConditions.click();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return this;
			}
			
			@FindBy(css=".form-create-account button")

			WebElement buttonCreateAccount;
			public customerAccountPage clickCreateAnAccount() {
				
				click(buttonCreateAccount);
				try {
					return new customerAccountPage(driver, Test);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			@FindBy(css=".form-create-account button")
			WebElement buttonNanoUserCreateAccount;
			public CartSummaryPage clickNanoUserCreateAnAccount() {
				
				click(buttonNanoUserCreateAccount);
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
