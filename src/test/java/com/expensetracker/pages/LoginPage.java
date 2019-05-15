package com.expensetracker.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;

public class LoginPage extends BaseClass {

	@FindBy(how = How.ID, using = "submit")
	public WebElement loginButton;

	@FindBy(how = How.XPATH, using = "/html/body/div/div/a")
	public WebElement registerNewUserLink;

	@FindBy(how = How.XPATH, using = "/html/body/div/h3")
	public WebElement loginHeading;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[1]/label")
	public WebElement userNameLabel;

	@FindBy(how = How.ID, using = "login")
	public WebElement userNameTextBox;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[2]/div[1]/label")
	public WebElement passwordLabel;

	@FindBy(how = How.ID, using = "password")
	public WebElement passwordTextBox;

	@FindBy(how = How.ID, using = "reset")
	public WebElement resetButton;

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
	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[3]/div")
	public WebElement errorLogin;
	@FindBy(how = How.ID, using = "editaccount")
	public WebElement editAccount;

	public String placeHolder = "placeholder";

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickLogin() {
		loginButton.click();
		ExtentReportLog.testCaseInfo("Login Button clicked.");
	}

	public void clickRegisterNewUser() {
		registerNewUserLink.click();
		ExtentReportLog.testCaseInfo("Register New User Link clicked.");
	}

	public String getLoginHeading() {
		String heading = loginHeading.getText();
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

	public void clickReset() throws IOException {
		ExtentReportLog.testCaseInfoWithImage("Before Clicking Reset Button.");
		resetButton.click();
		ExtentReportLog.testCaseInfoWithImage("Reset Button clicked.");
	}

	public void enterDetailsAndClickLogin(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickLogin();
	}

	public Boolean isUserLoggedin() {
		Boolean visible = logoutLink.isDisplayed();
		ExtentReportLog.testCaseInfo("User is logged in");
		return visible;
	}

	public void clickEditAccount() {
		editAccount.click();
		ExtentReportLog.testCaseInfo("Edit account clicked.");
	}

	public void clickLogout() {
		logoutLink.click();
		ExtentReportLog.testCaseInfo("Logout link clicked.");
	}

	public void clickExpenseTracker() {
		expenseTracker.click();
		ExtentReportLog.testCaseInfo("Clicked on Expense Tracker.");
	}

	public void clickAddExpenseLink() {
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
}
