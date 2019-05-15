package com.expensetracker.utility;

import java.io.IOException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.expensetracker.base.BaseClass;

public class ExtentReportLog extends BaseClass {

	public static void testCaseFailWithImage(String message) throws IOException {
		extentTest.log(Status.FAIL, message, MediaEntityBuilder
				.createScreenCaptureFromPath(extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
				.build());
	}

	public static void testCaseFail(String message) {
		extentTest.log(Status.FAIL, MarkupHelper.createLabel(message + " - Test Case Failed", ExtentColor.RED));
	}

	public static void testCaseInfoWithImage(String message) throws IOException {
		extentTest.log(Status.INFO, message, MediaEntityBuilder
				.createScreenCaptureFromPath(extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
				.build());
	}

	public static void testCaseInfo(String message) {
		extentTest.log(Status.INFO, message);
	}

	public static void testCasePassWithImage(String message) throws IOException {
		extentTest.log(Status.PASS, MarkupHelper.createLabel(message + " Test Case PASSED", ExtentColor.GREEN));
		extentTest.log(Status.PASS, message, MediaEntityBuilder
				.createScreenCaptureFromPath(extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
				.build());
	}

	public static void testCasePass(String message) {
		extentTest.log(Status.PASS, MarkupHelper.createLabel(message + " Test Case PASSED", ExtentColor.GREEN));
	}

	public static void testCaseSkipWithImage(String message) throws IOException {
		extentTest.log(Status.SKIP, message, MediaEntityBuilder
				.createScreenCaptureFromPath(extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
				.build());
	}

	public static void testCaseSkip(String message) {
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(message + " - Test Case Skipped", ExtentColor.ORANGE));
	}

	public static void testStart(String message) {
		extentTest = extent.createTest(message);
		System.out.println("Test Execution started: " + message);
		extentTest.assignAuthor("Yogesh Dawkhar");
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("Extent Report");
		htmlReporter.config().setReportName("FriendsuranceExpensetracker");
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
}
