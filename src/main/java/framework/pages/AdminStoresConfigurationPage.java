package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

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

public class AdminStoresConfigurationPage extends Class_initEcomPrac {

	AdminStoresConfigurationPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	@FindBy(xpath="//strong[contains(text(),'Security Suite')]")
	 WebElement securitySuite;
	 public AdminStoresConfigurationPage clickSecuritySuiteMenu() {
		 try {
		 click(securitySuite);
		 } catch(org.openqa.selenium.ElementNotInteractableException e) {
			 new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.elementToBeClickable(securitySuite));
			 try {
			 click(securitySuite);
			 } catch(org.openqa.selenium.ElementNotInteractableException e1) {
				 reportStep("Security suite section link could not be clicked.","info");
				 System.out.println("Security suite section link could not be clicked.");
			 }
			 return this;
		 }
		 
		 return this;
	 }
	 
	 @FindBy(xpath="//span[contains(text(),'Google reCaptcha')]")
	 WebElement googlereCaptcha;
	 public AdminStoresConfigurationPage clickGooglereCaptchaMenu() {
		 click(googlereCaptcha);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend-head")
	 WebElement verifyFrontEndEnabled;
	 public AdminStoresConfigurationPage verifyFrontEndEnabled() {
		 try {
			 WebDriverWait wait=new WebDriverWait(driver.get(),waitTimeout);
			 wait.until(ExpectedConditions.visibilityOf(loginCheckBox));
		 }
		 catch(NoSuchElementException | TimeoutException e) {
			 click(verifyFrontEndEnabled);
		 }
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_login_inherit")
	 WebElement loginCheckBox;
	 public AdminStoresConfigurationPage clickLoginCheckBox() {
		 if(verifySelected(loginCheckBox))
		 click(loginCheckBox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_forgot_inherit")
	 WebElement forgot_password_checkbox;
	 public AdminStoresConfigurationPage clickForgotPasswordCheckBox() {
		 if(verifySelected(forgot_password_checkbox))
		 click(forgot_password_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_contact_inherit")
	 WebElement contact_checkbox;
	 public AdminStoresConfigurationPage clickContactCheckBox() {
		 if(verifySelected(contact_checkbox))
		 click(contact_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_create_inherit")
	 WebElement create_user_checkbox;
	 public AdminStoresConfigurationPage clickCreateUserCheckBox() {
		 if(verifySelected(create_user_checkbox))
		 click(create_user_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_consultant_locator_inherit")
	 WebElement consultant_checkbox;
	 public AdminStoresConfigurationPage clickConsultantCheckBox() {
		 if(verifySelected(consultant_checkbox))
		 click(consultant_checkbox);
		 return this;
	 }
	 
	 public AdminStoresConfigurationPage enableLoginCheckBox() {
		 if(!verifySelected(loginCheckBox))
		 click(loginCheckBox);
		 return this;
	 }
	 
	 public AdminStoresConfigurationPage enableForgotPasswordCheckBox() {
		 if(!verifySelected(forgot_password_checkbox))
		 click(forgot_password_checkbox);
		 return this;
	 }
	 
	 public AdminStoresConfigurationPage enableContactCheckBox() {
		 if(!verifySelected(contact_checkbox))
		 click(contact_checkbox);
		 return this;
	 }
	 
	 public AdminStoresConfigurationPage enableCreateUserCheckBox() {
		 if(!verifySelected(create_user_checkbox))
		 click(create_user_checkbox);
		 return this;
	 }
	 
	 public AdminStoresConfigurationPage enableConsultantCheckBox() {
		 if(!verifySelected(consultant_checkbox))
		 click(consultant_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_login")
	 WebElement select_login;
	 public AdminStoresConfigurationPage selectLoginDropdownNo() {
	 selectDropDownUsingValue(select_login, "0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_forgot")
	 WebElement select_forgot_password;
	 public AdminStoresConfigurationPage selectForgotPasswordDropdownNo() {
		 selectDropDownUsingValue(select_forgot_password, "0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_contact")
	 WebElement select_contact;
	 public AdminStoresConfigurationPage selectContactDropdownNo() {
		 selectDropDownUsingValue(select_contact,"0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_create")
	 WebElement select_create_user;
	 public AdminStoresConfigurationPage selectCreateUserDropdownNo() {
		 selectDropDownUsingValue(select_create_user,"0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_consultant_locator")
	 WebElement select_consultant;
	 public AdminStoresConfigurationPage selectConsultantDropdownNo() {
		 selectDropDownUsingValue(select_consultant,"0");
		 return this;
	 }
	 
	 
	 @FindBy(id="save")
	 WebElement save_config;
	 public AdminStoresConfigurationPage clickSaveConfig() {
		 save_config.click();
		 return this;
	 }
	 
	 @FindBy(xpath="//div[@data-ui-id='messages-message-success']")
	 WebElement success_message;
	 public AdminStoresConfigurationPage verifyMessage() {
		 verifyExactText(success_message, "You saved the configuration.");
		 return this;
	 }
	 
	 @FindBy(className="admin-user-account-text")
	 WebElement user_account;
	 public AdminStoresConfigurationPage clickUserAccount(){
		 click(user_account);
		 return this;
	 }
	 
	 @FindBy(className="account-signout")
	 WebElement signout;
	 public AdminLandingPage clickSignOut() {
		 
		 click(signout);
		 try {
			return new AdminLandingPage(driver, Test);
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
