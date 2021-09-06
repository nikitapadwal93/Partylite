package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class MailinatorLandingPage extends Class_initEcomPrac{

	MailinatorLandingPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	
	public MailinatorLandingPage clickPartyLiteMail(String country) {
		/*
		String from="PartyLite";
		String subject="Party Invitation";
		String received="moments ago";*/
		
		
		//List<WebElement> mailRows=driver.findElements(By.xpath("//tr[@class='even pointer ng-scope']"));
		List<WebElement> mailRows=driver.get().findElements(By.cssSelector("a.ng-binding"));
		for(WebElement mailRow:mailRows) {
			if(mailRow.getText().contains(markets.get(country).getProperty("Invitation")))
				{
				//System.out.prinln(mailRow.getText());
				click(mailRow);
				break;
				}
				
		}
			
		return this;
		
	}
	
	public MailinatorLandingPage eShopOrderSuccessMail(String country) {
		
		WebElement mailRow=driver.get().findElement(By.cssSelector("a.ng-binding"));
		
			if(mailRow.getText().contains(markets.get(country).getProperty("eShopSuccessEmail")))
				{
				System.out.println(mailRow.getText());
				click(mailRow);
				reportStep("Order confirmation email is clicked", "PASS");
				return this;
				}
			
		reportStep("Order confirmation email is not received for eShop orders", "FAIL");
		return null;
		
	}
	
	@FindBy(xpath="//a[contains(text(),'Create an account')]")
	WebElement btn_CreateAnAccount_Mail;
	public NanoSiteLandingPage clickCreateAnAccountMail() {
		
		try {
			driver.get().switchTo().frame("msg_body");
			//String hrefAttribute = driver.findElementByXPath("//img[contains(@src,'see-you-there')]/parent::a").getAttribute("href");
			String hrefAttribute = driver.get().findElementByXPath("//h4[text()]/following::a").getAttribute("href");
			
			System.out.println("Retrieved attribute is = " + hrefAttribute);
			driver.get().navigate().to(hrefAttribute);
			/*
			if(hrefAttribute.length()!=0) {
			
				String[] splittedURL = hrefAttribute.split("[=]");
			
				System.out.println(splittedURL[1]);
		
				splittedURL[1] = splittedURL[1].replace("%3A",":");
				splittedURL[1] = splittedURL[1].replace("%3F", "?");
				splittedURL[1] = splittedURL[1].replace("%3D", "=");
				splittedURL[1] = splittedURL[1].replace("%26", "&");
				System.out.println("Final cleaned up URL link is - " + splittedURL[1]);
			
			driver.get().navigate().to(splittedURL[1]);
					}
			else
			{
				System.out.println("URL could not be parsed");
			}*/
		
			return new NanoSiteLandingPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(css=".email-summary a")
	WebElement email_order_num;
	public MailinatorLandingPage verifyOrderNumberInEmail() {
		
		try {
			driver.get().switchTo().frame("msg_body");
			String email_order_number=email_order_num.getText();
			System.out.println(email_order_number+"in order conf email");
			order_number.equals(email_order_number);
			reportStep("Order number verified in order conf email", "PASS");
			return this;
	}
		catch(Exception e) {
			
		}
		return null;
	}
	
}
