package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
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

public class partyHostBenefitsPage extends Class_initEcomPrac  {

	Boolean isPageReady;
	partyHostBenefitsPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;

	}

	@FindBy(id="nav-hostcredit")
	WebElement linkHostCredits;
	public partyHostBenefitsPage clickHostCredits() {
		try {
			new WebDriverWait(driver.get(),5).until(ExpectedConditions.refreshed(
					ExpectedConditions.attributeToBe(By.xpath("//body[@data-container='body']"), "aria-busy", "false")));
			System.out.println("Aria busy false check in host credit");
		}
		catch(Exception e1)
		{
			System.out.println("Aria busy catch");
		}
		click(linkHostCredits);
		return this;
	}

	@FindBy(name="myCredits")
	WebElement txtBoxCredits;
	public partyHostBenefitsPage enterHostCreditSKU(String SKU) {
		/*try {
			new WebDriverWait(driver.get(),reducedTimeout).until(ExpectedConditions.refreshed(
					ExpectedConditions.attributeToBe(By.xpath("//body[@data-container='body']"), "aria-busy", "false")));
		}
		catch(Exception e1)
		{
			click(linkHostCredits);
			try {
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.attributeToBe(By.xpath("//body[@data-container='body']"), "aria-busy", "false"));
			}
			catch(Exception e) {

			}

		}*/ //-unwanted code

		type(txtBoxCredits,SKU);
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("Loader after sku search");
		} catch (Exception e) {
			System.out.println("no Loader after sku search");
		}
		return this;
	}

	@FindBy(css=".customer-list li")
	WebElement suggestedProduct;
	public partyHostBenefitsPage selectProduct() {
		try {

			/*try {
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOfElementLocated(By.className("party__modal-content")));
				driver.get().findElementByXPath("//button[@class='yes primary' or @data-bind='click: applyCredit']").click();
			}
			catch(Exception e) {
				System.out.println("No GC Pop up");
			}*/ //GC pop up check

			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(suggestedProduct));
			click(suggestedProduct);

			try {
				WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering.");
				isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				System.out.println("Loader after product selection");
			} catch (Exception e) {
				System.out.println("no Loader after product selection");
			}
			return this;
		}
		catch(RuntimeException e) {
			reportStep("The suggestion of products was not displayed", "warning");
		}
		return null;
	}

	@FindBy(xpath="(//a[@data-toggle])[7]")
	WebElement linkHostOrderSummary;
	public partyHostBenefitsPage clickHostOrderSummary() {


		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("Loader after host summary tab selection");
		} catch (Exception e) {
			System.out.println("no Loader after host summary tab selection");
		}
		try {
			click(linkHostOrderSummary);
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return this;
	}


	@FindBy(xpath="//div[@class='primary-btn']/a")
	WebElement proceedToPayment;
	public CheckoutPage selectProceedToPayment() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("Loader before selecting proceed to payment for host order");
		} catch (Exception e) {
			System.out.println("no Loader before selecting proceed to payment for host order");
		}
		click(proceedToPayment);
		try {
			return new CheckoutPage(driver, Test);
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
