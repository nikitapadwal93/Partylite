package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initMagentoAdmin;

public class MagentoDashboardPage extends Class_initMagentoAdmin {

	 MagentoDashboardPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}
	 @FindBy(id="menu-magento-backend-stores")
	 WebElement store;
	 public MagentoDashboardPage clickStoreMenu() {
		 click(store);
		 return this;
	 }
	 
	 @FindBy(xpath="//li[@data-ui-id='menu-magento-config-system-config']")
	 WebElement configuration;
	 public MagentoDashboardPage clickConfigurationMenu() {
		 WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
		 wait.until(ExpectedConditions.visibilityOf(configuration));
		 click(configuration);
		 return this;
	 }
	 
	 @FindBy(xpath="//strong[contains(text(),'Security Suite')]")
	 WebElement securitySuite;
	 public MagentoDashboardPage clickSecuritySuiteMenu() {
		 click(securitySuite);
		 return this;
	 }
	 
	 @FindBy(xpath="//span[contains(text(),'Google reCaptcha')]")
	 WebElement googlereCaptcha;
	 public MagentoDashboardPage clickGooglereCaptchaMenu() {
		 click(googlereCaptcha);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend-head")
	 WebElement verifyFrontEndEnabled;
	 public MagentoDashboardPage verifyFrontEndEnabled() {
		 try {
			 WebDriverWait wait=new WebDriverWait(driver.get(),waitTimeout);
			 wait.until(ExpectedConditions.visibilityOf(loginCheckBox));
		 }
		 catch(NoSuchElementException | org.openqa.selenium.TimeoutException e) {
			 click(verifyFrontEndEnabled);
		 }
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_login_inherit")
	 WebElement loginCheckBox;
	 public MagentoDashboardPage clickLoginCheckBox() {
		 if(verifySelected(loginCheckBox))
		 click(loginCheckBox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_forgot_inherit")
	 WebElement forgot_password_checkbox;
	 public MagentoDashboardPage clickForgotPasswordCheckBox() {
		 if(verifySelected(forgot_password_checkbox))
		 click(forgot_password_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_contact_inherit")
	 WebElement contact_checkbox;
	 public MagentoDashboardPage clickContactCheckBox() {
		 if(verifySelected(contact_checkbox))
		 click(contact_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_create_inherit")
	 WebElement create_user_checkbox;
	 public MagentoDashboardPage clickCreateUserCheckBox() {
		 if(verifySelected(create_user_checkbox))
		 click(create_user_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_consultant_locator_inherit")
	 WebElement consultant_checkbox;
	 public MagentoDashboardPage clickConsultantCheckBox() {
		 if(verifySelected(consultant_checkbox))
		 click(consultant_checkbox);
		 return this;
	 }
	 
	 public MagentoDashboardPage enableLoginCheckBox() {
		 if(!verifySelected(loginCheckBox))
		 click(loginCheckBox);
		 return this;
	 }
	 
	 public MagentoDashboardPage enableForgotPasswordCheckBox() {
		 if(!verifySelected(forgot_password_checkbox))
		 click(forgot_password_checkbox);
		 return this;
	 }
	 
	 public MagentoDashboardPage enableContactCheckBox() {
		 if(!verifySelected(contact_checkbox))
		 click(contact_checkbox);
		 return this;
	 }
	 
	 public MagentoDashboardPage enableCreateUserCheckBox() {
		 if(!verifySelected(create_user_checkbox))
		 click(create_user_checkbox);
		 return this;
	 }
	 
	 public MagentoDashboardPage enableConsultantCheckBox() {
		 if(!verifySelected(consultant_checkbox))
		 click(consultant_checkbox);
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_login")
	 WebElement select_login;
	 public MagentoDashboardPage selectLoginDropdownNo() {
	 selectDropDownUsingValue(select_login, "0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_forgot")
	 WebElement select_forgot_password;
	 public MagentoDashboardPage selectForgotPasswordDropdownNo() {
		 selectDropDownUsingValue(select_forgot_password, "0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_contact")
	 WebElement select_contact;
	 public MagentoDashboardPage selectContactDropdownNo() {
		 selectDropDownUsingValue(select_contact,"0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_create")
	 WebElement select_create_user;
	 public MagentoDashboardPage selectCreateUserDropdownNo() {
		 selectDropDownUsingValue(select_create_user,"0");
		 return this;
	 }
	 
	 @FindBy(id="msp_securitysuite_recaptcha_frontend_enabled_consultant_locator")
	 WebElement select_consultant;
	 public MagentoDashboardPage selectConsultantDropdownNo() {
		 selectDropDownUsingValue(select_consultant,"0");
		 return this;
	 }
	 
	 
	 @FindBy(id="save")
	 WebElement save_config;
	 public MagentoDashboardPage clickSaveConfig() {
		 save_config.click();
		 return this;
	 }
	 
	 @FindBy(xpath="//div[@data-ui-id='messages-message-success']")
	 WebElement success_message;
	 public MagentoDashboardPage verifyMessage() {
		 verifyExactText(success_message, "You saved the configuration.");
		 return this;
	 }
	 
	 @FindBy(className="admin-user-account-text")
	 WebElement user_account;
	 public MagentoDashboardPage clickUserAccount(){
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
