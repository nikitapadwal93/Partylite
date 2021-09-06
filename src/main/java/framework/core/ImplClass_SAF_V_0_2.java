package framework.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.utils.Reports;

public class ImplClass_SAF_V_0_2 extends Reports implements Interf_SAF_V0_1

{
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public Properties p;
	public static Map<String, Properties> markets = new LinkedHashMap<String, Properties>();
	public static ConcurrentHashMap<String, Properties> conMarkets = new ConcurrentHashMap<String, Properties>(markets);
	
	//public Properties language; //****Removed language property file declaration on 28thMay for parallel multi-site Eshop
	
	public ImplClass_SAF_V_0_2() throws FileNotFoundException, IOException {
		
		p = new Properties();
		p.load(new FileInputStream("./Properties/App.properties"));
		
		
		//Properties language = new Properties(); //****Removed language property file declaration on 28thMay for parallel multi-site Eshop
	//	language.load(new FileInputStream("./Properties/"+p.getProperty("country")+".properties"));
		//System.out.println("I am the constructor of implClass, called well in advance, the first before any other code.");
	}
	
	@Override
	public void acceptAlert() {
		try
		{
			driver.get().switchTo().alert().accept();	
			reportStep("Alert accepted.","pass");
		} catch(NoAlertPresentException e)
		{
			reportStep("Alert is not present but tried interacting with it.","Warning");
			throw new RuntimeException("Alert not present, not able to interact.");
		}
		
		
	}

	@Override
	public void click(WebElement wb) {
		
		try{
			/*WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(wb));
			*/
			wb.click();
			reportStep("Click action on " + wb  + " performed successfully","pass");
		} catch(NoSuchElementException e) {
			reportStep("Click action " + wb + "not done due to no such element", "Warning");
			throw new RuntimeException("click action failed");
		}
	catch( org.openqa.selenium.ElementNotInteractableException e1) {
		reportStep("Click action " + wb + "not done due to element not interactable.", "Warning");
		throw new RuntimeException("click action failed");
		
	}
		
		finally {
			//takeSnap();
		}
		
	}

	@Override
	public void closeAllBrowsers() {
		driver.get().quit();
		reportStep("All browser instances got successfully closed", "info");  //commented jun18 to avoid ConcurrentModificationException
	}

	@Override
	public void closeBrowser() {
	driver.get().close();
		reportStep("The specific browser instance got closed successfully.", "info");
	}

	@Override
	public void clear(WebElement wb) {
		WebDriverWait wait = new WebDriverWait(driver.get(), 20);
		wait.until(ExpectedConditions.elementToBeClickable(wb));
		wb.clear();
		reportStep("Clear action on " + wb.toString() + " performed successfully","pass");
	}

	@Override
	public void dismissAlert() {
		try
		{
			driver.get().switchTo().alert().dismiss();
			reportStep("Alert dismissed successfully.","pass");
		} catch(NoAlertPresentException e)
		{
			reportStep("Alert is not present but tried dismissing it.","Warning");
			throw new RuntimeException();
		}
		
		
	}

	@Override
	public String getAlertText() {
		String text;
		try
		{
			text = driver.get().switchTo().alert().getText();
			reportStep("Alert text " + text + " retrieved successfully", "pass");
		} catch(NoAlertPresentException e)
		{
			reportStep("Alert text could not be retrieved", "Warning");
			throw new RuntimeException();
		}
		return text;
		
	}

	@Override
	public String getText(WebElement wb) {
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.elementSelectionStateToBe(wb, true));
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String text = wb.getText();
		reportStep("Text " + text + " retrieved successfully", "pass");
		return text;
	}

	@Override
	public WebElement locateElement(String s) {
		WebElement wb = driver.get().findElementById(s);
		reportStep("Text from - " + wb + " retrieved successfully", "pass");
		return wb;
	}

	@Override
	public WebElement locateElement(String locatorType, String locator) {
		switch(locatorType)
		{
		case "id":
				reportStep("Trying to identify element with id "+ "::" + locator ,"info");
				return driver.get().findElementById(locator);
				
		case "name":
			reportStep("Trying to identify element with name "+ "::" + locator ,"info");
				return driver.get().findElementByName(locator);
		
		case "linkText":
			reportStep("Trying to identify element with linkText "+ "::" + locator ,"info");
			return driver.get().findElementByLinkText(locator);
		
		case "tagName":
			reportStep("Trying to identify element with tagName "+ "::" + locator ,"info");
			return driver.get().findElementByTagName(locator);		
		
		case "className":
			reportStep("Trying to identify element with classname "+ "::" + locator ,"info");
			return driver.get().findElementByClassName(locator);
			
		case "partialLinkText":
			reportStep("Trying to identify element with partialLinkText "+ "::" + locator ,"info");
			return driver.get().findElementByPartialLinkText(locator);
		
		case "xpath":
			reportStep("Trying to identify element with xpath "+ "::" + locator ,"info");
			return driver.get().findElementByXPath(locator);
		
		case "cssSelector":
			reportStep("Trying to identify element with cssSelector "+ "::" + locator ,"info");
			return driver.get().findElementByCssSelector(locator);
			
		default:
				reportStep("Incorrect locator type or locator string", "Warning");
				throw new RuntimeException("Locate element method encountered exception");
				
		}
		
		
	}

	@Override
	public void selectDropDownUsingIndex(WebElement wb, int i) {
		Select sel = new Select(wb);
		sel.selectByIndex(i);
		reportStep("Dropdown value selected by Index", "pass");
				
			}

	@Override
	public void selectDropDownUsingText(WebElement wb, String s) {
		Select sel = new Select(wb);
		sel.selectByVisibleText(s);
		reportStep("Dropdown value selected by Text", "pass");
	}

	@Override
	public void selectDropDownUsingValue(WebElement wb, String s) {
		Select sel = new Select(wb);
		sel.selectByValue(s);
		reportStep("Dropdown value selected by value", "pass");
	}

	@Override
	public void startApp(String browser, String url, String HeadlessMode) {
		
		if(browser.equalsIgnoreCase("chrome"))
			
		{
			
			switch(System.getProperty("os.name")) {
			
			case "Mac OS X":
				
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
				
				break;
			case "Linux":
				System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");  //path updated for jenkins run on linux server - 23Sep2019
				break;
				
			case "Windows 10":
				System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
				break;
				
			default:
				throw new RuntimeException("Unknown Operating System or chrome driver not configured yet for this OS.");
			}
			
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--disable-notifications");
			
			op.addArguments("--no-sandbox");
			op.addArguments("--start-maximized");
			op.addArguments("--disable-infobars");
			op.addArguments("--disable-extensions");
			
			//Updated on 18-th September 2019
			
			op.addArguments("--window-size=1440,900");  // can this size be used ????
			op.addArguments("--force-device-scale-factor=1");  // Added to circumvent Jenkins - timed out receiving message from renderer: 10.000
			//op.addArguments("--disable-features=VizDisplayCompositor"); did not work alone by itself when set on 
			// end of 18th September 2019 update
			
			if(HeadlessMode.equalsIgnoreCase("true")) {
				
			op.addArguments("--headless");  //--> enabling headless mode leads to error in CreateExistingUserAccount test.
			//op.addArguments("--window-size=1440x900"); // to check again for different window sizes.
			}
			driver.set(new ChromeDriver(op));
			driver.get();
			
			reportStep("Launched chrome browser successfully", "info");
			
			
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "./drivers/MicrosoftWebDriver.exe");
			driver.set( new EdgeDriver());
			driver.get();
			reportStep("Launched edge browser successfully", "info");
			
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			/*System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
			driver = new FirefoxDriver();
			reportStep("Launched firefox browser successfully", "info");
			*/
		
			FirefoxOptions opF = new FirefoxOptions();
			
			if(HeadlessMode.equalsIgnoreCase("true")) {
			opF.setHeadless(true);
			
			}
			switch(System.getProperty("os.name")) {
			
			case "Mac OS X":
				
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
				
				break;
			case "Linux":
				System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
				break;
			default:
				throw new RuntimeException("Unknown Operating System or chrome driver not configured yet for this OS.");
			}
			
			driver.set( new FirefoxDriver(opF));
			driver.get();
			
			reportStep("Launched firefox browser successfully", "info");
		
		}
		
		else if(browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
			driver.set(new InternetExplorerDriver());
			driver.get();
			reportStep("Launched IE browser successfully", "info");
			
		}
		
		else if(browser.equalsIgnoreCase("safari"))
		{
			System.setProperty("webdriver.safari.driver", "./drivers/safaridriver");
			driver.set(new SafariDriver());
			driver.get();
			reportStep("Launched Safari browser successfully", "info");
			
		}
		else {
			//TO DO --- to call tcStatus() from ExtentReport class;
			reportStep("Unknown browser request, unable to process", "Warning");
			throw new RuntimeException("Browser instance could not be instantiated");
		}
		
		//18th September 2019 modifications
		
		driver.get().manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //wait updated to 12 sec
		if(HeadlessMode.equalsIgnoreCase("true")) {
			driver.get().manage().window().setSize(new Dimension(1440,900));  // 1440x900 party selection drop down issue - 1920x1080 ? possible previous values were 1325, 768
			//driver.get().manage().window().maximize(); on jenkins, this leads to more error.
		//End of 18th September 2019 modifications 
		
		}
		else {
			driver.get().manage().window().maximize();
			//driver.manage().window().setSize(new Dimension(1325, 768));
		}
		driver.get().get(url);
		
			URL urls = null;
			String msg = null;
				
		try {
			urls = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
			connection.connect();
			 msg = connection.getResponseMessage();
			connection.disconnect();
			
		//*******Updated to handle magento admin http responseMessage being NULL******** 
		try {
		if(msg.equalsIgnoreCase("OK")) {
			reportStep("Application is available for testing", "pass");
		}
		/*else {
			reportStep("Application is not available for testing", "Warning");
			throw new RuntimeException("Application down and unavailable");
		}*/
		}catch(NullPointerException e) {
			String msgCode = ""+ connection.getResponseCode();
			reportStep("Http response could not be retrieved, Code obtained" + msgCode,  "info");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//*******Updated to handle magento admin http responseMessage being NULL********
		}
		

	@Override
	public void switchToFrame(WebElement wb) {
		// TODO Auto-generated method stub
		try {
			driver.get().switchTo().frame(wb);	
			reportStep("Driver context switched to the frame", "info");
		} catch(NoSuchFrameException e) {
			reportStep("Driver context could not be switched to the frame", "Warning");
			throw new RuntimeException("Switching to frame failed");
			
		}
		
		
	}

	@Override
	public void switchToWindow(String s) {
				try {
					driver.get().switchTo().window(s);
			reportStep("Driver context switched to the window", "info");
		} catch(NoSuchFrameException e) {
			reportStep("Driver context could not be switched to the window", "Warning");
			throw new RuntimeException("Switching to window failed");
		}
		
	}

	@Override
	public void switchToAlert() {
		driver.get().switchTo().alert();
		reportStep("Driver context switched to the alert", "info");
	}
	
	@Override
	public String takeSnap() {
		long number = (long) Math.floor(Math.random() *  900000000L) + 10000000L; 
		String Base64StringofScreenshot="";
		try {
			try {
				File imgLocation = new File("./reports/images/"+number+".png");
				FileUtils.copyFile(driver.get().getScreenshotAs(OutputType.FILE) , imgLocation);
				byte[] fileContent = FileUtils.readFileToByteArray(imgLocation);  // Added Apr 29
				Base64StringofScreenshot = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent); // Added Apr 29
							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Base64StringofScreenshot;
		} catch (RuntimeException e) {
			reportStep("Test application initiation encountered an error and snapshot could not be taken", "Warning");
			throw new RuntimeException("Application not initiated correctly, snapshot failed");
		}
		
	}

		
	

	@Override
	public void type(WebElement wb, String s) {
		/*WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(wb));*/
		wb.sendKeys(s);
		reportStep("Text correctly entered into " + wb.toString() + " with value " + s, "pass");
	}

	@Override
	public boolean verifyDisplayed(WebElement wb) {
		
		try
		{
			WebDriverWait waitDisplay = new WebDriverWait(driver.get(), 4);
			waitDisplay.until(ExpectedConditions.visibilityOf(wb));
			
			if(wb.isDisplayed())
			{
				reportStep(wb + " is displayed correctly", "pass");
				return true;
			}
			else {
				reportStep(wb + " is not displayed", "Warning");
				return false;
			}
			
		} catch(NoSuchElementException e)
		{
			e.getMessage();
		}
		return false;
		}

	@Override
	public boolean verifyExactAttribute(WebElement wb, String attributeName, String attriValue) {
		
		return false;
	}

	@Override
	public boolean verifyExactText(WebElement wb, String x) {
		x = x.toLowerCase();  //multi-lingual support
		String txtWB = getText(wb).toLowerCase();
		if(txtWB.contains(x))
		 {
			reportStep("Text is displayed as expected - " + x, "pass");
			return true;}
		else
		{
			reportStep("Text is displayed incorrectly - " + x, "Warning");
			return false;
		}
	}
	
	@Override
	public String getAttribute(WebElement wb, String attribute) {
		return wb.getAttribute(attribute);
	}

	@Override
	public boolean verifyPartialAttribute(WebElement wb, String s, String t) {
		if(getAttribute(wb, s).contains(t))
			return true;
		else
		return false;
	}

	@Override
	public boolean verifyPartialText(WebElement wb, String s) {
		if(getText(wb).contains(s)) {
			reportStep(s +  " is displayed as expected", "pass");
		
			return true;
			}
		
		else {
			reportStep("Text displayed is incorrect - " + s, "Warning");
		
		return false; 
		}
	}

	@Override
	public boolean verifySelected(WebElement wb) {
		try
		{
			if(wb.isSelected())
			{
				return true;
			}
			else {
				return false;
			}
			
		} catch(NoSuchElementException e)
		{
			e.getMessage();
		}
		return false;
	}

	@Override
	public boolean verifyTitle(String s) {
		if(s.equalsIgnoreCase(driver.get().getTitle()))
		
			return true;
		else
			return false;
	}

	@Override
	public boolean verifyURL(Window w, String s) {
		if(s.equalsIgnoreCase(driver.get().getCurrentUrl()))
			
			return true;
		else
			return false;
	}

	@Override
	public boolean verifyEnabled(WebElement wb) {
		try
		{
			if(wb.isEnabled())
			{
				reportStep("Element "+wb+" is enabled", "pass");
				return true;
			}
			else {
				reportStep("Element "+wb+" is not enabled", "warning");
				return false;
			}
			
		} catch(NoSuchElementException e)
		{
			e.getMessage();
			reportStep("Element "+wb+" is not enabled", "warning");
		}
		return false;
	}

}
