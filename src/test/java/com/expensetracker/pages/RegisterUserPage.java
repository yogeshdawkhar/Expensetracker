package com.expensetracker.pages;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtililtyFunctions;

public class RegisterUserPage extends BaseClass {

	@FindBy(how = How.XPATH, using = "/html/body/div/h3")
	public WebElement registerNewUserHeading;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[1]/label")
	public WebElement userNameLabel;

	@FindBy(how = How.ID, using = "login")
	public WebElement userNameTextBox;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[2]/div[1]/label")
	public WebElement passwordLabel;

	@FindBy(how = How.ID, using = "password1")
	public WebElement passwordTextBox;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[3]/div[1]/label")
	public WebElement repeatPasswordLabel;

	@FindBy(how = How.ID, using = "password2")
	public WebElement repeatPasswordTextBox;

	@FindBy(how = How.ID, using = "reset")
	public WebElement resetButton;

	@FindBy(how = How.ID, using = "submit")
	public WebElement registerButton;

	@FindBy(how = How.ID, using = "logout")
	public WebElement logoutLink;

	@FindBy(how = How.XPATH, using = "/html/body/nav/div/div[1]/a")
	public WebElement expenseTracker;

	@FindBy(how = How.ID, using = "go_add_expense")
	public WebElement addExpense;

	@FindBy(how = How.ID, using = "go_list_expenses")
	public WebElement listExpenses;

	@FindBy(how = How.ID, using = "go_list_categories")
	public WebElement listCategories;

	@FindBy(how = How.ID, using = "go_show_statistics")
	public WebElement showStatistics;

	@FindBy(how = How.ID, using = "editaccount")
	public WebElement editAccount;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[4]/div")
	public WebElement errorSameUser;

	public String placeHolder = "placeholder";

	public String required = "required";

	public RegisterUserPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getRegisterNewUserHeading() {
		String heading = registerNewUserHeading.getText();
		ExtentReportLog.testCaseInfo("Register New UserHeading :" + heading);
		return heading;
	}

	public void enterUserName(String userName) {
		userNameTextBox.click();
		userNameTextBox.clear();
		userNameTextBox.sendKeys(userName);
		ExtentReportLog.testCaseInfo("Entered Username :" + userName);
	}

	public void enterPassword(String password) {
		passwordTextBox.click();
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
		ExtentReportLog.testCaseInfo("Entered Password :" + password);
	}

	public void enterRepeatPassword(String repeatPassword) {
		repeatPasswordTextBox.click();
		repeatPasswordTextBox.clear();
		repeatPasswordTextBox.sendKeys(repeatPassword);
		ExtentReportLog.testCaseInfo("Entered Repeat Password :" + repeatPassword);
	}

	public void clickReset() throws IOException {
		ExtentReportLog.testCaseInfoWithImage("Before Clicking Reset Button.");
		resetButton.click();
		ExtentReportLog.testCaseInfoWithImage("Reset Button clicked.");
	}

	public void clickRegister() throws IOException {
		String alertText;
		ExtentReportLog.testCaseInfoWithImage("Before Clicking Register Button.");
		try {
			registerButton.click();
			ExtentReportLog.testCaseInfoWithImage("Register Button clicked.");
		} catch (UnhandledAlertException f) {
			try {
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				ExtentReportLog.testCaseInfo("Alert data: " + alertText);
				alert.accept();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
	}

	public void enterDetailsAndClickRegister(String userName, String password, String repeatPassword) throws IOException {
		enterUserName(userName);
		enterPassword(password);
		enterRepeatPassword(repeatPassword);
		clickRegister();
	}

	public void registerUserAndLogout(String userName, String password, String repeatPassword) throws IOException {
		enterUserName(userName);
		enterPassword(password);
		enterRepeatPassword(repeatPassword);
		clickRegister();
		clickLogout();
	}

	public Boolean isUserLoggedin() {
		Boolean visible = logoutLink.isDisplayed();
		ExtentReportLog.testCaseInfo("User is logged in");
		return visible;
	}

	public void clickLogout() {
		logoutLink.click();
		ExtentReportLog.testCaseInfo("Logout link clicked.");
	}

	public void clickExpenseTracker() {
		expenseTracker.click();
		ExtentReportLog.testCaseInfo("Clicked on Expense Tracker.");
	}

	public void clickAddExpense() {
		addExpense.click();
		ExtentReportLog.testCaseInfo("Clicked on Add Expense.");
	}

	public void clickListExpenses() {
		listExpenses.click();
		ExtentReportLog.testCaseInfo("Clicked on List Expenses.");
	}

	public void clickListCategories() {
		listCategories.click();
		ExtentReportLog.testCaseInfo("Clicked on List Categories.");
	}

	public void clickShowStatistics() {
		showStatistics.click();
		ExtentReportLog.testCaseInfo("Clicked on Show Statistics.");
	}

	public void createUserAndLogout(String userName, String password) throws IOException {
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();
		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);
		loginPageobj.clickLogout();
	}
}
