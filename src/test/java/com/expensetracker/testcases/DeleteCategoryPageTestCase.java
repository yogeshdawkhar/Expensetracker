package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.AddExpensePage;
import com.expensetracker.pages.ListCategoriesPage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtcDate;
import com.expensetracker.utility.UtililtyFunctions;

public class DeleteCategoryPageTestCase extends BaseClass {
	String userName;
	String password;
	String category;

	public void createUser(String userName, String password) throws IOException {
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();
		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);
	}

	@Test
	public void verifyDeleteCategoryPopUpMessageAndDeleteFuctionality() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		AssertionUtil.assertEquals(utililtyFunctionsobj.clickButtonGetAlertText(listCategoryPageObj.deleteCategory),
				ValidationMessageContants.ERROR_DELETE_POPUP + category + " ?",
				ErrorMessageContants.DELETE_POPUP_PRESENT, ErrorMessageContants.DELETE_POPUP_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.findCategory(category)),
				ValidationMessageContants.BFALSE, ErrorMessageContants.CATEGORY_NOT_DISPLAYED,
				ErrorMessageContants.CATEGORY_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyDeleteCategoryPopUpCancelActionFuctionality() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);

		AssertionUtil.assertEquals(utililtyFunctionsobj.getAlertTextAndCancelAction(listCategoryPageObj.deleteCategory),
				ValidationMessageContants.ERROR_DELETE_POPUP + category + " ?",
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.findCategory(category)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.CATEGORY_DISPLAYED,
				ErrorMessageContants.CATEGORY_NOT_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyDeleteIconDisplayedFrontofCategory() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.deleteCategory.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ICON_DELETE_PRESENT,
				ErrorMessageContants.ICON_DELETE_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyDeleteCategoryFuctionalityWhenExpenseIsAddedAginstIt() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		AddExpensePage addExpensePageObj = PageFactory.initElements(driver, AddExpensePage.class);
		addExpensePageObj.clickAddExpenseLink();
		UtcDate utc = new UtcDate();
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "10.00", "shopping");
		addExpensePageObj.clickListExpenseLink();
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickListCategoriesLink();
		AssertionUtil.assertEquals(utililtyFunctionsobj.clickButtonGetAlertText(listCategoryPageObj.deleteCategory),
				ValidationMessageContants.ERROR_DELETE_POPUP + category + " ?",
				ErrorMessageContants.ERROR_PASSWORD_NOT_EQUAL_PRESENT,
				ErrorMessageContants.ERROR_PASSWORD_NOT_EQUAL_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.findCategory(category)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.CATEGORY_NOT_DISPLAYED,
				ErrorMessageContants.CATEGORY_DISPLAYED);
		loginPageobj.clickLogout();
	}
}
