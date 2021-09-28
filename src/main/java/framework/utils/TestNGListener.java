package framework.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class TestNGListener implements IRetryAnalyzer, IAnnotationTransformer {
		int maxCount =0;
	@Override
	public boolean retry(ITestResult result) {
		
		if(!(result.isSuccess()) && (maxCount<1)) {
			maxCount++;
			return true;
		}
		result.setStatus(ITestResult.FAILURE);
		return false;
	}
	@Override
	public void transform(ITestAnnotation annotation, 
			Class testClass, 
			Constructor testConstructor, 
			Method testMethod) {
		
		annotation.setRetryAnalyzer(this.getClass());
		
	}
	
	
	

}
