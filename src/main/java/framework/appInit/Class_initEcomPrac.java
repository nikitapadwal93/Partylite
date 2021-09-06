package framework.appInit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import framework.core.ImplClass_SAF_V_0_2;


public class Class_initEcomPrac extends ImplClass_SAF_V_0_2 {
	public String ConsultantName =null;
	public static String Guest_Email = null;//"codemdemo_one@mailinator.com";  //text hard-coded for outside order check
	public static String Party_Number;
	public static String Party_Url;
	public static String New_Guest_Email = null;
	public static String Virtual_New_Guest_Email = null;
	public static String Shareparty_guest_email_yes = null;
	public static String order_guest_email = null;
	public static String order_number = null;
	public static String random_oe_email = null;
	public static String random_oe_fname = null;
	public static String random_oe_lname = null;
	public static String random_oe_mname = null;
	public static String random_oe_pfname = null;
	public static String random_oe_ssn = null;
	public static String us_random_street = null;
	public static String us_random_city = null;
	public static String us_random_state = null;
	public static String us_random_zipcode = null;
	public static String us_random_mobnum = null;
	public static String us_random_phonenum = null;
	public static String ca_random_street = null;
	public static String ca_random_city = null;
	public static String ca_random_state = null;
	public static String ca_random_zipcode = null;
	public static String ca_random_mobnum = null;
	public static String ca_random_phonenum = null;
	public int waitTimeout = Integer.parseInt(p.getProperty("waitTimeout"));
	public int reducedTimeout= Integer.parseInt(p.getProperty("waitTimeout"))-Integer.parseInt(p.getProperty("reduceWait"));
	//public Properties language;  //removed static keyword for parallel multi-site Eshop on 28thMay
	//public static Map<String, Properties> markets = new LinkedHashMap<String, Properties>();  //****assigning value for language property file here on 28thMay for parallel multi-site Eshop
	//public FileInputStream fisLang; //  Declaring fisLang for parallel multi-site Eshop on 28thMay
	
	
	
	public FileInputStream fisLang;  //commented out here and declared globally above for  parallel multi-site Eshop
	public Properties countryList = new Properties();
	
	
	public Class_initEcomPrac() throws FileNotFoundException, IOException {
		super();
		
		// TODO Auto-generated constructor stub
	}

	@BeforeSuite(groups= {"Common"})
	public void beforeSuite(){
		initExtentReport();
	}

	@BeforeClass(groups= {"Common"})
	public void beforeClass(){		
		startTestModule(tcName, tcDescription);
		
	}
    
	
	@Parameters("paramURL")
	@BeforeMethod(groups= {"Common"})
	public void beforeMethod(String paramURL){
		try {
			startTestCase(testNodes)
			.assignCategory(category)
			.assignAuthor(authors);
			startApp(p.getProperty("browserApp"), paramURL, p.getProperty("HeadlessMode"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * Lets not override the declaration of P done in ImplClass 
		 * here in this Class_initEcomPrac again. Tested with code being removed and functioning.
		 */
		
//		FileInputStream fis = null;
//		
//		try {
//			fis = new FileInputStream("./Properties/App.properties");
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Properties p = new Properties();
		
//		try {
//			p.load(fis);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}

	@AfterSuite(groups= {"Common"})
	public void afterSuite(){
		endResult();
	}

	@AfterMethod(groups= {"Common"})
	public void afterMethod(){
		try {
			closeAllBrowsers();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void populatePropCountry(String paramCountry) {
		System.out.println("The actual country parameter passed from xml is " + paramCountry);
		//category = "Smokes_"+paramCountry; ?? to modify at each
		try {
			fisLang = new FileInputStream("./Properties/"+paramCountry+".properties");
			countryList.load(fisLang);
			markets.put(paramCountry, countryList);
			//System.out.println("initEcom - Map now has these many property files - " + markets.size());
			//System.out.println(markets.get(paramCountry).getProperty("propURL"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	

	@AfterMethod (groups= {"Common"})
	public void markExtentReportWithTestNGFailStatus(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
			Test.get().log(Status.FAIL,"Exception has occurred on re-execution, marking it as fail.");

		}
			}
	
	
}


	

