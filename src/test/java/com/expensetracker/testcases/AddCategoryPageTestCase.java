package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.AddExpensePage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtililtyFunctions;

public class AddCategoryPageTestCase extends BaseClass {

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
	public void verifyUserShouldNotableToUseListCategoriesFeatureWithoutLogin() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		if (UtililtyFunctions.isElementExists(loginPageobj.logoutLink)) {
			loginPageobj.clickLogout();
		}
		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.clickListCategories();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyListCategoriesLinkFunctionalityWithLogin() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();

		AssertionUtil.assertEquals(addCategoryPageObj.listCategoriesHeading.getText(),
				ValidationMessageContants.HEADER_LIST_CATEGORIES, ErrorMessageContants.REQUIRED_HEADER_PRESENT,
				ErrorMessageContants.REQUIRED_HEADER_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAddCategoriesLinkFunctionalityOnListCategoriesPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.clickAddCategoryLink();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), addCategoryUrl,
				ErrorMessageContants.USER_REDIRECTED_ON_NEXTPAGE, ErrorMessageContants.USER_NOT_REDIRECTED_ON_NEXTPAGE);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAddCategoryFunctionalityWithValidNameAddCategoryPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);

		AssertionUtil.assertEquals(String.valueOf(addCategoryPageObj.findCategory(category)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.CATEGORY_DISPLAYED,
				ErrorMessageContants.CATEGORY_NOT_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAddedCategoryDisplayedinAddExpenseDropdown() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		addCategoryPageObj.addExpense.click();
		AddExpensePage addExpensePageobj = PageFactory.initElements(driver, AddExpensePage.class);
		AssertionUtil.assertEquals(String.valueOf(addExpensePageobj.isCategoryPresent(category)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.CATEGORY_PRESENT,
				ErrorMessageContants.CATEGORY_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyNameFieldvalidationWithEmptyNameOnAddCategoryPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.clickAddCategoryLink();
		addCategoryPageObj.clickCreateCategoryBtn();
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addCategoryPageObj.categoryTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyPlaceHolderLabelButtonsOnAddCategoryPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.clickAddCategoryLink();
		AssertionUtil.assertEquals(addCategoryPageObj.categoryTextBox.getAttribute(addCategoryPageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_CATEGORY, ErrorMessageContants.PLACEHOLDER_CATEGORY_PRESENT,
				ErrorMessageContants.PLACEHOLDER_CATEGORY_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(addCategoryPageObj.resetButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISPLAYED,
				ErrorMessageContants.ELEMENT_NOT_DISPLAYED);
		AssertionUtil.assertEquals(String.valueOf(addCategoryPageObj.createCategoryBtn.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISPLAYED,
				ErrorMessageContants.ELEMENT_NOT_DISPLAYED);
		AssertionUtil.assertEquals(addCategoryPageObj.getListCategoriesHeading(),
				ValidationMessageContants.HEADER_ADD_CATEGORIES, ErrorMessageContants.HEADER_ADD_CATEGORY_PRESENT,
				ErrorMessageContants.HEADER_ADD_CATEGORY_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyResetButtonFunctionalityAddCategoryPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.clickAddCategoryLink();
		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		addCategoryPageObj.enterCategory(category);
		addCategoryPageObj.clickResetButton();
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getText(addCategoryPageObj.categoryTextBox),
				ValidationMessageContants.EMPTY_TEXTBOX, ErrorMessageContants.TEXTBOX_IS_EMPTY,
				ErrorMessageContants.TEXTBOX_IS_NOT_EMPTY);
		loginPageobj.clickLogout();
	}
}
