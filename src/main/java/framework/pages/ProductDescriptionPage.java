package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class ProductDescriptionPage extends Class_initEcomPrac{

	
	ProductDescriptionPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	public ProductDescriptionPage bodyContainer() {
		try {
			WebElement body = driver.get().findElementByXPath("//body");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(body, "aria-busy", "false")));
			System.out.println("body container pass in pdp");
		} catch (Exception e ) {
			System.out.println("body container catch pdp");
		}
		return this;
	}
	
	@FindBy(id="product-addtocart-button")
	WebElement btnAddToCart;
	public ProductDescriptionPage clickBtnAddToCart() {
		System.out.println(driver.get().findElementById("product-addtocart-button").getText());
		click(btnAddToCart);
		return this;
	}
	
	@FindBy(id="qty")
	WebElement qty_box;
	public ProductDescriptionPage enterQty(String qty) {
		bodyContainer();
		qty_box.sendKeys(Keys.BACK_SPACE);
		type(qty_box,qty);
		return this;
		
	}
	
	@FindBy(id="search-action")
	WebElement btnSearch;
	public ProductDescriptionPage clickBtnSearch() {
		click(btnSearch);
		return this;
	}
	
	@FindBy(id="search")
	WebElement txtBoxSearch;
	public ProductDescriptionPage enterSKU(String SKU) {
		type(txtBoxSearch,SKU);
		return this;
	}
	
	@FindBy(xpath="//*[@class='mst-product-image-wrapper']")
	WebElement linkProductImage;
	public ProductDescriptionPage clickProductImage() {
		
	try {
		new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(linkProductImage));
		click(linkProductImage);
		return this;
	} catch (NoSuchElementException e) {
		// TODO Auto-generated catch block
		reportStep("The entered SKU isn't available or invalid", "warnining");
		return null;
	} 
	}
	
	@FindBy(xpath="//*[@id=\"search_mini_form\"]/div[1]/div/div[1]/div[3]/div[1]/div[1]/div[1]/ul/li[1]/a/div")
	WebElement linkProductImage2;
	public ProductDescriptionPage clickProductImageForTwoResults() {
		
	try {
		new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(linkProductImage2));
		click(linkProductImage2);
		return this;
	} catch (NoSuchElementException e) {
		// TODO Auto-generated catch block
		reportStep("The entered SKU isn't available or invalid", "warnining");
		return null;
	} 
	}
	
	public ProductDescriptionPage loaderCheck() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("loader pass");
		} catch (Exception e ) {
			System.out.println("loader catch");
		}
		return this;
	}
	
	@FindBy(xpath="//*[@id=\"newsletter\"]")
	WebElement newsletterFooter;
	public ProductDescriptionPage enterEmailtoSubscribeFooter(String email) {
		type(newsletterFooter, email);
		System.out.println("Email entered in footer is "+ email);
		return this;
	}
	
	@FindBy(xpath="//*[@id=\"newsletter-validate-detail\"]/div/div/div[1]/button")
	WebElement clickSubscribefromFooter;
	public ProductDescriptionPage clickFooterSubscribeButton() {
		click(clickSubscribefromFooter);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	//@FindBy(xpath="//*[@id=\"html-body\"]/div[8]/aside[3]/div[2]/footer/button")
	@FindBy(xpath="//aside[contains(@class,'_show')]//footer[contains(@class,'modal-footer')]//button[contains(@type,'button')]")
	WebElement clickContinue;
	public ProductDescriptionPage clickContinueShopping() {
		try {
		click(clickContinue);
		Thread.sleep(3000);
		System.out.println("Continue button clicked");
		System.out.println(driver.get().findElement(By.xpath("//span[@class='base']")).getText());
		}
		catch(Exception e) {
			System.out.println("Continue button not found");
		}
		return null;
	}
	
	@FindBy(xpath="//button[@data-role='proceed-to-checkout']")
	WebElement btnGoToCheckout;
	public CheckoutPage clickGoToCheckout() {
		try {
			WebElement total_cart_page=driver.get().findElementByCssSelector("#cart-totals table .grand");
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(total_cart_page)));
			System.out.println("Totals table wait success");
		}
		catch(Exception e) {
			System.out.println("Aria busy false check in cart page");
		}

		loaderCheck();
		try {
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.elementToBeClickable(btnGoToCheckout));
			click(btnGoToCheckout);
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

	
	@FindBy(css=".minicart-wrapper .showcart")
	WebElement btnMiniCart;
	public ProductDescriptionPage clickBtnMiniCart() {
		
		try {
			WebElement count=driver.get().findElementByClassName("counter-number");
			(new WebDriverWait(driver.get(), waitTimeout)).until(ExpectedConditions.refreshed(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver input) {
					// TODO Auto-generated method stub
					System.out.println("qty count"+count.getText());
					return count.getText().length() != 0;
				}
	        }));
		}
		catch(Exception e) {
			System.out.println("catch in mini cart");
			e.printStackTrace();
		}
		
		try {
		
		click(btnMiniCart);
		new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains
				(By.xpath("//div[@data-block='minicart']"), "class", "active")));
		new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.visibilityOfElementLocated(By.id("minicart-content-wrapper")));
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("mini cart button not found");
			
		}
	return this;
	}
	
	@FindBy(css=".minicart-wrapper .viewcart, a.viewcart")
	WebElement btnViewBasket;
	public CartSummaryPage clickBtnViewBasket() {
		
		try {
			new WebDriverWait(driver.get(),reducedTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf
					(driver.get().findElement(By.xpath("//div[@id='ui-id-1'][contains(@style,'block')]")))));
			new WebDriverWait(driver.get(),reducedTimeout).until(ExpectedConditions.visibilityOf(btnViewBasket));
			new WebDriverWait(driver.get(),reducedTimeout).until(ExpectedConditions.elementToBeClickable(btnViewBasket));
		}
		catch(Exception e) {
			System.out.println("view basket click issue");
		}
		click(btnViewBasket);
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
