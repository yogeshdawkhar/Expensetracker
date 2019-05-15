package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.EditAccountPage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtililtyFunctions;

public class EditAccountPageTestCase extends BaseClass {
	String userName;
	String password;
	String category;

	public void createUser(String userName, String password) throws IOException {
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();
		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);
	}

	@Test
	public void verifyUserNameInputBoxisNonEditableOnEditAccountPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);
		AssertionUtil.assertEquals(
				String.valueOf(utililtyFunctionsobj.isElementClickable(editAccountPagePageobj.userNameTextBox)),
				ValidationMessageContants.BFALSE, ErrorMessageContants.ELEMENT_DISABLED,
				ErrorMessageContants.ELEMENT_NOT_DISABLED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyPlaceHolderOnEditAccountPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);

		AssertionUtil.assertEquals(
				editAccountPagePageobj.oldPasswordTextBox.getAttribute(editAccountPagePageobj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_PASSWORD,
				ErrorMessageContants.PLACEHOLDER_USERNAME_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_USERNAME_MESSAGE_NOT_PRESENT);

		AssertionUtil.assertEquals(
				editAccountPagePageobj.newPasswordTextBox.getAttribute(editAccountPagePageobj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_PASSWORD,
				ErrorMessageContants.PLACEHOLDER_PASSWORD_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_PASSWORD_MESSAGE_NOT_PRESENT);

		AssertionUtil.assertEquals(
				editAccountPagePageobj.repeatNewPasswordTextBox.getAttribute(editAccountPagePageobj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_REPEAT_PASSWORD,
				ErrorMessageContants.PLACEHOLDER_REPEAT_PASSWORD_MESSAGE_PRESENT,
				ErrorMessageContants.PLACEHOLDER_REPEAT_PASSWORD_MESSAGE_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyLabelsOnEditAccountPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);

		AssertionUtil.assertEquals(editAccountPagePageobj.editAccountHeading.getText(),
				ValidationMessageContants.HEADER_EDIT_ACCOUNT, ErrorMessageContants.REQUIRED_HEADER_PRESENT,
				ErrorMessageContants.REQUIRED_HEADER_NOT_PRESENT);

		AssertionUtil.assertEquals(editAccountPagePageobj.oldPasswordLabel.getText(),
				ValidationMessageContants.LABEL_OLD_PASSWORD, ErrorMessageContants.LABEL_PRESENT,
				ErrorMessageContants.LABEL_NOT_PRESENT);

		AssertionUtil.assertEquals(editAccountPagePageobj.newPasswordLabel.getText(),
				ValidationMessageContants.LABEL_NEW_PASSWORD, ErrorMessageContants.LABEL_PRESENT,
				ErrorMessageContants.LABEL_NOT_PRESENT);

		AssertionUtil.assertEquals(editAccountPagePageobj.repeatNewPasswordLabel.getText(),
				ValidationMessageContants.LABEL_REPEAT_NEW_PASSWORD, ErrorMessageContants.LABEL_PRESENT,
				ErrorMessageContants.LABEL_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyUpdateAccountWithoutOldPassword() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);
		editAccountPagePageobj.enterDetailsAndClickUpdate("", password, password);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editAccountPagePageobj.oldPasswordTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyUpdateAccountWithoutNewPasswordandRepeatnewpassword() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);
		editAccountPagePageobj.enterDetailsAndClickUpdate(password, "", "");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editAccountPagePageobj.newPasswordTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyUpdateAccountWithWrongOldPassword() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);
		editAccountPagePageobj.enterDetailsAndClickUpdate(password + 1, password, password);
		AssertionUtil.assertEquals(editAccountPagePageobj.errorWrongPassword.getText(),
				ValidationMessageContants.ERROR_OLD_PASSWORD_WASNT_CORRECT, ErrorMessageContants.REQUIRED_ERROR_PRESENT,
				ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyUpdateAccountWithlengthlessThan4NewPasswordandRepeatnewpassword() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		String newpassword = UtililtyFunctions.generateRandomString(3);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);
		editAccountPagePageobj.enterDetailsAndClickUpdate(password, newpassword, newpassword);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editAccountPagePageobj.newPasswordTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyUpdatePasswordWithCorrectData() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		String newpassword = UtililtyFunctions.generateRandomString(8);
		loginPageobj.clickEditAccount();
		EditAccountPage editAccountPagePageobj = PageFactory.initElements(driver, EditAccountPage.class);
		editAccountPagePageobj.enterDetailsAndClickUpdate(password, newpassword, newpassword);
		loginPageobj.clickLogout();
		loginPageobj.enterDetailsAndClickLogin(userName, newpassword);

		AssertionUtil.assertEquals(loginPageobj.editAccount.getText(), userName, ErrorMessageContants.USER_LOGGED_IN,
				ErrorMessageContants.USER_NOT_LOGGED_IN);
		loginPageobj.clickLogout();
	}
}
