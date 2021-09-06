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

import com.aventstack.extentreports.Status;

import framework.core.ImplClass_SAF_V_0_2;

public class Class_initUSFakeAddress extends ImplClass_SAF_V_0_2 {
	public String ConsultantName =null;
	public int waitTimeout = Integer.parseInt(p.getProperty("waitTimeout")); 
	public int reducedTimeout= Integer.parseInt(p.getProperty("waitTimeout"))-Integer.parseInt(p.getProperty("reduceWait"));
	
	
//	public FileInputStream fisLang;  //commented out here and declared globally above for  parallel multi-site Eshop
//	public Properties countryList = new Properties();
	
	
	
	public Class_initUSFakeAddress() throws FileNotFoundException, IOException {
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
    
	@BeforeMethod(groups= {"Common"})
	public void beforeMethod(){
		startTestCase(testNodes)
		.assignCategory(category)
		.assignAuthor(authors);
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("./Properties/USAddress.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties p = new Properties();
		try {
			p.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startApp(p.getProperty("browserApp"), p.getProperty("URL"), p.getProperty("HeadlessMode"));
	}

	@AfterSuite(groups= {"Common"})
	public void afterSuite(){
		endResult();
	}

	@AfterMethod(groups= {"Common"})
	public void afterMethod(){
		
		closeAllBrowsers();
	}


	
//	@Parameters("paramCountry")
//	@BeforeTest(groups= {"Common"})
//	public void populatePropCountry(String paramCountry) {
//		System.out.println("The actual country parameter passed from xml is " + paramCountry);
//		//category = "Smokes_"+paramCountry; ?? to modify at each
//		try {
//			fisLang = new FileInputStream("./Properties/"+paramCountry+".properties");
//			countryList.load(fisLang);
//			markets.put(paramCountry, countryList);
//			System.out.println("initMailinator - Map now has these many property files - " + markets.size());
//			System.out.println(markets.get(paramCountry).getProperty("propURL"));
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	@AfterMethod (groups= {"Common"})
	public void markExtentReportWithTestNGFailStatus(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
			Test.get().log(Status.FAIL,"Exception has occurred on re-execution, marking it as fail.");

		}
			}
}

