package framework.utils;


import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public abstract class Reports {
	public static ExtentHtmlReporter html;
	public static ExtentReports extentReport;
	public ExtentTest suiteTest;
	public ThreadLocal<ExtentTest> Test = new ThreadLocal<ExtentTest>();
	public String tcName, testNodes, tcDescription, category, authors, ExcelFileName, sheetName;


	public void initExtentReport() {
		html = new ExtentHtmlReporter("./reports/result.html");
		//html.setAppendExisting(false);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(html);
	}

	public ExtentTest startTestModule(String tcName, String tcDescription) {
		suiteTest = extentReport.createTest(tcName, tcDescription);
		return suiteTest;
	}

	public ExtentTest startTestCase(String testNodes) {
		
		Test.set(suiteTest.createNode(testNodes));
		return Test.get();
	}

	public abstract String takeSnap();

	public void reportStep(String desc, String status, boolean bSnap) {

		MediaEntityModelProvider img = null;
		if (bSnap && (status.equalsIgnoreCase("FAIL") || status.equalsIgnoreCase("warning"))) {
		//if (bSnap && !status.equalsIgnoreCase("INFO")) {
			String snapNumber ="";
			snapNumber = takeSnap();
			try {
				/*img = MediaEntityBuilder.createScreenCaptureFromPath("./../reports/images/" + snapNumber + ".png")
						.build();*/
				img=MediaEntityBuilder.createScreenCaptureFromBase64String(snapNumber).build(); // Added Apr 29	
			} catch (IOException e) {

			}
		} 
		if (status.equalsIgnoreCase("PASS")) {
			if(!(img==null)) {
			Test.get().pass(desc, img);}
			else
			Test.get().pass(desc);
		} else if (status.equalsIgnoreCase("FAIL")) {
			if(!(img==null)) {
				Test.get().fail(desc, img);
				throw new RuntimeException();}
				else {
					Test.get().fail(desc);
			//throw new RuntimeException();
		}}
		else if (status.equalsIgnoreCase("WARNING")) {
			if(!(img==null)) {
				Test.get().warning(desc, img);
				throw new RuntimeException();}
				else {
					Test.get().warning(desc);
				throw new RuntimeException();}
		} else if (status.equalsIgnoreCase("INFO")) {
			Test.get().info(desc);
		}}
	

	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}

	public void endResult() {
		extentReport.flush();
	}
	
	

}
