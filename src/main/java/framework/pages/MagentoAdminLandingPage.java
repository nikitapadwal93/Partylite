package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initMagentoAdmin;

public class MagentoAdminLandingPage extends Class_initMagentoAdmin{

	
	public MagentoAdminLandingPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	@FindBy(id="username")
	WebElement txtBoxUserName;
	public MagentoAdminLandingPage enterUserName(String uname) {
		type(txtBoxUserName,uname);
		return this;
	}
	
	@FindBy(id="login")
	WebElement txtBoxPassWord;
	public MagentoAdminLandingPage enterPassWord(String password) {
		type(txtBoxPassWord,password);
		return this;
	}
	
	@FindBy(xpath="//button/span[contains(text(),'Sign in')]")
	WebElement buttonSign_In;
	public MagentoDashboardPage clickSignIn() {
		click(buttonSign_In);
		try {
			return new MagentoDashboardPage(driver,Test);
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
