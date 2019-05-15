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

public class RegisterUserPageTestCase extends BaseClass {
	@Test
	public void verifyRegisterNewUserFunctionalityOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(8);

		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);

		AssertionUtil.assertEquals(String.valueOf(registerUserPageObj.logoutLink.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.USER_LOGGED_IN,
				ErrorMessageContants.USER_NOT_LOGGED_IN);
		registerUserPageObj.clickLogout();
	}

	@Test
	public void verifyRegisterNewUserFunctionalityWithSameCredentialsOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(8);

		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);

		AssertionUtil.assertEquals(String.valueOf(registerUserPageObj.logoutLink.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.USER_LOGGED_IN,
				ErrorMessageContants.USER_NOT_LOGGED_IN);
		registerUserPageObj.clickLogout();
		loginPageobj.clickRegisterNewUser();
		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);

		AssertionUtil.assertEquals(registerUserPageObj.errorSameUser.getText(),
				ValidationMessageContants.ERROR_PASSWORD1_NOT_EQUAL_PASSWORD2,
				ErrorMessageContants.ERROR_USER_ALREADY_REGISTER_PRESENT,
				ErrorMessageContants.ERROR_USER_ALREADY_REGISTER_NOT_PRESENT);
	}

	@Test
	public void verifyPlaceHolderOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		AssertionUtil.assertEquals(registerUserPageObj.userNameTextBox.getAttribute(registerUserPageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_USERNAME,
				ErrorMessageContants.PLACEHOLDER_USERNAME_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_USERNAME_MESSAGE_NOT_PRESENT);

		AssertionUtil.assertEquals(registerUserPageObj.passwordTextBox.getAttribute(registerUserPageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_PASSWORD,
				ErrorMessageContants.PLACEHOLDER_PASSWORD_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_PASSWORD_MESSAGE_NOT_PRESENT);

		AssertionUtil.assertEquals(
				registerUserPageObj.repeatPasswordTextBox.getAttribute(registerUserPageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_REPEAT_PASSWORD,
				ErrorMessageContants.PLACEHOLDER_REPEAT_PASSWORD_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_REPEAT_PASSWORD_MESSAGE_NOT_PRESENT);
	}

	@Test
	public void verifyLabelsOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		AssertionUtil.assertEquals(registerUserPageObj.registerNewUserHeading.getText(),
				ValidationMessageContants.HEADER_REGISTER_NEW_USER,
				ErrorMessageContants.HEADER_REGISTER_NEW_USER_PRESENT,
				ErrorMessageContants.HEADER_REGISTER_NEW_USER_NOT_PRESENT);

		AssertionUtil.assertEquals(registerUserPageObj.userNameLabel.getText(),
				ValidationMessageContants.LABEL_USERNAME, ErrorMessageContants.LABEL_USERNAME_PRESENT,
				ErrorMessageContants.LABEL_USERNAME_NOT_PRESENT);

		AssertionUtil.assertEquals(registerUserPageObj.passwordLabel.getText(),
				ValidationMessageContants.LABEL_PASSWORD, ErrorMessageContants.LABEL_PASSWORD_PRESENT,
				ErrorMessageContants.LABEL_PASSWORD_NOT_PRESENT);

		AssertionUtil.assertEquals(registerUserPageObj.repeatPasswordLabel.getText(),
				ValidationMessageContants.LABEL_REPEAT_PASSWORD, ErrorMessageContants.LABEL_REPEAT_PASSWORD_PRESENT,
				ErrorMessageContants.LABEL_REPEAT_PASSWORD_NOT_PRESENT);
	}

	@Test
	public void verifyRegisterUserWithoutAnyValue() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.clickRegister();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(registerUserPageObj.userNameTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
	}

	@Test
	public void userNameEmptyFieldValidationRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.clickRegister();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(registerUserPageObj.userNameTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
	}

	@Test
	public void userNameFieldValidationForSizelessthan4CharactersRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(2);
		registerUserPageObj.enterUserName(userName);
		registerUserPageObj.clickRegister();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(registerUserPageObj.userNameTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_NOT_PRESENT);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getTitle(registerUserPageObj.userNameTextBox),
				ValidationMessageContants.TITLE_USERNAME_ATLEAST_4_CHARACTERS,
				ErrorMessageContants.TITLE_USERNAME_ATLEAST_4_CHARACTERS_PRESENT,
				ErrorMessageContants.TITLE_USERNAME_ATLEAST_4_CHARACTERS_NOT_PRESENT);
	}

	@Test
	public void passwordEmptyFieldValidationRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		String userName = UtililtyFunctions.generateRandomString(5);
		registerUserPageObj.enterUserName(userName);
		registerUserPageObj.clickRegister();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(registerUserPageObj.passwordTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
	}

	@Test
	public void passwordFieldValidationForIncorrectFormatRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(1);

		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(registerUserPageObj.passwordTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_NOT_PRESENT);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getTitle(registerUserPageObj.passwordTextBox),
				ValidationMessageContants.TITLE_REGISTER_NEW_USER_PASSWORD,
				ErrorMessageContants.TITLE_REGISTER_NEW_USER_PASSWORD_PRESENT,
				ErrorMessageContants.TITLE_REGISTER_NEW_USER_PASSWORD_NOT_PRESENT);
	}

	@Test
	public void verifyErrorMessageforDiffrentRepeatPasswordOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(8);
		String repeatPassword = UtililtyFunctions.generateRandomString(8);
		registerUserPageObj.enterUserName(userName);
		registerUserPageObj.enterPassword(password);
		registerUserPageObj.enterRepeatPassword(repeatPassword);
		AssertionUtil.assertEquals(utililtyFunctionsobj.clickButtonGetAlertText(registerUserPageObj.registerButton),
				ValidationMessageContants.ERROR_PASSWORD_NOT_EQUAL,
				ErrorMessageContants.ERROR_PASSWORD_NOT_EQUAL_PRESENT,
				ErrorMessageContants.ERROR_PASSWORD_NOT_EQUAL_NOT_PRESENT);
	}

	@Test
	public void verifyErrorMessageWithEmptyRepeatPasswordOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(8);
		String repeatPassword = UtililtyFunctions.generateRandomString(0);
		registerUserPageObj.enterUserName(userName);
		registerUserPageObj.enterPassword(password);
		registerUserPageObj.enterRepeatPassword(repeatPassword);
		AssertionUtil.assertEquals(utililtyFunctionsobj.clickButtonGetAlertText(registerUserPageObj.registerButton),
				ValidationMessageContants.ERROR_PASSWORD_NOT_EQUAL,
				ErrorMessageContants.ERROR_PASSWORD_NOT_EQUAL_PRESENT,
				ErrorMessageContants.ERROR_PASSWORD_NOT_EQUAL_NOT_PRESENT);
	}

	@Test
	public void verifyResetButtonFunctionalityRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(4);
		registerUserPageObj.enterUserName(userName);
		registerUserPageObj.enterPassword(password);
		registerUserPageObj.enterRepeatPassword(password);
		registerUserPageObj.clickReset();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getText(registerUserPageObj.userNameTextBox),
				ValidationMessageContants.EMPTY_TEXTBOX, ErrorMessageContants.TEXTBOX_IS_EMPTY,
				ErrorMessageContants.TEXTBOX_IS_NOT_EMPTY);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getText(registerUserPageObj.passwordTextBox),
				ValidationMessageContants.EMPTY_TEXTBOX, ErrorMessageContants.TEXTBOX_IS_EMPTY,
				ErrorMessageContants.TEXTBOX_IS_NOT_EMPTY);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getText(registerUserPageObj.repeatPasswordTextBox),
				ValidationMessageContants.EMPTY_TEXTBOX, ErrorMessageContants.TEXTBOX_IS_EMPTY,
				ErrorMessageContants.TEXTBOX_IS_NOT_EMPTY);
	}

	@Test
	public void verifyUserShouldNotableToUseExpenseTrackerFeatureWithoutLoginOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.clickExpenseTracker();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), registerUrl + "#",
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseAddExpenseFeatureWithoutLoginOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		registerUserPageObj.clickAddExpense();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseListExpensesFeatureWithoutLoginOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		registerUserPageObj.clickListExpenses();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseListCategoriesFeatureWithoutLoginOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		registerUserPageObj.clickListCategories();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldNotableToUseShowStatisticsFeatureWithoutLoginOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		registerUserPageObj.clickShowStatistics();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyBrowseBackButtonfunctionalityAfterRegisterNewUserOnRegisterUserPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);

		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();

		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);

		String userName = UtililtyFunctions.generateRandomString(8);
		String password = UtililtyFunctions.generateRandomString(8);

		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);

		utililtyFunctionsobj.navigateBack();
		AssertionUtil.assertEquals(String.valueOf(UtililtyFunctions.isCurrentUrlDiffrent(listExpensesUrl)),
				ValidationMessageContants.BFALSE, ErrorMessageContants.USER_NOT_REDIRECTED_ON_PREVIOUS_PAGE,
				ErrorMessageContants.USER_REDIRECTED_ON_PREVIOUS_PAGE);
	}
}
