package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtililtyFunctions;

public class LoginPageTestCase extends BaseClass {
	@Test
	public void verifyLoginFunctionalityWithRegisterUserLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();
		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(8);
		registerUserPageObj.registerUserAndLogout(userName, password, password);
		loginPageobj.enterDetailsAndClickLogin(userName, password);

		AssertionUtil.assertEquals(String.valueOf(loginPageobj.logoutLink.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.USER_LOGGED_IN,
				ErrorMessageContants.USER_NOT_LOGGED_IN);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyLoginFunctionalityWithNonRegisteredUserLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(8);
		loginPageobj.enterDetailsAndClickLogin(userName, password);

		AssertionUtil.assertEquals(loginPageobj.errorLogin.getText(),
				ValidationMessageContants.ERROR_UNKNOWN_LOGIN_OR_WRONG_PASSWORD,
				ErrorMessageContants.ERROR_UNKNOWN_LOGIN_OR_WRONG_PASSWORD_PRSENT,
				ErrorMessageContants.ERROR_UNKNOWN_LOGIN_OR_WRONG_PASSWORD_NOT_PRSENT);
	}

	@Test
	public void verifyPlaceHolderOnLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		AssertionUtil.assertEquals(loginPageobj.userNameTextBox.getAttribute(loginPageobj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_USERNAME,
				ErrorMessageContants.PLACEHOLDER_USERNAME_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_USERNAME_MESSAGE_NOT_PRESENT);

		AssertionUtil.assertEquals(loginPageobj.passwordTextBox.getAttribute(loginPageobj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_PASSWORD,
				ErrorMessageContants.PLACEHOLDER_PASSWORD_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_PASSWORD_MESSAGE_NOT_PRESENT);
	}

	@Test
	public void verifyLabelsOnLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		AssertionUtil.assertEquals(loginPageobj.getLoginHeading(), ValidationMessageContants.HEADER_REGISTER_LOGIN,
				ErrorMessageContants.HEADER_LOGIN_PRESENT, ErrorMessageContants.HEADER_LOGIN_NOT_PRESENT);

		AssertionUtil.assertEquals(loginPageobj.userNameLabel.getText(), ValidationMessageContants.LABEL_USERNAME,
				ErrorMessageContants.LABEL_USERNAME_PRESENT, ErrorMessageContants.LABEL_USERNAME_NOT_PRESENT);

		AssertionUtil.assertEquals(loginPageobj.passwordLabel.getText(), ValidationMessageContants.LABEL_PASSWORD,
				ErrorMessageContants.LABEL_PASSWORD_PRESENT, ErrorMessageContants.LABEL_PASSWORD_NOT_PRESENT);
	}

	@Test
	public void userNameEmptyFieldValidationLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		loginPageobj.clickLogin();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(loginPageobj.userNameTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
	}

	@Test
	public void userNameFieldValidationForSizelessthan4CharactersLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		String userName = UtililtyFunctions.generateRandomString(2);
		loginPageobj.enterUserName(userName);
		loginPageobj.clickLogin();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(loginPageobj.userNameTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_NOT_PRESENT);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getTitle(loginPageobj.userNameTextBox),
				ValidationMessageContants.TITLE_USERNAME_ATLEAST_4_CHARACTERS,
				ErrorMessageContants.TITLE_USERNAME_ATLEAST_4_CHARACTERS_PRESENT,
				ErrorMessageContants.TITLE_USERNAME_ATLEAST_4_CHARACTERS_NOT_PRESENT);
	}

	@Test
	public void passwordEmptyFieldValidationLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		String userName = UtililtyFunctions.generateRandomString(5);
		loginPageobj.enterUserName(userName);
		loginPageobj.clickLogin();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(loginPageobj.passwordTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
	}

	@Test
	public void passwordFieldValidationForIncorrectFormatLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(1);

		loginPageobj.enterDetailsAndClickLogin(userName, password);

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(loginPageobj.passwordTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getTitle(loginPageobj.passwordTextBox),
				ValidationMessageContants.TITLE_LOGIN_PASSWORD, ErrorMessageContants.TITLE_LOGIN_PASSWORD_PRESENT,
				ErrorMessageContants.TITLE_LOGIN_PASSWORD_NOT_PRESENT);
	}

	@Test
	public void verifyResetButtonFunctionalityLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(4);
		loginPageobj.enterUserName(userName);
		loginPageobj.enterPassword(password);
		loginPageobj.clickReset();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getText(loginPageobj.userNameTextBox),
				ValidationMessageContants.EMPTY_TEXTBOX, ErrorMessageContants.TEXTBOX_IS_EMPTY,
				ErrorMessageContants.TEXTBOX_IS_NOT_EMPTY);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getText(loginPageobj.passwordTextBox),
				ValidationMessageContants.EMPTY_TEXTBOX, ErrorMessageContants.TEXTBOX_IS_EMPTY,
				ErrorMessageContants.TEXTBOX_IS_NOT_EMPTY);
	}

	@Test
	public void verifyUserShouldNotableToUseExpenseTrackerFeatureWithoutLoginOnLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		loginPageobj.clickExpenseTracker();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl + "#",
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseAddExpenseFeatureWithoutLoginOnLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		loginPageobj.clickAddExpenseLink();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseListExpensesFeatureWithoutLoginOnLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		loginPageobj.clickListExpenses();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseListCategoriesFeatureWithoutLoginOnLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		loginPageobj.clickListCategories();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseShowStatisticsFeatureWithoutLoginOnLoginPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);

		loginPageobj.clickShowStatistics();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}
}
