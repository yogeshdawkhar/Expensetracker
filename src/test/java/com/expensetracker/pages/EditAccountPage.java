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

public class EditAccountPage extends BaseClass {

	@FindBy(how = How.XPATH, using = "/html/body/div/h1")
	public WebElement editAccountHeading;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[1]/label")
	public WebElement userNameLabel;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[2]/div[1]/label")
	public WebElement oldPasswordLabel;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[3]/div[1]/label")
	public WebElement newPasswordLabel;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[4]/div[1]/label")
	public WebElement repeatNewPasswordLabel;

	@FindBy(how = How.NAME, using = "login")
	public WebElement userNameTextBox;

	@FindBy(how = How.ID, using = "password")
	public WebElement oldPasswordTextBox;

	@FindBy(how = How.ID, using = "newpassword1")
	public WebElement newPasswordTextBox;

	@FindBy(how = How.ID, using = "newpassword2")
	public WebElement repeatNewPasswordTextBox;

	@FindBy(how = How.ID, using = "reset")
	public WebElement resetButton;

	@FindBy(how = How.ID, using = "submit")
	public WebElement updateAccountButton;

	@FindBy(how = How.ID, using = "logout")
	public WebElement logoutLink;
	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[5]/div")
	public WebElement errorWrongPassword;
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

	public String placeHolder = "placeholder";

	public EditAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getHeading() {
		String heading = editAccountHeading.getText();
		ExtentReportLog.testCaseInfo("Heading :" + heading);
		return heading;
	}

	public void enterUserName(String userName) {
		userNameTextBox.click();
		userNameTextBox.clear();
		userNameTextBox.sendKeys(userName);
		ExtentReportLog.testCaseInfo("Entered Username :" + userName);
	}

	public void enterOldPassword(String password) {
		oldPasswordTextBox.click();
		oldPasswordTextBox.clear();
		oldPasswordTextBox.sendKeys(password);
		ExtentReportLog.testCaseInfo("Entered Old Password :" + password);
	}

	public void enterNewPassword(String password) {
		newPasswordTextBox.click();
		newPasswordTextBox.clear();
		newPasswordTextBox.sendKeys(password);
		ExtentReportLog.testCaseInfo("Entered new Password :" + password);
	}

	public void enterRepeatNewPassword(String repeatPassword) {
		repeatNewPasswordTextBox.click();
		repeatNewPasswordTextBox.clear();
		repeatNewPasswordTextBox.sendKeys(repeatPassword);
		ExtentReportLog.testCaseInfo("Entered Repeat new Password :" + repeatPassword);
	}

	public void clickReset() throws IOException {
		ExtentReportLog.testCaseInfoWithImage("Before Clicking Reset Button.");
		resetButton.click();
		ExtentReportLog.testCaseInfoWithImage("Reset Button clicked.");
	}

	public void clickUpdate() throws IOException {
		String alertText;
		ExtentReportLog.testCaseInfoWithImage("Before Clicking Register Button.");
		try {
			updateAccountButton.click();
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

	public void enterDetailsAndClickUpdate(String password, String newPassword, String repeatPassword)
			throws IOException {
		enterOldPassword(password);
		enterNewPassword(newPassword);
		enterRepeatNewPassword(repeatPassword);
		clickUpdate();
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

	public void createUserAndLogout(String userName, String password) throws InterruptedException, IOException {
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();
		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);
		loginPageobj.clickLogout();
	}
}
