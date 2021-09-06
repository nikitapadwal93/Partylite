package framework.core;

import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;

public interface Interf_SAF_V0_1 {

	void acceptAlert();

	void click(WebElement wb);
	
	void closeAllBrowsers();
	void closeBrowser();
	void clear(WebElement wb); //---. new functionality

	void dismissAlert();

	String getAlertText();
	String getText(WebElement wb);
	String getAttribute(WebElement wb, String s); // --> new functionality
	WebElement locateElement(String s);
	WebElement locateElement(String s, String t);

	void selectDropDownUsingIndex(WebElement wb, int i);
	void selectDropDownUsingText(WebElement wb, String s);
	void selectDropDownUsingValue(WebElement wb, String s);  // -- > new functionality

	void startApp(String s, String t, String h);
	void switchToFrame(WebElement wb);
	void switchToWindow(String s);
	void switchToAlert(); //  ---> Not required to be included in the framework.

	String takeSnap();
	void type(WebElement wb, String s);

	boolean verifyDisplayed(WebElement wb);
	
	boolean verifyExactAttribute(WebElement wb, String s, String t);
	
	boolean verifyExactText(WebElement wb, String x);
	
	boolean verifyPartialAttribute(WebElement wb, String s, String t);
	boolean verifyPartialText(WebElement wb, String s);
	boolean verifySelected(WebElement wb);
	boolean verifyTitle(String s);
	boolean verifyURL(Window w, String s); // --> new functionality
	// TO DO boolean verifyURL(frame f, String s);  //--> how to refer to frame ??? new functionality
	boolean verifyEnabled(WebElement wb); //--> new functionality

	// TO DO void wait(driver d) --> how to refer to driver ??? new functionality


}
