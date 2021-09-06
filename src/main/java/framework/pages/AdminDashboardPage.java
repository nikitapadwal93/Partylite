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

public class AdminDashboardPage extends Class_initEcomPrac {

	 AdminDashboardPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}
	 @FindBy(id="menu-magento-backend-stores")
	 WebElement store;
	 public AdminDashboardPage clickStoreMenu() {
		 click(store);
		 return this;
	 }
	 
	 @FindBy(id="menu-mw2consulting-party-configuration")
	 WebElement party;
	 public AdminDashboardPage clickPartyMenu() {
		 click(party);
		 return this;
	 }
	 
	 @FindBy(xpath="//li[@data-ui-id='menu-mw2consulting-party-party']")
	 WebElement manage_parties;
	 public ManagePartiesPage clickManageParties() {
		 WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
		 wait.until(ExpectedConditions.elementToBeClickable(manage_parties));
		 click(manage_parties);
		 try {
			return new ManagePartiesPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	 @FindBy(xpath="//li[@data-ui-id='menu-magento-config-system-config']")
	 WebElement configuration;
	 public AdminStoresConfigurationPage clickConfigurationMenu() {
		 WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
		 wait.until(ExpectedConditions.visibilityOf(configuration));
		 click(configuration);
		 try {
			return new AdminStoresConfigurationPage(driver,Test);
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
