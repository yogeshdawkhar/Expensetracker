package com.expensetracker.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.expensetracker.utility.ExtentReportLog;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	protected static String browser;
	protected static String executeOnbrowser = "chrome";
	protected static String baseUrl = "http://thawing-shelf-73260.herokuapp.com";
	protected static String registerUrl = baseUrl + "/register.jsp";
	protected static String loginUrl = baseUrl + "/index.jsp";
	protected static String addExpenseUrl = baseUrl + "/addexpense.jsp";
	protected static String listExpensesUrl = baseUrl + "/listexpenses";
	protected static String editExpensesUrl = baseUrl + "/editexpense.jsp";
	protected static String listCategoriesUrl = baseUrl + "/listcategories.jsp";
	protected static String showStatisticsUrl = baseUrl + "/showstatistics.jsp";
	protected static String addCategoryUrl = baseUrl + "/addcategory.jsp";
	protected static String editCategoryUrl = baseUrl + "/editcategory.jsp";

	protected static String randomNumber = new SimpleDateFormat("dd_MMM_yy_HH:mm:ss:SSSZZZ").format(new Date());
	protected static String fileSeprator = File.separator;

	protected static Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
	protected static String extentReportFilePath = System.getProperty("user.dir") + fileSeprator + "src" + fileSeprator
			+ "ExtentReports" + fileSeprator + randomNumber + fileSeprator;
	protected static String extentReportFilename = extentReportFilePath + "extentReportFile" + ".html";
	protected static String extentReportPathForStoringImage = extentReportFilePath + "images" + fileSeprator;

	protected static String extentReportImagePathForHTML = ".." + fileSeprator + randomNumber + fileSeprator + "images"
			+ fileSeprator;
	protected static ExtentTest extentTest;
	protected static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportFilename);
	protected static ExtentReports extent = new ExtentReports();
	protected static ExtentTest logger;

	@BeforeSuite
	public static void startReport() {
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("ExtentReport");
		htmlReporter.config().setReportName("iCast Web Test");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().enableTimeline(true);
	}

	@Parameters({ "browser" })
	@BeforeClass
	public static void setUp(String browser) {
		if (executeOnbrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
					"--ignore-certificate-errors");
			// driver = new ChromeDriver(options);
			driver = new ChromeDriver();
		} else if (executeOnbrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
					"--ignore-certificate-errors");
			driver = new FirefoxDriver(options);
		} else if (executeOnbrowser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (executeOnbrowser.equalsIgnoreCase("safari")) {
			WebDriverManager.iedriver().setup();
			driver = new SafariDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
					"--ignore-certificate-errors");
			driver = new ChromeDriver(options);
		}
		browser = executeOnbrowser;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Test Case Failed.");
			System.out.println("Test Case Failed Due to: " + result.getThrowable());
			ExtentReportLog.testCaseFail("Test Case Failed.");
			ExtentReportLog.testCaseFailWithImage("Test Case Failed Snapshot is below :" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("Test Case Skipped.");
			ExtentReportLog.testCaseSkip("Test Case Skipped.");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("Test Case Passed.");
			ExtentReportLog.testCasePassWithImage("Test Case Passed.");
		}
		extent.flush();
	}

	@AfterTest
	public void endReport() {
		if (extent != null) {
			extent.flush();
		}
	}

	@AfterClass
	public static void closeBrowser() {
		if ((driver != null) && (!driver.toString().contains("(null)"))) {
			driver.quit();
		}
	}
}