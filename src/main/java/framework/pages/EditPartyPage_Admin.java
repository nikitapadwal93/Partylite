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

public class EditPartyPage_Admin extends Class_initEcomPrac {

	boolean isPageReady;
	EditPartyPage_Admin(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	@FindBy(name="party[expire_date]")
	 WebElement expire_date;
	 public EditPartyPage_Admin typeExpireDate(){
		 clear(expire_date);
		 type(expire_date,"01/05/2019");
		 return this;
	 }
	 
	 @FindBy(id="party_edit_tabs_public_page_tab")
	 WebElement public_page_edit;
	 public EditPartyPage_Admin clickPublicPage(){
		 click(public_page_edit);
		 return this;
	 }
	 
	 @FindBy(id="show_public_page")
	 WebElement show_public_page;
	 public EditPartyPage_Admin selectPublicPageYes() {
	 selectDropDownUsingValue(show_public_page, "1");
		 return this;
	 }
	 
	 @FindBy(id="save")
	 WebElement save_button;
	 public ManagePartiesPage clickSaveButton() {
	 click(save_button);
	 try {
		 WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
		 wait.until(ExpectedConditions.visibilityOf(driver.get().findElementByXPath("//div[contains(text(),'You saved this record.')]")));
			return new ManagePartiesPage(driver,Test);
		} catch (NoSuchElementException  | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return null;
	 }
	
}
