package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class partyHostPage extends Class_initEcomPrac  {

	partyHostPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
		
	@FindBy(id="create-order")
	WebElement btnCreatePaperOrder;
		public ProductSelectionPage clickCreatePaperOrder() {
			
			/*try {
				WebElement partyguest=driver.get().findElement(By.xpath("(//a[@class='name-underline'])[3]"));
				click(partyguest);
				}
			catch(Exception e)
				{
				System.out.println("Navigated to Guest Detail Page");
				}*/ //unwanted code
			
			click(btnCreatePaperOrder);
			
			/*if(btnCreatePaperOrder.getAttribute("id").equals("create-order")) {
				//System.out.prinln("Insdie actions class");
			
			new Actions(driver.get()).moveToElement(btnCreatePaperOrder, 10, 10).click().build().perform();*/
			try {
				return new ProductSelectionPage(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			}
		
		@FindBy(xpath="//a[@class='small-btn primary']")
		WebElement book_party;
		public PartyCreationPage bookParty() {
			click(book_party);
			try {
				return new PartyCreationPage(driver, Test);
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@FindBy(xpath="//div[@data-ui-id='fme-productattachments-message-message-success']")
		WebElement thanks_msg;
		public partyHostPage verifyChildPartyMessage() {
			verifyDisplayed(thanks_msg);
			return this;
		}
		
			
		}
		
	
