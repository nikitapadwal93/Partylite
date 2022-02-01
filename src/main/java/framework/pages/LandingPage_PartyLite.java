package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;
//import framework.utils.NameOfTestListener;


//@Listeners(framework.utils.NameOfTestListener.class)

public class LandingPage_PartyLite extends Class_initEcomPrac {
	String genEmail= null;
    int a=1;
	public LandingPage_PartyLite(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;

	}

	@FindBy(xpath="//div[@class='newsletter-subscribe-modal']")
	//"//(//button[@class='action-close'])[2]")
	WebElement SignUpToNewsLetterText;
	public LandingPage_PartyLite closeDialogSignUpNewsLetter() {

		//code for security error
		/*try {
				driver.get().findElementById("details-button").click();
			}
			catch(Exception e) {
				System.out.println("No security error");
			}

			try {
				driver.get().findElementById("proceed-link").click();
			}
			catch(Exception e) {
				System.out.println("homepage shown without error");
			}*/

		try {
			System.out.println(driver.get().getCurrentUrl());
			reportStep(driver.get().getCurrentUrl(), "pass");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(SignUpToNewsLetterText)));
			WebElement closeSignUpDialog = driver.get().findElementByXPath("//button[@data-role='action']");
			driver.get().executeScript("arguments[0].click()", closeSignUpDialog);
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.invisibilityOf(SignUpToNewsLetterText));
			System.out.println("Newsletter closed in "+a+"attempt");
			reportStep("Newsletter closed in "+a+"attempt","info");
			
		} catch(Exception e) {
			System.out.println("Newsletter subscription missing" +a+"time");
			reportStep("Newsletter subscription missing" +a+"time","info");
			driver.get().navigate().refresh();
			a++;
			/*try {
				System.out.println("Body container modal check");
				WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
						refreshed(ExpectedConditions.attributeContains(bodyContainer, "class", "_has-modal")));
				new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.stalenessOf(SignUpToNewsLetterText));
			}
			catch(Exception e1) {
				System.out.println("Body container modal check failed");
			}*/ //commenting to check May 12
			closeDialogSignUpNewsLetter();
			/*if(!(driver.get().getCurrentUrl().contains("pexdev")))
			closeDialogSignUpNewsLetter();*/ // - commenting for pexdev enabled newsletter subscription 
		}

		return this;
	}	
	
	@FindBy(id = "ltkpopup-close-button")
	WebElement ListrakNewsLetterText;
	public LandingPage_PartyLite closeListrakNewsLetter() {
		try {
			click(ListrakNewsLetterText);
			reportStep("Newsletter closed in "+a+"st attempt","info");
			
		} catch(Exception e) {
			System.out.println("Newsletter subscription missing");
			reportStep("Newsletter subscription missing","info");
			
		}

		return this;
	}

	@FindBy(xpath = "//input[@id='ltkpopup-email']")
	WebElement NewsLetterEmail;
	public LandingPage_PartyLite enterNewsletterEmail(String emailsubscribe) {
		try {
			long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
			genEmail= emailsubscribe+randomNum+"@mailinator.com";
			type(NewsLetterEmail, genEmail);
			System.out.println("email entered in newsletter subscription is"+ genEmail);
			reportStep("Newsletter email entered as "+genEmail+"","info");
			
		} catch(Exception e) {
			System.out.println("Newsletter email textbox not found");
			reportStep("Newsletter subscription email textbox missing","info");
		}

		return this;
	}
	
	@FindBy(xpath = "//input[@id='fname']")
	WebElement NewsLetterName;
	public LandingPage_PartyLite enterNewsletterName(String subscribername) {
		try {
			type(NewsLetterName, subscribername);
			reportStep("Newsletter email entered as "+subscribername+"","info");
			
		} catch(Exception e) {
			System.out.println("Newsletter name textbox not found");
			reportStep("Newsletter subscription email textbox missing","info");
		}

		return this;
	}

	@FindBy(xpath = "//input[@id='ltkpopup-submit']")
	WebElement NewsLetterbuttonClick;
	public LandingPage_PartyLite clickNewsletterbtn() {
		click(NewsLetterbuttonClick);
		return this;
	}
	
	
	@FindBy(xpath="((//button[@data-role='action']/span)[2]")
	WebElement Continue;
	public LandingPage_PartyLite clickContinue()
	{
		click(Continue);
		return this;
	}
	
	@CacheLookup
	@FindBy(id="btn-cookie-allow")
	WebElement btnContinue;
	public LandingPage_PartyLite clickToAllowCookie() {

		//new code to close newsletter popup - added on Nov 5
		/*driver.get().navigate().refresh();

		try {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
			WebElement closeSignUpDialog = driver.get().findElementByXPath("(//button[@class='action-close'])[2]");
			driver.get().executeScript("arguments[0].click()", closeSignUpDialog);
			System.out.println("Newsletter closed in third attempt after second refresh");
			reportStep("Newsletter closed in third attempt after second refresh","info");
			return this;
			} catch(Exception e) {
				System.out.println("Newsletter closed before second refresh");
			}*/

		try
		{
			WebElement modalCurtain = driver.get().findElementByXPath("//aside");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalCurtain));
		}catch (Exception e3) {

			try {
				WebElement btnAcceptCookie = driver.get().findElementByXPath("//button[@id='btn-cookie-allow']");
				reportStep("About to click on the continue button","info");
				try {
					WebElement modalOverlay = driver.get().findElementByXPath("//div[@class='modals-overlay']");
					new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalOverlay));
				}catch (Exception e) {

					driver.get().executeScript("arguments[0].click()", btnAcceptCookie);
					return this;
				}
			} catch (org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e) {
				reportStep("Button to allow cookie usage timed out or missing ","info");
				return this;
			}
		}
		try {
			WebElement btnAcceptCookie = driver.get().findElementByXPath("//button[@id='btn-cookie-allow']");
			reportStep("To close the continue button","info");
			try {
				WebElement modalOverlay = driver.get().findElementByXPath("//div[@class='modals-overlay']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalOverlay));
				driver.get().executeScript("arguments[0].click()", btnAcceptCookie);
				return this;
			}catch (Exception e) {
				System.out.println("Cookie status?");
				try {
					new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.elementToBeClickable(btnAcceptCookie));
					driver.get().executeScript("arguments[0].click()", btnAcceptCookie);
					return this;
				}
				catch(Exception e1) {
					return this;
				}
			}

		} catch (org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e) {
			reportStep("Button to allow cookie usage timed out or missing ","info");
			return this;
		}

	}



	@CacheLookup
	@FindBy(id="search-action")
	WebElement btnSearch;
	public LandingPage_PartyLite clickBtnSearch() {
		click(btnSearch);
		return this;
	}

	@CacheLookup
	@FindBy(id="search")
	WebElement txtBoxSearch;
	public LandingPage_PartyLite enterSKU(String SKU) {  //due to updated code deployed
		try {
			type(txtBoxSearch,SKU);
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated
					(By.xpath("//*[@id=\\\"search_mini_form\\\"]/div[2]/button")));
			//new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".sku span"), SKU)));
			System.out.println("SKU enter success");
		} catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.ElementNotVisibleException | org.openqa.selenium.TimeoutException e) {
			try {
				WebElement searchProduct = driver.get().findElementById("search");
				searchProduct.clear();
			} catch(org.openqa.selenium.NoSuchCookieException e1) {
				reportStep("Product search was not successful.","warning");
			}
			type(txtBoxSearch,SKU);
			return this;
		}
		return this;
	}

	@CacheLookup
	@FindBy(xpath="//*[@id=\"search_mini_form\"]/div[2]/button")
	WebElement clickSearch;
	public LandingPage_PartyLite clickSearchButton() {
		click(clickSearch);
		return this;
	}

	@CacheLookup
	@FindBy(xpath="//*[@class='mst-product-image-wrapper']")
	//@FindBy(xpath="//*[@id=\"mana_ajax_wrapper_search_result\"]/div[3]/div[2]/ol/li/div/a/span/span/img")
	//*[@id="mana_ajax_wrapper_search_result"]/div[3]/div[2]/ol/li/div/a/span/span/img
	WebElement linkProductImage;
	public ProductDescriptionPage clickProductImage() {

		try {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(linkProductImage)));
			click(linkProductImage);
			try {
				return new ProductDescriptionPage(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (org.openqa.selenium.TimeoutException |org.openqa.selenium.NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportStep("The entered SKU did not result in display of product image ", "info");
		} 
		return null;
	}
	
	@FindBy(xpath="(//button[@data-action='customer-menu-toggle'])[1]")
	WebElement linkMenuExpansion;
	public LandingPage_PartyLite clickMenuExpansionLink() {
		click(linkMenuExpansion);
		return this;
	}

	@FindBy(xpath="//li[@class='authorization-link']/a")
	WebElement linkSignIn;
	public CustomerLoginPage clickSignin() {
		try {
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.elementToBeClickable(linkSignIn)));
		} catch(org.openqa.selenium.TimeoutException e) {
			//do nothing
		}
		try{
			linkSignIn.click();
		} catch(org.openqa.selenium.ElementNotInteractableException e2) {
			System.out.println("Clicking on signin link did not work as expected, trying again");
			click(linkSignIn);
		}
		try {
			return new CustomerLoginPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@CacheLookup
	@FindBy(xpath="(//a[@class='consultant-find'])[1]")
	WebElement linkFindConsultant;
	public FindYourConsultant clickFindConsultant() {
		try {
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.elementToBeClickable(linkFindConsultant)));

			click(linkFindConsultant);

			try {
				return new FindYourConsultant(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch(org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.TimeoutException e) {
			try {
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.elementToBeClickable(linkFindConsultant));


				click(linkFindConsultant);
				WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
						refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
				try {
					return new FindYourConsultant(driver, Test);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch(org.openqa.selenium.TimeoutException e1) {
				click(linkFindConsultant);

				try {
					return new FindYourConsultant(driver, Test);
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	@FindBy(xpath="//input[@name='email']")
	WebElement tbEmailSubscriptioninDialog;
	public LandingPage_PartyLite enterEmailAddressinDialog(String ExistingUserEmail) {
		verifyDisplayed(SignUpToNewsLetterText);
		click(tbEmailSubscriptioninDialog);
		type(tbEmailSubscriptioninDialog, ExistingUserEmail);
		return this;
	}


	@FindBy(xpath="//button[@title='Subscribe']")
	WebElement btnSubscribeEmailinDialog;
	public LandingPage_PartyLite clickSubscribeinDialog() {
		click(btnSubscribeEmailinDialog);
		return this;
	}

	@FindBy(xpath="(//button[@class='action-close'])[3]")
	WebElement btnContinueShopping;
	public LandingPage_PartyLite clickContinueShopping() {
		click(btnContinueShopping);
		return this;
	}

	@FindBy(xpath="//*[@id='newsletter']")
	WebElement newsletterFooter;
	public LandingPage_PartyLite enterEmailtoSubscribeFooter(String email) {
		type(newsletterFooter, email);
		return this;

	}
	
	@FindBy(xpath="//*[@id='newsletter-validate-detail']/div/div/div[1]/button")
	WebElement clickSubscribefromFooter;
	public LandingPage_PartyLite clickFooterSubscribeButton() {
		click(clickSubscribefromFooter);
		return this;

	}
	
	@FindBy(xpath="(//*[@id=\"id50L0qXaW\"])[1]")
	WebElement clickDashboard;
	public LandingPage_PartyLite clickOnDashboard() {
		click(clickDashboard);
		return this;

	}
	
	@FindAll({@FindBy(css="div[class='customer-menu'][aria-hidden='false'] a")})
	List<WebElement> my_account_dropmenu_links;
	public LandingPage_PartyLite menuLinksDropdown(String Country) {
		List<String> text,links;
		text=new ArrayList<String>();
		links=new ArrayList<String>();
		
		if(my_account_dropmenu_links.size()!=7)
			reportStep("my account menu dropdown links size verification fail", "fail");
		
		for (WebElement webElement : my_account_dropmenu_links) {
			System.out.println(webElement.getText()+": "+webElement.getAttribute("href"));
			text.add(webElement.getText());
			links.add(webElement.getAttribute("href"));
		}
		String[] url_matches=new String[] {"/wishlist","/customer/account/","/customer/account/edit","/customer/account/logout","consultant/consultant","pslogin/account/view/","sales/order/history/"};
		for (String string : url_matches) {
			if(links.contains(string))
			{
				System.out.println(string+" Link matched");
				reportStep(string+" Link matched", "pass");
			}
			else
			{
				System.out.println(string+" Link match fail");
				reportStep(string+" Link match fail", "fail");
			}
		}
		String[] text_matches=new String[] {markets.get(Country).getProperty("wishlist"),markets.get(Country).getProperty("Dashboard"),markets.get(Country).getProperty("account_info"),markets.get(Country).getProperty("orders"),markets.get(Country).getProperty("my_consultant"),markets.get(Country).getProperty("product_reviews"),markets.get(Country).getProperty("sign_out")};
		for (String string : text_matches) {
			if(text.contains(string))
			{
				System.out.println(string+" text matched");
				reportStep(string+" text matched", "pass");
			}
			else
			{
				System.out.println(string+" text match fail");
				reportStep(string+" text match fail", "fail");
			}
		}
		WebElement customer_name=driver.get().findElementByCssSelector("div[class='customer-menu'][aria-hidden='false'] .customer-info-name");
		verifyDisplayed(customer_name);
		WebElement customer_email=driver.get().findElementByCssSelector("div[class='customer-menu'][aria-hidden='false'] .customer-info-email");
		verifyDisplayed(customer_email);
		WebElement my_account_button=driver.get().findElementByCssSelector("div[class='customer-menu'][aria-hidden='false'] .customer-my-account-button");
		verifyDisplayed(my_account_button);
		return this;
	}
	
	public LandingPage_PartyLite loaderCheck() {
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


	@CacheLookup
	@FindBy(xpath="//label[@for='address_format_0']")
	WebElement selectAddress_Format;
	public LandingPage_PartyLite selectAddressFormat() {
		loaderCheck();
		bodyContainer();
		try {
			loaderCheck();
			click(selectAddress_Format);
			System.out.println("Address format clicked");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		loaderCheck();
		bodyContainer();
		return this;

	}
	
	@FindBy(xpath="//*[@id=\"block-collapsible-nav\"]/ul/li[8]/a")
	WebElement clickAddressBook;
	public LandingPage_PartyLite clickAddressBookLink() {
		click(clickAddressBook);
		return this;
	}
	
	@FindBy(xpath="//button[@class='action primary add']")
	WebElement clickAddressNewAddress;
	public LandingPage_PartyLite clickNewAddressLink() {
		click(clickAddressNewAddress);
		return this;
	}
	
	@FindBy(xpath="//button[@class='action save primary']")
	WebElement saveAddress;
	public LandingPage_PartyLite clickSaveAddressLink() {
		click(saveAddress);
		return this;
	}
	
	@FindBy(id="street_1")
	WebElement streetAddress1;
	public LandingPage_PartyLite enterStreetAddress1(String address1) {
		type(streetAddress1, address1);
		return this;
	}
	
	@FindBy(id="city")
	WebElement addresscity;
	public LandingPage_PartyLite entercity(String city) {
		type(addresscity, city);
		return this;
	}
	
	@FindBy(id="postcode")
	WebElement postalcode;
	public LandingPage_PartyLite enterPostalCode(String zipcode) {
		type(postalcode, zipcode);
		return this;
	} 	
	
	@FindBy(id="telephone")
	WebElement telephone;
	public LandingPage_PartyLite enterTelephone(String phone) {
		type(telephone, phone);
		return this;
	} 	
	
	@FindBy(linkText="My Party Contacts")
	WebElement linkMyPartyContacts;
	public MyParties_ContactsPage clickLinkMyPartyContacts() {

		click(linkMyPartyContacts);
		try {
			return new MyParties_ContactsPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@FindBy(xpath="(//a[text()[contains(.,'Sign Out')]])[1]")
	WebElement linkSignOut;
	public LandingPage_PartyLite clickSignout() {
		click(linkSignOut);
		return this;
	}

	public LandingPage_PartyLite verifyPLTitle(String Country) {
		boolean TitleValidated = driver.get().getTitle().contains(markets.get(Country).getProperty("homepageTitle"));


		if(TitleValidated) {
			reportStep("Validated PartyLite home page title  - " + TitleValidated, "pass");
			return this;
		}
		else {
			reportStep("PartyLite website navigation could not be validated","warning");
			throw new RuntimeException("Title of PartyLite could not be validated to be \"Home page\".");
		} 
	}

	public CategoryPage_PartyLite gotoCategory(String category_url) {
		driver.get().navigate().to(category_url);
		try {
			return new CategoryPage_PartyLite(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}

	@FindBy(xpath="//span[@class='customer-name'][@tabindex='0']/span")
	WebElement linkMyAccount;
	public LandingPage_PartyLite verifyUserLogin(String Country, String ContentToVerify) {
		ContentToVerify = markets.get(Country).getProperty("loginVerify");
		System.out.println("Login status text read from property file is - " + ContentToVerify);
		verifyExactText(linkMyAccount, ContentToVerify);
		return this;
	}
	
	@FindBy(xpath="(//a[contains(@href,'customer/account/')])[1]")
	WebElement my_dashboard;
	public MyAccount_Dashboard clickMyDashboard() {
		click(my_dashboard);
		try {
			return new MyAccount_Dashboard(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(xpath="//div[@class='pslogin-share']//a[text()='✕']")
	WebElement dialogSocialNewAccountLink; 
	public LandingPage_PartyLite closeSocialNewAcctDialog() {
		try {

			new WebDriverWait(driver.get(), waitTimeout-reducedTimeout).until(ExpectedConditions.visibilityOf(dialogSocialNewAccountLink));
			click(dialogSocialNewAccountLink);

		} catch(NoSuchElementException | TimeoutException e){
			reportStep("Display of connect new social sites dialog missing","info");
			return this;
		}
		return this;
	}


	public CustomerNewAddressPage gotoCustomerAddress(String Country) {
		System.out.println("Value passed for country from test method is " + Country);
		System.out.println(markets.get(Country).toString());
		System.out.println(markets.get(Country).size());
		System.out.println("Property value read for propURL is " + markets.get(Country).getProperty("propURL"));
		driver.get().navigate().to(markets.get(Country).getProperty("propURL")+"/customer/address/new");

		try {
			return new CustomerNewAddressPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public MyParties_ContactsPage clickPartyContacts() {
		driver.get().navigate().to(p.getProperty("URL")+"/party/contact/dashboard");
		try {
			return new MyParties_ContactsPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@FindBy(css="li.parent")
	WebElement onlineshop;
	public LandingPage_PartyLite clickOnlineShop() {
		try {
			onlineshop.click();
		}
		catch(Exception e) {
			System.out.println("Element not interactable");
			driver.get().executeScript("arguments[0].click()", onlineshop);
		}

		return this;
	}
	
	public ProductDescriptionPage addRandomProducts(String product_count) {
		int i=0;
		while(i<Integer.parseInt(product_count))
		{
			try {
				clickOnlineShop();
				List<WebElement> category_list=driver.get().findElementsByCssSelector(".item-content1 .level2 a");
				int random=new Random().nextInt(category_list.size());
				System.out.println("category list size:"+category_list.size()+" random number : "+random);
				reportStep("category list size:"+category_list.size()+" random number : "+random, "pass");
				System.out.println(category_list.get(random).getText()+" category is selected");
				reportStep(category_list.get(random).getText()+" category is selected", "pass");
				driver.get().executeScript("arguments[0].click()", category_list.get(random));
				try {
					List<WebElement> product_list=driver.get().findElementsByClassName("product-image-photo");
					if(product_list.size()>0)
					{
						random=new Random().nextInt(product_list.size());
						System.out.println("product list size:"+product_list.size()+" random number : "+random);
						reportStep("product list size:"+product_list.size()+" random number : "+random, "pass");
						bodyContainer();
						driver.get().executeScript("arguments[0].click()", product_list.get(random));
						List<WebElement> qty_input=driver.get().findElementsById("qty");
						if(qty_input.size()>0)
						{
							ThreadLocalRandom random_thread = ThreadLocalRandom.current();
							random=random_thread.nextInt(1, 10);
							qty_input.get(0).sendKeys(Keys.BACK_SPACE);
							type(qty_input.get(0),Integer.toString(random));
							System.out.println(Integer.toString(random)+"qty");
							WebElement add_to_cart=driver.get().findElementById("product-addtocart-button");
							click(add_to_cart);
							bodyContainer();
							WebElement prod_add_msg=driver.get().findElementByCssSelector("div.message-success");
							verifyDisplayed(prod_add_msg);
							i++;
						}
						else
						{
							System.out.println("Out of stock product selected");
							reportStep("Out of stock product selected", "pass");
							continue;
						}
					}
					else
					{
						System.out.println("No Products in that category");
						reportStep("No Products in that category", "pass");
						continue;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		try {
			return new ProductDescriptionPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public LandingPage_PartyLite bodyContainer() {
		try {
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
					refreshed(ExpectedConditions.attributeContains(bodyContainer, "aria-busy", "false")));
			reportStep("Body container reached false", "info");
		}
		catch(Exception e) {
			System.out.println("Body container wait failed");
		}
		return this;
	}

	@FindBy(css=".level2 a")
	WebElement firstCategory;
	public CategoryPage_PartyLite clickFirstCategory() {
		try {
			firstCategory.click();
		}
		catch(Exception e) {
			System.out.println("Element not interactable");
			driver.get().executeScript("arguments[0].click()", firstCategory);
		}

		try {
			return new CategoryPage_PartyLite(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(xpath="//button[@class='action primary right' or @data-bind='click: sendData']")
	WebElement next_button;
	public LandingPage_PartyLite clickNextButton() {
		try {
			WebElement bodyContainer = driver.get().findElementByXPath("//body");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
			refreshed(ExpectedConditions.attributeContains(bodyContainer, "aria-busy", "false")));
			System.out.println("Aria busy false");
		}
		catch(Exception e) {
			System.out.println("Aria busy false not shown");
		}
		
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
			refreshed(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("loader present");
		}
		catch(Exception e) {
			System.out.println("No loader");
		}
		WebElement partymodal_heading=driver.get().findElementByClassName("party__modal-header");
		WebElement partymodal_close=driver.get().findElementById("partyCloseModal");
		verifyDisplayed(partymodal_close);
		verifyDisplayed(partymodal_heading);
		click(next_button);
		return this;
	}
	
	@FindBy(xpath="//a[@href='/our-mission']")
	WebElement our_mission;
	public LandingPage_PartyLite clickOurMission() {
		try {
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.refreshed
			(ExpectedConditions.elementToBeClickable(our_mission)));
		click(our_mission);
		System.out.println("our mission button clicked");
		}
		catch (Exception e) {
			System.out.println("Cannot find button our mission");
		}
		return this;
	}
	
	@FindBy(xpath="//a[@href='/affiliate-enrollment']")
	WebElement enroll_now;
	public OnlineEnrollmentLandingPage_PartyLite clickEnrollNow() {
		click(enroll_now);
		try {
			return new OnlineEnrollmentLandingPage_PartyLite(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(id = "switcher-language-trigger")
	WebElement EnglishBtn;

	public LandingPage_PartyLite englishCABtnClick() {
		click(EnglishBtn);
		return this;
	}

	@FindBy(xpath = "//*[@id='switcher-language']/div/ul/li/a")
	WebElement FranceCABtn;

	public LandingPage_PartyLite franceCABtnClick() {
		click(FranceCABtn);
		return this;
	}

	@FindBy(xpath = "//*[@id='fname']")
	WebElement ltkname;
	public LandingPage_PartyLite enterNamePopup() {
		try {
			new WebDriverWait(driver.get(), waitTimeout)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ltkpopup-wrapper']")));
			type(ltkname, "Saravanan");
			reportStep("Name entered in the popup field ", "PASS");
		}

		catch (org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e2) {
			reportStep("Listrak popup is not displayed ", "info");
			System.out.println("Listrak popup is not displayed");
		}

		return this;
	}
	
	@FindBy(xpath = "//*[@id='ltkpopup-email']")
	WebElement ltkemail;

	public LandingPage_PartyLite enterEmailPopup() {
		try {
			// new
			// WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ltklpopup-bg-c']")));
			new WebDriverWait(driver.get(), waitTimeout)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ltkpopup-wrapper']")));
			click(ltkemail);
			long randomNum = (long) Math.floor(Math.random() * 9000000000L) + 8888888888888L;
			random_oe_email = "Popup" + randomNum + "@mailinator.com";
			type(ltkemail, random_oe_email);
			reportStep("Entered the email-id= " + a + random_oe_email, "PASS");
		}

		catch (org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e1) {
			reportStep("Listrak popup is not displayed ", "info");
			System.out.println("Listrak popup is not displayed");
		}

		return this;
	}
	
	@FindBy(xpath = "//*[@id='ltkpopup-submit']")  //This method used to submit the subscription for Smoke-TC1
	WebElement ltksubmit;
	public LandingPage_PartyLite clickLtkSubmit() {
		click(ltksubmit);
		try {
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated
					(By.xpath("//div[@class='ltklpopup-bg-c']")));
			WebElement continuebtn = driver.get().findElementByXPath("//*[@id='ltkpopup-thanks']/div/a");
			click(continuebtn);	
			System.out.println("Submit the pop-up subscription");
		}
				
			catch (Exception e1) {
				System.out.println("Missed listrak Thank you page");
				reportStep("Missed listrak Thank you page", "info");
			}
			
		return this;
	}	
	
	@FindBy(xpath = "//*[@id='ltkpopup-submit']")
	WebElement ltksubmitpop;

	public LandingPage_PartyLite LtkSubmitpopup() {
		click(ltksubmitpop);
		try {
			new WebDriverWait(driver.get(), waitTimeout)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ltkpopup-wrapper']")));
			WebElement closebtn = driver.get().findElementById("ltkpopup-close-button");
			click(closebtn);
			System.out.println("Submit the pop-up subscription");
		}

		catch (Exception e1) {
			System.out.println("Missed listrak Thank you page");
			reportStep("Missed listrak Thank you page", "info");
		}

		return this;
	}
}




