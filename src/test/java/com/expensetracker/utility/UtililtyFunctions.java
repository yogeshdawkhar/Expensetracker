package com.expensetracker.utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;

/**
 * Class for setting up the excel file for data driven approach
 */
public class UtililtyFunctions extends BaseClass {
	public String placeHolder = "placeholder";

	/**
	 * Constructor to initialize Utils class
	 * 
	 * @param WebDriver driver
	 */
	public UtililtyFunctions(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Instance method to scroll into the web page
	 * 
	 * @param WebElement
	 */
	public void scrollTillElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method will check if an element exists on the page
	 * 
	 * @param WebElement
	 * @return boolean
	 */
	public static boolean isElementExists(WebElement ele) {
		try {
			ele.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isCurrentUrlSame(String url) {
		try {
			if (driver.getCurrentUrl().equalsIgnoreCase(url))
				;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getPlaceHolder(WebElement ele) {
		return ele.getAttribute(placeHolder);
	}

	public void navigateBack() {
		driver.navigate().back();
		ExtentReportLog.testCaseInfo("navigated back.");
	}

	public void navigateForward() {
		driver.navigate().forward();
		ExtentReportLog.testCaseInfo("navigated forward.");
	}

	public static boolean isCurrentUrlDiffrent(String url) {
		return (!driver.getCurrentUrl().equalsIgnoreCase(url));
	}

	/**
	 * This method will get the placeholder attribute value of an element
	 * 
	 * @param WebElement
	 * @return String
	 */
	public static String getPlaceholderAttributeValue(WebElement ele) {
		return ele.getAttribute("placeholder");
	}

	/**
	 * This method will refresh the page
	 */
	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void openURL(String url) {
		driver.get(url);
		ExtentReportLog.testCaseInfo(ErrorMessageContants.ENTERED_URL + url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getLastTwoDigitofYear(String year) {
		String lastTwoDigits;
		if (year.length() > 2) {
			lastTwoDigits = year.substring(year.length() - 2);
		} else {
			lastTwoDigits = year;
		}
		return lastTwoDigits;
	}

	public String getDateInListExpenseFormat(String day, String month, String year) {
		return day + "." + month + "." + getLastTwoDigitofYear(year);
	}

	public String getFormatedAmount(String amount) {
		return amount.replace(".", ",") + " €";
	}

	public String getValue(WebElement ele) {
		return ele.getAttribute("value");
	}

	public void navigateToURL(String url) {
		driver.get(url);
		ExtentReportLog.testCaseInfo(ErrorMessageContants.UESR_REDIRECTED_TO + url);
	}

	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	public String clickButtonGetAlertText(WebElement ele) throws IOException {
		String alertText = null;
		try {
			ele.click();
			ExtentReportLog.testCaseInfoWithImage("Button clicked.");
		} catch (UnhandledAlertException f) {
			try {
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				alert.accept();
				return alertText;
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
		return alertText;
	}

	public String getAlertTextAndCancelAction(WebElement ele) throws IOException {
		String alertText = null;
		try {
			ele.click();
			ExtentReportLog.testCaseInfoWithImage("Button clicked.");
		} catch (UnhandledAlertException f) {
			try {
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				alert.dismiss();
				return alertText;
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
		return alertText;
	}

	public boolean isElementClickable(WebElement ele) throws IOException {
		boolean val = true;
		try {
			ele.sendKeys("ss");
			ExtentReportLog.testCaseInfoWithImage("Button clicked.");
		} catch (Exception f) {
			try {
				val = false;
				return val;
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
			val = false;
		}
		return val;
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();

	}

	public void sleepTime(long time) throws InterruptedException {
		Thread.sleep(time);
	}

	public String getValidationMessage(WebElement ele) {
		WebElement username = ele;
		return username.getAttribute("validationMessage");
	}

	public String getTitle(WebElement ele) {
		WebElement username = ele;
		return username.getAttribute("title");
	}

	public String getText(WebElement ele) {
		return ele.getText();
	}

	public int getTableRowCount() {
		return driver.findElements(By.xpath("/html/body/div/table/tbody/tr")).size();
	}

	public String getTableRowData(String rowNum, String colNum) {
		return driver.findElement(By.xpath("/html/body/div/table/tbody/tr[" + rowNum + "]/td[" + colNum + "]"))
				.getText();
	}

	/**
	 * Get the name of currently executed function
	 * 
	 * @return String
	 */
	public static String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[2].getClassName() + " -> "
				+ Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	/**
	 * @Purpose This method will generate a random string
	 * @param length --> the length of the random string we want to generate
	 * @return method will return a random String
	 */
	public static String generateRandomString(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
				+ new SimpleDateFormat("ddMMMyyHHmmssSSSZZZ").format(new Date());
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	public static String generateRandomCredentials1(int length) {
		String allowedChars = "abcdefghijkl" + "1234567890" + "!@#$%&" + "mnopqrstuvwxyz";
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	/**
	 * @Purpose This method will generate a random integer
	 * @param length --> the length of the random emails we want to generate
	 * @return method will return a random email String
	 */
	public static String generateRandomEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890"
				+ new SimpleDateFormat("ddMMMyyHHmmssSSSZZZ").format(new Date());
		return RandomStringUtils.random(length, allowedChars).substring(0, length) + "@mailinator.com";
	}

	public static String generateRandomStringWithAlphabetesOnly(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	public static String generateRandomStringwithalphabetandNumber(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890"
				+ new SimpleDateFormat("ddMMMyyHHmmssSSSZZZ").format(new Date());
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

	public static String generateRandomImageName(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890"
				+ new SimpleDateFormat("ddMMMyyHHmmssSSSZZZ").format(new Date());
		return RandomStringUtils.random(length, allowedChars).substring(0, length);
	}

}
