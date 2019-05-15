package com.expensetracker.utility;

import java.io.IOException;

import org.testng.Assert;

import com.expensetracker.base.BaseClass;

public class AssertionUtil extends BaseClass {
	public static void assertNotEquals(String actual, String expected, String errorMessage) throws IOException {
		if (actual.equals(expected)) {
			ExtentReportLog.testCaseFailWithImage(errorMessage);
		}
		Assert.assertNotEquals(actual, expected);
	}

	public static void assertEquals(String actual, String expected, String infoMessage, String errorMessage)
			throws IOException {
		if (actual.equals(expected)) {
			ExtentReportLog.testCasePass(infoMessage);
		} else {
			ExtentReportLog.testCaseFailWithImage(errorMessage);
		}
		Assert.assertEquals(actual, expected);
	}

	public static void assertEqualsWithImage(String actual, String expected, String infoMessage, String errorMessage)
			throws IOException {

		if (actual.equals(expected)) {
			ExtentReportLog.testCaseInfoWithImage(infoMessage);
		} else {
			ExtentReportLog.testCaseFailWithImage(errorMessage);
		}
		Assert.assertEquals(actual, expected);
	}

	public static void assertFail(String errorMessage) throws IOException {
		ExtentReportLog.testCaseFailWithImage(errorMessage);
		Assert.fail(errorMessage);
	}

	public static void assertPass(String message) throws IOException {
		ExtentReportLog.testCasePassWithImage(message);
		Assert.assertEquals(true, true);
	}

	public static void assertInfo(String errorMessage) throws IOException {
		ExtentReportLog.testCaseInfoWithImage(errorMessage);
	}

	public static void assertEquals(int actual, int expected, String infoMessage, String errorMessage) throws IOException {

		if (actual==expected) {
			ExtentReportLog.testCaseInfoWithImage(infoMessage);
		} else {
			ExtentReportLog.testCaseFailWithImage(errorMessage);
		}
		Assert.assertEquals(actual, expected);
		
	}
}
