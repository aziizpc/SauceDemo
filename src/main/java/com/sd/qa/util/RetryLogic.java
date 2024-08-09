package com.sd.qa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer {

	int currentCount = 0;
	int retryLimit = 3;

	@Override
	public boolean retry(ITestResult result) {
		if (currentCount < retryLimit) {
			currentCount++;
			return true;
		}
		return false;
	}

}