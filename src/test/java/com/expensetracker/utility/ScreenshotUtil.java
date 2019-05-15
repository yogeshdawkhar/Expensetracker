package com.expensetracker.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.expensetracker.base.BaseClass;

public class ScreenshotUtil extends BaseClass {

	public static void takeScreenshot(final String name) throws IOException {
		String screenshotDirectory = extentReportPathForStoringImage;
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, (new File(screenshotDirectory, String.format("%s.png", name))));
	}

	public static String createimagename() throws IOException {
		String imagename = UtililtyFunctions.generateRandomImageName(15);
		takeScreenshot(imagename);
		return imagename;
	}
}
